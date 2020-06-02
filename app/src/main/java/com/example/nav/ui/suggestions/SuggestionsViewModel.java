package com.example.nav.ui.suggestions;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class SuggestionsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public SuggestionsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Suggestions");
    }

    public LiveData<String> getText() {
        return mText;
    }
}