package es.guillermoorellana.punkapidroid.application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import es.guillermoorellana.punkapidroid.main.MainActivityComponent;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        DataModule.class,
        ViewModelModule.class
})
public interface ApplicationComponent {
    void inject(PunkApplication application);

    MainActivityComponent mainActivityComponent();

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(PunkApplication app);

        ApplicationComponent build();
    }
}
