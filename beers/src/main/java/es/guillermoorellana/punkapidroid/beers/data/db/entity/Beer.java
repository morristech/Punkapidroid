package es.guillermoorellana.punkapidroid.beers.data.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "beers")
public class Beer {

    @PrimaryKey private final int id;
    @NonNull private final String name;
    @NonNull private final String imageUrl;

    public Beer(int id, @NonNull String name, @NonNull String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getImageUrl() {
        return imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Beer beer = (Beer) o;

        if (id != beer.id) return false;
        if (!name.equals(beer.name)) return false;
        return imageUrl.equals(beer.imageUrl);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + imageUrl.hashCode();
        return result;
    }
}
