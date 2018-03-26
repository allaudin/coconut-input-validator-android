package io.github.allaudin.coconut.widget;

import android.support.annotation.Nullable;

/**
 * An error aware view. The {@link CoconutInput} returned by
 * this view contains all the information about input validation
 * and error messages.
 *
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public interface CoconutView {
    /**
     * Set error message on this view
     *
     * @param error - error message
     */
    void setErrorMessage(@Nullable CharSequence error);

    @Nullable
    CoconutInput getCoconutView();

    /**
     * Remove error message from the view
     */
    void removeError();

    /**
     * Set text watcher on view's {@link CoconutInput} for removing
     * errors.
     *
     * @param inputView input view
     */
    void setWatcher(@Nullable CoconutInput inputView);
}
