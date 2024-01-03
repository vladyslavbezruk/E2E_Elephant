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

import static com.codeborne.selenide.Selenide.*;

public class TC097_2Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
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

    @Then("Вибирати мову українську на сторінці профіля.")
    public void a() {
//        open("http://localhost:7000/profile");
        homePage.linkProfile.click();
        profilePage.linkUkraine.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Текст та інформація на сторінці профіля повинні бути відображені на українській мові.")
    public void b() {
        open("http://localhost:7000");
        Assertions.assertEquals(homePage.linkTextNowrap.getText(), "Вийти");
        Assertions.assertEquals(homePage.linkActive.getText(), "Головна");
        Assertions.assertEquals(homePage.link.getText(), "Анкета");
        Assertions.assertEquals(homePage.spanTotalSpace.getText(), "Всього місця");
        Assertions.assertEquals(homePage.spanBases.getText(), "Баз");
        Assertions.assertEquals(homePage.smallUsedFromTotalSpace2.getText(), "Використано від загальної кількості місця у вашому плані");
        Assertions.assertEquals(homePage.smallCreatedFromYourPlan2.getText(), "Створений з 2 у вашому плані");
        Assertions.assertEquals(homePage.h3.getText(), "Бази даних");
        Assertions.assertEquals(homePage.button2.getText(), "Створіть нову базу даних");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}