package huce.fit.mvvmpattern.views.fragments.library;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import huce.fit.mvvmpattern.api.PlaylistService;
import huce.fit.mvvmpattern.api.SongInfoService;
import huce.fit.mvvmpattern.model.DataJson;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.services.MyService;
import huce.fit.mvvmpattern.utils.Tmp;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.appInterface.IClickSongOption;
import huce.fit.mvvmpattern.views.fragments.home.section.Section;
import huce.fit.mvvmpattern.views.fragments.library.itemFavorite.Favorite;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.Playlist;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LibraryFragment extends Fragment {
    private RecyclerView recyclerViewLibrary;
    private LibraryAdapter section_adapter;

    private MainActivity mainActivity;
    List<Playlist> playList;
    List<Section> sections;
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
        sections = new ArrayList<>();

        List<Song> history = new ArrayList<>();
        history.add(new Song( "1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_1.jpg", "Song 1", "Artist 1", "", ""));
        history.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Song 2", "Artist 2", "", ""));
        history.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg", "Song 3", "Artist 3", "", ""));
        history.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg", "Song 4", "Artist 4", "", ""));
        history.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_5.jpg", "Song 5", "Artist 5", "", ""));

        List<Song> favorite = new ArrayList<>();
        favorite.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_5.jpg", "Song 5", "Artist 5", "", ""));
        favorite.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg", "Song 4", "Artist 4", "", ""));
        favorite.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg", "Song 3", "Artist 3", "", ""));
        favorite.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Song 2", "Artist 2", "", ""));

        playList = new ArrayList<>();
//        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_1.jpg", "ten 1", "1"));
//        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "ten 2", "2"));
//        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg", "ten 3", "3"));
        PlaylistService.playlistservice.getPlayListList(Tmp.current_username)
            .enqueue(new Callback<DataJson<Playlist>>() {
                @Override
                public void onResponse(Call<DataJson<Playlist>> call, Response<DataJson<Playlist>> response) {
                    DataJson<Playlist> dataJson = response.body();
                    if (dataJson != null) {
                        if (dataJson.isStatus() == true) {
//                            playList = (List<Playlist>) dataJson.getData();
                            for (int i = 0; i < dataJson.getData().size(); i++) {
                                playList.add(new Playlist(dataJson.getData().get(i).getImageUrl(), dataJson.getData().get(i).getTitle(), dataJson.getData().get(i).getId()));
                            }
                            updateSectionAdapter();
                        }
                    }
                }

                @Override
                public void onFailure(Call<DataJson<Playlist>> call, Throwable t) {
                    Log.e("ERROR", this.getClass().getName()+"getListSection()->onFailure: "+t.getMessage());
                    Toast.makeText(mainActivity, R.string.not_connection, Toast.LENGTH_SHORT).show();
                }
            });

        sections.add(new Section("History", history));
        sections.add(new Section("Favorites", favorite));
        sections.add(new Section("Playlist", playList));

        return sections;
    }

    private void updateSectionAdapter() {
        section_adapter.setSections(sections, new IClickSongOption() {
            @Override
            public void onClickSongOption(Song song) {

            }

            @Override
            public void onClickSong(Song song) {

                mainActivity.openMusicPlayer();
            }
        });
    }
}