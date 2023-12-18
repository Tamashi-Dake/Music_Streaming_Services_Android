package huce.fit.mvvmpattern.views.fragments.nowPlaying;

import android.content.Intent;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.services.MediaService;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.MusicPlayerActivity;


public class MediaPlayerFragment extends Fragment {

    public static final String TAG = "MediaPlayerFragment";

    private TextView tvSongName;
    private TextView tvArtistName;
    private TextView tvCurrentTime;
    private TextView tvTotalTime;
    private TextView tvHintTime;
    private ImageView ivDisc;
    private ImageButton ibBack;
    private ImageButton ibPlay;
    private ImageButton ibPrevious;
    private ImageButton ibNext;
    private ImageButton ibRepeat;
    private ImageButton ibShuffle;
    private SeekBar sbTimeLine;
    private ProgressBar pbLoading;
    private List<String> linkSongList;
    private List<Song> linkSongAdapterList;

    private MusicPlayerActivity musicPlayerActivity;
    private Intent intent;

    public MediaPlayerFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_media_player, container, false);
        musicPlayerActivity = (MusicPlayerActivity) getActivity();

//        MediaService.isComing = musicPlayerActivity.getIntent().ge;
        MediaService.isComing = true;

        intent = new Intent(musicPlayerActivity, MediaService.class);
        musicPlayerActivity.startService(intent);

        init(view);
//        addSong();
        addSongAdapter();
        initMediaPlayer();
        processEvent();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

//    private void loadingImage () {
//        musicPlayerActivity.runOnUiThread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        if (mediaPlayer != null) {
//                            Glide.with(ivDisc.getContext()).load(MainActivity.song.getImage()).into(ivDisc);
//                        }
//                        handler.postDelayed(this, 100);
//                    }
//                }
//        );
//    }

    private void startAnimation() {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                ivDisc.animate().rotationBy(360f).setDuration(10000).withEndAction(this).setInterpolator(new LinearInterpolator()).start();
            }
        };
        ivDisc.animate().rotationBy(360f).setDuration(10000).withEndAction(runnable).setInterpolator(new LinearInterpolator()).start();
    }

    private void stopAnimation() {
        ivDisc.animate().cancel();
    }

