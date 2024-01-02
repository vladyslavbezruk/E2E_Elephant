package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.ResetPasswordPage;
import com.e2e.e2e_elephant.ResetPasswordPageToken;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.*;

public class TC026Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
    ResetPasswordPageToken resetPasswordPageToken = new ResetPasswordPageToken();

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

    @When("Ввести валідні дані нового паролю новий пароль та натиснути кнопку 'Change password'.")
    public void a() {
        resetPasswordPageToken.inputWebPassword.setValue("NewNewpass123!");

        resetPasswordPageToken.inputWebPassword2.setValue("NewNewpass123!");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        resetPasswordPageToken.buttonChangePassword.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Переадресація на сторінку авторизації. Увійти з новим паролем новий пароль.")
    public void b() {
        open("http://127.0.0.1:7000/login");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        loginPage.inputFloating.setValue("bezrukvladislav@gmail.com");

        loginPage.inputFloatingPassword.setValue("NewNewpass123!");

        loginPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Отримати помилку про невдалий вхід.")
    public void c() {
        if (webdriver().driver().getCurrentFrameUrl().equals("http://127.0.0.1:7000/home")) {
            homePage.linkLogout.click();

            throw new AssertionError("Відбувся вхід, помилки не було");
        } else {
            Assertions.assertEquals(loginPage.divUserPasswordNotKnown.text(), "User or password not known");
        }
    }
}