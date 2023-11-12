package huce.fit.mvvmpattern.views;

import android.app.Activity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.databinding.ActivityMainBinding;
import huce.fit.mvvmpattern.utils.Status;
import huce.fit.mvvmpattern.viewmodels.HomeViewModel;
import huce.fit.mvvmpattern.viewmodels.LoginViewModel;


public class MainActivity extends AppCompatActivity {
    private HomeViewModel homeViewModel;
    private ActivityMainBinding binding;

//    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//
//        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
//
        binding.setLifecycleOwner(this);
//
        binding.setHomeViewModel(homeViewModel);

        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_home){
                        Toast.makeText(MainActivity.this,"Home",Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.action_search){
                    Toast.makeText(MainActivity.this,"Search",Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.action_library){
                    Toast.makeText(MainActivity.this,"Library",Toast.LENGTH_SHORT).show();
                }
                else if (id == R.id.action_profile){
                    Toast.makeText(MainActivity.this,"Profile",Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });
    }

}
