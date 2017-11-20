package com.exposit.carsharing.configuration;

public class Constants {
    public static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String PASSWORD_REGEX = "^((?=.*\\d)(?=.*[A-z]).{6,60})$";
    public static final String PROPER_NOUN_REGEX = "^[A-z'-]{2,30}$";
    public static final String SERIES_AND_NUMBER_REGEX = "[A-Z]{2}[0-9]{7}";
    public static final String PERSONAL_NUMBER_REGEX = "[A-Z0-9]{14}";
}
