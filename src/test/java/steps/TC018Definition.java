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

public class TC018Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();

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

    @When("Заповнити поле 'password' будь-якими значенням.")
    public void заповнити_поле_password_будь_якими_значенням() {
        loginPage.inputFloatingPassword.setValue("Correct_1234");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Кожен введений символ у паролі відображається у вигляді зірочок. Кількість введених символів сппівпадає з кількістю зірочок.")
    public void кожен_введений_символ_у_паролі_відображається_у_вигляді_зірочок_кількість_введених_символів_сппівпадає_з_кількістю_зірочок() {
        Assertions.assertEquals(loginPage.inputFloatingPassword.getValue().length(), "Correct_1234".length());

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Натиснути кнопку для розмаскування паролю.")
    public void натиснути_кнопку_для_розмаскування_паролю() {
        loginPage.linkPassShowHide.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("При розмаскуванні пароль відображається введеним текстом символами користувачем.")
    public void при_розмаскуванні_пароль_відображається_введеним_текстом_символами_користувачем() {
        Assertions.assertEquals(loginPage.inputFloatingPassword.getValue(), "Correct_1234");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Натиснути кнопку для маскування паролю.")
    public void натиснути_кнопку_для_маскування_паролю() {
        loginPage.linkPassShowHide.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}