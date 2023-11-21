
package huce.fit.mvvmpattern.views.fragments.search;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.fragments.home.itemPopular.Popular;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.PopularViewHolder>{

private List<Popular> items;
public void setItems(List<Popular> list){
    this.items = list;
//    load và bind dữ liệu vào adapter
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_popular,parent,false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        Popular item = items.get(position);
        if (item == null) {
            return;
        }
        holder.imageView.setImageResource(item.getResourceId());
        holder.tvTitle.setText(item.getTrackName());
        holder.tvArtist.setText(item.getArtistName());
    }
    @Override
    public int getItemCount() {
    if (items != null){
        return items.size();
    }
        return 0;
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView tvTitle;
    private TextView tvArtist;
    public PopularViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgPopular);
        tvTitle = itemView.findViewById(R.id.tvPopularTitle);
        tvArtist = itemView.findViewById(R.id.tvArtistPopular);
    }
}
}