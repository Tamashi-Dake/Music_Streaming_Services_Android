package huce.fit.mvvmpattern.views.fragments.nowPlaying;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import huce.fit.mvvmpattern.R;


public class MediaPlayerFragment extends Fragment {

public static final String TAG = "MediaPlayerFragment";

    private TextView tvSongName;
//    private TextView tvArtistName;
//    private TextView tvAlbumName;
//    private TextView tvDuration;
//    private TextView tvCurrentTime;
    private ImageButton btnGoBack;
    private View view;
    public ImageView playPause;
    public MediaPlayer mediaPlayer;
    private ImageView ivSongImage;
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
        ivSongImage = view.findViewById(R.id.ivSongImage);
        playPause = view.findViewById(R.id.btnPlay);
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    playPause.setImageResource(R.drawable.ic_play_white);
                    stopAnimation();
                }else {
                    mediaPlayer.start();
                    playPause.setImageResource(R.drawable.ic_pause_white);
                    startAnimation();
                }
            }
        });
//        String music_url = "https://samplelib.com/lib/preview/mp3/sample-3s.mp3";
        Uri uri = Uri.parse("android.resource://" + getActivity().getPackageName() + "/raw/anytimeanywhere_milet");
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
//            mediaPlayer.setDataSource(music_url);
            mediaPlayer.setDataSource(getActivity().getApplicationContext(), uri);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                }
            });
        }catch (Exception e) {
            e.printStackTrace();
        }

        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });
        return view;

    }
    private void startAnimation() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ivSongImage.animate().rotationBy(360f).setDuration(10000).withEndAction(this).setInterpolator(new LinearInterpolator()).start();
            }
        };
        ivSongImage.animate().rotationBy(360f).setDuration(10000).withEndAction(runnable).setInterpolator(new LinearInterpolator()).start();
    }
    private void stopAnimation() {
        ivSongImage.animate().cancel();
    }
}