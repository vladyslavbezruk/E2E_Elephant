package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.ResetPasswordPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.screenshot;

public class TC025Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

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

    @When("Ввести у поле не існуючу пошту та натинсути клавішу 'Send Reset Link'.")
    public void ввести_у_поле_не_існуючу_пошту_та_натинсути_клавішу_send_reset_link() {
        resetPasswordPage.inputWebEmail.setValue("nonexistmail@gmail.com");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        resetPasswordPage.buttonSendLinkForPassword.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Виведення загального валідаційного повідомлення про помилку 'reset password'.")
    public void виведення_загального_валідаційного_повідомлення_про_помилку_reset_password() {
        Assertions.assertEquals(resetPasswordPage.divUserPasswordNotKnown.text(), "User or password not known");
    }
}