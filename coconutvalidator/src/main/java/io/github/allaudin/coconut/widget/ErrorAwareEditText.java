package io.github.allaudin.coconut.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.allaudin.coconut.R;

public class ErrorAwareEditText extends LinearLayout implements CoconutInput, CoconutView {

    private boolean isOptional;
    private String errorMessage;
    private String validationRegex;

    private EditText editTextView;
    private TextView errorTextView;

    public ErrorAwareEditText(Context context) {
        this(context, null);
    }

    public ErrorAwareEditText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ErrorAwareEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.ErrorAwareEditText,
                defStyleAttr, 0);

        String editTextHint;
        int errorColor = -1;
        float textSize = -1, errorTextSize = -1;

        try {
            editTextHint = typedArray.getString(
                    R.styleable.ErrorAwareEditText_cnt_eav_hint);
            errorColor = typedArray.getColor(
                    R.styleable.ErrorAwareEditText_cnt_eav_error_color, errorColor);

            textSize = typedArray.getDimension(R.styleable.ErrorAwareEditText_cnt_eav_text_size, textSize);
            errorTextSize = typedArray.getDimension(R.styleable.ErrorAwareEditText_cnt_eav_error_text_size, errorTextSize);

            errorMessage = typedArray.getString(
                    R.styleable.ErrorAwareEditText_cnt_eav_error_message);
            validationRegex = typedArray.getString(
                    R.styleable.ErrorAwareEditText_cnt_eav_validation_regex);
            isOptional = typedArray.getBoolean(
                    R.styleable.ErrorAwareEditText_cnt_eav_optional, false);
        } finally {
            typedArray.recycle();
        }

        setOrientation(VERTICAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        ViewProvider viewProvider = getViewProvider();

        editTextView = viewProvider.getEditTextView(context, this);
        errorTextView = viewProvider.getErrorTextView(context, this);
        editTextView.setHint(editTextHint);
        errorTextView.setVisibility(GONE);
        if (errorColor != -1) {
            errorTextView.setTextColor(errorColor);
        }

        if (textSize != -1) {
            editTextView.setTextSize(textSize);
        }
        if (errorTextSize != -1) {
            errorTextView.setTextSize(errorTextSize);
        }
        addView(editTextView, params);
        addView(errorTextView, params);

        setWatcher(this);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = 0;

        for (int i = 0, len = getChildCount(); i < len; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);
            if (child.getVisibility() == VISIBLE) {
                height += child.getMeasuredHeight();
            }
        }

        setMeasuredDimension(resolveSize(width, widthMeasureSpec),
                resolveSize(height, heightMeasureSpec));

    } // onMeasure


    protected ViewProvider getViewProvider() {
        return new DefaultViewProvider();
    }

    @Nullable
    @Override
    public String getInput() {
        Editable text = editTextView.getText();
        return text == null ? null : text.toString();
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
        editTextView.addTextChangedListener(watcher);
    }

    @Override
    public void setErrorMessage(@Nullable CharSequence error) {
        errorTextView.setVisibility(VISIBLE);
        errorTextView.setText(error);
    }

    @Nullable
    @Override
    public CoconutInput getCoconutView() {
        return this;
    }

    @Override
    public void removeError() {
        errorTextView.setVisibility(GONE);
        errorTextView.setText(null);
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


    private static class DefaultViewProvider implements ViewProvider {

        @Override
        public EditText getEditTextView(Context context, ViewGroup parent) {
            return new EditText(context);
        }

        @Override
        public TextView getErrorTextView(Context context, ViewGroup parent) {
            return new TextView(context);
        }
    } // DefaultViewProvider

    public interface ViewProvider {
        EditText getEditTextView(Context context, ViewGroup parent);

        TextView getErrorTextView(Context context, ViewGroup parent);
    } // ViewProvider


    public void setOptional(boolean optional) {
        isOptional = optional;
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
} // ErrorAwareEditText
