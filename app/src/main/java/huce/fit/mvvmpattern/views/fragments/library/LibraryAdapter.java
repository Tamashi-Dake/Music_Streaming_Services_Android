package huce.fit.mvvmpattern.views.fragments.library;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.FavoritesActivity;
import huce.fit.mvvmpattern.views.HistoryActivity;
import huce.fit.mvvmpattern.views.PlaylistActivity;

import huce.fit.mvvmpattern.views.fragments.home.itemHistory.Item;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.ItemAdapter;

import huce.fit.mvvmpattern.views.fragments.home.section.Section;
import huce.fit.mvvmpattern.views.fragments.library.itemFavorite.Favorite;
import huce.fit.mvvmpattern.views.fragments.library.itemFavorite.FavoriteAdapter;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.Playlist;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.PlaylistAdapter;
import huce.fit.mvvmpattern.views.mussInterface.IClickItemPlaylist;

public class LibraryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HISTORY = 0;
    private static final int TYPE_FAVORITE = 1;
    private static final int TYPE_PLAYLIST = 2;

    private Context context;
    private List<Section> sections;

    public LibraryAdapter(Context context) {
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
        switch (viewType) {
            case TYPE_HISTORY:
                View viewHistory = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new HistoryViewHolder(viewHistory);
            case TYPE_FAVORITE:
                View viewFavorite = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new FavoriteViewHolder(viewFavorite);
            case TYPE_PLAYLIST:
                View viewPlaylist = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_section, parent, false);
                return new PlaylistViewHolder(viewPlaylist);
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
        switch (holder.getItemViewType()) {
            case TYPE_HISTORY:
                HistoryViewHolder historyViewHolder = (HistoryViewHolder) holder;
                historyViewHolder.sectionName.setText(section.getSectionName());
                historyViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                ItemAdapter itemAdapter = new ItemAdapter();
                itemAdapter.setItems((List<Item>) section.getItems());
                historyViewHolder.rcvItem.setAdapter(itemAdapter);
                break;
            case TYPE_FAVORITE:
                FavoriteViewHolder favoriteViewHolder = (FavoriteViewHolder) holder;
                favoriteViewHolder.sectionName.setText(section.getSectionName());
                favoriteViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.HORIZONTAL, false));
                FavoriteAdapter favoriteAdapter = new FavoriteAdapter( );
                favoriteAdapter.setItems((List<Favorite>) section.getItems());
                favoriteViewHolder.rcvItem.setAdapter(favoriteAdapter);
                break;
            case TYPE_PLAYLIST:
                PlaylistViewHolder playlistViewHolder = (PlaylistViewHolder) holder;
                playlistViewHolder.sectionName.setText(section.getSectionName());
                playlistViewHolder.rcvItem.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
                PlaylistAdapter playlistAdapter = new PlaylistAdapter((List<Playlist>) section.getItems(), new IClickItemPlaylist() {
                    @Override
                    public void onclickItemPlaylist(Playlist playlist) {
                        playlistViewHolder.onclickItemPlaylist(playlist);
                    }
                });
                playlistViewHolder.rcvItem.setAdapter(playlistAdapter);
                break;
        }
    }
    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 1:
                return TYPE_FAVORITE;
            case 2:
                return TYPE_PLAYLIST;
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

            sectionName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, HistoryActivity.class);
                    context.startActivity(intent);
                }
            });
        }

    }
    public class FavoriteViewHolder extends RecyclerView.ViewHolder {

        private TextView sectionName;
        private RecyclerView rcvItem;

        public FavoriteViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);
            sectionName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, FavoritesActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }
    public class PlaylistViewHolder extends RecyclerView.ViewHolder {

        private TextView sectionName;
        private RecyclerView rcvItem;

        public PlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            sectionName = itemView.findViewById(R.id.tvSectionName);
            rcvItem = itemView.findViewById(R.id.rcvSectionItem);

            sectionName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlaylistActivity.class);
                    context.startActivity(intent);
                }
            });
        }
        public void onclickItemPlaylist(Playlist playlist){
            Intent intent = new Intent(context, PlaylistActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("playlist", playlist);
            intent.putExtras(bundle);
            context.startActivity(intent);
        }
    }
}