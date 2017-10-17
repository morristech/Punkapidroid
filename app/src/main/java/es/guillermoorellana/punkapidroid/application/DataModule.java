package es.guillermoorellana.punkapidroid.application;

import dagger.Module;
import es.guillermoorellana.punkapidroid.beers.data.BeersDataModule;

@Module(includes = {BeersDataModule.class})
class DataModule {
}
