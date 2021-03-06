### DESCRIPTION

Необходимо автоматизировать тестирование формы заказа карты:

![img.png](src/test/resources/img.png)

Требования к содержимому полей:

1. Поле Фамилия и имя - разрешены только русские буквы, дефисы и пробелы.
1. Поле телефон - только цифры (11 цифр), символ + (на первом месте).
1. Флажок согласия должен быть выставлен.  

### [TASK](https://github.com/netology-code/aqa-homeworks/tree/master/web)
* Тестируемая функциональность: отправка формы.  
* Selenide должен запускаться в headless-режиме.

Условия: если все поля заполнены корректно, то вы получаете сообщение об успешно отправленной заявке:

![img.png](src/test/resources/result.png)

### LAUNCH

1. Запускаем приложение командой ``java -jar artifacts/app-order.jar``
1. Запускаем тесты ``gradlew test``
1. Доступно в браузере http://localhost:9999/

### TOOLS

[![Build status](https://ci.appveyor.com/api/projects/status/l1m9ce2lrrwxfn95?svg=true)](https://ci.appveyor.com/project/Kasparidi/ordercard)
CI AppVeyor  
Java, Gradle, Selenide, headless-режим
