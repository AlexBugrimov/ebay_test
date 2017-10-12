# language: ru
Функция: Поиск товара
    Сценарий: Регистрация на сайте и поиск искомого товара
        Дано открыта страница почтового сервиса
        И получен временный почтовый адрес, сгенерированный специально для этого теста

        Дано открыта главная страница ebay
        Когда нажимаем на ссылку "зарегистрируйтесь"
        Тогда открыта страница регистрации
        Когда заполняем поле Имя значеним "Иван"
        И заполняем поле Фамилия значение "Иванов"
        И заполняем поле Адрес эл. почты полученным ранее значением
        И заполняем поле Пароль значением "qwerty456!"
        Когда нажимаем на кнопку "Зарегистрироваться"

        Дано открыта страница почтового сервиса
        Когда в журнале присутствует новое сообщение
        И открываем письмо
        И переходим по ссылке

        Когда в строку поиска вводим искомый товар "blackberry"
        Когда нажимаем на кнопку "Найти"
        Тогда появляются результаты поиска
        И количество отображаемых товаров соответствует 50
        Когда выходим из под своей учетной записи
        Когда нажимаем на ссылку "Выход"
        Тогда можем зарегистрироваться заново