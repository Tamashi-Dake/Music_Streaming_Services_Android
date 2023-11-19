package huce.fit.mvvmpattern.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.databinding.ActivityMainBinding;
import huce.fit.mvvmpattern.viewmodels.HomeViewModel;
import huce.fit.mvvmpattern.views.fragments.nowPlaying.MediaPlayerFragment;


public class MainActivity extends AppCompatActivity {
    private HomeViewModel homeViewModel;
    private ActivityMainBinding binding;
    private ViewPager2 viewPager;
    private BottomNavigationView bottomNav;

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

        View view = findViewById(R.id.mini_player);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MusicPlayerActivity.class);
                startActivity(intent);
//                vì lý do nào đó mà animation ko nhận đc
//                overridePendingTransition(R.anim.slide_up, R.anim.slide_up_out);
            }
        });

//        handle event when user select item on bottom navigation
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//               vì lý do gì đó mà switch không nhận được R.id... nên đành phải dùng if else
                int id = item.getItemId();
                if (id == R.id.action_home) {
//                        Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(0);
                } else if (id == R.id.action_search) {
//                    Toast.makeText(MainActivity.this,"Search",Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(1);
                } else if (id == R.id.action_library) {
//                    Toast.makeText(MainActivity.this,"Library",Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(2);
                } else if (id == R.id.action_profile) {
//                    Toast.makeText(MainActivity.this,"Profile",Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(3);
                }
                return true;
            }
        });
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


}
