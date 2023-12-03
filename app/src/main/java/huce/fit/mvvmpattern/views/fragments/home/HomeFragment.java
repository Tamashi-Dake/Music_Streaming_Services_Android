package huce.fit.mvvmpattern.views.fragments.home;

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
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.fragments.home.itemArtist.Artist;
import huce.fit.mvvmpattern.views.fragments.home.itemCategories.Category;
import huce.fit.mvvmpattern.views.fragments.home.itemHistory.Item;
import huce.fit.mvvmpattern.views.fragments.home.itemRandom.RandomTrack;
import huce.fit.mvvmpattern.views.fragments.home.section.Section;
import huce.fit.mvvmpattern.views.fragments.home.section.SectionAdapter;
import huce.fit.mvvmpattern.views.appInterface.IClickSongOption;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewHome;
    private SectionAdapter section_adapter;
    private MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mainActivity = (MainActivity) getActivity();

        recyclerViewHome = view.findViewById(R.id.rcvHome);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false);
        recyclerViewHome.setLayoutManager(linearLayoutManager);
        section_adapter = new SectionAdapter(mainActivity);
        section_adapter.setSections(getListSection(),new IClickSongOption() {
            @Override
            public void onClickSongOption(Song song) {
                mainActivity.openSongBottomSheet();
            }
            @Override
            public void onClickSong(Song song) {
                mainActivity.openMusicPlayer();
            }
        });
        recyclerViewHome.setAdapter(section_adapter);


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
        history.add(new Item(R.drawable.img_2, "Item 6"));

        List listURL = new ArrayList<>();
        listURL.add("https://i.pinimg.com/170x/47/df/7f/47df7f619a9b9ceba2f3d948e41fb450.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg");

        List<Song> popular = new ArrayList<>();
        popular.add(new Song(R.drawable.img_1, "Song 1", "Artist 1"));
        popular.add(new Song(R.drawable.img_2, "Song 2", "Artist 2"));
        popular.add(new Song(R.drawable.img_4, "Song 3", "Artist 3"));

        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(R.drawable.img_1, "Artist 1"));
        artists.add(new Artist(R.drawable.img_2, "Artist 2"));
        artists.add(new Artist(R.drawable.img_3, "Artist 3"));


        List<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.img_4, "Category 1"));
        categories.add(new Category(R.drawable.img_2, "Category 2"));
        categories.add(new Category(R.drawable.img_1, "Category 3"));

        List<RandomTrack> randomTracks = new ArrayList<>();
        randomTracks.add(new RandomTrack(R.drawable.img_1, "Random 1"));
        randomTracks.add(new RandomTrack(R.drawable.img_3, "Random 2"));
        randomTracks.add(new RandomTrack(R.drawable.img_2, "Random 3"));

        sections.add(new Section("History", history));
        sections.add(new Section("Big Hits", listURL ));
        sections.add(new Section("Popular", popular));
        sections.add(new Section("Artist", artists));
        sections.add(new Section("Continue your journey", categories));
        sections.add(new Section("Have you try this?", randomTracks));


        return sections;
    }
}
