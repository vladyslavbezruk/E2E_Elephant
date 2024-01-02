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

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import static com.codeborne.selenide.Selenide.*;

public class TC014Definition {
    com.e2e.e2e_elephant.RegistrationPage registrationPage = new com.e2e.e2e_elephant.RegistrationPage();

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

    @When("Навмисно ввести невірні дані у форму реєстрації та спробувати подати форму.")
    public void навмисно_ввести_невірні_дані_у_форму_реєстрації_та_спробувати_подати_форму() {
        registrationPage.inputEmail.setValue("validemailexample.com");

        registrationPage.inputPassword.setValue("Password1");

        registrationPage.inputConformation.setValue("Password1");

        registrationPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("З’являється повідомлення 'Please fill out the form once again'.")
    public void з_являється_повідомлення_Please_fill_out_the_form_once_again() {
        Assertions.assertEquals(webdriver().driver().getCurrentFrameUrl(), "http://127.0.0.1:7000/registration");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("Введені користувачем дані залишаються у відповідних полях форми.")
    public void введені_користувачем_дані_залишаються_у_відповідних_полях_форми() {
        Assertions.assertEquals(registrationPage.inputEmail.getValue(), "validemailexample.com");
        Assertions.assertEquals(registrationPage.inputPassword.getValue(), "Password1");
        Assertions.assertEquals(registrationPage.inputConformation.getValue(), "Password1");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}