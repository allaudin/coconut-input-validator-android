package io.github.allaudin.coconut;

import android.content.Context;
import android.support.annotation.StringRes;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;

import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Muhammad allaudin
 *         Created on 2018-03-25.
 */

@RunWith(RobolectricTestRunner.class)
public class PatternsTest {

    private Context context;

    @Before
    public void setUp() {
        context = RuntimeEnvironment.application.getApplicationContext();
    }

    @Test
    public void verifyOnlyDigitsTrue() throws Exception {
        assertTrue("Digits pattern returns false for digits",
                getPattern(R.string.cnt_regex_digits_only).matcher("1234").matches());
    }

    @Test
    public void verifyOnlyDigitsFalse() throws Exception {
        assertFalse("Digits pattern returns true for string value.",
                getPattern(R.string.cnt_regex_digits_only).matcher("foo").matches());
    }

    @Test
    public void verifyNonEmptyFalse() throws Exception {
        String message = "Non empty pattern returns true for empty value";
        Pattern pattern = getPattern(R.string.cnt_regex_non_empty);
        assertFalse(message, pattern.matcher("       \n").matches());
        assertFalse(message, pattern.matcher("       ").matches());
        assertFalse(message, pattern.matcher("").matches());
    }


    @Test
    public void verifyNonEmptyTrue() throws Exception {
        String message = "Non empty pattern returns false for non-empty value";
        Pattern pattern = getPattern(R.string.cnt_regex_non_empty);
        assertTrue(message, pattern.matcher("value").matches());
        assertTrue(message, pattern.matcher("1345923&*^%&^").matches());
    }

    @Test
    public void verifyEmailAddressFalse() throws Exception {
        String message = "Email address pattern returns true for invalid email.";
        Pattern pattern = getPattern(R.string.cnt_regex_email_address);
        assertFalse(message, pattern.matcher("ali.com").matches());
        assertFalse(message, pattern.matcher("@gmail.com").matches());
        assertFalse(message, pattern.matcher("foo@bar").matches());
    }

    @Test
    public void verifyEmailAddressTrue() throws Exception {
        String message = "Email address pattern returns false for valid email.";
        Pattern pattern = getPattern(R.string.cnt_regex_email_address);
        assertTrue(message, pattern.matcher("foo@bar.com").matches());
    }

    private Pattern getPattern(@StringRes int id) {
        return Pattern.compile(context.getString(id));
    }

} // PatternsTest
