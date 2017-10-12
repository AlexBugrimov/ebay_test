package ru.sberbank.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegEbayPage extends Page {

    @FindBy(xpath = "//div[@id='firstname_r']/input")
    private WebElement firstname;

    @FindBy(xpath = "//div[@id='lastname_r']/input")
    private WebElement lastname;

    @FindBy(xpath = "//div[@id='email_r']/input")
    private WebElement email;

    @FindBy(xpath = "//div[@id='PASSWORD_r']/input")
    private WebElement password;

    public void fillFirstname(String firstname) {
        this.firstname.sendKeys(firstname);
    }

    public void fillLastname(String lastname) {
        this.lastname.sendKeys(lastname);
    }

    public void fillEmail(String email) {
        this.email.sendKeys(email);
    }

    public void fillPassword(String password) {
        this.password.sendKeys(password);
    }
}
