Feature: TC093_3
  Перевірка доступу до входу та виходу для авторизованих користувачів

  Scenario: Перевірка доступу до входу та виходу для авторизованих користувачів
    Given Перейти на сторінку реєстрації.
    When Успішно заповнити та подати форму реєстрації.
    Then Переконайтеся, що після реєстрації вам присвоєна роль 'Not-checked user'.
    Then Користувач перенаправляється на домашню сторінку протягом '5' секунд.
    Given Перейти на сторінку авторизації.
    When Увійдіть в систему, використовуючи свій обліковий запис.
    Then Упевніться, що ви можете ввійти в систему без проблем.
    Then Перевірте сторінку свого профілю користувача, щоб побачити, чи відображається посилання для перевірки 'e-mail'.
    Then Вийдіть із системи.
