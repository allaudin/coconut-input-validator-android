
# Coconut Input Validator for Android [![Build Status](https://travis-ci.org/allaudin/coconut-input-validator.svg?branch=master)](https://travis-ci.org/allaudin/coconut-input-validator)

Coconut Validator is **Input data validator** for Android.

<img src="https://github.com/allaudin/coconut-input-validator/blob/master/coconut.gif" width="350" height="600" />

## Why Coconut Validator?

*Coconut Input Validator* moves your data validation logic from `Java code` to `XML`. By this,
you are able to **define `validation regex` and `error messages` directly on `view definition`**.
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

2. Set **validation regex** and **error message** on the view in `xml` using
`error message` and `validation regex` properties.

    ```xml
   app:cnt_et_error_message="Only Digits"
   app:cnt_et_validation_regex="[0-9]+"
    ```
3. Pass the `root view group` to `CoconutValidator.areFieldsValidRecursive` for validating inputs.

    ```java
       CoconutValidator.areFieldsValidRecursive(root);
    ```

## Validation

Once `Coconut` views are defined in the `XML`, you can pass those views directly to `CoconutValidator` utility
class which handles all the **validation of input data and displaying respective error messages**. This class
has **2 main** methods, `areFieldsValid(CoconutView... views)` & `areFieldsValidRecursive(ViewGroup parent)`. First method
accepts `CoconutViews` as arguments and validates them one by one while second method accepts `root view` which
contains `CoconutViews` as argument. It finds all the `CoconutViews` in the parent layout and validates them.

### Example

```java
// recursive
 boolean areFieldsValid = CoconutValidator.areFieldsValidRecursive(root);
 // manuall passing of views
 boolean areFieldsValid = CoconutValidator.areFieldsValid(emailView, passwordView);
```
## Commonly Used Regex Patterns

Commonly used regex patterns are available from the **library strings** starting with prefix `cnt` e.g.
`R.string.cnt_non_empty` for a field which should not be empty.

```xml
    app:cnt_et_validation_regex="@string/cnt_non_empty"
```

## Key Points

- A field is considered as `mandatory` if it has both **regex** and **error message** defined.
- Any mandatory field which does not have an **error message** or **validation regex** will be **skipped** while validating input.
- Validation of a **mandatory field** can be skipped by setting `app:cnt_input_et_optional` property to `true` on the view.

## Download [ ![Download](https://api.bintray.com/packages/mallaudin/android/coconutvalidator/images/download.svg) ](https://bintray.com/mallaudin/android/coconutvalidator/_latestVersion)

```xml
implementation 'io.github.allaudin:coconutvalidator:latest-version@aar'
```

## To Do List
 - Create error aware Spinner View


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