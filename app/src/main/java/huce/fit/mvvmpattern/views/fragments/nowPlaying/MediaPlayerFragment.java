package huce.fit.mvvmpattern.views.fragments.nowPlaying;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import huce.fit.mvvmpattern.R;


public class MediaPlayerFragment extends Fragment {

public static final String TAG = "MediaPlayerFragment";

    private TextView tvSongName;
    private TextView tvArtistName;
    private TextView tvAlbumName;
    private TextView tvDuration;
    private TextView tvCurrentTime;
    private Button btnGoBack;
    private View view;

    public MediaPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_media_player, container, false);
        tvSongName = view.findViewById(R.id.tvSongName);
        btnGoBack = view.findViewById(R.id.btnBack);

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });
        return view;

    }
}