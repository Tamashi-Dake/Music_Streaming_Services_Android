package huce.fit.mvvmpattern.views.fragments.nowPlaying;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.IOException;
import java.util.Locale;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.MusicPlayerActivity;


public class MediaPlayerFragment extends Fragment {

    public static final String TAG = "MediaPlayerFragment";

    private TextView tvSongName;
    private ImageButton btnGoBack;
    private ImageView ivSongImage;
    private ImageView playPause;
    private SeekBar seekbar;
    private TextView tvDuration;
    private TextView tvCurrentTime;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private Runnable runnable;

    private MusicPlayerActivity musicPlayerActivity;

    public MediaPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_player, container, false);

        tvSongName = view.findViewById(R.id.tvSongName);
        btnGoBack = view.findViewById(R.id.btnBack);
        ivSongImage = view.findViewById(R.id.ivSongImage);
        playPause = view.findViewById(R.id.btnPlay);
        seekbar = view.findViewById(R.id.seekBar);
        tvDuration = view.findViewById(R.id.tvTotalTime);
        tvCurrentTime = view.findViewById(R.id.tvCurrentTime);
        musicPlayerActivity = (MusicPlayerActivity) getActivity();

        musicPlayerActivity.runOnUiThread(
                new Runnable() {
                    @Override
                    public void run() {
                        if (mediaPlayer != null) {
                            int mCurrentPosition = mediaPlayer.getCurrentPosition() / 1000;
                            seekbar.setProgress(mCurrentPosition);
                            tvCurrentTime.setText(getFormattedTime(mediaPlayer.getCurrentPosition()));
                        }
                        handler.postDelayed(this, 100);
                    }
                }
        );

        // Set up MediaPlayer
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
                    // Thực hiện các thao tác sau khi MediaPlayer đã sẵn sàng
                    seekbar.setMax(mediaPlayer.getDuration());
                    tvDuration.setText(getFormattedTime(mediaPlayer.getDuration()));
//                    playCycle();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set up play/pause button
        playPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer.isPlaying()) {
                    pauseMediaPlayer();
                } else {
                    startMediaPlayer();
                }
            }
        });

        // Set up seekbar
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                    tvCurrentTime.setText(getFormattedTime(progress));
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                pauseMediaPlayer();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                startMediaPlayer();
            }
        });

        // Set up go back button
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    private void startMediaPlayer() {
        mediaPlayer.start();
        playPause.setImageResource(R.drawable.ic_pause_white);
        startAnimation();
    }

    private void pauseMediaPlayer() {
        mediaPlayer.pause();
        playPause.setImageResource(R.drawable.ic_play_white);
        stopAnimation();
    }

    private void releaseMediaPlayer() {
        mediaPlayer.release();
        mediaPlayer = null;
    }

    private void playCycle() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            int currentPosition = mediaPlayer.getCurrentPosition();
            seekbar.setProgress(currentPosition);
            tvCurrentTime.setText(getFormattedTime(currentPosition));
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    playCycle();
                }
            }, 1000);
        }
    }

    private String getFormattedTime(int milliSeconds) {
        int seconds = (milliSeconds / 1000) % 60;
        int minutes = (milliSeconds / (1000 * 60)) % 60;
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
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