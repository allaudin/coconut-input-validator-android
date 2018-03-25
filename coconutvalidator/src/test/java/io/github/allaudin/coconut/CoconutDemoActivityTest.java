package io.github.allaudin.coconut;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import io.github.allaudin.coconut.widget.CoconutEditText;
import io.github.allaudin.coconut.widget.CoconutInputEditText;

import static junit.framework.Assert.assertEquals;

/**
 * @author Muhammad allaudin
 *         Created on 2018-03-25.
 */
@RunWith(RobolectricTestRunner.class)
public class CoconutDemoActivityTest {

    private CoconutDemoActivity activity;

    @Before
    public void setUp() {
        activity = Robolectric.setupActivity(CoconutDemoActivity.class);
    }

    @Test
    public void verifyErrorMessages() {
        CoconutInputEditText emailView = activity.findViewById(R.id.email);
        CoconutEditText mandatoryField = activity.findViewById(R.id.mandatory_field);
        assertEquals(activity.getString(R.string.email_error),
                emailView.getErrorMessage());
        assertEquals(activity.getString(R.string.mandatory_field_error),
                mandatoryField.getErrorMessage());

    } // testDemo

} // CoconutDemoActivityTest
