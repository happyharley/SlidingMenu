package com.example.test.cavas.ui.porterduffxfermode;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PorterDuffXferModeViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PorterDuffXferModeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}