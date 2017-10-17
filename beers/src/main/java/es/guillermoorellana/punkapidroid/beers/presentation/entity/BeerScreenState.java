package es.guillermoorellana.punkapidroid.beers.presentation.entity;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

public class BeerScreenState {
    @NonNull
    private final List<BeerEntry> beerEntries;
    @Nullable
    private final Throwable error;

    public BeerScreenState(@NonNull List<BeerEntry> beerEntries) {
        this(beerEntries, null);
    }

    public BeerScreenState(@NonNull List<BeerEntry> beerEntries, @Nullable Throwable error) {
        this.beerEntries = beerEntries;
        this.error = error;
    }

    @NonNull
    public List<BeerEntry> getBeerEntries() {
        return beerEntries;
    }

    @Nullable
    public Throwable getError() {
        return error;
    }

    @NonNull
    public BeerScreenState withError(Throwable e) {
        return new BeerScreenState(beerEntries, e);
    }
}
