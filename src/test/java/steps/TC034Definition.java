package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.ProfilePage;
import com.e2e.e2e_elephant.ResetPasswordPageToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

public class TC034Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();

    ProfilePage profilePage = new ProfilePage();

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

    @Then("Переконайтеся, що після реєстрації вам присвоєна роль 'Not-checked user'.")
    public void a() {
        open("http://127.0.0.1:7000/profile");

        Assertions.assertEquals(profilePage.buttonOutlinePrimary.text(), "Upgrade");

        Assertions.assertEquals(profilePage.buttonOutlinePrimary2.text(), "Upgrade");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Упевніться, що ви можете ввійти в систему без проблем.")
    public void b() {
        Assertions.assertEquals(webdriver().driver().getCurrentFrameUrl(), "http://127.0.0.1:7000/home");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Перевірте сторінку свого профілю користувача, щоб побачити, чи відображається посилання для перевірки 'e-mail'.")
    public void c() {
        Assertions.assertEquals(homePage.pCheckYourMailAnd.text(), "Check your mail and push mail-verification link - to be able create new database.");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}