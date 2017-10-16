package es.guillermoorellana.core.data;

import android.support.annotation.NonNull;

import com.fernandocejas.arrow.functions.Function;
import com.fernandocejas.arrow.optional.Optional;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.reactivex.Flowable;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.PublishProcessor;
import io.reactivex.schedulers.Schedulers;

/**
 * This reactive store has only a memory cache as form of storage.
 */
public class MemoryReactiveStore<Key, Value> implements ReactiveStore<Key, Value> {

    @NonNull
    private final Store.MemoryStore<Key, Value> cache;

    @NonNull
    private final Function<Value, Key> extractKeyFromModel;

    @NonNull
    private final FlowableProcessor<Optional<List<Value>>> allProcessor;

    @NonNull
    private final Map<Key, FlowableProcessor<Optional<Value>>> processorMap = new HashMap<>();

    public MemoryReactiveStore(@NonNull final Function<Value, Key> extractKeyFromModel,
                               @NonNull final Store.MemoryStore<Key, Value> cache) {
        this.allProcessor = PublishProcessor.<Optional<List<Value>>>create().toSerialized();
        this.cache = cache;
        this.extractKeyFromModel = extractKeyFromModel;
    }

    public void storeSingular(@NonNull final Value model) {
        final Key key = extractKeyFromModel.apply(model);
        cache.put(model);
        getOrCreateSubjectForKey(key).onNext(Optional.of(model));
        // One item has been added/updated, notify to all as well
        final Optional<List<Value>> allValues = cache.getAll().map(Optional::of).blockingGet(Optional.absent());
        allProcessor.onNext(allValues);
    }

    public void storeAll(@NonNull final List<Value> modelList) {
        cache.putAll(modelList);
        allProcessor.onNext(Optional.of(modelList));
        // Publish in all the existing single item streams.
        // This could be improved publishing only in the items that changed. Maybe use DiffUtils?
        publishInEachKey();
    }

    public void replaceAll(@NonNull final List<Value> modelList) {
        cache.clear();
        storeAll(modelList);
    }

    @NonNull
    public Flowable<Optional<Value>> getSingular(@NonNull final Key key) {
        final Optional<Value> model = cache.get(key).map(Optional::of).blockingGet(Optional.absent());
        return getOrCreateSubjectForKey(key).startWith(model)
                .observeOn(Schedulers.computation());
    }

    @NonNull
    public Flowable<Optional<List<Value>>> getAll() {
        final Optional<List<Value>> allValues = cache.getAll().map(Optional::of).blockingGet(Optional.absent());
        return allProcessor.startWith(allValues)
                .observeOn(Schedulers.computation());
    }

    @NonNull
    private FlowableProcessor<Optional<Value>> getOrCreateSubjectForKey(@NonNull final Key key) {
        synchronized (processorMap) {
            return Optional.of(processorMap.get(key)).or(createAndStoreNewSubjectForKey(key));
        }
    }

    @NonNull
    private FlowableProcessor<Optional<Value>> createAndStoreNewSubjectForKey(@NonNull final Key key) {
        final FlowableProcessor<Optional<Value>> processor = PublishProcessor.<Optional<Value>>create().toSerialized();
        synchronized (processorMap) {
            processorMap.put(key, processor);
        }
        return processor;
    }

    /**
     * Publishes the cached data in each independent stream only if it exists already.
     */
    private void publishInEachKey() {
        final Set<Key> keySet;
        synchronized (processorMap) {
            keySet = new HashSet<>(processorMap.keySet());
        }
        for (Key key : keySet) {
            final Optional<Value> value = cache.get(key).map(Optional::of).blockingGet(Optional.absent());
            publishInKey(key, value);
        }
    }

    /**
     * Publishes the cached value if there is an already existing stream for the passed key. The case where there isn't a stream for the passed key
     * means that the data for this key is not being consumed and therefore there is no need to publish.
     */
    private void publishInKey(@NonNull final Key key, @NonNull final Optional<Value> model) {
        final FlowableProcessor<Optional<Value>> processor;
        synchronized (processorMap) {
            processor = processorMap.get(key);
        }
        if (processor != null) {
            processor.onNext(model);
        }
    }
}
