Feature: TC060
  За замовчанням, при відкритті таблиці, відображається 10 рядків

  Scenario: За замовчанням, при відкритті таблиці, відображається 10 рядків
    Given Перейти на сторінку авторизації.
    When Увійдіть в систему, використовуючи свій обліковий запис, на якому встановлено роль 'Basic'.
    Then Виконайте дію Відкрити для однієї з баз даних.
    When Відкрити тестову таблицю.
    Then Переконатись що відображаються тільки 10 рядків даних.
    Given Вийдіть із системи.

