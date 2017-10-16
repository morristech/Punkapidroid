package es.guillermoorellana.punkapidroid.beers.presentation;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import es.guillermoorellana.punkapidroid.beers.R;
import es.guillermoorellana.punkapidroid.beers.domain.BeerListViewModel;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;

public class BeersFragment extends Fragment {
    ViewModelProvider.Factory viewModelFactory = new ViewModelProvider.Factory() {
        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            return null;
        }
    };

    public BeersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beers, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView(view);
        setupViewModel();
    }

    private void setupRecyclerView(View view) {
        BeerAdapter adapter = new BeerAdapter();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupViewModel() {
        ViewModelProviders.of(this, viewModelFactory)
                .get(BeerListViewModel.class)
                .getBeerLiveData()
                .observe(this, this::updateList);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_beers, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateList(List<BeerEntry> beerEntries) {

    }
}