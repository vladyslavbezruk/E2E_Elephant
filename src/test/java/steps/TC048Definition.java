package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.DatabasePage;
import com.e2e.e2e_elephant.ProfilePage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class TC048Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
    ProfilePage profilePage = new ProfilePage();
    DatabasePage databasePage = new DatabasePage();
    String dbName = null;

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

    @When("Натиснути кнопку 'Re-generate' для ключа 'API'.")
    public void a() {
        profilePage.buttonGenerate.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Перевіити чи ключ 'API' оновився.")
    public void b() {
        Assertions.assertEquals(profilePage.divApiKeysWasReset.getText(), "API keys was reset successful");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}