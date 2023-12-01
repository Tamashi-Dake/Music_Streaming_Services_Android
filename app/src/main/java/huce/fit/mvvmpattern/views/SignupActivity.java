package huce.fit.mvvmpattern.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import huce.fit.mvvmpattern.databinding.ActivitySignupBinding;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.viewmodels.SignUpViewModel;

public class SignupActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivitySignupBinding binding;

    private SignUpViewModel signupViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        signupViewModel = new ViewModelProvider(this).get(SignUpViewModel.class);

        binding = DataBindingUtil.setContentView(SignupActivity.this, R.layout.activity_signup);

        binding.setLifecycleOwner(this);

        binding.setSignupViewModel(signupViewModel);

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignup = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intSignup);
            }
        });

    }

}