package es.guillermoorellana.punkapidroid.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import es.guillermoorellana.core.injection.InjectingActivity;
import es.guillermoorellana.punkapidroid.R;
import es.guillermoorellana.punkapidroid.application.PunkApplication;
import es.guillermoorellana.punkapidroid.beers.presentation.list.BeerListComponent;

public class MainActivity extends AppCompatActivity implements InjectingActivity<BeerListComponent> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private MainActivityComponent getMainComponent() {
        return PunkApplication.fromApplication(getApplication()).getComponent().mainActivityComponent();
    }

    @Override
    public BeerListComponent getComponent() {
        return getMainComponent().beerListComponent();
    }
}
