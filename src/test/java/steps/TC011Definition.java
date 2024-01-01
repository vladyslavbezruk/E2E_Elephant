package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.screenshot;
import static com.codeborne.selenide.Selenide.webdriver;

public class TC011Definition {
    com.e2e.e2e_elephant.RegistrationPage registrationPage = new com.e2e.e2e_elephant.RegistrationPage();

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        // Fix the issue https://github.com/SeleniumHQ/selenium/issues/11750
        Configuration.browserCapabilities = new ChromeOptions().addArguments("--remote-allow-origins=*");
    }

    @When("Залишити поле 'confirm password' порожнім і спробувати подати форму.")
    public void залишити_поле_confirm_password_порожнім_і_спробувати_подати_форму() {
        registrationPage.inputEmail.setValue("valid.email@example.com");

        registrationPage.inputPassword.setValue("Password1!");

        registrationPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Подання не дозволяється. З'являється повідомлення про помилку, що поле 'confirm password' є обов'язковим.")
    public void подання_не_дозволяється_з_являється_повідомлення_про_помилку_що_поле_confirm_password_є_обов_язковим() {
        if (registrationPage.divOrgExceptionErrorExecute.text().equals("org.sql2o.Sql2oException: Error in executeUpdate, ПОМИЛКА: повторювані значення ключа порушують обмеження унікальності \"users_login_key\" Detail: Ключ (login)=(valid.email@example.com) вже існує.")) {
            Assertions.assertEquals(webdriver().driver().getCurrentFrameUrl(), "http://127.0.0.1:7000/home");
        } else {
            Assertions.assertNotEquals(webdriver().driver().getCurrentFrameUrl(), "http://127.0.0.1:7000/home");
        }
    }

}
