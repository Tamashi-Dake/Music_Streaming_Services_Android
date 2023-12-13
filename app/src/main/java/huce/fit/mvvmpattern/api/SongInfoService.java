package huce.fit.mvvmpattern.api;

import huce.fit.mvvmpattern.model.DataJson;
import huce.fit.mvvmpattern.model.SongInfo;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface SongInfoService {
    SongInfoService songInfoService = new Retrofit.Builder()
            .baseUrl("https://tongdangtu.000webhostapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SongInfoService.class);

    @GET("songInfo/getPopular.php")
    Call<DataJson<SongInfo>> getPopular ();

    @GET("songInfo/getRandom.php")
    Call<DataJson<SongInfo>> getRandom ();
}
