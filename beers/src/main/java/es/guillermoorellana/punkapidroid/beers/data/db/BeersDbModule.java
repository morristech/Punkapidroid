package es.guillermoorellana.punkapidroid.beers.data.db;

import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class BeersDbModule {

    @Provides
    BeersDao beersDao(BeerDatabase beersDatabase) {
        return beersDatabase.beersDao();
    }

    @Singleton
    @Provides
    BeerDatabase beerDatabase(Context context) {
        return Room.databaseBuilder(context, BeerDatabase.class, "beer-db").build();
    }
}
