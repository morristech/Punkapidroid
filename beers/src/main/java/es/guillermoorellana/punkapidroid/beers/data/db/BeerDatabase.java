package es.guillermoorellana.punkapidroid.beers.data.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import es.guillermoorellana.punkapidroid.beers.data.db.entity.DbBeer;

@Database(entities = {DbBeer.class}, version = 1)
public abstract class BeerDatabase extends RoomDatabase {
    public abstract BeersDao beersDao();
}
