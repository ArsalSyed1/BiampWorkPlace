
package com.framework.playwright.utilities;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

public class waitUtils {
    public static final int DEFAULT_TIMEOUT_MS = 5000;
    private static final int POLL_INTERVAL_MS = 100;

    // Wait until selector is visible; returns the Locator for further actions.
    public static Locator waitForVisible(Page page, String selector) {
        return waitForVisible(page, selector, DEFAULT_TIMEOUT_MS);
    }

    public static Locator waitForVisible(Page page, String selector, int timeoutMs) {
        Locator locator = page.locator(selector);
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout((double) timeoutMs));
        return locator;
    }

    // Wait until selector is visible AND enabled (clickable). Polls until timeout.
    public static Locator waitForClickable(Page page, String selector) {
        return waitForClickable(page, selector, DEFAULT_TIMEOUT_MS);
    }

    public static Locator waitForClickable(Page page, String selector, int timeoutMs) {
        long deadline = System.currentTimeMillis() + timeoutMs;
        Locator locator = waitForVisible(page, selector, timeoutMs);
        while (System.currentTimeMillis() <= deadline) {
            try {
                if (locator.isVisible() && locator.isEnabled()) {
                    return locator;
                }
            } catch (Exception ignored) {
                // locator.state may change between checks; continue polling
            }
            sleepMs(POLL_INTERVAL_MS);
        }
        throw new RuntimeException("Timed out waiting for clickable: " + selector);
    }

    // Wait for a visible element that contains the given text.
    public static Locator waitForText(Page page, String text) {
        return waitForText(page, text, DEFAULT_TIMEOUT_MS);
    }

    public static Locator waitForText(Page page, String text, int timeoutMs) {
        Locator locator = page.getByText(text);
        locator.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout((double) timeoutMs));
        return locator;
    }

    // Wait for the page to reach a specified load state.
    public static void waitForLoadState(Page page, LoadState state) {
        waitForLoadState(page, state, DEFAULT_TIMEOUT_MS);
    }

    public static void waitForLoadState(Page page, LoadState state, int timeoutMs) {
        page.waitForLoadState(state, new Page.WaitForLoadStateOptions().setTimeout((double) timeoutMs));
    }

    // Small helper to sleep without throwing checked exception upstream.
    public static void sleepMs(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ignored) {
            Thread.currentThread().interrupt();
        }
    }
}