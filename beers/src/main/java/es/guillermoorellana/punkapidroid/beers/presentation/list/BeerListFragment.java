package es.guillermoorellana.punkapidroid.beers.presentation.list;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import es.guillermoorellana.core.injection.InjectingActivity;
import es.guillermoorellana.punkapidroid.beers.R;
import es.guillermoorellana.punkapidroid.beers.presentation.BeerListAdapter;
import es.guillermoorellana.punkapidroid.beers.presentation.BeerListViewModel;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerScreenState;

public class BeerListFragment extends Fragment {

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private final BeerListAdapter adapter = new BeerListAdapter();
    private BeerListViewModel viewModel;
    private SearchCallbacks searchCallbacks;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        InjectingActivity<BeerListComponent> activity = (InjectingActivity<BeerListComponent>) getActivity();
        BeerListComponent component = activity.getComponent();
        component.inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(BeerListViewModel.class);

        viewModel.getBeerLiveData()
                .observe(this, this::updateUi);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_beers, menu);

        searchCallbacks = new SearchCallbacks();
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setOnQueryTextListener(searchCallbacks);
        searchView.setOnCloseListener(searchCallbacks);
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

    private void updateUi(@NonNull BeerScreenState state) {
        if (state.getError() != null) {
            showError(state.getError());
        }
        updateList(state.getBeerEntries());
    }

    private void showError(Throwable error) {
        Snackbar.make(getView(), error.getClass().getSimpleName() + ": " + error.getLocalizedMessage(), Snackbar.LENGTH_SHORT).show();
    }

    private void updateList(List<BeerEntry> beerEntries) {
        adapter.setBeerEntries(beerEntries);
        adapter.notifyDataSetChanged();
    }

    private class SearchCallbacks implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
        @Override
        public boolean onQueryTextSubmit(String query) {
            viewModel.setQuery(query);
            return false;
        }

        @Override
        public boolean onQueryTextChange(String query) {
            viewModel.setQuery(query);
            return false;
        }

        @Override
        public boolean onClose() {
            viewModel.setQuery(null);
            return false;
        }
    }
}
