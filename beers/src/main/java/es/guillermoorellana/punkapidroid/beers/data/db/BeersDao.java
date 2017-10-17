package es.guillermoorellana.punkapidroid.beers.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import es.guillermoorellana.punkapidroid.beers.data.db.entity.DbBeer;
import io.reactivex.Flowable;

@Dao
public abstract class BeersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(List<DbBeer> beers);

    @Query("SELECT * FROM beers")
    public abstract Flowable<List<DbBeer>> getAllStream();
}
