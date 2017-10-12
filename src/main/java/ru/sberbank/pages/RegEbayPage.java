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

    public void fillRegForm(String firstname, String lastname, String email, String password) {
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);
        this.email.sendKeys(email);
        this.password.sendKeys(password);
    }

    public void clickOnRegistryButton() {
        explicitWaitForButton.apply(registry).click();
    }
}
