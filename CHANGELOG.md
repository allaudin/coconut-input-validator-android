Change Log
==========

Version 1.0.0 *(2018-03-27)*
------------------------------

* Initial release.

Version 1.0.1 *(2018-05-18)*
------------------------------

* Downgraded minimum SDK version from 17 to 15
* Deprecated `areFieldsValidRecursive(ViewGroup)` method in favor of `validateLayout(View)`
* Updated dependencies to latest version

Version 1.0.2 *(2018-07-01)*
----------------------------

* Prevent `null` crash from `Pattern Matcher` when input is `null`
* Other minor code and doc improvements

Version 1.1.0 *(2018-07-12)*
----------------------------

* Added new layout (CoconutErrorAwareEt) for nicely showing errors below EditText

Version 1.1.1 *(2018-07-14)*
----------------------------

* `validateLayout(View parent)` methods is now deprecated. Use `validateLayout(ViewGroup parent)` instead.

Version 1.1.2 *(2018-08-19)*
----------------------------

* Fix 'style' bug in `CoconutErrorAwareEt` which prevented `EditText` from picking `style`attributes from current theme.

Version 1.1.3 *(2018-09-15)*
----------------------------

* Add necessary `getter/setter` methods for `EditText` in `CoconutErrorAwareEt`