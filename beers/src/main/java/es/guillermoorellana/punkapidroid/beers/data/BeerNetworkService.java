package es.guillermoorellana.punkapidroid.beers.data;

import java.util.List;

import es.guillermoorellana.punkapidroid.beers.data.entity.Beer;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BeerNetworkService {

    @GET("beers")
    Single<List<Beer>> getBeers(@Query("per_page") Integer perPage);
}
