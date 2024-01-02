package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.ResetPasswordPageToken;
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

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class TC029Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
    ResetPasswordPageToken resetPasswordPageToken = new ResetPasswordPageToken();

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

    @When("Ввести невалідні дані нового паролю та натиснути кнопку 'Change password'.")
    public void ввести_невалідні_дані_нового_паролю_та_натиснути_кнопку() {
        resetPasswordPageToken.inputWebPassword.setValue("Pass123");

        resetPasswordPageToken.inputWebPassword2.setValue("Pass123");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        resetPasswordPageToken.buttonChangePassword.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Відображення повідомлення про невалідний пароль з вказівкою на вимоги до паролю.")
    public void відображення_повідомлення_про_невалідний_пароль_з_вказівкою_на_вимоги_до_паролю() {
        Assertions.assertEquals(resetPasswordPageToken.divPasswordNotFoundValidation.text(), "password I18n not found:validation.invalid.empty");
    }
}