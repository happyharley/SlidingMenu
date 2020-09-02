package com.example.test.cavas.ui.colormatrix;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MatrixViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MatrixViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is matrix fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}