package ru.sberbank.pages;

import org.openqa.selenium.support.PageFactory;
import ru.sberbank.infrastructure.WebDriverManager;

public abstract class AbstractPage {

    public AbstractPage() {
        PageFactory.initElements(WebDriverManager.DRIVER, this);
    }
}
