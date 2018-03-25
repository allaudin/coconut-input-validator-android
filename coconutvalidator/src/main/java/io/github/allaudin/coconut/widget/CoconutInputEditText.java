package io.github.allaudin.coconut.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;

import io.github.allaudin.coconut.R;


/**
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public class CoconutInputEditText extends TextInputEditText implements CoconutInput {

    private String errorMessage;
    private String validationRegex;
    private boolean isOptional;

    public CoconutInputEditText(Context context) {
        super(context);
    }

    public CoconutInputEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(context, attrs, 0);
    }

    public CoconutInputEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(context, attrs, defStyleAttr);
    }

    private void setAttributes(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CoconutInputEditText,
                defStyleAttr, 0);

        try {
            errorMessage = typedArray.getString(
                    R.styleable.CoconutInputEditText_cnt_input_et_error_message);
            validationRegex = typedArray.getString(
                    R.styleable.CoconutInputEditText_cnt_input_et_validation_regex);
            isOptional = typedArray.getBoolean(
                    R.styleable.CoconutInputEditText_cnt_input_et_optional, false);
        } finally {
            typedArray.recycle();
        }
    }

    @Nullable
    @Override
    public String getInput() {
        return getText() == null ? null : getText().toString();
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

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setValidationRegex(String validationRegex) {
        this.validationRegex = validationRegex;
    }

    public void setOptional(boolean optional) {
        isOptional = optional;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setTextWatcher(@NonNull CoconutTextWatcher watcher) {
        addTextChangedListener(watcher);
    }

} // CoconutInputEditText
