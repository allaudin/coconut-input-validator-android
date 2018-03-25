package io.github.allaudin.coconut.widget;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public interface CoconutInput {
    @Nullable
    String getInput();

    @Nullable
    String getValidationRegex();

    @Nullable
    String getErrorMessages();

    boolean isOptional();

    void setTextWatcher(@NonNull CoconutTextWatcher watcher);
}
