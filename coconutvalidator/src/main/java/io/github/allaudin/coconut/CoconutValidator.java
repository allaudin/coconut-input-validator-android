package io.github.allaudin.coconut;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import io.github.allaudin.coconut.widget.CoconutInput;
import io.github.allaudin.coconut.widget.CoconutView;

/**
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

public final class CoconutValidator {

    private CoconutValidator() {
        throw new AssertionError("Can't instantiate " + CoconutValidator.class);
    }

    public static boolean areFieldsValid(CoconutView... views) {
        boolean ret = true;
        for (CoconutView view : views) {
            CoconutInput inputView = view.getCoconutView();
            if (inputView == null || inputView.isOptional()) {
                continue;
            }

            if (!inputView.isOptional() && inputView.getInput() == null) {
                ret = false;
                continue;
            }
            String regex = inputView.getValidationRegex();
            String errorMessage = inputView.getErrorMessages();
            if (regex != null && errorMessage != null) {
                boolean patternMatches = Pattern.compile(regex).matcher(inputView.getInput())
                        .matches();
                if (!patternMatches) {
                    ret = false;
                    view.setErrorMessage(errorMessage);
                }
            }
        }
        return ret;
    }  // areFieldsValid

    public static boolean areFieldsValidRecursive(ViewGroup parent) {
        List<CoconutView> views = new ArrayList<>();
        getViews(parent, views);
        return areFieldsValid(views.toArray(new CoconutView[]{}));
    }

    private static void getViews(ViewGroup root, List<CoconutView> viewList) {
        for (int i = 0, len = root.getChildCount(); i < len; i++) {
            View view = root.getChildAt(i);
            if (view instanceof CoconutView) {
                viewList.add((CoconutView) view);
            } else if (view instanceof ViewGroup) {
                getViews((ViewGroup) view, viewList);
            }
        }
    } // getViews


} // CoconutValidator
