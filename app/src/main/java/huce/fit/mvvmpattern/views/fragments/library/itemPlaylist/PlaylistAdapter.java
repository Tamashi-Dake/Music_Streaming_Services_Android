package huce.fit.mvvmpattern.views.fragments.library.itemPlaylist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.Item;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>{

private List<Playlist> items;
public void setItems(List<Playlist> list){
    this.items = list;
//    load và bind dữ liệu vào adapter
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public PlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlist,parent,false);
        return new PlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlaylistViewHolder holder, int position) {
        Playlist item = items.get(position);
        if (item == null) {
            return;
        }
        holder.imageView.setImageResource(item.getResourceId());
        holder.tvTitle.setText(item.getTitle());
    }
    @Override
    public int getItemCount() {
    if (items != null){
        return items.size();
    }
        return 0;
    }

    public class PlaylistViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView tvTitle;
    public PlaylistViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgPlaylist);
        tvTitle = itemView.findViewById(R.id.tvPlaylistTitle);
    }
}
}