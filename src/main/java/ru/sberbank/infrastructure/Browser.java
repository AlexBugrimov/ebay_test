package ru.sberbank.infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public enum Browser {

    CHROME, IE;

    public WebDriver createDriver() {
        switch (this) {
            case CHROME:
                return new ChromeDriver();
            case IE:
                return new InternetExplorerDriver();
            default:
                throw new IllegalStateException();
        }
    }
}
