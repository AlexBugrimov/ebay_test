# language: ru
Функция: Поиск товара
    Сценарий: Регистрация на сайте и поиск искомого товара
        Дано открыта страница почтового сервиса
        И можем получить почтовый адрес

        Дано открыта главная страница ebay
        Когда нажимаем на ссылку "зарегистрируйтесь"
        Тогда переходим на страницу регистрации
        Когда заполним все поля формы
        Тогда нажимаем на кнопку "Зарегистрироваться"

        Дано открыта страница почтового сервиса
        Когда в журнале присутствует новое сообщение
        И открываем письмо
        И переходим по ссылке

        Когда заполняем искомый товар
        И нажимаем кнопку "Найти"
        Тогда появляются результаты поиска
        И кличество отображаемых товаров соответствует требуемому
        Когда выходим из под своей учетной записи
        Тогда можем зарегистрироваться заново