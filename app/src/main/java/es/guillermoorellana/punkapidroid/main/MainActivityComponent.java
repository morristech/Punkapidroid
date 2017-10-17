package es.guillermoorellana.punkapidroid.main;

import dagger.Subcomponent;
import es.guillermoorellana.core.injection.scopes.ActivityScope;
import es.guillermoorellana.punkapidroid.beers.presentation.beerlist.BeerListComponent;

@ActivityScope
@Subcomponent(modules = ActivityModule.class)
public interface MainActivityComponent {
    void inject(MainActivity activity);

    BeerListComponent beerListComponent();
}
