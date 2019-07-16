package com.aquent.crudapp.constants;

/**
 * Constants class containing Regular Expressions
 * used elsewhere in the application.
 *
 * Keeping a repository here instead of scattered
 * throughout the application.
 */
public class RegEx {
    public static final String ZIP_CODE = "^[0-9]{5}(?:-?[0-9]{4})?$";

    public static final String EMAIL_ADDR = "^.+\\@.+\\..+$";

    public static final String WEB_URL =
            "^https?:\\/\\/(www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{2,256}\\.[a-z]{2,4}\\b([-a-zA-Z0-9@:%_\\+.~#?&//=]*)$";

    public static final String PHONE_NUMBER = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
}