//    NEW CODE:

    private void eventIbBack () {
        ibBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });
    }

    private void updateSbTimeLine() {
        MediaService.getStartMillisecondsMutableLiveData().observe(musicPlayerActivity, milliseconds -> {
            sbTimeLine.setProgress(milliseconds);
        });
    }

    private void freezingButtonControl () {
        ibPlay.setEnabled(false);
        sbTimeLine.setEnabled(false);
    }

    private void thawingButtonControl () {
        ibPlay.setEnabled(true);
        sbTimeLine.setEnabled(true);
    }

    private void eventBtnBack () {
        ibBack.setOnClickListener(view -> {
            musicPlayerActivity.finish();
        });
    }

    private void eventIbPlay () {
        ibPlay.setOnClickListener(view -> {
            MediaService.eventPlayPause();
        });
    }

    private void eventIbPrevious () {
        ibPrevious.setOnClickListener(view -> {
            MediaService.eventPrevious();
            ibPlay.setImageResource(R.drawable.ic_pause_white);
        });
    }

    private void eventIbNext () {
        ibNext.setOnClickListener(view -> {
            MediaService.eventNext();
            ibPlay.setImageResource(R.drawable.ic_pause_white);
        });
    }

    private void eventIbRepeat () {
        ibRepeat.setOnClickListener(view -> {
            MediaService.eventRepeat();
//            if (ibRepeat.getTag() == null) {
//                ibRepeat.setTag("2");
//            }
//
//            String status = ibRepeat.getTag().toString();
//            // 0: off  |  1: one  |  2: all
//            switch (status) {
//                case "0":
//                case "1":
//                    ibRepeat.setTag("2");
//                    ibShuffle.setTag("0");
//                    ibRepeat.setImageResource(R.drawable.ic_repeat_red);
//                    ibShuffle.setImageResource(R.drawable.ic_shuffle_white);
//                    break;
//                case "2":
//                    ibRepeat.setTag("1");
//                    ibShuffle.setTag("0");
//                    ibRepeat.setImageResource(R.drawable.ic_repeat_one_red);
//                    ibShuffle.setImageResource(R.drawable.ic_shuffle_white);
//                    break;
//            }
        });
    }

    private void eventIbShuffle () {
        ibShuffle.setOnClickListener(view -> {
            MediaService.eventShuffle();
//            if (ibShuffle.getTag() == null) {
//                ibShuffle.setTag("0");
//            }
//
//            String status = ibShuffle.getTag().toString();
//            // 0: off  |  1: on
//            switch (status) {
//                case "0":
//                    ibShuffle.setTag("1");
//                    ibRepeat.setTag("0");
//                    ibShuffle.setImageResource(R.drawable.ic_shuffle_red);
//                    ibRepeat.setImageResource(R.drawable.ic_repeat_white);
//                    break;
//                case "1":
//                    ibShuffle.setTag("0");
//                    ibRepeat.setTag("2");
//                    ibShuffle.setImageResource(R.drawable.ic_shuffle_white);
//                    break;
//            }
        });
    }

    private void eventSbTimeLine () {
        sbTimeLine.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                int milliseconds = seekBar.getProgress();
                SimpleDateFormat timeFormat = new SimpleDateFormat("mm:ss");
                tvHintTime.setText(timeFormat.format(milliseconds));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvHintTime.setVisibility(View.VISIBLE);
//                MediaService.removeUpdateSeekBarHandle();
                MediaService.isUpdatingSeekBar = false;
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvHintTime.setVisibility(View.INVISIBLE);
                int milliseconds = seekBar.getProgress();
                MediaService.eventSeekTo(milliseconds);
//                MediaService.postUpdateSeekBarHandle();
                MediaService.isUpdatingSeekBar = true;
            }
        });
    }

    private void init (View view) {
        tvSongName = view.findViewById(R.id.tv_song_name);
        tvArtistName = view.findViewById(R.id.tv_artist_name);
        tvCurrentTime = view.findViewById(R.id.tv_current_time);
        tvTotalTime = view.findViewById(R.id.tv_total_time);
        tvHintTime = view.findViewById(R.id.tv_hint_time);
        sbTimeLine = view.findViewById(R.id.sb_timeline);
        ibPlay = view.findViewById(R.id.ib_play);
        ibPrevious = view.findViewById(R.id.ib_previous);
        ibNext = view.findViewById(R.id.ib_next);
        ibRepeat = view.findViewById(R.id.ib_repeat);
        ibShuffle = view.findViewById(R.id.ib_shuffle);
        ivDisc = view.findViewById(R.id.iv_disc);
        pbLoading = view.findViewById(R.id.pb_loading);
        ibBack = view.findViewById(R.id.ib_back);
    }

