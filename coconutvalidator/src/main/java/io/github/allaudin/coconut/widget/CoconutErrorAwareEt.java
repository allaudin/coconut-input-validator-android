package io.github.allaudin.coconut.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.view.ContextThemeWrapper;
import android.text.Editable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.github.allaudin.coconut.R;

public class CoconutErrorAwareEt extends LinearLayout implements CoconutInput, CoconutView {

    private static class DefaultViewProvider implements ViewProvider {

        @Override
        public EditText getEditTextView(Context context, ViewGroup parent, int style) {

            EditText editText;

            if (style != 0) {
                editText = new EditText(new ContextThemeWrapper(context, style),
                        null,0);
            } else {
                editText = new EditText(new ContextThemeWrapper(context, context.getTheme()),
                        null, R.attr.editTextStyle);
            }
            return editText;
        }

        @Override
        public TextView getErrorTextView(Context context, ViewGroup parent) {
            return new TextView(context);
        }
    } // DefaultViewProvider

    private boolean isOptional;
    private String errorMessage;
    private String validationRegex;

    private EditText editTextView;
    private TextView errorTextView;

    public CoconutErrorAwareEt(Context context) {
        this(context, null);
    }

    public CoconutErrorAwareEt(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CoconutErrorAwareEt(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        TypedArray typedArray = context.obtainStyledAttributes(attrs,
                R.styleable.CoconutErrorAwareEt,
                defStyleAttr, 0);


        String editTextHint;
        int errorColor = -1;
        int textSize = -1, errorTextSize = -1;

        try {
            editTextHint = typedArray.getString(
                    R.styleable.CoconutErrorAwareEt_cnt_eav_hint);
            errorColor = typedArray.getColor(
                    R.styleable.CoconutErrorAwareEt_cnt_eav_error_color, errorColor);

            textSize = typedArray.getDimensionPixelSize(R.styleable.CoconutErrorAwareEt_cnt_eav_text_size, textSize);
            errorTextSize = typedArray.getDimensionPixelSize(R.styleable.CoconutErrorAwareEt_cnt_eav_error_text_size, errorTextSize);

            errorMessage = typedArray.getString(
                    R.styleable.CoconutErrorAwareEt_cnt_eav_error_message);
            validationRegex = typedArray.getString(
                    R.styleable.CoconutErrorAwareEt_cnt_eav_validation_regex);
            isOptional = typedArray.getBoolean(
                    R.styleable.CoconutErrorAwareEt_cnt_eav_optional, false);
        } finally {
            typedArray.recycle();
        }

        generateViews(context, attrs.getStyleAttribute(),
                editTextHint, errorColor, textSize, errorTextSize);

        setWatcher(this);
    }

    private void generateViews(Context context,
                               int style,
                               String editTextHint,
                               int errorColor,
                               int textSize,
                               int errorTextSize) {
        setOrientation(VERTICAL);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        ViewProvider viewProvider = getViewProvider();

        editTextView = viewProvider.getEditTextView(context, this, style);
        errorTextView = viewProvider.getErrorTextView(context, this);
        editTextView.setHint(editTextHint);
        errorTextView.setVisibility(GONE);
        if (errorColor != -1) {
            errorTextView.setTextColor(errorColor);
        }

        if (textSize != -1) {
            editTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        if (errorTextSize != -1) {
            errorTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, errorTextSize);
        }
        addView(editTextView, params);
        addView(errorTextView, params);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    protected ViewProvider getViewProvider() {
        return new DefaultViewProvider();
    }

    @Nullable
    @Override
    public String getInput() {
        Editable text = editTextView.getText();
        return text == null ? null : text.toString();
    }

    public void setText(CharSequence text) {
        editTextView.setText(text);
    }

    public void setText(@StringRes int stringRes){
        editTextView.setText(stringRes);
    }

    @Nullable
    @Override
    public String getValidationRegex() {
        return validationRegex;
    }

    public EditText getEditTextView() {
        return editTextView;
    }

    public TextView getErrorTextView() {
        return errorTextView;
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


    /**
     * A provider for generating {@link EditText} for input and a
     * {@link TextView} for showing errors.
     */
    public interface ViewProvider {

        /**
         * Generate {@link EditText} for getting input
         *
         * @param context context for generating view
         * @param parent  parent of the view
         * @return {@link EditText} for getting input
         */
        EditText getEditTextView(Context context, ViewGroup parent, int style);

        /**
         * Generate {@link TextView} for showing input errors
         *
         * @param context context for generating view
         * @param parent  parent of the view
         * @return {@link TextView} for showing errors
         */

        TextView getErrorTextView(Context context, ViewGroup parent);
    } // ViewProvider
} // ErrorAwareEditText
