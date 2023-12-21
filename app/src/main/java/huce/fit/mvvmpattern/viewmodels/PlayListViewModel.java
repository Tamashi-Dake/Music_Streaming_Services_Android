package huce.fit.mvvmpattern.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import huce.fit.mvvmpattern.model.SongInfo;
import huce.fit.mvvmpattern.repository.PlayListRepository;

public class PlayListViewModel extends AndroidViewModel {
    private PlayListRepository playlist = new PlayListRepository();
    public PlayListViewModel(@NonNull Application application) {
        super(application);
    }
//    public LiveData<List<SongInfo>> getSongPlaylist(String str){
//        return playlist.getPlayListMutableLiveData(str);
//    }
}
