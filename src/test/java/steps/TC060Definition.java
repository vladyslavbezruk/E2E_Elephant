package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.DatabasePage;
import com.e2e.e2e_elephant.DatabaseTablePage;
import com.e2e.e2e_elephant.ProfilePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

public class TC060Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
    ProfilePage profilePage = new ProfilePage();
    DatabasePage databasePage = new DatabasePage();
    DatabaseTablePage databaseTablePage = new DatabaseTablePage();

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

    @When("Відкрити тестову таблицю.")
    public void a() {
        open("http://127.0.0.1:7000/database/bxmivcytpu/table/example_table");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Переконатись що відображаються тільки 10 рядків даних.")
    public void b() {
        Assertions.assertEquals(databaseTablePage.tdLast.getText(), "10");

        SelenideElement tdTest = $("html > body > div > div > main > table > tbody > tr:nth-of-type(12) > td:nth-of-type(1)");

        Assertions.assertFalse(tdTest.exists());
    }
}