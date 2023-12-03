package huce.fit.mvvmpattern.views.fragments.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.model.Song;
import huce.fit.mvvmpattern.views.MainActivity;
import huce.fit.mvvmpattern.views.appInterface.IClickSongOption;

public class SearchFragment extends Fragment {
    private RecyclerView recyclerView;
    private SearchAdapter songAdapter;
    private MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_search,container,false);
        mainActivity = (MainActivity) getActivity();
        recyclerView = view.findViewById(R.id.rcvSearchItems);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mainActivity, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        songAdapter = new SearchAdapter();
        songAdapter.setItems(getListSong(), new IClickSongOption() {
            @Override
            public void onClickSongOption(Song song) {
                mainActivity.openSongBottomSheet();
            }
            @Override
            public void onClickSong(Song song) {
                mainActivity.openMusicPlayer();
            }
        });
        recyclerView.setAdapter(songAdapter);

        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
    private List<Song> getListSong(){
        List<Song> list = new ArrayList<>();
        list.add(new Song(R.drawable.img_1,"Song 1","Artist 1"));
        return list;
    }
}
