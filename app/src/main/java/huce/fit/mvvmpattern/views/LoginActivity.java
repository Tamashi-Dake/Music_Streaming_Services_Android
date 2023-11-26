package huce.fit.mvvmpattern.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.databinding.ActivityLoginBinding;
import huce.fit.mvvmpattern.utils.Status;
import huce.fit.mvvmpattern.viewmodels.LoginViewModel;

public class LoginActivity extends AppCompatActivity {
    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    private int loginflag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        binding.setLifecycleOwner(this);

        binding.setLoginViewModel(loginViewModel);

        loginstatus();
        Button btnSignup = findViewById(R.id.btnSignUp);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignup = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intSignup);
            }
        });
    }

    private void loginstatus() {
        loginViewModel.getLoginStatus().observe(this, status -> {
            switch (status) {
                case Status.loginSuccess:
                    //xu ly dang nhap thanh cong
                    Toast.makeText(LoginActivity.this, "Login success.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
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
