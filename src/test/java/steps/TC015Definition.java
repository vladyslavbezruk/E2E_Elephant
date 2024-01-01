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

public class TC015Definition {
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

    @When("Ввести email адресу, яка вже використовується в системі, та спробувати подати форму.")
    public void ввести_email_адресу_яка_вже_використовується_в_системі_та_спробувати_подати_форму() {
        registrationPage.inputEmail.setValue("valid.email@example.com");

        registrationPage.inputPassword.setValue("Password1!");

        registrationPage.inputConformation.setValue("Password1!");

        registrationPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("З’являється повідомлення {string}.")
    public void з_являється_повідомлення(String string) {
        Assertions.assertEquals(registrationPage.divOrgExceptionErrorExecute.text(),"org.sql2o.Sql2oException: Error in executeUpdate, ПОМИЛКА: повторювані значення ключа порушують обмеження унікальності \"users_login_key\" Detail: Ключ (login)=(valid.email@example.com) вже існує.");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}