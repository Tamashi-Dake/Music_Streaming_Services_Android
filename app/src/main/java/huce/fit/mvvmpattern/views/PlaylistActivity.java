package huce.fit.mvvmpattern.views;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.transition.Transition;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.model.SongInfo;
import huce.fit.mvvmpattern.viewmodels.PlayListViewModel;
import huce.fit.mvvmpattern.views.adapter.PlaylistAdapter;
import huce.fit.mvvmpattern.views.appInterface.IClickSongOption;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.Playlist;

public class PlaylistActivity extends AppCompatActivity {
    private ImageButton btnGoBack;

    private RecyclerView recyclerView;
    private FloatingActionButton fabPlay;
    private PlaylistAdapter adapter;
    private ConstraintLayout constraintLayout;
    private PlayListViewModel playlistVM;
    Playlist playlist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        playlistVM = new ViewModelProvider(this).get(PlayListViewModel.class);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        playlist = (Playlist) bundle.get("playlist");
        TextView textView = findViewById(R.id.tvPlaylist);
        fabPlay = findViewById(R.id.fabAddSongs);
        textView.setText(playlist.getTitle());
        constraintLayout = findViewById(R.id.clPlaylistInfo);
//        constraintLayout
        Glide.with(this).load(playlist.getImageUrl()).into(new CustomTarget<Drawable>() {

            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable com.bumptech.glide.request.transition.Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    constraintLayout.setBackground(resource);
                }
            }

            @Override
            public void onLoadCleared(@Nullable Drawable placeholder) {

            }
        });
        btnGoBack = findViewById(R.id.btnBack);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PlaylistActivity.this, "Back", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = findViewById(R.id.rcvPlaylistSongs);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new PlaylistAdapter();
        adapter.setItems(getPlaylistSong(), new IClickSongOption() {
            @Override
            public void onClickSongOption(Song song) {
                openSongBottomSheet();
            }
            @Override
            public void onClickSong(Song song) {
                openMusicPlayer();
            }
        });
        recyclerView.setAdapter(adapter);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                requireActivity().getOnBackPressedDispatcher().onBackPressed();

            }
        });
        fabPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
            }
        });
    }public void openMusicPlayer() {
        Intent intent = new Intent(this, MusicPlayerActivity.class);
        startActivity(intent);
    }
    public void openSongBottomSheet() {
        View viewBottom = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_song_playlist, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewBottom);
        bottomSheetDialog.show();
    }
    private List<SongInfo> getPlaylistSong(){
        List<SongInfo> list = new ArrayList<>();
//        list.add(new Song("1","https://raw.githubusercontent.com/Tamashi-Dake/Online_Music_Player_Android/main/app/src/main/res/drawable/img_3.jpg","Song 1","Artist 1", "", ""));
//        list.clear();
//        Intent intent = getIntent();
//        if (intent != null && intent.getExtras() != null) {
//            // Nhận Bundle từ Intent
//            Bundle bundle = intent.getExtras();
//
//            // Kiểm tra xem Bundle có chuỗi với key "key" không
//            if (bundle != null && bundle.containsKey("playlist")) {
//                // Nhận chuỗi từ Bundle
//                Playlist pList = (Playlist) bundle.getSerializable("playlist");
//                String data = playlist.getId();
//                playlistVM.getSongPlaylist(data).observe(this, new Observer<List<SongInfo>>() {
//                    @Override
//                    public void onChanged(List<SongInfo> songInfos) {
//                        for (int i = 0; i < songInfos.size(); i++) {
//                            list.add(new SongInfo(songInfos.get(i).getId_song(),songInfos.get(i).getName_song(),songInfos.get(i).getPlayedTime(),songInfos.get(i).getLinkSong(),songInfos.get(i).getLinkPicture(),songInfos.get(i).getName_artist(),songInfos.get(i).getName_category()));
//                        }
//                    }
//                });
//                Log.e("data",data);
//            }
//        }
        return list;
    }
}