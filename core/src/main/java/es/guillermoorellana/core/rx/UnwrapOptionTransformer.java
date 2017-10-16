package es.guillermoorellana.core.rx;

import com.fernandocejas.arrow.optional.Optional;

import org.reactivestreams.Publisher;

import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;

/**
 * Filters out all Option of NONE if any, but if Some, then unwraps and returns the value.
 */
public final class UnwrapOptionTransformer<T> implements FlowableTransformer<Optional<T>, T> {

    @Override
    public Publisher<T> apply(final Flowable<Optional<T>> upstream) {
        return upstream.filter(Optional::isPresent)
                .map(Optional::get);
    }

    public static <T> UnwrapOptionTransformer<T> create() {
        return new UnwrapOptionTransformer<>();
    }
}
