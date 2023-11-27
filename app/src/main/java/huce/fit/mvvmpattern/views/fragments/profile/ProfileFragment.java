package huce.fit.mvvmpattern.views.fragments.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import huce.fit.mvvmpattern.R;
import huce.fit.mvvmpattern.views.MainActivity;

public class ProfileFragment extends Fragment {
    private MainActivity mainActivity;
    private FloatingActionButton btnChangeUsername;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.fragment_profile,container,false);
        mainActivity = (MainActivity) getActivity();
        btnChangeUsername = view.findViewById(R.id.btnChangeUsername);
        btnChangeUsername.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                View viewDialog = LayoutInflater.from(mainActivity).inflate(R.layout.dialog_change_username,null);
                TextInputEditText edtChangeUsername = viewDialog.findViewById(R.id.edtChangeUsername);
                AlertDialog alertDialog = new MaterialAlertDialogBuilder(mainActivity)
                        .setTitle("Change Username")
                        .setView(viewDialog)
                        .setPositiveButton("Change", (dialog, which) -> {
                            String username = edtChangeUsername.getText().toString();
                            Toast.makeText(mainActivity, username, Toast.LENGTH_SHORT).show();
                        })
                        .setNegativeButton("Cancel", (dialog, which) -> {
                            dialog.dismiss();
                        })
                        .create();
                alertDialog.show();
            }
        });
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
