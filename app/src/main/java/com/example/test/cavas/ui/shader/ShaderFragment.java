package com.example.test.cavas.ui.shader;

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


public class ShaderFragment extends Fragment {

    private ShaderViewModel shaderViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shaderViewModel =
                ViewModelProviders.of(this).get(ShaderViewModel.class);
        View root = inflater.inflate(R.layout.fragment_shader, container, false);
        shaderViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
            }
        });

        return root;
    }
}