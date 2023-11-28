package huce.fit.mvvmpattern.views;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.Playlist;

public class PlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        Playlist playlist = (Playlist) bundle.get("playlist");
        TextView textView = findViewById(R.id.tvPlaylist);
        textView.setText(playlist.getTitle());
    }
}