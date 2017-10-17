package es.guillermoorellana.punkapidroid.application;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class ApplicationModule {

    @Singleton
    @Provides
    public Context applicationContext(PunkApplication application) {
        return application.getApplicationContext();
    }
}
