package ru.sberbank.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sberbank.infrastructure.WebDriverManager;

import java.util.List;
import java.util.function.Function;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;
import static ru.sberbank.infrastructure.WebDriverManager.PROPERTIES;

public abstract class Page {

    final int TIMEOUT = Integer.valueOf(PROPERTIES.getProperty("timeout"));

    public Page() {
        PageFactory.initElements(WebDriverManager.DRIVER, this);
    }

    abstract boolean isLoaded();

    public void clickOnLink(String buttonName) {
        getElementByLinkName.apply(buttonName).click();
    }

    public void clickOnButton(String buttonName) {
        getElementByInputValue.apply(buttonName).click();
    }

    private <T> T assertThat(com.google.common.base.Function<? super WebDriver, T> condition) {
        return new WebDriverWait(DRIVER, TIMEOUT).until(condition);
    }

    Function<WebElement, WebElement> explicitWaitForInput = element -> assertThat(visibilityOf(element));

    Function<List<WebElement>, List> explicitWaitForList = elements -> assertThat(ExpectedConditions.visibilityOfAllElements(elements));

    private Function<String, WebElement> getElementByLinkName =
            name -> assertThat(elementToBeClickable(By.xpath("//a[.='" + name + "']")));

    private Function<String, WebElement> getElementByInputValue =
            name -> assertThat(elementToBeClickable(By.xpath("//input[@value='" + name + "' and not(@disabled='disabled')]")));
}
