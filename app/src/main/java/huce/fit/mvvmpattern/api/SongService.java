package huce.fit.mvvmpattern.api;

import huce.fit.mvvmpattern.model.Artist;
import huce.fit.mvvmpattern.model.DataJson;
import huce.fit.mvvmpattern.model.SongApi;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SongService {
    SongService songService = new Retrofit.Builder()
            .baseUrl("https://tongdangtu.000webhostapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(SongService.class);

    @GET("song/songList.php")
    Call<DataJson<SongApi>> getSongList ();

    @GET("song/songGet.php")
    Call<DataJson<SongApi>> getSong (@Query("id") String id);  // id của bài hát

    @GET("song/songPopular.php")
    Call<DataJson<SongApi>> getSongPopular ();

    @GET("song/getArtist.php")
    Call<DataJson<Artist>> getArtist (@Query("id") String id);  // id của bài hát
}
