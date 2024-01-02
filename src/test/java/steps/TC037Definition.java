package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.ProfilePage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import static com.codeborne.selenide.Selenide.screenshot;

public class TC037Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
    ProfilePage profilePage = new ProfilePage();

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

    @Then("Переконайтеся, що на сторінці відображається статус обмежень для 'Basic' відсоток вільного місця у сховищі та кількість баз даних, які можна створити.")
    public void a() {
        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        String span = homePage.span.getText();
        String span2 = homePage.span2.getText();
        String smallUsedFromTotalSpace = homePage.smallUsedFromTotalSpace.getText();
        String smallCreatedFromYourPlan = homePage.smallCreatedFromYourPlan.getText();
        String usedTotalSpaceStr = span.substring(0, span.length() - 1);
        String maxTotalBasesStr = smallCreatedFromYourPlan.replaceAll("[^0-9]+", "");

        int usedTotalSpace = Integer.parseInt(usedTotalSpaceStr);
        int totalBases = Integer.parseInt(span2);
        int maxTotalBases = Integer.parseInt(maxTotalBasesStr);

        if (!(usedTotalSpace >= 0 && usedTotalSpace <= 100)) {
            throw new AssertionError("Used total space wrong value.");
        }

        if (!(totalBases >= 0 && totalBases <= maxTotalBases)) {
            throw new AssertionError("Total bases wrong value.");
        }

        Assertions.assertEquals(smallUsedFromTotalSpace, "used from total space in your plan");

        Assertions.assertEquals(smallCreatedFromYourPlan, "created from 2 in your plan");
    }

    @Then("Переконайтеся, що на сторінці відображається статус обмежень для 'Pro' відсоток вільного місця у сховищі та кількість баз даних, які можна створити.")
    public void b() {
        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        String span = homePage.span.getText();
        String span2 = homePage.span2.getText();
        String smallUsedFromTotalSpace = homePage.smallUsedFromTotalSpace.getText();
        String smallCreatedFromYourPlan = homePage.smallCreatedFromYourPlan.getText();
        String usedTotalSpaceStr = span.substring(0, span.length() - 1);
        String maxTotalBasesStr = smallCreatedFromYourPlan.replaceAll("[^0-9]+", "");

        int usedTotalSpace = Integer.parseInt(usedTotalSpaceStr);
        int totalBases = Integer.parseInt(span2);
        int maxTotalBases = Integer.parseInt(maxTotalBasesStr);

        if (!(usedTotalSpace >= 0 && usedTotalSpace <= 100)) {
            throw new AssertionError("Used total space wrong value.");
        }

        if (!(totalBases >= 0 && totalBases <= maxTotalBases)) {
            throw new AssertionError("Total bases wrong value.");
        }

        Assertions.assertEquals(smallUsedFromTotalSpace, "used from total space in your plan");

        Assertions.assertEquals(smallCreatedFromYourPlan, "created from 3 in your plan");
    }
}