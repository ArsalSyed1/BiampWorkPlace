package biamp.framework.biampworkplace.base;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class basePage {

    private final Page page;
    // Constructor
    public basePage(Page page) {
        this.page = page;
    }


    public void navigateTo(String url) {
        page.navigate(url);
    }

    public void click(String selector) {
        page.locator(selector).click();
    }

    public void type(String selector, String text) {
        page.locator(selector).fill(text);
    }

    public String getText(String selector) {
        return page.locator(selector).innerText();
    }

    public boolean isVisible(String selector) {
        return page.locator(selector).isVisible();
    }

    public void hover(String selector) {
        page.locator(selector).hover();
    }

    public void doubleClick(String selector) {
        page.locator(selector).dblclick();
    }

    public void selectOption(String selector, String value) {
        page.locator(selector).selectOption(value);
    }

    public boolean containsText(String selector, String text) {
        return page.locator(selector).innerText().contains(text);
    }

    public void clearField(String selector) {
        page.locator(selector).fill("");
    }

    // Method to upload a file
    public void uploadFile(String selector, String filePath) {
        page.locator(selector).setInputFiles(Paths.get(filePath));
    }

    // Method to download a file
    public void downloadFile(String downloadUrl) {
        Download download = page.waitForDownload(() -> {
            page.click("#downloadBtn");
        });
        download.saveAs(Paths.get("downloads/report.pdf"));
    }


}
