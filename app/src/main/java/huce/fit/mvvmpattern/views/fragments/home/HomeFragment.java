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

        List<Song> history = new ArrayList<>();
        history.add(new Song(R.drawable.img_1, "Chờ", "Artist 1"));
        history.add(new Song(R.drawable.img_2, "Any time any where", "Artist 2"));
        history.add(new Song(R.drawable.img_4, "Gió đêm qua đường", "Artist 3"));
        history.add(new Song(R.drawable.img_3, "Qua cầu rước em", "Artist 4"));
        history.add(new Song(R.drawable.img_8, "Kẹo bông gòn", "Artist 5"));

        List listURL = new ArrayList<>();
        listURL.add("https://avatar-ex-swe.nixcdn.com/singer/avatar/2023/08/10/f/5/f/e/88743.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg");
        listURL.add("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg");

        List<Song> popular = new ArrayList<>();
        popular.add(new Song(R.drawable.img_1, "Chờ", "KIMM KUNNI"));
        popular.add(new Song(R.drawable.img_2, "River in the Rain", "Roger Miller"));
        popular.add(new Song(R.drawable.img_4, "Gió đêm qua đường", "Hải Lai A Mộc"));

        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist(R.drawable.img_1, "KIMM KUNNI"));
        artists.add(new Artist(R.drawable.img_2, "Roger Miller"));
        artists.add(new Artist(R.drawable.img_3, "Her Majesty"));


        List<Category> categories = new ArrayList<>();
        categories.add(new Category(R.drawable.img_9, "Pop"));
        categories.add(new Category(R.drawable.img_10, "Jazz"));
        categories.add(new Category(R.drawable.img_11, "Blue"));

        List<Song> randomTracks = new ArrayList<>();
        randomTracks.add(new Song(R.drawable.img_5, "Con đường của lời hứa", "Artist 1"));
        randomTracks.add(new Song(R.drawable.img_2, "Learn To Meow", "Artist 2"));
        randomTracks.add(new Song(R.drawable.img_4, "Gió đêm qua đường", "Artist 3"));

        sections.add(new Section("History", history));
        sections.add(new Section("Big Hits", listURL ));
        sections.add(new Section("Popular", popular));
        sections.add(new Section("Artist", artists));
        sections.add(new Section("Continue your journey", categories));
        sections.add(new Section("Have you try this?", randomTracks));


        return sections;
    }
}
