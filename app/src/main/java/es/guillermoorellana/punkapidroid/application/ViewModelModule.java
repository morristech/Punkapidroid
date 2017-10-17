package es.guillermoorellana.punkapidroid.application;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import es.guillermoorellana.punkapidroid.AppViewModelFactory;
import es.guillermoorellana.punkapidroid.beers.presentation.BeerListViewModel;
import es.guillermoorellana.punkapidroid.injection.ViewModelKey;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(BeerListViewModel.class)
    public abstract ViewModel beerListViewModel(BeerListViewModel beerListViewModel);

    @Binds
    public abstract ViewModelProvider.Factory viewModelFactory(AppViewModelFactory appViewModelFactory);
}
