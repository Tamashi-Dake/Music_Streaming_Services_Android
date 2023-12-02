package huce.fit.mvvmpattern.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import huce.fit.mvvmpattern.api.AccountService;
import huce.fit.mvvmpattern.model.Account;
import huce.fit.mvvmpattern.model.DataJson;
import huce.fit.mvvmpattern.utils.Status;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountRepository {
    private Application application;
    public AccountRepository(Application application) {
        this.application = application;
    }
    private MutableLiveData<Integer> loginStatus = new MutableLiveData<>();

    public MutableLiveData<Integer> getLoginStatus(Account account) {
        if (account.getUsername()!=null && account.getPassword()!=null) {
            AccountService.accountService.checkAccount(account)
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
        return loginStatus;
    }

    public void setLoginStatus(Integer loginStatus) {
        this.loginStatus.setValue(loginStatus);
    }
}
