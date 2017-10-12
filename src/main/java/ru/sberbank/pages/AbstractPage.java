package ru.sberbank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sberbank.infrastructure.WebDriverManager;

import java.util.List;
import java.util.function.Function;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;

public abstract class AbstractPage {

    private static final int TIMEOUT = 30;

    public AbstractPage() {
        PageFactory.initElements(WebDriverManager.DRIVER, this);
    }

    Function<WebElement, WebElement> explicitWaitForButton =
            element -> new WebDriverWait(DRIVER, TIMEOUT).until(ExpectedConditions.elementToBeClickable(element));

    Function<WebElement, WebElement> explicitWaitForInput =
            element -> new WebDriverWait(DRIVER, TIMEOUT).until(ExpectedConditions.visibilityOf(element));

    Function<List<WebElement>, List> explicitWaitForList =
            elements -> new WebDriverWait(DRIVER, TIMEOUT).until(ExpectedConditions.visibilityOfAllElements(elements));
}
