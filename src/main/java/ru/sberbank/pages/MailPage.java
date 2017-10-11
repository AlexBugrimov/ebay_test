package ru.sberbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;

public class MailPage extends AbstractPage {

    @FindBy(xpath = "//input[@id='mail']")
    private WebElement mailForm;

    @FindBy(xpath = "//a[@id='click-to-refresh']")
    private WebElement refreshButton;

    @FindBy(xpath = "//a[.='Добро пожаловать на eBay!']")
    private WebElement ebayWelcomeMessage;

    private By linkInTheMessage = By.xpath("//a[@title='Перейти к покупкам']");

    public String getEmail() {
        return mailForm.getAttribute("value");
    }

    public void refreshButton() {
        refreshButton.click();
    }

    public boolean isNewMessagePresented() {
        try {
            return ebayWelcomeMessage.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void openNewMessage() {
        ebayWelcomeMessage.click();
    }

    public void clickOnTheMessageLink() throws InterruptedException {
        WebElement element = new WebDriverWait(DRIVER, 5).until(ExpectedConditions.presenceOfElementLocated(linkInTheMessage));
        ((JavascriptExecutor) DRIVER).executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(500);
        element.click();
    }
}
