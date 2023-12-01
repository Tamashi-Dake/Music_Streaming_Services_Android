package huce.fit.mvvmpattern.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import huce.fit.mvvmpattern.api.AccountService;
import huce.fit.mvvmpattern.model.Account;
import huce.fit.mvvmpattern.model.DataJson;
import huce.fit.mvvmpattern.utils.Status;
import huce.fit.mvvmpattern.utils.ValidatorUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {
    private Account account = new Account();

    public Account getAccount() {
        return account;
    }

    private MutableLiveData<Integer> loginStatus = new MutableLiveData<>();
    public MutableLiveData<Integer> getLoginStatus () {
        return loginStatus;
    }

    public void onClickLogin () {
        validateData();
        Account tmpAccount = new Account(account.getUsername(), account.getPassword());
        AccountService.accountService.checkAccount(tmpAccount)
                .enqueue(new Callback<DataJson<Account>>() {
                    @Override
                    public void onResponse(Call<DataJson<Account>> call, Response<DataJson<Account>> response) {
                        DataJson<Account> dataJson = response.body();
                        if (dataJson != null) {
                            if (dataJson.isStatus() == true) {
                                loginStatus.setValue(Status.loginSuccess);
                            }
                            else {
                                loginStatus.setValue(Status.loginFail);
                            }
                        }
                        else {
                            loginStatus.setValue(Status.loginFail);
                        }
                    }

                    @Override
                    public void onFailure(Call<DataJson<Account>> call, Throwable t) {
                        Log.e("ERROR", this.getClass().getName()+"onClickLogin()->onFailure");
                        loginStatus.setValue(Status.loginFail);
                    }
                });
    }

    public void onClickSignUp () {

    }

    public boolean validateData () {
        if (ValidatorUtil.emptyValue(account.getUsername())) {
            loginStatus.setValue(Status.emptyUsername);
            return false;
        } 
        else if (ValidatorUtil.emptyValue(account.getPassword())) {
            loginStatus.setValue(Status.emptyPassword);
            return false;
        }
        return true;
    }
}
