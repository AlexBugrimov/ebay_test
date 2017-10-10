package ru.sberbank.ebay;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;
import cucumber.api.java.ru.Тогда;
import org.junit.Assert;
import ru.sberbank.infrastructure.WebDriverManager;
import ru.sberbank.pages.MainEbayPage;
import ru.sberbank.pages.RegEbayPage;

public class StepsForSearch {
    final MainEbayPage mainEbayPage = new MainEbayPage();
    final RegEbayPage regEbayPage = new RegEbayPage();

    @Дано("^открыта главная страница ebay$")
    @Тогда("^открылась главная страница ebay$")
    public void openMainPage() {
//        WebDriverManager.DRIVER.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        Assert.assertEquals(WebDriverManager.PROPERTIES.getProperty("ebay.url"),(mainEbayPage.getPageUrl()));
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
        regEbayPage.fillRegForm();
    }

    @Тогда("^нажимаем на кнопку \"Зарегистрироваться\"$")
    public void clickOnRegistryButton() throws InterruptedException {
        regEbayPage.clickOnRegistryButton();
    }
    @Когда("^заполняем искомый товар$")
    public void fillSearchInput() {
        mainEbayPage.fillSearchInput();
    }

    @Когда("^нажимаем кнопку \"Найти\"$")
    public void startSearching() {
        mainEbayPage.startSearching();
    }

//    @Тогда("^появляются результаты поиска$")
//    public void clickOnRegistryButton() {
//        mainEbayPage;
//    }
}
