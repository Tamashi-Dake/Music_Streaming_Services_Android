package huce.fit.mvvmpattern.views;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.databinding.ActivityMainBinding;
import huce.fit.mvvmpattern.utils.Status;
import huce.fit.mvvmpattern.viewmodels.LoginViewModel;


public class MainActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        binding.setLifecycleOwner(this);

        binding.setLoginViewModel(loginViewModel);

        loginstatus();

    }

    private void loginstatus() {
        loginViewModel.getLoginStatus().observe(this, status -> {
            switch (status) {
                case Status.loginSuccess:
                    //xu ly dang nhap thanh cong
                    Toast.makeText(MainActivity.this, "Login success.", Toast.LENGTH_LONG).show();
                    break;
                case Status.emptyUsername:
                    //thong bao Toast message hoac hien thi loi Username trong
                    binding.txtUsername.setError(getString(R.string.emptyUsername));
                    binding.txtUsername.requestFocus();
                    break;
                case Status.emptyPassWord:
                    //tuong tu cho password
                    binding.txtPassword.setError(getString(R.string.emptypassword));
                    binding.txtPassword.requestFocus();
                    break;


            }

        });
    }
}
