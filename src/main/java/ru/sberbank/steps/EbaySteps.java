package ru.sberbank.steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import ru.sberbank.pages.MainEbayPage;
import ru.sberbank.pages.RegEbayPage;

import static ru.sberbank.infrastructure.WebDriverManager.DRIVER;
import static ru.sberbank.infrastructure.WebDriverManager.PROPERTIES;

public class EbaySteps {
    private final MainEbayPage mainEbayPage = new MainEbayPage();
    private final RegEbayPage regEbayPage = new RegEbayPage();

    @Дано("^открыта главная страница ebay$")
    public void openMainPage() {
        DRIVER.navigate().to(PROPERTIES.getProperty("ebay.url"));
        Assert.assertTrue("Не удалось открыть страницу ebay",
                mainEbayPage.isLoaded());
    }

    @Тогда("^открыта страница регистрации$")
    public void openRegPage() {
        Assert.assertTrue("Не удалось осуществить переход на страницу регистрации",
                mainEbayPage.getPageUrl().startsWith("https://reg.ebay.com/"));
    }

    @Когда("^заполняем поле Имя значением \"([^\"]*)\"$")
    public void fillFirstname(String firstname) {
        regEbayPage.fillFirstname(firstname);
    }

    @И("^заполняем поле Фамилия значением \"([^\"]*)\"$")
    public void fillLastname (String lastname) {
        regEbayPage.fillLastname(lastname);
    }

    @И("заполняем поле Адрес эл. почты полученным ранее значением$")
    public void fillEmail () {
        regEbayPage.fillEmail(PROPERTIES.getProperty("email"));
    }

    @И("^заполняем поле Пароль значением \"([^\"]*)\"$")
    public void fillPassword (String password) {
        regEbayPage.fillPassword(password);
    }

    @Тогда("^нажимаем на кнопку \"([^\"]*)\"$")
    public void clickOnRegistryButton(String nameButton) {
        regEbayPage.clickOnButton(nameButton);
    }

    @Когда("^нажимаем на ссылку \"([^\"]*)\"$")
    public void clickOnRegisterLink(String link) {
        mainEbayPage.clickOnLink(link);
    }

    @Когда("^в строку поиска вводим искомый товар \"([^\"]*)\"$")
    public void fillSearchInput(String requestedProduct) {
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

    @Когда("^наводим курсор на поле учетной записи$")
    public void hover() {
        mainEbayPage.hover();
    }

    @Тогда("^можем зарегистрироваться заново$")
    public void registerLinkIsPresent() {
        mainEbayPage.registerLinkIsPresent();
    }
}
