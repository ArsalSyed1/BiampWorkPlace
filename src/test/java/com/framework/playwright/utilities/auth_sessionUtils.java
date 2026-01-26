package com.framework.playwright.utilities;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;

import java.nio.file.Paths;

public class auth_sessionUtils {

    public static void saveStorageState(BrowserContext context) {
        context.storageState(
                new BrowserContext.StorageStateOptions()
                        .setPath(Paths.get("com/framework/playwright/artifacts/auth.json")));
    }

    public static BrowserContext reuseSession(Browser browser) {
        return browser.newContext(
                new Browser.NewContextOptions()
                        .setStorageStatePath(Paths.get("com/framework/playwright/artifacts/auth.json")));
    }

}
