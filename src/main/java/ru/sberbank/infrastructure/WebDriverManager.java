package ru.sberbank.infrastructure;

import org.openqa.selenium.WebDriver;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


public class WebDriverManager {

    public static final Properties PROPERTIES = new Properties();
    public static final WebDriver DRIVER;

    public static void timeout() {
        DRIVER.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
    }

    static {
        try {
            System.setProperty("webdriver.chrome.driver", ".\\drivers\\chromedriver.exe");
            System.setProperty("webdriver.ie.driver", ".\\drivers\\IEDriverServer32.exe");

            PROPERTIES.load(WebDriverManager.class.getResourceAsStream("/config.properties"));
            DRIVER = Browser.valueOf(PROPERTIES.getProperty("browser").toUpperCase()).createDriver();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}