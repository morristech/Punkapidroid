package es.guillermoorellana.punkapidroid.beers.presentation;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import es.guillermoorellana.punkapidroid.beers.R;
import es.guillermoorellana.punkapidroid.beers.presentation.entity.BeerEntry;

import static java.util.Collections.emptyList;

public class BeerListAdapter extends RecyclerView.Adapter<BeerListAdapter.BeerHolder> {

    @NonNull
    private List<BeerEntry> beerEntries = emptyList();

    @Override
    public BeerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_beer_card, parent, false);
        return new BeerHolder(view);
    }

    @Override
    public void onBindViewHolder(BeerHolder holder, int position) {
        BeerEntry entry = beerEntries.get(position);
        holder.beerNameText.setText(entry.getName());
        holder.itemView.setOnClickListener(getOnClickListener(holder.itemView.getContext(), entry.getImageUrl()));
    }

    @NonNull
    private View.OnClickListener getOnClickListener(Context context, String imageUrl) {
        return v -> context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(imageUrl)));
    }

    @Override
    public int getItemCount() {
        return beerEntries.size();
    }

    public void setBeerEntries(@NonNull List<BeerEntry> beerEntries) {
        this.beerEntries = beerEntries;
    }

    static class BeerHolder extends RecyclerView.ViewHolder {

        @NonNull final TextView beerNameText;

        BeerHolder(View itemView) {
            super(itemView);
            beerNameText = itemView.findViewById(R.id.beerName);
        }
    }
}
