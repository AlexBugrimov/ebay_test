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
    private static final String FIRST_NAME = "Иванов";
    private static final String LAST_NAME = "Иван";
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
        Assert.assertEquals("Не удалось открыть страницу ebay",
                            mainEbayPage.getPageUrl(),
                            WebDriverManager.PROPERTIES.getProperty("ebay.url"));
    }

    @Когда("^нажимаем на ссылку \"зарегистрируйтесь\"$")
    public void clickOnRegisterLink() {
        mainEbayPage.clickOnRegisterLink();
    }

    @Тогда("^переходим на страницу регистрации$")
    public void openRegPage() {
        Assert.assertTrue("Не удалось осуществить переход на страницу регистрации",
                            mainEbayPage.getPageUrl().startsWith("https://reg.ebay.com/"));
    }

    @Когда("^заполним все поля формы$")
    public void fillRegForm() {
        regEbayPage.fillRegForm(FIRST_NAME, LAST_NAME, email, PASSWORD);
    }

    @Тогда("^нажимаем на кнопку \"Зарегистрироваться\"$")
    public void clickOnRegistryButton() {
        regEbayPage.clickOnRegistryButton();
    }

    @Когда("^заполняем искомый товар$")
    public void fillSearchInput() {
        DRIVER.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
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
        Assert.assertEquals("Количество продуктов не соответствует актуальному",
                            NUMBER_OF_PRODUCTS_ON_THE_PAGE,
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
        DRIVER.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
    }
}
