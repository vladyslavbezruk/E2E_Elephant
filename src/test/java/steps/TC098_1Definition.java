package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.LoginPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;


import static com.codeborne.selenide.Selenide.screenshot;

public class TC098_1Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1800x1000";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
    }

    @Then("Вибирати мову наприклад, українську на сторінці лендінгу.")
    public void a() {
        loginPage.linkTextNowrap4.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Текст та інформація на основних сторінках повинні бути відображені в обраній мові у даному випадку, українській.")
    public void b() {
        Assertions.assertEquals(loginPage.linkTextNowrap2.getText(), "Увійти");
        Assertions.assertEquals(loginPage.linkTextNowrap3.getText(), "Зареєстуватися");
        Assertions.assertEquals(loginPage.inputFloating2.getText(), "Адреса електронної пошти");
        Assertions.assertEquals(loginPage.inputFloatingPassword2.getText(), "Пароль");
        Assertions.assertEquals(loginPage.label.getText(), "Пам'ятай мене");
        Assertions.assertEquals(loginPage.button.getText(), "Увійти");
        Assertions.assertEquals(loginPage.smallTextMuted.getText(), "Натиснувши Увійти, ви погоджуєтеся з умовами використання.");
        Assertions.assertEquals(loginPage.link.getText(), "Забули пароль?");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}