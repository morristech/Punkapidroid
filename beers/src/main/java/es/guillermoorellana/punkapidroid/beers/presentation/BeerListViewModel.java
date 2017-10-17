package es.guillermoorellana.punkapidroid.beers.presentation;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import es.guillermoorellana.punkapidroid.beers.domain.SearchBeers;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerScreenState;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static java.util.Collections.emptyList;

public class BeerListViewModel extends ViewModel {

    @NonNull
    private final SearchBeers interactor;
    @NonNull
    private final MutableLiveData<BeerScreenState> beerLiveData = new MutableLiveData<>();
    @NonNull
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    BeerListViewModel(@NonNull SearchBeers interactor) {
        this.interactor = interactor;

        compositeDisposable.add(bindInteractors());
    }

    @NonNull
    public MutableLiveData<BeerScreenState> getBeerLiveData() {
        return beerLiveData;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.dispose();
    }

    private Disposable bindInteractors() {
        return interactor.getStream()
                .observeOn(Schedulers.computation())
                .map(BeerScreenState::new)
                .onErrorReturn(this::makeErrorPartOfState)
                .subscribe(beerLiveData::postValue);
    }

    @NonNull
    private BeerScreenState makeErrorPartOfState(Throwable e) {
        BeerScreenState state = beerLiveData.getValue();
        if (state == null) {
            return new BeerScreenState(emptyList(), e);
        } else {
            return state.withError(e);
        }
    }

    public void setQuery(@Nullable String query) {
        interactor.setSearchQuery(query);
    }
}
