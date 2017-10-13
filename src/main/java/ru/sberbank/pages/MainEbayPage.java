package ru.sberbank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;

public class MainEbayPage extends Page {
    @FindBy(xpath = "//a[.='зарегистрируйтесь']")
    private WebElement registerLink;

    @FindBy(xpath = "//input[@placeholder='Найдите любые товары']")
    private WebElement searchInput;

    @FindBy(xpath = "//ul[@id='ListViewInner']/li")
    private List<WebElement> searchingResults;

    @FindBy(xpath = "//button[@id='gh-ug']")
    private WebElement userInformation;

    public String getPageUrl() {
        return DRIVER.getCurrentUrl();
    }

    @Override
    public boolean isLoaded() {
        return searchInput.isDisplayed();
    }

    public void fillSearchInput(String requestedProduct) {
        DRIVER.switchTo().defaultContent();
        explicitWaitForInput.apply(searchInput).sendKeys(requestedProduct);
    }

    public List searchingResults() {
        return explicitWaitForList.apply(searchingResults);
    }

    public void hover() {
        new Actions(DRIVER).moveToElement(explicitWaitForInput.apply(userInformation)).build().perform();
    }

    public void registerLinkIsPresent() {
        registerLink.isDisplayed();
    }
}
