package huce.fit.mvvmpattern.views;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.databinding.ActivityMainBinding;
import huce.fit.mvvmpattern.utils.Status;
import huce.fit.mvvmpattern.viewmodels.HomeViewModel;
import huce.fit.mvvmpattern.viewmodels.LoginViewModel;


public class MainActivity extends Activity {
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
//        binding.setLifecycleOwner(this);
//
//        binding.setHomeViewModel(homeViewModel);


    }

}
