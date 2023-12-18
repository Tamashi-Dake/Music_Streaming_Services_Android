package huce.fit.mvvmpattern.views;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.navigation.NavigationBarView;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.databinding.ActivityMainBinding;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.services.MediaService;
import huce.fit.mvvmpattern.viewmodels.HomeViewModel;
import huce.fit.mvvmpattern.views.adapter.ViewPageAdapter;


public class MainActivity extends AppCompatActivity {
    private HomeViewModel homeViewModel;
    private ActivityMainBinding binding;
    private ViewPager2 viewPager;
    private TextView tvSongName;
    private TextView tvArtistName;
    public ImageView playPause;
    public ImageView next;
    public MediaPlayer mediaPlayer;
    private ImageView ivSongImage;
    private BottomNavigationView bottomNav;

    private LinearLayout linearLayout;
    private BottomSheetDialog bottomSheetSongOption;
    public static Song song = new Song("", "", "", "", "", "");
    //    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setHomeViewModel(homeViewModel);

        viewPager = findViewById(R.id.viewPager);
        bottomNav = findViewById(R.id.bottomNavigation);

        setViewPager();
        tvSongName = findViewById(R.id.tvMiniSongTitle);
        tvArtistName = findViewById(R.id.tvMiniSongArtist);
        ivSongImage = findViewById(R.id.imgMiniImage);
        playPause = findViewById(R.id.iconPlayPause);
        next = findViewById(R.id.iconNextTrack);



//        playPause.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(mediaPlayer.isPlaying()) {
//                    mediaPlayer.pause();
//                    playPause.setImageResource(R.drawable.ic_play);
//                    stopAnimation();
//                }else {
//                    mediaPlayer.start();
//                    playPause.setImageResource(R.drawable.ic_pause);
//                    startAnimation();
//                }
//            }
//        });
////        String music_url = "https://samplelib.com/lib/preview/mp3/sample-3s.mp3";
//        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/anytimeanywhere_milet");
//        mediaPlayer = new MediaPlayer();
//        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
//        try {
////            mediaPlayer.setDataSource(music_url);
//            mediaPlayer.setDataSource(getApplicationContext(), uri);
//            mediaPlayer.prepareAsync();
//            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                }
//            });
//        }catch (Exception e) {
//            e.printStackTrace();
//        }

        View view = findViewById(R.id.mini_player);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openMusicPlayerFromMiniPlayer();
//                vì lý do nào đó mà animation ko nhận đc
//                overridePendingTransition(R.anim.slide_up, R.anim.slide_up_out);
            }
        });

        playPause.setOnClickListener(view_ -> {
            MediaService.eventPlayPause();
        });

        next.setOnClickListener(view_ -> {
            MediaService.eventNext();
        });

        MediaService.getStatusPrepareMutableLiveData().observe(this, statusPrepare -> {
            if (statusPrepare) {
                view.setVisibility(View.VISIBLE);
                thawingButtonControl();
            }
            else {
                freezingButtonControl();
            }
        });
        MediaService.getStatusPlayingMutableLiveData().observe(this, statusPlaying -> {
            if (statusPlaying) {
                playPause.setImageResource(R.drawable.ic_pause);
                startAnimation();
            }
            else {
                playPause.setImageResource(R.drawable.ic_play);
                stopAnimation();
            }
        });
        MediaService.getTitleMutableLiveData().observe(this, songName -> {
            tvSongName.setText(songName);
        });
        MediaService.getArtistMutableLiveData().observe(this, artistName -> {
            tvArtistName.setText(artistName);
        });
        MediaService.getLinkPictureMutableLiveData().observe(this, linkPicture -> {
            Glide.with(ivSongImage.getContext()).load(linkPicture).into(ivSongImage);
        });

//        handle event when user select item on bottom navigation
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//               vì lý do gì đó mà switch không nhận được R.id... nên đành phải dùng if else
                int id = item.getItemId();
                if (id == R.id.action_home) {
                    viewPager.setCurrentItem(0);
                } else if (id == R.id.action_search) {
                    viewPager.setCurrentItem(1);
                } else if (id == R.id.action_library) {
                    viewPager.setCurrentItem(2);
                } else if (id == R.id.action_profile) {
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
    }
//    public void openMusicPlayer() {
//        Intent intent = new Intent(this, MusicPlayerActivity.class);
//        startActivity(intent);
//    }
    public void openMusicPlayer() {
        Intent intent = new Intent(this, MusicPlayerActivity.class);
        startActivity(intent);
    }
    public void openMusicPlayerFromMiniPlayer() {
        Intent intent = new Intent(this, MusicPlayerActivity.class);
        intent.putExtra("isComingFromMiniPlayer", true);
        startActivity(intent);
    }
    public void openArtistFragment() {
        Intent intent = new Intent(this, ArtistActivity.class);
        startActivity(intent);
    }
    public void openSongBottomSheet() {
        View viewBottom = getLayoutInflater().inflate(R.layout.layout_bottom_sheet_song, null);
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        bottomSheetDialog.setContentView(viewBottom);
        bottomSheetDialog.show();
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
    private void setViewPager() {
        //        set adapter for viewpager
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);
        viewPager.setUserInputEnabled(false);
//        set active bottom navigation when user swipe viewpager
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position) {
                    case 1:
                        bottomNav.getMenu().findItem(R.id.action_search).setChecked(true);
                        break;
                    case 2:
                        bottomNav.getMenu().findItem(R.id.action_library).setChecked(true);
                        break;
                    case 3:
                        bottomNav.getMenu().findItem(R.id.action_profile).setChecked(true);
                        break;
                    default:
                        bottomNav.getMenu().findItem(R.id.action_home).setChecked(true);
                        break;
                }
            }
        });
    }


    public void openCategoryFragment() {
        Intent intent = new Intent(this, CategoryActivity.class);
        startActivity(intent);
    }

//  NEW CODE:
    private void freezingButtonControl () {
        playPause.setEnabled(false);
    }

    private void thawingButtonControl () {
        playPause.setEnabled(true);
    }
}
