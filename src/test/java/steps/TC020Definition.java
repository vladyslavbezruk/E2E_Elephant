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

public class TC020Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();

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

    @When("Заповнити поля авторизації некоректними значеннями.")
    public void заповнити_поля_авторизації_некоректними_значеннями() {
        loginPage.inputFloating.setValue("unregistermail@gmail.com");

        loginPage.inputFloatingPassword.setValue("Correct_1234!");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        loginPage.buttonSign.click();
    }
    @Then("Виведення загального валідаційного повідомлення про помилку.")
    public void виведення_загального_валідаційного_повідомлення_про_помилку() {
        Assertions.assertEquals(loginPage.divUserPasswordNotKnown.text(), "User or password not known");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}