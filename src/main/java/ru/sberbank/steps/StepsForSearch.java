package ru.sberbank.steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import ru.sberbank.infrastructure.WebDriverManager;
import ru.sberbank.pages.MailPage;
import ru.sberbank.pages.MainEbayPage;
import ru.sberbank.pages.RegEbayPage;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;
import static ru.sberbank.infrastructure.WebDriverManager.PROPERTIES;

public class StepsForSearch {
    private String email;

    private final MainEbayPage mainEbayPage = new MainEbayPage();
    private final RegEbayPage regEbayPage = new RegEbayPage();
    private final MailPage mailPage = new MailPage();

    @Дано("^открыта страница почтового сервиса$")
    public void openMailPage() {
        DRIVER.navigate().to(PROPERTIES.getProperty("mail.url"));
    }

    @Дано("^получен временный почтовый адрес, сгенерированный специально для этого теста$")
    public void getEmail() {
        email = mailPage.getEmail();
    }

    @Дано("^открыта главная страница ebay$")
    public void openMainPage() {
        DRIVER.navigate().to(PROPERTIES.getProperty("ebay.url"));
        Assert.assertEquals("Не удалось открыть страницу ebay",
                            mainEbayPage.getPageUrl(),
                            WebDriverManager.PROPERTIES.getProperty("ebay.url"));
    }

    @Когда("^нажимаем на ссылку \"([^\"]*)\"$")
    public void clickOnRegisterLink(String link) {
        mainEbayPage.clickOnLink(link);
    }

    @Тогда("^открыта страница регистрации$")
    public void openRegPage() {
        Assert.assertTrue("Не удалось осуществить переход на страницу регистрации",
                            mainEbayPage.getPageUrl().startsWith("https://reg.ebay.com/"));
    }

    @Когда("^заполняем поле Имя значеним \"([^\"]*)\"$")
    public void fillFirstname(String firstname) {
        regEbayPage.fillFirstname(firstname);
    }

    @И("^заполняем поле Фамилия значение \"([^\"]*)\"$")
    public void fillLastname (String lastname) {
        regEbayPage.fillLastname(lastname);
    }

    @И("заполняем поле Адрес эл. почты полученным ранее значением$")
    public void fillEmail () {
        regEbayPage.fillEmail(email);
    }

    @И("^заполняем поле Пароль значением \"([^\"]*)\"$")
    public void fillPassword (String password) {
        regEbayPage.fillPassword(password);
    }

    @Тогда("^нажимаем на кнопку \"([^\"]*)\"$")
    public void clickOnRegistryButton(String nameButton) {
        regEbayPage.clickOnButton(nameButton);
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
        WebDriverManager.timeout();
    }

    @Когда("^в строку поиска вводим искомый товар \"([^\"]*)\"$")
    public void fillSearchInput(String requestedProduct) {
        WebDriverManager.timeout();
        mainEbayPage.fillSearchInput(requestedProduct);
    }

    @Тогда("^появляются результаты поиска$")
    public void searchingResults() {
        mainEbayPage.searchingResults();
    }

    @Тогда("^количество отображаемых товаров соответствует (\\d+)$")
    public void checkSize(int numberOfProducts) {
        Assert.assertEquals("Количество продуктов не соответствует актуальному",
                            numberOfProducts,
                            mainEbayPage.searchingResults().size());
    }

    @Когда("^выходим из под своей учетной записи$")
    public void logOut() {
        mainEbayPage.logOut();
    }

    @Тогда("^можем зарегистрироваться заново$")
    public void registerLinkIsPresent() {
        mainEbayPage.registerLinkIsPresent();
    }
}
