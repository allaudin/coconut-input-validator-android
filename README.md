
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
              android:hint="@string/mandatory_field"
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

4. Commonly used regex patterns are available from the library strings starting with `cnt` e.g.
`R.string.cnt_non_empty` for a field which should not be empty.

    ```xml
    app:cnt_et_validation_regex="@string/cnt_non_empty"
    ```

## Download [ ![Download](https://api.bintray.com/packages/mallaudin/android/coconutvalidator/images/download.svg) ](https://bintray.com/mallaudin/android/coconutvalidator/_latestVersion)

1. Add following URL to root project repositories.

```xml
maven {
            url "https://dl.bintray.com/mallaudin/android"
        }
```

2. Declare dependency in build.gradle file
```xml
implementation 'io.github.allaudin:coconutvalidator:latest-version@aar'
```

## To Do List
 - Create error aware spinner view


License
-------

    Copyright 2018 M.Allaudin

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

Made with :heart: by Allaudin