package biamp.framework.biampworkplace.utilities;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;

import java.nio.file.Path;
import java.nio.file.Paths;

public class sessionUtilities {

    private static final Path STORAGE_STATE_PATH = Paths.get("src/test/java/biamp/framework/biampworkplace/artifacts/auth.json");

    public static void saveStorageState(BrowserContext context) {
        context.storageState(
                new BrowserContext.StorageStateOptions()
                        .setPath(STORAGE_STATE_PATH));
    }

    public static BrowserContext reuseSession(Browser browser) {
        return browser.newContext(
                new Browser.NewContextOptions()
                        .setStorageStatePath(STORAGE_STATE_PATH));
    }
}
