package com.corp.k.androidos.android.view.customviews;

import android.support.design.widget.TextInputLayout;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * Created by hoangtuan on 12/15/16.
 */
public class TextViewWatcher implements TextWatcher {
    private TextInputLayout mLayout;
    public TextViewWatcher(TextInputLayout ltEdt){
        this.mLayout = ltEdt;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        mLayout.setError(null);
        mLayout.setErrorEnabled(false);
        mLayout.refreshDrawableState();
    }
}
