package ru.sberbank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegEbayPage extends AbstractPage {

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

    @FindBy(id = "userid")
    private WebElement userId;

    @FindBy(id = "pass")
    private WebElement userPassword;

    @FindBy(id = "sgnBt")
    private WebElement logInButton;

    public void fillRegForm(String firstname, String lastname, String email, String password) throws InterruptedException {
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
        Thread.sleep(2000);
    }

    public void clickOnRegistryButton() throws InterruptedException {
        registry.click();
    }

    public void fillLogInForm(String email, String password) throws InterruptedException {
        userId.sendKeys(email);
        userPassword.sendKeys(password);
        Thread.sleep(2000);
        logInButton.click();
    }
}
