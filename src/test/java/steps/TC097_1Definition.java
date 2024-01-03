package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.DatabasePage;
import com.e2e.e2e_elephant.HomePage;
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

public class TC097_1Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    HomePage homePage = new HomePage();
    ProfilePage profilePage = new ProfilePage();
    DatabasePage databasePage = new DatabasePage();

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

    @Then("Вибирати українську мову на сторінці профіля.")
    public void a() {
        homePage.linkProfile.click();
        profilePage.linkUkraine.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Текст та інформація на сторінці профіля повинні бути на українській мові.")
    public void b() {
        try {
            Assertions.assertEquals(profilePage.h3UserPreset.getText(), "Користувацьке налаштування");
            Assertions.assertEquals(profilePage.divUserPresetYourPrefered.getText(), "Ваша бажана мова:");
            Assertions.assertEquals(profilePage.linkEnglish.getText(), "Англійська");
            Assertions.assertEquals(profilePage.linkUkraine2.getText(), "Українська");
            Assertions.assertEquals(profilePage.h3ApiKeys.getText(), "API's ключі");
            Assertions.assertEquals(profilePage.labelPublicApiKey.getText(), "Публічний Api ключ");
            Assertions.assertEquals(profilePage.labelPrivateApiKey.getText(), "Приватний Api ключ");
            Assertions.assertEquals(profilePage.buttonGenerate.getText(), "Перегенерувати");
            Assertions.assertEquals(profilePage.h3DatabaseUser.getText(), "Користувач баз даних");
            Assertions.assertEquals(profilePage.buttonChangePassword.getText(), "Змінити пароль");
        } catch (org.opentest4j.AssertionFailedError e) {
            profilePage.linkEnglish.click();

            homePage.linkLogout.click();

            throw new AssertionError(e.getMessage());
        }

        profilePage.linkEnglish.click();

        homePage.linkLogout.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}