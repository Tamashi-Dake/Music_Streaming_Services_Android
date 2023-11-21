package huce.fit.mvvmpattern.views.fragments.nowPlaying;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import huce.fit.mvvmpattern.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TrackInfoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TrackInfoFragment extends Fragment {
    private ImageButton btnGoBack;
    public TrackInfoFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_track_info, container, false);
        btnGoBack = view.findViewById(R.id.btnBack);
        btnGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().getOnBackPressedDispatcher().onBackPressed();
            }
        });
        return view;
    }
}