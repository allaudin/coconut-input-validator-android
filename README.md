
# Coconut Input Validator for Android [![Build Status](https://travis-ci.org/allaudin/coconut-input-validator.svg?branch=master)](https://travis-ci.org/allaudin/coconut-input-validator)

Coconut Validator is **Input data validator** for Android.

## Why Coconut Validator?

*Coconut Input Validator* moves your data validation logic from `Java code` to `xml`. By this,
you are able to define `validation regex` and `error messages` directly on `view definition`.
Later on, these views or their parent view can be passed to `CoconutValidator` which validates
 the input and shows error messages for incorrect input. Error messages are removed when
 an input is updated after error.

## Usage

1. Get input from user by using `CoconutEditText` or `CoconutTextInputLayout`

    ```xml
          <io.github.allaudin.coconut.widget.CoconutEditText
              android:id="@+id/mandatory_field"
              android:layout_width="0dp"
              android:layout_height="wrap_content"
              android:layout_marginEnd="8dp"
              android:layout_marginStart="8dp"
              android:layout_marginTop="8dp"
              android:hint="@string/mandatory_field_error"
              android:singleLine="true"
              app:layout_constraintEnd_toEndOf="parent"
              app:layout_constraintStart_toStartOf="parent"
              app:layout_constraintTop_toBottomOf="@+id/optional_field" />
    ```

2. Set validation regex and error message on the view in `xml` using
`error message` and `validation regex` properties.

    ```xml
   app:cnt_et_error_message="bar"
   app:cnt_et_validation_regex="foo"
    ```
3. Pass the `root view group` to `CoconutValidator.areFieldsValidRecursive` for validating inputs.

    ```java
       CoconutValidator.areFieldsValidRecursive(root);
    ```
