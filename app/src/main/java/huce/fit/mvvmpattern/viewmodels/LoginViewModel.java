package huce.fit.mvvmpattern.viewmodels;

import android.content.Intent;
import android.util.Log;

import androidx.databinding.Bindable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import huce.fit.mvvmpattern.BR;
import huce.fit.mvvmpattern.model.LoginUser;
import huce.fit.mvvmpattern.utils.Status;
import huce.fit.mvvmpattern.utils.ValidatorUtil;
import huce.fit.mvvmpattern.views.LoginActivity;
import huce.fit.mvvmpattern.views.SignupActivity;

public class LoginViewModel extends ViewModel {
//    @Bindable
//    private String messageIdToStartMain;
    public MutableLiveData<Integer> getLoginStatus() {
        return LoginStatus;
    }

    private MutableLiveData<Integer> LoginStatus = new MutableLiveData<>();
    private LoginUser loginUser = new LoginUser();
    public void onclickLogin() {
        try {
            if (validateData()) {
//               //call api login here
                LoginStatus.setValue(Status.loginSuccess);

//                messageIdToStartMain = "main";

            }
        } catch (Exception ex) {
            Log.e("Login viewmodel",ex.getMessage());
        }
    }

    private boolean validateData() {
        try {
            if (ValidatorUtil.emptyValue(loginUser.getUsername())) {
                LoginStatus.setValue(Status.emptyUsername);
                return false;
            } else if (ValidatorUtil.emptyValue(loginUser.getPassword())) {
                LoginStatus.setValue(Status.emptyPassWord);
                return false;
            }
        } catch (Exception ex) {
            Log.e("Validate viewmodel",ex.getMessage());
        }
        return true;
    }

//
//    public String getMessageIdToStartMain() {
//        return messageIdToStartMain;
//    }
//    public void setMessageIdToStartMain(String message) {
//        this.messageIdToStartMain = messageIdToStartMain;
//    }
    public LoginUser getLoginUser() {
        return loginUser;
    }
}