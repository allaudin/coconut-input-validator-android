package io.github.allaudin.coconut.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

import io.github.allaudin.coconut.R;


/**
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public class CoconutEditText extends AppCompatEditText implements CoconutInput, CoconutView {

    private String errorMessage;
    private String validationRegex;
    private boolean isOptional;

    public CoconutEditText(Context context) {
        super(context);
    }

    public CoconutEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(context, attrs, 0);
    }

    public CoconutEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(context, attrs, defStyleAttr);
    }

    private void setAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CoconutEditText,
                defStyleAttr, 0);

        try {
            errorMessage = typedArray.getString(
                    R.styleable.CoconutEditText_cnt_et_error_message);
            validationRegex = typedArray.getString(
                    R.styleable.CoconutEditText_cnt_et_validation_regex);
            isOptional = typedArray.getBoolean(
                    R.styleable.CoconutEditText_cnt_et_optional, false);
        } finally {
            typedArray.recycle();
        }
        setWatcher(this);
    }

    @Override
    public void setErrorMessage(@Nullable CharSequence error) {
        setError(error);
    }

    @Nullable
    @Override
    public CoconutInput getCoconutView() {
        return this;
    }

    @Override
    public void removeError() {
        setError(null);
    }

    @Override
    public void setWatcher(@Nullable CoconutInput inputView) {
        if (inputView != null) {
            inputView.setTextWatcher(new CoconutTextWatcher(this) {
                @Override
                protected void onTextChange(CoconutView view) {
                    view.removeError();
                }
            });
        }
    }

    @Nullable
    @Override
    public String getInput() {
        return getText().toString();
    }

    @Nullable
    @Override
    public String getValidationRegex() {
        return validationRegex;
    }

    @Nullable
    @Override
    public String getErrorMessages() {
        return errorMessage;
    }

    @Override
    public boolean isOptional() {
        return isOptional;
    }

    @Override
    public void setTextWatcher(@NonNull CoconutTextWatcher watcher) {
        addTextChangedListener(watcher);
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setValidationRegex(String validationRegex) {
        this.validationRegex = validationRegex;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }
}
