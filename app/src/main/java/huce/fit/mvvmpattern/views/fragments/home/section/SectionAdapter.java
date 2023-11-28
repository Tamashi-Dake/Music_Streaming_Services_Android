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
import java.util.Timer;
import java.util.TimerTask;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.views.fragments.home.itemArtist.Artist;
import huce.fit.mvvmpattern.views.fragments.home.itemArtist.ArtistAdapter;
import huce.fit.mvvmpattern.views.fragments.home.itemBigHit.BigHit;
import huce.fit.mvvmpattern.views.fragments.home.itemBigHit.BigHitAdapter;
import huce.fit.mvvmpattern.views.fragments.home.itemCategories.CategoriesAdapter;
import huce.fit.mvvmpattern.views.fragments.home.itemCategories.Category;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.Item;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.ItemAdapter;
import huce.fit.mvvmpattern.views.fragments.home.itemPopular.PopularAdapter;
import huce.fit.mvvmpattern.views.fragments.home.itemRandom.RandomAdapter;
import huce.fit.mvvmpattern.views.fragments.home.itemRandom.RandomTrack;

public class SectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HISTORY = 0;
    private static final int TYPE_BIG_HIT = 1;
    private static final int TYPE_POPULAR = 2;
    private static final int TYPE_ARTIST = 3;
    private static final int TYPE_CATEGORIES = 4;
    private static final int TYPE_RANDOM = 5;


    private Context context;
    private List<Section> sections;
    private Timer timer;
    private TimerTask timerTask;
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
            case TYPE_POPULAR:
                View viewPopular = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new PopularViewHolder(viewPopular);
            case TYPE_ARTIST:
                View viewArtist = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new ArtistViewHolder(viewArtist);
            case TYPE_CATEGORIES:
                View viewCategories = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new CategoriesViewHolder(viewCategories);
            case TYPE_RANDOM:
                View viewRandom = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new RandomViewHolder(viewRandom);
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
                itemAdapter.setItems((List<Item>) section.getItems());
                historyViewHolder.rcvItem.setAdapter(itemAdapter);
                break;
            case TYPE_BIG_HIT:
                BigHitViewHolder bigHitViewHolder = (BigHitViewHolder) holder;
                bigHitViewHolder.sectionName.setText(section.getSectionName());
                bigHitViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                BigHitAdapter bigHitAdapter = new BigHitAdapter(context,section.getItems());
                bigHitAdapter.setItems((List<BigHit>) section.getItems());
                bigHitViewHolder.rcvItem.setAdapter(bigHitAdapter);
                break;
            case TYPE_POPULAR:
                PopularViewHolder popularViewHolder = (PopularViewHolder) holder;
                popularViewHolder.sectionName.setText(section.getSectionName());
                popularViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                PopularAdapter popularAdapter = new PopularAdapter();
                popularAdapter.setItems((List<Song>) section.getItems());
                popularViewHolder.rcvItem.setAdapter(popularAdapter);
                break;
            case TYPE_ARTIST:
                ArtistViewHolder artistViewHolder = (ArtistViewHolder) holder;
                artistViewHolder.sectionName.setText(section.getSectionName());
                artistViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                ArtistAdapter artistAdapter = new ArtistAdapter();
                artistAdapter.setItems((List<Artist>) section.getItems());
                artistViewHolder.rcvItem.setAdapter(artistAdapter);
                break;
            case TYPE_CATEGORIES:
                CategoriesViewHolder categoriesViewHolder = (CategoriesViewHolder) holder;
                categoriesViewHolder.sectionName.setText(section.getSectionName());
                categoriesViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                CategoriesAdapter categoriesAdapter = new CategoriesAdapter();
                categoriesAdapter.setItems((List<Category>) section.getItems());
                categoriesViewHolder.rcvItem.setAdapter(categoriesAdapter);
                break;
            case TYPE_RANDOM:
                RandomViewHolder randomViewHolder = (RandomViewHolder) holder;
                randomViewHolder.sectionName.setText(section.getSectionName());
                randomViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                RandomAdapter randomAdapter = new RandomAdapter();
                randomAdapter.setItems((List<RandomTrack>) section.getItems());
                randomViewHolder.rcvItem.setAdapter(randomAdapter);
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

//            public void startAutoScroll() {
//                if (timer != null && timerTask != null) {
//                    timer.cancel();
//                    timerTask.cancel();
//                }
//                // Tạo một TimerTask mới để chạy chuyển đổi tự động
//                timerTask = new TimerTask() {
//                    @Override
//                    public void run() {
//                        // Tăng vị trí hiện tại của RecyclerView
//                        int currentPosition = rcvItem.getLayoutManager().getPosition(rcvItem.getChildAt(0));
//                        int nextPosition = (currentPosition + 1) % getItemCount();
//                        rcvItem.scrollToPosition(nextPosition);
//                    }
//                };
//                // Khởi tạo Timer và lên lịch thực thi TimerTask
//                timer = new Timer();
//                timer.schedule(timerTask, 5000, 5000);
//            }
        }

    }
    public class PopularViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionName;
        private RecyclerView rcvItem;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);
        }
    }
    public class ArtistViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionName;
        private RecyclerView rcvItem;
        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);
        }
    }
    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionName;
        private RecyclerView rcvItem;
        public CategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);
        }
    }
    public class RandomViewHolder extends RecyclerView.ViewHolder {
        private TextView sectionName;
        private RecyclerView rcvItem;
        public RandomViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);
        }
    }
}
