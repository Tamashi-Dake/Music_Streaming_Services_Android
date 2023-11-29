package huce.fit.mvvmpattern.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.fragments.library.itemPlaylist.Playlist;

public class PlaylistActivity extends AppCompatActivity {

    private AppBarLayout appBarLayout;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private FloatingActionButton fabPlay;
    private PlaylistAdapter adapter;
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