//    private void addSong () {
//        linkSongList = new ArrayList<>();
//        linkSongList.add(MainActivity.song.getLinkSong());
////        linkSongList.add("https://tongdangtu.000webhostapp.com/song/Red.mp3");
////        linkSongList.add("https://tongdangtu.000webhostapp.com/song/Smooth Criminal.mp3");
////        linkSongList.add("https://tongdangtu.000webhostapp.com/song/Em của ngày hôm qua.mp3");
////        linkSongList.add("https://tongdangtu.000webhostapp.com/song/Nơi này có anh.mp3");
////        linkSongList.add("https://tongdangtu.000webhostapp.com/song/Chạy ngay đi.mp3");
//        MediaService.addSong(linkSongList);
//    }

    private void addSongAdapter () {
        linkSongAdapterList = new ArrayList<>();
        linkSongAdapterList.add(MainActivity.song);
        linkSongAdapterList.add(new Song("10005", "https://nhomhungtu.000webhostapp.com/img/Em của ngày hôm qua.jpg", "Em của ngày hôm qua", "Sơn Tùng MTP", "https://nhomhungtu.000webhostapp.com/song/Em của ngày hôm qua.mp3", "pop"));
        linkSongAdapterList.add(new Song("10001", "https://nhomhungtu.000webhostapp.com/img/Nơi này có anh.jpg", "Nơi này có anh", "Sơn Tùng MTP", "https://nhomhungtu.000webhostapp.com/song/Nơi này có anh.mp3", "pop"));
        linkSongAdapterList.add(new Song("10004", "https://nhomhungtu.000webhostapp.com/img/Chạy ngay đi.jpg", "Chạy ngay đi", "Sơn Tùng MTP", "https://nhomhungtu.000webhostapp.com/song/Chạy ngay đi.mp3", "pop"));
        linkSongAdapterList.add(new Song("10002", "https://nhomhungtu.000webhostapp.com/img/Red.jpg", "Red", "Taylor Swift", "https://nhomhungtu.000webhostapp.com/song/Red.mp3", "pop"));
        linkSongAdapterList.add(new Song("10003", "https://nhomhungtu.000webhostapp.com/img/Smooth Criminal.jpg", "Smooth Criminal", "Michael Jackson", "https://nhomhungtu.000webhostapp.com/song/Smooth Criminal.mp3", "pop"));
        MediaService.addSongAdapter(linkSongAdapterList);
    }

    private void initMediaPlayer () {
        try {
            MediaService.getStatusPrepareMutableLiveData().observe(musicPlayerActivity, statusPrepare -> {
                if (statusPrepare == true) {
                    pbLoading.setVisibility(View.INVISIBLE);
                    thawingButtonControl();
                }
                else {
                    pbLoading.setVisibility(View.VISIBLE);
                    freezingButtonControl();
                }
            });
            MediaService.getStatusPlayingMutableLiveData().observe(musicPlayerActivity, statusPlaying -> {
                if (statusPlaying) {
                    ibPlay.setImageResource(R.drawable.ic_pause_white);
                    startAnimation();
                }
                else {
                    ibPlay.setImageResource(R.drawable.ic_play_white);
                    stopAnimation();
                }
            });
            MediaService.getStatusRepeatMutableLiveData().observe(musicPlayerActivity, statusRepeat -> {
                switch (statusRepeat) {
                    case "0":
                        ibRepeat.setImageResource(R.drawable.ic_repeat_white);
                        break;
                    case "1":
                        ibRepeat.setImageResource(R.drawable.ic_repeat_one_red);
                        break;
                    case "2":
                        ibRepeat.setImageResource(R.drawable.ic_repeat_red);
                        break;
                }
            });
            MediaService.getStatusShuffleMutableLiveData().observe(musicPlayerActivity, statusShuffle -> {
                switch (statusShuffle) {
                    case "0":
                        ibShuffle.setImageResource(R.drawable.ic_shuffle_white);
                        break;
                    case "1":
                        ibShuffle.setImageResource(R.drawable.ic_shuffle_red);
                        break;
                }
            });
            MediaService.getTitleMutableLiveData().observe(musicPlayerActivity, songName -> {
                tvSongName.setText(songName);
            });
            MediaService.getArtistMutableLiveData().observe(musicPlayerActivity, artistName -> {
                tvArtistName.setText(artistName);
            });
//            MediaService.getCategoryMutableLiveData().observe(musicPlayerActivity, categoryName -> {
//                tvCategoryName.setText(categoryName);
//            });
            MediaService.getLinkPictureMutableLiveData().observe(musicPlayerActivity, linkPicture -> {
                Glide.with(ivDisc.getContext()).load(linkPicture).into(ivDisc);
            });
            MediaService.getStartTimeMutableLiveData().observe(musicPlayerActivity, startTime -> {
                tvCurrentTime.setText(startTime);
            });
            MediaService.getEndTimeMutableLiveData().observe(musicPlayerActivity, endTime -> {
                tvTotalTime.setText(endTime);
            });
            MediaService.getEndMillisecondsMutableLiveData().observe(musicPlayerActivity, milliseconds -> {
                sbTimeLine.setMax(milliseconds);
            });
        } catch (Exception e) {
            Log.e("ERROR", this.getClass().getName()+": initMediaPlayer()");
        }
    }

    private void processEvent () {
        eventIbBack();
        eventIbPlay();
        eventIbPrevious();
        eventIbNext();
        eventIbRepeat();
        eventIbShuffle();
        updateSbTimeLine();
        eventSbTimeLine();
    }
}