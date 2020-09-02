package com.example.test.cavas.ui.porterduffxfermode;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.test.R;


public class PorterDuffXferModeFragment extends Fragment {

    private PorterDuffXferModeViewModel porterDuffXferModeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        porterDuffXferModeViewModel =
                ViewModelProviders.of(this).get(PorterDuffXferModeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_porterduffxfermode, container, false);
        final ScratchCardView textView = root.findViewById(R.id.text_notifications);
        porterDuffXferModeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        return root;
    }
}