package es.guillermoorellana.punkapidroid.beers.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import es.guillermoorellana.punkapidroid.beers.domain.RetrieveBeers;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BeerListViewModel extends ViewModel {

    @NonNull
    private final RetrieveBeers retrieveBeers;
    @NonNull
    private final MutableLiveData<List<BeerEntry>> beerLiveData = new MutableLiveData<>();
    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    BeerListViewModel(@NonNull RetrieveBeers retrieveBeers) {
        this.retrieveBeers = retrieveBeers;

        compositeDisposable.add(bindBeers());
    }

    @NonNull
    public MutableLiveData<List<BeerEntry>> getBeerLiveData() {
        return beerLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    private Disposable bindBeers() {
        return retrieveBeers.getStream()
                .observeOn(Schedulers.computation())
                .subscribe(beerLiveData::postValue);
    }
}
