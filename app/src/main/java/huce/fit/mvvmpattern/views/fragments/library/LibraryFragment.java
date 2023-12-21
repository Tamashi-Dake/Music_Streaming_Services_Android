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
import huce.fit.mvvmpattern.model.DataJson;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.services.MyService;
import huce.fit.mvvmpattern.utils.Tmp;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.PlaylistActivity;
import huce.fit.mvvmpattern.views.appInterface.IClickItemPlaylist;
import huce.fit.mvvmpattern.views.appInterface.IClickSongOption;
import huce.fit.mvvmpattern.views.fragments.home.section.Section;
import huce.fit.mvvmpattern.views.fragments.library.itemFavorite.Favorite;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.Playlist;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.PlaylistAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LibraryFragment extends Fragment {
    private RecyclerView recyclerViewLibrary;
    private LibraryAdapter section_adapter;

    private MainActivity mainActivity;
    private FloatingActionButton btnAddPlaylist;
    private PlaylistAdapter playlistAdapter;
    private List<Playlist> playList = new ArrayList<>();
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
                            Playlist plz = new Playlist(playlistName);
                            plz.setUsername(Tmp.current_username);
                            plz.setImageUrl("https://nhomhungtu.000webhostapp.com/img/playlist_img_default.jpg");
                            PlaylistService.playlistservice.PlaylistAdd(plz).enqueue(new Callback<DataJson<Playlist>>() {
                                @Override
                                public void onResponse(Call<DataJson<Playlist>> call, Response<DataJson<Playlist>> response) {
                                    DataJson<Playlist> djson = response.body();
                                    if(djson!=null){
                                        if(djson.isStatus()){
                                            Toast.makeText(mainActivity,"Thêm playlist thành công",Toast.LENGTH_SHORT).show();
                                            Log.e("updateplaylist","success");
                                            playList.add(new Playlist(plz.getImageUrl(),plz.getTitle(),plz.getId()));
                                            updatePlayListAdapter();
                                        }
//                                        Toast.makeText(mainActivity,"Thêm playlist không thành công",Toast.LENGTH_SHORT).show();
                                    }
//                                    Toast.makeText(mainActivity,"Null",Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void onFailure(Call<DataJson<Playlist>> call, Throwable t) {
                                    Log.e("ERROR", this.getClass().getName()+"onClickLogin()->onFailure: "+t.getMessage());
                                    Toast.makeText(mainActivity, "Network Error!", Toast.LENGTH_SHORT).show();
                                }
                            });
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

        List<Playlist> playList2 = new ArrayList<>();

        //
        PlaylistService.playlistservice.getPlayListList(Tmp.current_username).enqueue(new Callback<DataJson<Playlist>>() {
            @Override
            public void onResponse(Call<DataJson<Playlist>> call, Response<DataJson<Playlist>> response) {
                playList.clear();
                DataJson<Playlist> data_playlist = response.body();
                if(data_playlist!=null){
                    if(data_playlist.isStatus()){
                        List<Playlist> playlists = data_playlist.getData();
                        for (int i = 0; i < playlists.size(); i++) {
                            if (playlists.get(i).getImageUrl() == null) {
                                playlists.get(i).setImageUrl("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg");
                                Log.e("sta",playlists.get(i).getTitle());
                            }
                            else {
                                playList.add(new Playlist(playlists.get(i).getImageUrl(), playlists.get(i).getTitle(), playlists.get(i).getId()));
                                Log.e("link", playlists.get(i).getId() + "|" + playlists.get(i).getImageUrl() + "|" + playlists.get(i).getTitle());
                            }
                        }
                        updatePlayListAdapter();
                    }
                    else{
                        updatePlayListAdapter();
                        Toast.makeText(mainActivity, data_playlist.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("status","false");
                    }
                }
            }

            @Override
            public void onFailure(Call<DataJson<Playlist>> call, Throwable t) {
                Log.e("ERROR", this.getClass().getName()+"getPlayListSong()->onFailure: "+t.getMessage());
                Toast.makeText(mainActivity, R.string.not_connection, Toast.LENGTH_SHORT).show();
            }
        });
        Log.e("list",String.valueOf(playList.size()));
//        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_4.jpg", "Random 1"));
//        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_2.jpg", "Random 2"));
//        playList.add(new Playlist("https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_1.jpg", "Random 3"));

        sections.add(new Section("History", history));
        sections.add(new Section("Favorites",favorite));
        sections.add(new Section("Playlist", playList));

        return sections;
    }
    private void updatePlayListAdapter() {
        section_adapter.setItems(playList, new IClickItemPlaylist() {
            @Override
            public void onclickItemPlaylist(Playlist playlist) {

            }
        });
    }
}
