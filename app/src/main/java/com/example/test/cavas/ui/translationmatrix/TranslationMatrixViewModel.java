package com.example.test.cavas.ui.translationmatrix;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TranslationMatrixViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public TranslationMatrixViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}