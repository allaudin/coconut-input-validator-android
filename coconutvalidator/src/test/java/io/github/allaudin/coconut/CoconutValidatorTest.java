package io.github.allaudin.coconut;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.github.allaudin.coconut.widget.CoconutInput;
import io.github.allaudin.coconut.widget.CoconutView;

import static org.mockito.Mockito.when;

/**
 * @author Muhammad allaudin
 *         Created on 2018-03-24.
 */

@RunWith(MockitoJUnitRunner.class)
public class CoconutValidatorTest {

    @Mock
    private CoconutView coconutView;
    @Mock
    private CoconutInput coconutInput;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTrueForNullInputView() {
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Assert.assertTrue(condition);
    }

    @Test
    public void testTrueForAllOptionalFields() {
        when(coconutView.getCoconutView()).thenReturn(coconutInput);
        when(coconutInput.isOptional()).thenReturn(true);
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Assert.assertTrue(condition);
    }

    @Test
    public void testFalseForNonOptionalNullInput() {
        when(coconutView.getCoconutView()).thenReturn(coconutInput);
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Assert.assertFalse(condition);
    }

    @Test
    public void testTrueForNonOptionalNullRegexAndNullErrorMessage() {
        when(coconutView.getCoconutView()).thenReturn(coconutInput);
        when(coconutInput.getInput()).thenReturn("foo");
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Assert.assertTrue(condition);
    }

    @Test
    public void testTrueForNonOptionalNullRegex() {
        when(coconutView.getCoconutView()).thenReturn(coconutInput);
        when(coconutInput.getInput()).thenReturn("foo");
        when(coconutInput.getErrorMessages()).thenReturn("bar");
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Assert.assertTrue(condition);
    }

    @Test
    public void testTrueForNonOptionalNullErrorMessage() {
        when(coconutView.getCoconutView()).thenReturn(coconutInput);
        when(coconutInput.getInput()).thenReturn("foo");
        when(coconutInput.getValidationRegex()).thenReturn("bar");
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Assert.assertTrue(condition);
    }

    @Test
    public void testTrueForMatchedPattern() {
        when(coconutView.getCoconutView()).thenReturn(coconutInput);
        when(coconutInput.getInput()).thenReturn("1234");
        when(coconutInput.getValidationRegex()).thenReturn("[0-9]+");
        when(coconutInput.getErrorMessages()).thenReturn("only numbers");
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Assert.assertTrue(condition);
    }

    @Test
    public void testFalseForInvalidMatch() {
        when(coconutView.getCoconutView()).thenReturn(coconutInput);
        when(coconutInput.getInput()).thenReturn("string");
        when(coconutInput.getValidationRegex()).thenReturn("[0-9]+");
        when(coconutInput.getErrorMessages()).thenReturn("only numbers");
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Assert.assertFalse(condition);
    }

    @Test
    public void testErrorMessageIsSetForInvalidMatch() {
        String errorMessage = "only numbers";
        when(coconutView.getCoconutView()).thenReturn(coconutInput);
        when(coconutInput.getInput()).thenReturn("invalid");
        when(coconutInput.getValidationRegex()).thenReturn("[0-9]+");
        when(coconutInput.getErrorMessages()).thenReturn(errorMessage);
        boolean condition = CoconutValidator.areFieldsValid(coconutView);
        Mockito.verify(coconutView).setErrorMessage(errorMessage);
        Assert.assertFalse(condition);
    }
} // CoconutValidatorTest
