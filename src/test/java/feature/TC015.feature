Feature: TC015
  Перевірка повідомлення при реєстрації з існуючим email

  Scenario: Перевірка повідомлення при реєстрації з існуючим email
    Given Перейти на сторінку реєстрації.
    When Ввести email адресу, яка вже використовується в системі, та спробувати подати форму.
    Then З’являється повідомлення 'The user with such email address has been already registered. Please fill out another email address'.