Feature: TC040
  Перевірка наявності та активності кнопки створення нової бази даних

  Scenario: Перевірка наявності та активності кнопки створення нової бази даних
    Given Перейти на сторінку авторизації.
    When Увійдіть в систему, використовуючи свій обліковий запис, на якому встановлено роль 'Basic'.
    Then Переконайтесь, що на головній сторінці є кнопка 'Створити нову базу даних.'
    Given Вийдіть із системи.
