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

import java.io.*;
import java.util.Random;

import static com.codeborne.selenide.Selenide.webdriver;

public class TC012Definition {
    Random rnd = new Random();
    String email = "valid.email" + rnd.nextInt(99999) + "@example.com";
    com.e2e.e2e_elephant.RegistrationPage registrationPage = new com.e2e.e2e_elephant.RegistrationPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();

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

    @When("Успішно заповнити та подати форму реєстрації.")
    public void успішно_заповнити_та_подати_форму_реєстрації() {
        registrationPage.inputEmail.setValue(email);

        registrationPage.inputPassword.setValue("Password1!");

        registrationPage.inputConformation.setValue("Password1!");

        registrationPage.buttonSign.click();
    }

    @Then("Користувач перенаправляється на домашню сторінку протягом '5' секунд.")
    public void користувач_перенаправляється_на_домашню_сторінку_протягом_5_секунд() {
        String filePath = "C:\\ProjectElephant\\messages.txt";
        String searchText = email;

        homePage.linkLogout.click();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean textFound = false;

            while ((line = reader.readLine()) != null) {
                if (line.contains(searchText)) {
                    textFound = true;
                    break;
                }
            }

            if (textFound) {
                System.out.println("Email found!");

                return;
            } else {
                throw new AssertionError("Email не знайдено");
            }

        } catch (IOException e) {
            throw new AssertionError("Email не знайдено");
        }
    }
}