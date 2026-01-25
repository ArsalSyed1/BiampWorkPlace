package com.framework.playwright.pages;

import com.framework.playwright.base.BasePage;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class loginPage extends BasePage {

    private Locator signInCTA;
    private Locator username;
    private Locator userSubmit;
    private Locator password;
    private Locator loginButton;

    public loginPage(Page page) {
        super(page);
        this.signInCTA= page.getByText("Sign in or Register");
        this.username= page.locator("#loginName");
        this.userSubmit= page.locator("#submit-button");
        this.password=  page.getByPlaceholder("Password");
        this.loginButton= page.locator("#idSIButton9");
    }

    public void login(String user, String pass) {
    signInCTA.click();
    username.fill(user);
    userSubmit.click();
    password.fill(pass);
    loginButton.click();
    }
}
