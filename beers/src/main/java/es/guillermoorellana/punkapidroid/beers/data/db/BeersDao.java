package es.guillermoorellana.punkapidroid.beers.data.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.support.annotation.NonNull;

import java.util.List;

import es.guillermoorellana.punkapidroid.beers.data.db.entity.Beer;
import io.reactivex.Flowable;
import io.reactivex.Single;

@Dao
public abstract class BeersDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public abstract void insert(@NonNull List<Beer> beers);

    @Query("SELECT * FROM beers")
    public abstract Flowable<List<Beer>> getAllStream();

    @Query("SELECT * FROM beers WHERE name LIKE '%' || :search || '%'")
    public abstract Flowable<List<Beer>> findBeersByName(@NonNull String search);
}
