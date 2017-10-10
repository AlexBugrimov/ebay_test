package ru.sberbank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.sberbank.infrastructure.WebDriverManager;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;

public class MainEbayPage {
    @FindBy(xpath = "//a[.='зарегистрируйтесь']")
    private WebElement registerLink;

    @FindBy(xpath = "//input[@placeholder='Найдите любые товары']")
    private WebElement searchInput;

    @FindBy(xpath = "//input[@id=\"gh-btn\"]")
    private WebElement searchButton;

    public MainEbayPage() {
        PageFactory.initElements(WebDriverManager.DRIVER, this);
    }

    public String getPageUrl() {
        return DRIVER.getCurrentUrl();
    }

    public void clickOnRegisterLink() {
        registerLink.click();
    }

    public void fillSearchInput() {
        searchInput.sendKeys("blackberry");
    }

    public void startSearching() {
        searchButton.click();
    }
}
