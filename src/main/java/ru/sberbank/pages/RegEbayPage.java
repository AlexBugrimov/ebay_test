package ru.sberbank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.sberbank.infrastructure.WebDriverManager;
public class RegEbayPage {
    @FindBy(xpath = "//div[@id='firstname_r']/input")
    private WebElement firstname;

    @FindBy(xpath = "//div[@id='lastname_r']/input")
    private WebElement lastname;

    @FindBy(xpath = "//div[@id='email_r']/input")
    private WebElement email;

    @FindBy(xpath = "//div[@id='PASSWORD_r']/input")
    private WebElement password;

    @FindBy(id = "ppaFormSbtBtn")
    private WebElement registry;

    public RegEbayPage() {
        PageFactory.initElements(WebDriverManager.DRIVER, this);
    }

    public void fillRegForm() throws InterruptedException {
        firstname.sendKeys("Иванов");
        lastname.sendKeys("Иван");
        email.sendKeys("112@111.ru");
        password.sendKeys("qwerty456!");
        Thread.sleep(2000);
    }

    public void clickOnRegistryButton() throws InterruptedException {
        registry.click();

    }
}
