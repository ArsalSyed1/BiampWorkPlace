package biamp.framework.biampworkplace.utilities;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class actionWrapper {

    // Need to make all our methods static to be used without creating an object in other classes


    public static void navigateTo(Page page, String url) {
        page.navigate(url);
    }

    public static void click(Locator locator) {

        locator.click();
    }

    public static void type(Locator locator, String text) {

        locator.fill(text);
    }

    public static String getText(Locator locator) {

        return locator.innerText();
    }

    public static boolean isVisible(Locator locator) {

        return locator.isVisible();
    }

    public static void hover(Locator locator) {

        locator.hover();
    }

    public void doubleClick(Locator locator) {

        locator.dblclick();
    }

    public static void selectOption(Locator locator, String value) {

        locator.selectOption(value);
    }

    public static boolean containsText(Locator locator, String text) {
        return locator.innerText().contains(text);
    }

    public static void clearField(Locator locator) {
        locator.fill("");
    }

    // Method to upload a file
    public static void uploadFile(Locator locator, String filePath) {
        locator.setInputFiles(Paths.get(filePath));
    }

    // Method to download a file
    public static void downloadFile(Page page, String downloadUrl) {
        Download download = page.waitForDownload(() -> {
            page.click("#downloadBtn");
        });
        download.saveAs(Paths.get("downloads/report.pdf"));
    }
}
