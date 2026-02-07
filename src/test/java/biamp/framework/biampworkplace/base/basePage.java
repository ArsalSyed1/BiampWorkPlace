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



}
