package es.guillermoorellana.punkapidroid.application;

import android.app.Application;
import android.support.annotation.NonNull;

public class PunkApplication extends Application {

    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        getComponent().inject(this);
    }

    @NonNull
    public ApplicationComponent getComponent() {
        if (component == null) {
            component = DaggerApplicationComponent.builder()
                    .application(this)
                    .build();
        }
        return component;
    }

    public static PunkApplication fromApplication(Application application) {
        return (PunkApplication) application;
    }
}
