package ru.sberbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;

public class MainEbayPage extends AbstractPage {
    private static final String REQUESTED_PRODUCT = "blackberry";

    @FindBy(xpath = "//a[.='зарегистрируйтесь']")
    private WebElement registerLink;

    @FindBy(xpath = "//input[@placeholder='Найдите любые товары']")
    private WebElement searchInput;

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
        searchInput.sendKeys(REQUESTED_PRODUCT);
    }

    public void startSearching() {
        searchButton.click();
    }

    public List searchingResults() {
        List<WebElement> searchResults = DRIVER.findElements(By.xpath("//ul[@id='ListViewInner']/li"));
        return searchResults;
    }

    public void logOut() {
        Actions actions = new Actions(DRIVER);
        actions.moveToElement(userInformation).build().perform();
        WebElement element = new WebDriverWait(DRIVER, 5).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[.='Выход']")));
        logOutButton.click();
    }

    public void registerLinkIsPresent() {
        registerLink.isDisplayed();
    }

    public void logIn() {
        logIn.click();
    }
}
