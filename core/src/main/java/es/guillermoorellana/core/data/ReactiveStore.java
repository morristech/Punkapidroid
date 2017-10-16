package es.guillermoorellana.core.data;

import android.support.annotation.NonNull;

import com.fernandocejas.arrow.optional.Optional;

import java.util.List;

import io.reactivex.Flowable;

public interface ReactiveStore<Key, Value> {

    void storeSingular(@NonNull final Value model);

    void storeAll(@NonNull final List<Value> modelList);

    void replaceAll(@NonNull final List<Value> modelList);

    Flowable<Optional<Value>> getSingular(@NonNull final Key key);

    Flowable<Optional<List<Value>>> getAll();
}
