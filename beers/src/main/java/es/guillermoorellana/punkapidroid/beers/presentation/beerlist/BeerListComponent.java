package es.guillermoorellana.punkapidroid.beers.presentation.beerlist;

import dagger.Subcomponent;
import es.guillermoorellana.core.injection.scopes.FragmentScope;

@FragmentScope
@Subcomponent(modules = BeerListModule.class)
public interface BeerListComponent {

    void inject(BeerListFragment fragment);
}
