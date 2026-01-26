package com.framework.playwright.utilities;

public class config_environmentUtils {

    public static final String BASE_URL =
            System.getProperty("baseUrl", "https://test.app.com");

    public static final boolean HEADLESS =
            Boolean.parseBoolean(System.getProperty("headless", "true"));

}
