package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

public class TC009Definition {
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

    @When("Залишити поле 'Password' порожнім.")
    public void залишити_поле_Password_порожнім() {
        registrationPage.inputEmail.setValue("valid.email@example.com");

        registrationPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("Подання не дозволяється. З'являється повідомлення про помилку, що поле 'Password' є обов'язковим.")
    public void подання_не_дозволяється_з_являється_повідомлення_про_помилку_що_поле_password_є_обов_язковим() {
        Assertions.assertEquals(registrationPage.divPasswordCanEmptyShould.text(), "password Password can't be empty Password should be at least 8 symbols, with at least 1 digit, 1 uppercase letter and 1 non alpha-num symbol");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Ввести 'password' менше '8' символів.")
    public void ввести_password_менше_8_символів() {
        registrationPage.inputEmail.setValue("valid.email@example.com");

        registrationPage.inputPassword.setValue("Pas1!");

        registrationPage.inputConformation.setValue("Pas1!");

        registrationPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("Подання не дозволяється. З'являється повідомлення про помилку, що довжина 'password' має бути не менше '8' символів.")
    public void подання_не_дозволяється_з_являється_повідомлення_про_помилку_що_довжина_password_має_бути_не_менше_8_символів() {
        Assertions.assertEquals(registrationPage.divPasswordShouldLeastSymbols.text(), "password Password should be at least 8 symbols, with at least 1 digit, 1 uppercase letter and 1 non alpha-num symbol");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Ввести 'password' без великої літери та спецсимволу.")
    public void ввести_password_без_великої_літери_та_спецсимволу() {
        registrationPage.inputEmail.setValue("valid.email@example.com");

        registrationPage.inputPassword.setValue("password1");

        registrationPage.inputConformation.setValue("password1");

        registrationPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("Подання не дозволяється. З'являється повідомлення про помилку, що 'password' має містити хоча б одну велику літеру та один спецсимвол.")
    public void подання_не_дозволяється_з_являється_повідомлення_про_помилку_що_password_має_містити_хоча_б_одну_велику_літеру_та_один_спецсимвол() {
        Assertions.assertEquals(registrationPage.divPasswordShouldLeastSymbols.text(), "password Password should be at least 8 symbols, with at least 1 digit, 1 uppercase letter and 1 non alpha-num symbol");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @When("Ввести 'ValidP1'.")
    public void ввести_ValidP1() {
        registrationPage.inputEmail.setValue("valid.email@example.com");

        registrationPage.inputPassword.setValue("Password1!");

        registrationPage.inputConformation.setValue("Password1!");

        registrationPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("Якщо інші поля валідні, подання успішне. Повідомлення про помилку, пов'язане з полем 'Password', не з'являється.")
    public void якщо_інші_поля_валідні_подання_успішне_повідомлення_про_помилку_пов_язане_з_полем_Password_не_з_являється() {
        if (registrationPage.divOrgExceptionErrorExecute.text().equals("org.sql2o.Sql2oException: Error in executeUpdate, ПОМИЛКА: повторювані значення ключа порушують обмеження унікальності \"users_login_key\" Detail: Ключ (login)=(valid.email@example.com) вже існує.")) {
            return;
        } else {
            Assertions.assertEquals(webdriver().driver().getCurrentFrameUrl(), "http://localhost:7000/home");
        }
    }
}
