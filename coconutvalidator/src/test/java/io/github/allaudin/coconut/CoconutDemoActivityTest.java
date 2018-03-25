package io.github.allaudin.coconut;

import android.widget.Button;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import io.github.allaudin.coconut.widget.CoconutEditText;
import io.github.allaudin.coconut.widget.CoconutInputEditText;
import io.github.allaudin.coconut.widget.CoconutTextInputLayout;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * @author Muhammad allaudin
 *         Created on 2018-03-25.
 */
@RunWith(RobolectricTestRunner.class)
public class CoconutDemoActivityTest {

    private CoconutDemoActivity activity;
    private CoconutInputEditText emailView;
    private CoconutInputEditText passwordView;
    private CoconutTextInputLayout emailLayout;
    private CoconutTextInputLayout passwordLayout;
    private CoconutEditText optionalField;
    private CoconutEditText mandatoryField;
    private Button submit;


    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(CoconutDemoActivity.class);
        emailView = activity.findViewById(R.id.email);
        emailLayout = activity.findViewById(R.id.email_layout);
        passwordView = activity.findViewById(R.id.password);
        passwordLayout = activity.findViewById(R.id.password_layout);
        mandatoryField = activity.findViewById(R.id.mandatory_field);
        optionalField = activity.findViewById(R.id.optional_field);
        submit = activity.findViewById(R.id.submit);
    }

    @Test
    public void verifyErrorMessages() {
        assertEquals(activity.getString(R.string.email_error),
                emailView.getErrorMessage());
        assertEquals(activity.getString(R.string.mandatory_field_error),
                mandatoryField.getErrorMessage());
    }

    @Test
    public void verifyRegexPatterns() {
        assertEquals(activity.getString(R.string.cnt_email_address),
                emailView.getValidationRegex());
        assertEquals(activity.getString(R.string.cnt_digits_only),
                mandatoryField.getValidationRegex());
    }

    @Test
    public void verifyErrorAreBingShownForInvalidInput() {
        submit.performClick();
        assertNotNull(emailLayout.getError());
        assertNotNull(passwordLayout.getError());
        assertNotNull(mandatoryField.getError());
        assertNull(optionalField.getError());
    }

    @Test
    public void verifyErrorMessageRemovedAfterTextChange() {
        submit.performClick();
        assertNotNull(passwordLayout.getError());
        passwordView.setText("12A");
        assertNull(passwordLayout.getError());
    }

    @Test
    public void verifyErrorMessageRemovedAfterValidInput() {
        submit.performClick();
        assertNotNull(passwordLayout.getError());
        passwordView.setText("12A");
        assertNull(passwordLayout.getError());
        passwordView.setText("1234RTT");
        submit.performClick();
        assertNull(passwordLayout.getError());
    }

    @Test
    public void verifyNoErrorMessageForOptionalField() {
        submit.performClick();
        assertNull(optionalField.getError());
        optionalField.setText("foo");
        submit.performClick();
    }

    @Test
    public void verifyAllFieldsShowRespectiveError() {
        submit.performClick();
        assertNotNull(emailLayout.getError());
        assertNotNull(passwordLayout.getError());
        assertNotNull(mandatoryField.getError());
        assertNull(optionalField.getError());
    }

    @Test
    public void verifyAllFieldsDonNotShowErrorForValidInput() {
        emailView.setText("foo@bar.com");
        mandatoryField.setText("123");
        passwordView.setText("1234AAA");
        assertNull(emailLayout.getError());
        assertNull(passwordLayout.getError());
        assertNull(mandatoryField.getError());
        assertNull(optionalField.getError());
        submit.performClick();
    }

} // CoconutDemoActivityTest
