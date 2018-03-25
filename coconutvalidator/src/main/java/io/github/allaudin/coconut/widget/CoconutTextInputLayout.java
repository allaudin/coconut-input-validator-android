package io.github.allaudin.coconut.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.util.AttributeSet;



/**
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public class CoconutTextInputLayout extends TextInputLayout implements CoconutView {

    private CoconutTextWatcher watcher;

    public CoconutTextInputLayout(Context context) {
        super(context);
    }

    public CoconutTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CoconutTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Nullable
    @Override
    public CoconutInput getCoconutView() {
        CoconutInput input = getEditText() instanceof CoconutInput ?
                (CoconutInput) getEditText() : null;
        setWatcher(input);
        return input;
    }



    @Override
    public void removeError() {
        setErrorEnabled(false);
        setError(null);
    }

    @Override
    public void setWatcher(@Nullable CoconutInput inputView) {
        if (inputView == null || watcher != null) {
            return;
        }
        watcher = new CoconutTextWatcher(this) {
            @Override
            protected void onTextChange(CoconutView view) {
                view.removeError();
            }
        };
        inputView.setTextWatcher(watcher);
    }

    @Override
    public void setErrorMessage(@Nullable CharSequence error) {
        setErrorEnabled(true);
        setError(error);
    }
}
