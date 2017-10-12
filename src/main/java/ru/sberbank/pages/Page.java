package ru.sberbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sberbank.infrastructure.WebDriverManager;

import java.util.List;
import java.util.function.Function;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;
import static ru.sberbank.infrastructure.WebDriverManager.PROPERTIES;

public class Page {

    final int TIMEOUT = Integer.valueOf(PROPERTIES.getProperty("timeout"));

    public Page() {
        PageFactory.initElements(WebDriverManager.DRIVER, this);
    }

    public void clickOnLink (String buttonName) {
        getElementByLinkName.apply(buttonName).click();
    }

    public void clickOnButton (String buttonName) {
        getElementByInputValue.apply(buttonName).click();
    }

    Function<WebElement, WebElement> explicitWaitForButton =
            element -> new WebDriverWait(DRIVER, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));

    Function<WebElement, WebElement> explicitWaitForInput =
            element -> new WebDriverWait(DRIVER, TIMEOUT).until(ExpectedConditions.visibilityOf(element));

    Function<List<WebElement>, List> explicitWaitForList =
            elements -> new WebDriverWait(DRIVER, TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(elements));

    Function<String, WebElement> getElementByLinkName =
            name -> new WebDriverWait(DRIVER, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath("//a[.='" + name + "']")));

    Function<String, WebElement> getElementByInputValue =
            name -> new WebDriverWait(DRIVER, TIMEOUT).until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@value='" + name + "' and not(@disabled='disabled')]")));
}
