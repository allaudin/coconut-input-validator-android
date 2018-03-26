
# Coconut Input Validator for Android [![Build Status](https://travis-ci.org/allaudin/coconut-input-validator.svg?branch=master)](https://travis-ci.org/allaudin/coconut-input-validator)

Coconut Validator is **Input data validator** for Android.

## Why Coconut Validator?

*Coconut Input Validator* moves your data validation logic from `Java code` to `xml`. By this,
you are able to define `validation regex` and `error messages` directly on `view definition`.
Later on, these views or their parent view can be passed to `CoconutValidator` which validates
 the input and shows error messages for incorrect input. Error messages are removed when
 an input is updated after error.

 ## Usage