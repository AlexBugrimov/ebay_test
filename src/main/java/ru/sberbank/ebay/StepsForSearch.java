package ru.sberbank.ebay;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import ru.sberbank.infrastructure.WebDriverManager;
import ru.sberbank.pages.MailPage;
import ru.sberbank.pages.MainEbayPage;
import ru.sberbank.pages.RegEbayPage;

import java.util.concurrent.TimeUnit;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;
import static ru.sberbank.infrastructure.WebDriverManager.PROPERTIES;

public class StepsForSearch {

    private static final int NUMBER_OF_PRODUCTS_ON_THE_PAGE = 50;
    private static final String FIRSTNAME = "Иванов";
    private static final String LASTNAME = "Иван";
    private static final String PASSWORD = "qwerty456!";
    private String email;

    private final MainEbayPage mainEbayPage = new MainEbayPage();
    private final RegEbayPage regEbayPage = new RegEbayPage();
    private final MailPage mailPage = new MailPage();

    @Дано("^открыта страница почтового сервиса$")
    public void openMailPage() {
        DRIVER.navigate().to(PROPERTIES.getProperty("mail.url"));
    }

    @Дано("^можем получить почтовый адрес$")
    public void getEmail() {
        email = mailPage.getEmail();
    }

    @Дано("^открыта главная страница ebay$")
    public void openMainPage() {
        DRIVER.navigate().to(PROPERTIES.getProperty("ebay.url"));
        Assert.assertEquals(WebDriverManager.PROPERTIES.getProperty("ebay.url"), (mainEbayPage.getPageUrl()));
    }

    @Когда("^нажимаем на ссылку \"зарегистрируйтесь\"$")
    public void clickOnRegisterLink() {
        mainEbayPage.clickOnRegisterLink();
    }

    @Тогда("^переходим на страницу регистрации$")
    public void openRegPage() {
        Assert.assertTrue(mainEbayPage.getPageUrl().startsWith("https://reg.ebay.com/"));
    }

    @Когда("^заполним все поля формы$")
    public void fillRegForm() throws InterruptedException {
        regEbayPage.fillRegForm(FIRSTNAME, LASTNAME, email, PASSWORD);
    }

    @Тогда("^нажимаем на кнопку \"Зарегистрироваться\"$")
    public void clickOnRegistryButton() throws InterruptedException {
        regEbayPage.clickOnRegistryButton();
    }

    @Когда("^заполняем искомый товар$")
    public void fillSearchInput() {
        DRIVER.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        mainEbayPage.fillSearchInput();
    }

    @Когда("^нажимаем кнопку \"Найти\"$")
    public void startSearching() {
        mainEbayPage.startSearching();
    }

    @Тогда("^появляются результаты поиска$")
    public void searchingResults() {
        mainEbayPage.searchingResults();
    }

    @Тогда("^кличество отображаемых товаров соответствует требуемому$")
    public void checkSize() {
        Assert.assertEquals(mainEbayPage.searchingResults().size(), NUMBER_OF_PRODUCTS_ON_THE_PAGE);
    }

    @Когда("^выходим из под своей учетной записи$")
    public void logOut() {
        mainEbayPage.logOut();
    }

    @Тогда("^можем зарегистрироваться заново$")
    public void registerLinkIsPresent() {
        mainEbayPage.registerLinkIsPresent();
    }

    @Когда(value = "^в журнале присутствует новое сообщение$", timeout = 5000)
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
        DRIVER.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }

    @Тогда("^входим без регистрации$")
    public void logIn() throws InterruptedException {
        mainEbayPage.logIn();
        regEbayPage.fillLogInForm(email, PASSWORD);
    }
}
