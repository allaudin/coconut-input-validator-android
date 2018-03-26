package io.github.allaudin.coconut.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * An input field which collects data from the user and
 * contains regex and error messages for validating the input.
 * <p>
 * It must be wrapper in {@link CoconutView} in order to work with
 * {@link io.github.allaudin.coconut.CoconutValidator}
 *
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public interface CoconutInput {

    /**
     * User input
     *
     * @return input as string
     */
    @Nullable
    String getInput();

    /**
     * Regex against which field is validated
     *
     * @return regex - field validation regex
     */
    @Nullable
    String getValidationRegex();

    /**
     * Error message for this input.
     *
     * @return error message
     */
    @Nullable
    String getErrorMessages();

    /**
     * @return true - if field is optional, false otherwise
     */
    boolean isOptional();

    /**
     * Adds text watcher to this view. This text watcher
     * must remove error by call {@link CoconutView#removeError()} from
     * {@link CoconutTextWatcher#onTextChange(CoconutView)}
     *
     * @param watcher text watcher for removing error
     */
    void setTextWatcher(@NonNull CoconutTextWatcher watcher);
}
