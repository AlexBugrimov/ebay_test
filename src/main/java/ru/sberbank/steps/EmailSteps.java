package ru.sberbank.steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import ru.sberbank.pages.MailPage;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;
import static ru.sberbank.infrastructure.WebDriverManager.PROPERTIES;

public class EmailSteps {
    private final MailPage mailPage = new MailPage();

    @Дано("^открыта страница почтового сервиса$")
    public void openEmailService() {
        DRIVER.navigate().to(PROPERTIES.getProperty("mail.url"));
    }

    @И("^получен временный почтовый адрес, сгенерированный специально для этого теста$")
    public void getEmail() {
        PROPERTIES.setProperty("email",mailPage.getEmail());
    }

    @Когда(value = "^в журнале присутствует новое сообщение$", timeout = 10000)
    public void newMessage() throws InterruptedException {
        while (!mailPage.isNewMessagePresented()) {
            mailPage.refreshButton();
            Thread.sleep(1000);
        }
    }

    @Тогда("^открываем письмо$")
    public void openNewMessage() {
        mailPage.openNewMessage();
    }

    @Тогда("^переходим по ссылке$")
    public void clickOnTheMessageLink() throws InterruptedException {
        mailPage.clickOnTheMessageLink();
    }
}

