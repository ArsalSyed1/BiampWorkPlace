package biamp.framework.biampworkplace.base;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.nio.file.Paths;

public class basePage {

    protected final Page page;
    // Constructor
    public basePage(Page page) {
        this.page = page;
    }


    public void navigateTo(String url) {

        page.navigate(url);
    }

    public void click(Locator locator) {

        locator.click();
    }

    public void type(Locator locator, String text) {

        locator.fill(text);
    }

    public String getText(Locator locator) {

        return locator.innerText();
    }

    public boolean isVisible(Locator locator) {

        return locator.isVisible();
    }

    public void hover(Locator locator) {

        locator.hover();
    }

    public void doubleClick(Locator locator) {

        locator.dblclick();
    }

    public void selectOption(Locator locator, String value) {

        locator.selectOption(value);
    }

    public boolean containsText(Locator locator, String text) {
        return locator.innerText().contains(text);
    }

    public void clearField(Locator locator) {
        locator.fill("");
    }

    // Method to upload a file
    public void uploadFile(Locator locator, String filePath) {
        locator.setInputFiles(Paths.get(filePath));
    }

    // Method to download a file
    public void downloadFile(String downloadUrl) {
        Download download = page.waitForDownload(() -> {
            page.click("#downloadBtn");
        });
        download.saveAs(Paths.get("downloads/report.pdf"));
    }


}
