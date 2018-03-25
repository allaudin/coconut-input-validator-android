package io.github.allaudin.coconut.widget;

import android.support.annotation.Nullable;

/**
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public interface CoconutView {
    void setErrorMessage(@Nullable CharSequence error);

    @Nullable
    CoconutInput getCoconutView();

    void removeError();

    void setWatcher(@Nullable CoconutInput inputView);
}
