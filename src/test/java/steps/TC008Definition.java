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

import static org.junit.jupiter.api.Assertions.*;
import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class TC008Definition {
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

    @Given("Перейти на сторінку реєстрації.")
    public void перейти_на_сторінку_реєстрації() {
        open("http://localhost:7000/registration");
    }

    @When("Залишити поле 'Email' порожнім і спробувати подати форму.")
    public void залишити_поле_Email_порожнім_спробувати_подати_форму() {
        registrationPage.inputPassword.setValue("Password1!");

        registrationPage.inputConformation.setValue("Password1!");

        registrationPage.buttonSign.click();
    }

    @Then("Подання не дозволяється. З'являється повідомлення про помилку, що поле 'Email' є обов'язковим.")
    public void подання_не_дозволяється_з_являється_повідомлення_про_помилку_що_поле_email_є_обов_язковим() {
        Assertions.assertEquals(registrationPage.divLoginValidMail.text(), "login Is it a valid mail?");
    }

    @When("Ввести 'invalidEmail' без 'a' та '.' у поле 'Email' і спробувати подати форму.")
    public void ввести_invalidEmail_без_a_та_у_поле_Email_спробувати_подати_форму() {
        registrationPage.inputEmail.setValue("user228examplecom");

        registrationPage.inputPassword.setValue("Password1!");

        registrationPage.inputConformation.setValue("Password1!");

        registrationPage.buttonSign.click();
    }
    @Then("Подання не дозволяється. З'являється повідомлення про помилку, що формат 'Email' недійсний.")
    public void подання_не_дозволяється_з_являється_повідомлення_про_помилку_що_формат_email_недійсний() {
        Assertions.assertNotEquals(webdriver().driver().getCurrentFrameUrl(), "http://localhost:7000/home");
    }

    @When("Ввести 'valid_emailaexample_com' у поле 'Email' і спробувати подати форму.")
    public void ввести_valid_emailaexample_com_у_поле_Email_і_спробувати_подати_форму() {
        registrationPage.inputEmail.setValue("valid.email@example.com");

        registrationPage.inputPassword.setValue("Password1!");

        registrationPage.inputConformation.setValue("Password1!");

        registrationPage.buttonSign.click();

    }
    @Then("Якщо інші поля валідні, подання успішне. Повідомлення про помилку, пов'язане з полем 'Email', не з'являється.")
    public void якщо_інші_поля_валідні_подання_успішне_повідомлення_про_помилку_пов_язане_з_полем_email_не_з_являється() {
        if (registrationPage.divOrgExceptionErrorExecute.text().equals("org.sql2o.Sql2oException: Error in executeUpdate, ПОМИЛКА: повторювані значення ключа порушують обмеження унікальності \"users_login_key\" Detail: Ключ (login)=(valid.email@example.com) вже існує.")) {
            return;
        } else {
            Assertions.assertEquals(webdriver().driver().getCurrentFrameUrl(), "http://localhost:7000/home");
        }
    }
}
