package huce.fit.mvvmpattern.views.fragments.library;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.services.MyService;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.appInterface.IClickSongOption;
import huce.fit.mvvmpattern.views.fragments.home.section.Section;
import huce.fit.mvvmpattern.views.fragments.library.itemFavorite.Favorite;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.Playlist;


public class LibraryFragment extends Fragment {
    private RecyclerView recyclerViewLibrary;
    private LibraryAdapter section_adapter;

    private MainActivity mainActivity;
    private FloatingActionButton btnAddPlaylist;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library, container, false);

        mainActivity = (MainActivity) getActivity();
        recyclerViewLibrary = view.findViewById(R.id.rcvLibrary);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false);
        recyclerViewLibrary.setLayoutManager(linearLayoutManager);
        section_adapter = new LibraryAdapter(mainActivity);
        section_adapter.setSections(getListSection(), new IClickSongOption() {
            @Override
            public void onClickSongOption(Song song) {

            }

            @Override
            public void onClickSong(Song song) {

                mainActivity.openMusicPlayer();
            }
        });
        recyclerViewLibrary.setAdapter(section_adapter);

        btnAddPlaylist = view.findViewById(R.id.btnAddPlaylist);
        btnAddPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewDialog = LayoutInflater.from(mainActivity).inflate(R.layout.dialog_add_playlist, null);
                TextInputEditText edtPlaylistName = viewDialog.findViewById(R.id.edtPlaylistName);
                AlertDialog alertDialog = new MaterialAlertDialogBuilder(mainActivity)
                        .setTitle("Add Playlist")
                        .setView(viewDialog)
                        .setPositiveButton("Add", (dialog, which) -> {
                            String playlistName = edtPlaylistName.getText().toString();
                            Toast.makeText(mainActivity, playlistName, Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Cancel", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .create();
                alertDialog.show();
            }
        });
        btnAddPlaylist.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                holdStartService();
                return true;
            }
        });
        return view;
    }
    public void holdStartService() {
        Intent serviceIntent = new Intent(mainActivity, MyService.class);
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("song", new Song(R.drawable.img_1, "Song 1", "Artist 1"));
//        serviceIntent.putExtras(bundle);
        mainActivity.startService(serviceIntent);
    }

    private List<Section> getListSection() {
        List<Section> sections = new ArrayList<>();

        List<Song> history = new ArrayList<>();
        history.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_1.jpg", "Song 1", "Artist 1"));
        history.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Song 2", "Artist 2"));
        history.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg", "Song 3", "Artist 3"));
        history.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg", "Song 4", "Artist 4"));
        history.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_5.jpg", "Song 5", "Artist 5"));

        List<Song> favorite = new ArrayList<>();
        favorite.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_5.jpg", "Song 5", "Artist 5"));
        favorite.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg", "Song 4", "Artist 4"));
        favorite.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg", "Song 3", "Artist 3"));
        favorite.add(new Song("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Song 2", "Artist 2"));

        List<Playlist> playList = new ArrayList<>();
        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg", "Random 1"));
        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Random 2"));
        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_1.jpg", "Random 3"));

        sections.add(new Section("History", history));
        sections.add(new Section("Favorites",favorite));
        sections.add(new Section("Playlist", playList));

        return sections;
    }
}