package es.guillermoorellana.punkapidroid.beers.data;

import com.squareup.moshi.Moshi;

import okhttp3.HttpUrl;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class BeersDataModule {

    private static final String BASE_URL = "https://api.punkapi.com/v2/";

    public BeerNetworkService beerNetworkService() {
        return retrofit(baseUrl(), moshi())
                .create(BeerNetworkService.class);
    }

    private Moshi moshi() {
        return new Moshi.Builder()
                .build();
    }

    private HttpUrl baseUrl() {
        return HttpUrl.parse(BASE_URL);
    }

    private Retrofit retrofit(HttpUrl baseUrl, Moshi moshi) {
        return new Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .baseUrl(baseUrl)
                .build();
    }
}
