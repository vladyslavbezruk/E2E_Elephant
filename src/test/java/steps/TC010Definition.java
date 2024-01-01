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

import static com.codeborne.selenide.Selenide.webdriver;

public class TC010Definition {
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

    @When("Ввести 'password' у поле 'password'.")
    public void ввести_password_у_поле_password() {
        registrationPage.inputPassword.setValue("Password1!");
    }

    @Then("Текст у полі 'password' прихований відображається як зірочки або крапки.")
    public void текст_у_полі_password_прихований_відображається_як_зірочки_або_крапки() {
        Assertions.assertNotEquals(registrationPage.inputPassword.text(), "Password1!");
        Assertions.assertEquals(registrationPage.inputPassword.getAttribute("type"), "password");
    }

    @When("Клікнути на елемент 'unmask' поряд із полем 'password'.")
    public void клікнути_на_елемент_unmask_поряд_із_полем_password() {
        registrationPage.linkPassShowHide.click();
    }

    @Then("Текст у полі 'password' стає видимим.")
    public void текст_у_полі_password_стає_видимим() {
        Assertions.assertEquals(registrationPage.inputPassword.getAttribute("type"), "text");
    }
}
