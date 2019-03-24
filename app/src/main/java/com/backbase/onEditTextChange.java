package com.backbase;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class onEditTextChange implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence value, int start, int before, int count) {
        if (value != null)
            onTextChanged(value.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    public abstract void onTextChanged(String value);
}
