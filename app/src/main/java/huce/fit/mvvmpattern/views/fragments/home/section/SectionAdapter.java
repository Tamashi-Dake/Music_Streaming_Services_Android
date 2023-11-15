package huce.fit.mvvmpattern.views.fragments.home.section;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.fragments.home.itemBigHit.BigHitAdapter;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.ItemAdapter;

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HISTORY = 0;
    private static final int TYPE_BIG_HIT = 1;
    private static final int TYPE_POPULAR = 1;
    private static final int TYPE_ARTIST = 1;
    private static final int TYPE_CATEGORIES = 1;
    private static final int TYPE_RANDOM = 1;


    private Context context;
    private List<Section> sections;

    public SectionAdapter(Context context) {
        this.context = context;
    }

    public void setSections(List<Section> sections) {
        if (sections == null) {
            this.sections = new ArrayList<>();
        } else {
            this.sections = sections;
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        switch (viewType){
            case TYPE_HISTORY:
                View viewHistory = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new HistoryViewHolder(viewHistory);
            case TYPE_BIG_HIT:
                View viewBigHit = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new BigHitViewHolder(viewBigHit);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Section section = sections.get(position);
        if (section == null) {
            return;
        }
        switch (holder.getItemViewType()){
            case TYPE_HISTORY:
                HistoryViewHolder historyViewHolder = (HistoryViewHolder) holder;
                historyViewHolder.sectionName.setText(section.getSectionName());
                historyViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                ItemAdapter itemAdapter = new ItemAdapter();
                itemAdapter.setItems(section.getItems());
                historyViewHolder.rcvItem.setAdapter(itemAdapter);
                break;
            case TYPE_BIG_HIT:
                BigHitViewHolder bigHitViewHolder = (BigHitViewHolder) holder;
                bigHitViewHolder.sectionName.setText(section.getSectionName());
                bigHitViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                BigHitAdapter bigHitAdapter = new BigHitAdapter(context,section.getItems());
                bigHitAdapter.setItems(section.getItems());
                bigHitViewHolder.rcvItem.setAdapter(bigHitAdapter);
                break;
        }
    }


    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 1:
                return TYPE_BIG_HIT;
            case 2:
                return TYPE_POPULAR;
            case 3:
                return TYPE_ARTIST;
            case 4:
                return TYPE_CATEGORIES;
            case 5:
                return TYPE_RANDOM;
            default:
                return TYPE_HISTORY;
        }
    }
    @Override
    public int getItemCount() {
        if (sections != null) {
            return sections.size();
        }
        return 0;
    }

    public class HistoryViewHolder extends RecyclerView.ViewHolder {

        private TextView sectionName;
        private RecyclerView rcvItem;

        public HistoryViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);
        }
    }
    public class BigHitViewHolder extends RecyclerView.ViewHolder {

        private TextView sectionName;
        private RecyclerView rcvItem;
        public BigHitViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);

        }
    }
}
