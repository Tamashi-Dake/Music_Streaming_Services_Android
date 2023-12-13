package huce.fit.mvvmpattern.views.fragments.profile;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.model.Account;
import huce.fit.mvvmpattern.utils.Status;
import huce.fit.mvvmpattern.viewmodels.LoginViewModel;
import huce.fit.mvvmpattern.viewmodels.UpdateAccountViewModel;
import huce.fit.mvvmpattern.views.LoginActivity;
import huce.fit.mvvmpattern.views.MainActivity;

public class ProfileFragment extends Fragment implements LifecycleOwner{
    private MainActivity mainActivity;
    private FloatingActionButton btnChangeUsername;
    private TextInputEditText current_password;
    private TextInputEditText new_password;
    private TextInputEditText renew_password;
    private UpdateAccountViewModel UpdateAccount;
    private LoginViewModel login;
    private Boolean one = false;
    Account acc = new Account();
    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_profile,container,false);
        UpdateAccount = new ViewModelProvider(this).get(UpdateAccountViewModel.class);
        login = new ViewModelProvider(this).get(LoginViewModel.class);
        mainActivity = (MainActivity) getActivity();
        btnChangeUsername = view.findViewById(R.id.btnChangeUsername);


        //
        current_password = view.findViewById(R.id.edtCurrentPassword);
        new_password = view.findViewById(R.id.edtNewPassword);
        renew_password = view.findViewById(R.id.edtPasswordIncorrect);
        btnChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewDialog = LayoutInflater.from(mainActivity).inflate(R.layout.dialog_change_password,null);
                TextInputEditText edtCurrentPassword = viewDialog.findViewById(R.id.edtCurrentPassword);
                TextInputEditText edtNewPassword = viewDialog.findViewById(R.id.edtNewPassword);

                TextInputEditText edtPasswordIncorrect = viewDialog.findViewById(R.id.edtPasswordIncorrect);
                @SuppressLint("FragmentLiveDataObserve") AlertDialog alertDialog = new MaterialAlertDialogBuilder(mainActivity)
                        .setTitle("Change Password")
                        .setView(viewDialog)
                        .setPositiveButton("Change", (dialog, which) -> {
                            String strCurrentPassword = edtCurrentPassword.getText().toString();
                            String strNewPassword = edtNewPassword.getText().toString();
                            String strPasswordIncorrect = edtPasswordIncorrect.getText().toString();
                            acc.setUsername(Status.current_username);
                            acc.setPassword(strCurrentPassword);
                            // do chưa xử lý luồng kĩ nên đôi khi thông báo trễ hay bị nhảy
                                login.checkLogin(acc).observe(mainActivity, integer -> {
                                    // lỗi này kiểu kiểu luôn phải bắt đầu tiên
                                    if((!strNewPassword.equals(strPasswordIncorrect))){
                                        integer = Status.incorrectConfirmPassword;
                                    }
                                    if(strNewPassword.equals("")){
                                        integer = Status.emptyNewPassword;
                                    }
                                    switch (integer) {
                                        case Status.incorrectPassword:
                                            if(!one) {
                                                one = true;
                                                Toast.makeText(mainActivity, R.string.error_check_failed_password, Toast.LENGTH_SHORT).show();
                                            }
                                            break;
                                        case Status.correctPassword:
                                            if(!one) {
                                                one = true;
                                                acc.setPassword(strNewPassword);
                                                UpdateAccount.onChangePassword(acc).observe(mainActivity, accountDataJson -> {
                                                });
                                                Toast.makeText(mainActivity, R.string.toast_update_success, Toast.LENGTH_SHORT).show();
                                            }
                                            break;
                                        case Status.emptyPassword:
                                            if(!one) {
                                                one = true;
                                                Toast.makeText(mainActivity, R.string.error_input_password, Toast.LENGTH_SHORT).show();
                                            }
                                            break;
                                        case Status.incorrectConfirmPassword:
                                            if(!one) {
                                                one = true;
                                                Toast.makeText(mainActivity, R.string.error_confirm_password_incorrect, Toast.LENGTH_SHORT).show();
                                                Log.e("Status",integer.toString());
                                            }
                                            break;
                                        case Status.emptyNewPassword:
                                            if(!one) {
                                                one = true;
                                                // thông báo giống do mật khẩu mới khôn
                                                Toast.makeText(mainActivity, R.string.error_input_new_password, Toast.LENGTH_SHORT).show();
                                            }
                                            break;
                                        case Status.updateFail:
                                            String message = login.getMessage().getValue();
                                            if (message.contains(getString(R.string.sub_str_error_php_api)) == true) {
                                                Toast.makeText(mainActivity, getString(R.string.toast_failed_update)+". "+getString(R.string.error_php_api), Toast.LENGTH_SHORT).show();
                                            }
                                            else {
                                                Toast.makeText(mainActivity, message, Toast.LENGTH_SHORT).show();
                                            }
                                            break;
                                    }
                                });
//                            Toast.makeText(mainActivity, strCurrentPassword+", "+strNewPassword+", "+strPasswordIncorrect, Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Cancel", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .create();
                alertDialog.show();
                one = false;
            }
        });
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
