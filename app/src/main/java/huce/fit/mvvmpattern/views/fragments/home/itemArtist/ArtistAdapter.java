
package huce.fit.mvvmpattern.views.fragments.home.itemArtist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huce.fit.mvvmpattern.R;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder>{

private List<Artist> items;
public void setItems(List<Artist> list){
    this.items = list;
//    load và bind dữ liệu vào adapter
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist,parent,false);
        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist item = items.get(position);
        if (item == null) {
            return;
        }
        holder.imageView.setImageResource(item.getResourceId());
        holder.tvArtist.setText(item.getTitle());
    }
    @Override
    public int getItemCount() {
    if (items != null){
        return items.size();
    }
        return 0;
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView tvArtist;
    public ArtistViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgArtist);
        tvArtist = itemView.findViewById(R.id.tvArtistTitle);
    }
}
}