package io.github.allaudin.coconut.widget;

import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;

/**
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public abstract class CoconutTextWatcher implements TextWatcher {

    @NonNull
    private CoconutView view;

    CoconutTextWatcher(@NonNull CoconutView view) {
        this.view = view;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // no-op
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // no-op
    }

    @Override
    public void afterTextChanged(Editable s) {
        onTextChange(view);
    }

    protected abstract void onTextChange(CoconutView view);
}
