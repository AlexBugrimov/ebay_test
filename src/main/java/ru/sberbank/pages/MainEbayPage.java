package ru.sberbank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;

public class MainEbayPage extends AbstractPage {
    private static final String REQUESTED_PRODUCT = "blackberry";

    @FindBy(xpath = "//a[.='зарегистрируйтесь']")
    private WebElement registerLink;

    @FindBy(xpath = "//input[@placeholder='Найдите любые товары']")
    private WebElement searchInput;

    @FindBy(xpath = "//ul[@id='ListViewInner']/li")
    private List<WebElement> searchingResults;

    @FindBy(xpath = "//input[@id='gh-btn']")
    private WebElement searchButton;

    @FindBy(xpath = "//a[@id='gh-ug']")
    private WebElement userInformation;

    @FindBy(xpath = "//a[.='Выход']")
    private WebElement logOutButton;

    @FindBy(xpath = "//a[.='Войдите']")
    private WebElement logIn;

    public String getPageUrl() {
        return DRIVER.getCurrentUrl();
    }

    public void clickOnRegisterLink() {
        registerLink.click();
    }

    public void fillSearchInput() {
        DRIVER.switchTo().defaultContent();
        explicitWaitForInput.apply(searchInput).sendKeys(REQUESTED_PRODUCT);
    }

    public void startSearching() {
        explicitWaitForButton.apply(searchButton).click();
    }

    public List searchingResults() {
        return explicitWaitForList.apply(searchingResults);
    }

    public void logOut() {
        Actions actions = new Actions(DRIVER);
        explicitWaitForInput.apply(userInformation);
        actions.moveToElement(userInformation).build().perform();
        explicitWaitForInput.apply(logOutButton).click();
    }

    public void registerLinkIsPresent() {
        registerLink.isDisplayed();
    }

    public void logIn() {
        logIn.click();
    }
}
