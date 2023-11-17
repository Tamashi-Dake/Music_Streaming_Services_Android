
package huce.fit.mvvmpattern.views.fragments.home.itemRandom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huce.fit.mvvmpattern.R;

public class RandomAdapter extends RecyclerView.Adapter<RandomAdapter.RandomViewHolder>{

private List<RandomTrack> items;
public void setItems(List<RandomTrack> list){
    this.items = list;
//    load và bind dữ liệu vào adapter
    notifyDataSetChanged();
}
    @NonNull
    @Override
    public RandomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_random,parent,false);
        return new RandomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RandomViewHolder holder, int position) {
        RandomTrack item = items.get(position);
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

    public class RandomViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    private TextView tvTitle;
    public RandomViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgRandom);
        tvTitle = itemView.findViewById(R.id.tvRandomTitle);
    }
}
}