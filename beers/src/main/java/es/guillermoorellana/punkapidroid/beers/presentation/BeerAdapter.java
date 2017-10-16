package es.guillermoorellana.punkapidroid.beers.presentation;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import es.guillermoorellana.punkapidroid.beers.R;

class BeerAdapter extends RecyclerView.Adapter<BeerAdapter.BeerHolder> {

    @Override
    public BeerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_beer_card, parent, false);
        return new BeerHolder(view);
    }

    @Override
    public void onBindViewHolder(BeerHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    static class BeerHolder extends RecyclerView.ViewHolder {
        BeerHolder(View itemView) {
            super(itemView);
        }
    }
}
