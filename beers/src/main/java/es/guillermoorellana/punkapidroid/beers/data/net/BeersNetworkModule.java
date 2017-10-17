package es.guillermoorellana.punkapidroid.beers.data.net;

import com.squareup.moshi.Moshi;

import dagger.Module;
import dagger.Provides;
import es.guillermoorellana.punkapidroid.beers.data.net.BeerNetworkService;
import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

@Module
public class BeersNetworkModule {

    private static final String BASE_URL = "https://api.punkapi.com/v2/";

    @Provides
    BeerNetworkService beerNetworkService(HttpUrl baseUrl, Moshi moshi) {
        return retrofit(baseUrl, moshi)
                .create(BeerNetworkService.class);
    }

    @Provides
    Moshi moshi() {
        return new Moshi.Builder()
                .build();
    }

    @Provides
    HttpUrl baseUrl() {
        return HttpUrl.parse(BASE_URL);
    }

    @Provides
    Retrofit retrofit(HttpUrl baseUrl, Moshi moshi) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(baseUrl)
                .build();
    }
}
