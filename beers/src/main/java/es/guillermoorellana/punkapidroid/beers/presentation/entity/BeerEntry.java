package es.guillermoorellana.punkapidroid.beers.presentation.entity;

import android.support.annotation.NonNull;

public class BeerEntry {
    private int id;
    @NonNull
    private String name;

    public BeerEntry(int id, @NonNull String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BeerEntry beerEntry = (BeerEntry) o;

        if (id != beerEntry.id) return false;
        return name.equals(beerEntry.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }
}
