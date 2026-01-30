package biamp.framework.biampworkplace.utilities;

import com.microsoft.playwright.Locator;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class assertionUtilities {

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
