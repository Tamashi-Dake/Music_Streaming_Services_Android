package huce.fit.mvvmpattern.views.fragments.home.section;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.ItemAdapter;

public class SectionAdapter extends RecyclerView.Adapter<SectionAdapter.SectionViewHolder> {
    private Context context;
    private List<Section> sections;

    public SectionAdapter(Context context) {
        this.context = context;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SectionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionViewHolder holder, int position) {
        Section section = sections.get(position);
        if (section == null) {
            return;
        }
        holder.sectionName.setText(section.getSectionName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false);
        holder.rcvItem.setLayoutManager(linearLayoutManager);

        ItemAdapter itemAdapter = new ItemAdapter();
        itemAdapter.setItems(section.getItems());
        holder.rcvItem.setAdapter(itemAdapter);
    }

    @Override
    public int getItemCount() {
        if (sections != null) {
            return sections.size();
        }
        return 0;
    }

    public class SectionViewHolder extends RecyclerView.ViewHolder {

        private TextView sectionName;
        private RecyclerView rcvItem;

        public SectionViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);
        }
    }
}
