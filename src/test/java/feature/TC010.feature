Feature: TC010
  Перевірка функціоналу Unmask для 'Password'

  Scenario: Перевірка функціоналу Unmask для 'Password'
    Given Перейти на сторінку реєстрації.
    When Ввести 'password' у поле 'password'.
    Then Текст у полі 'password' прихований відображається як зірочки або крапки.

    When Клікнути на елемент 'unmask' поряд із полем 'password'.
    Then Текст у полі 'password' стає видимим.