package huce.fit.mvvmpattern.views.fragments.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.fragments.home.itemArtist.Artist;
import huce.fit.mvvmpattern.views.fragments.home.itemCategories.Category;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.Item;
import huce.fit.mvvmpattern.views.fragments.home.itemPopular.Popular;
import huce.fit.mvvmpattern.views.fragments.home.itemRandom.RandomTrack;
import huce.fit.mvvmpattern.views.fragments.home.section.Section;
import huce.fit.mvvmpattern.views.fragments.home.section.SectionAdapter;
import huce.fit.mvvmpattern.views.fragments.library.itemFavorite.Favorite;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.Playlist;


public class LibraryFragment extends Fragment {
    private RecyclerView recyclerViewLibrary;
    private LibraryAdapter section_adapter;

    private MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        mainActivity = (MainActivity) getActivity();
        recyclerViewLibrary = view.findViewById(R.id.rcvLibrary);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false);
        recyclerViewLibrary.setLayoutManager(linearLayoutManager);
        section_adapter = new LibraryAdapter(mainActivity);
        section_adapter.setSections(getListSection());
        recyclerViewLibrary.setAdapter(section_adapter);
        return view;
    }
    private List<Section> getListSection() {
        List<Section> sections = new ArrayList<>();

        List<Item> history = new ArrayList<>();
        history.add(new Item(R.drawable.img_1, "Item 1"));
        history.add(new Item(R.drawable.img_2, "Item 2"));
        history.add(new Item(R.drawable.img_3, "Item 3"));
        history.add(new Item(R.drawable.img_4, "Item 4"));
        history.add(new Item(R.drawable.img_1, "Item 5"));

        List<Favorite> favorite = new ArrayList<>();
        favorite.add(new Favorite(R.drawable.img_1, "Random 1"));
        favorite.add(new Favorite(R.drawable.img_1, "Random 2"));
        favorite.add(new Favorite(R.drawable.img_3, "Random 3"));

        List<Playlist> playList = new ArrayList<>();
        playList.add(new Playlist(R.drawable.img_4, "Random 1"));
        playList.add(new Playlist(R.drawable.img_3, "Random 2"));
        playList.add(new Playlist(R.drawable.img_2, "Random 3"));

        sections.add(new Section("History", history));
        sections.add(new Section("Favorites",favorite));
        sections.add(new Section("Playlist", playList));

        return sections;
    }
}