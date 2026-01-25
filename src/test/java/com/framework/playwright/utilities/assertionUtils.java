package com.framework.playwright.utilities;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class assertionUtils {

    public static void assertVisible(Locator locator) {
        assertTrue(locator.isVisible(),
                "Expected element to be visible");
    }

    public static void assertText(Locator locator, String expected) {
        Assertions.assertEquals(expected, locator.textContent());
    }

    public static void assertEquals(int actual, int expected) {
        Assertions.assertEquals(expected, actual);

    }

}
