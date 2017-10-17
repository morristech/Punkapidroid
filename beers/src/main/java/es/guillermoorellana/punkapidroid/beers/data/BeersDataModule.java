package es.guillermoorellana.punkapidroid.beers.data;

import dagger.Module;
import dagger.Provides;
import es.guillermoorellana.punkapidroid.beers.data.db.BeersDbModule;
import es.guillermoorellana.punkapidroid.beers.data.db.entity.DbBeer;
import es.guillermoorellana.punkapidroid.beers.data.net.BeersNetworkModule;
import es.guillermoorellana.punkapidroid.beers.data.net.entity.NetBeer;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;
import io.reactivex.functions.Function;

@Module(includes = {
        BeersNetworkModule.class,
        BeersDbModule.class
})
public class BeersDataModule {

    @Provides
    Function<NetBeer, DbBeer> mapperNetToDb() {
        return netBeer -> new DbBeer(netBeer.getId(), netBeer.getName(), netBeer.getImageUrl());
    }

    @Provides
    Function<DbBeer, BeerEntry> mapperDbToView() {
        return dbBeer -> new BeerEntry(dbBeer.getId(), dbBeer.getName(), dbBeer.getImageUrl());
    }
}
