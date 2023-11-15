package huce.fit.mvvmpattern.views.fragments.home.itemBigHit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.Item;

public class BigHitAdapter extends RecyclerView.Adapter<BigHitAdapter.ItemViewHolder>{
    private Context context;

private List<Item> items;

public BigHitAdapter(Context context){
    this.context = context;
}
public void setItems(List<Item> list){
    this.items = list;
//    load và bind dữ liệu vào adapter
    notifyDataSetChanged();
}
    public BigHitAdapter(Context context,List<Item> list){
        this.context = context;
        this.items = list;
    }
    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_big_hit,parent,false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        String url = String.valueOf(items.get(position % items.size()));
        Glide.with(context).load(url).centerCrop().placeholder(R.drawable.ic_launcher_background).into(holder.imageView);
    }
    @Override
    public int getItemCount() {
    if (items != null){
        return Integer.MAX_VALUE;
    }
        return 0;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
    private ImageView imageView;
    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView = itemView.findViewById(R.id.imgBigHit);
    }
}
}