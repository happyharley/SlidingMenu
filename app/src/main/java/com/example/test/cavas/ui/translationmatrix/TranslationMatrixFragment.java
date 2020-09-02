package com.example.test.cavas.ui.translationmatrix;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.test.R;


public class TranslationMatrixFragment extends Fragment {

    private TranslationMatrixViewModel translationMatrixViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        translationMatrixViewModel =
                ViewModelProviders.of(this).get(TranslationMatrixViewModel.class);
        View root = inflater.inflate(R.layout.fragment_translationmatrix, container, false);
        final TransView textView = root.findViewById(R.id.text_dashboard);
        translationMatrixViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });
        return root;
    }
}