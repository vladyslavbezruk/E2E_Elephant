package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.Selenide.screenshot;

public class TC013Definition {
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

    @Given("Перейти на сторінку, де відображається кнопка 'send again'.")
    public void перейти_на_сторінку_де_відображається_кнопка_send_again() {
        String filePath = "C:\\ProjectElephant\\messages.txt";

        open("http://127.0.0.1:7000/registration");

        registrationPage.inputEmail.setValue(email);

        registrationPage.inputPassword.setValue("Password1!");

        registrationPage.inputConformation.setValue("Password1!");

        registrationPage.buttonSign.click();

        File myObj = new File(filePath);

        myObj.delete();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Клікнути на кнопку 'send again'.")
    public void клікнути_на_кнопку_send_again() {
        homePage.linkResendMail.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Користувач отримує новий 'email' для активації акаунту.")
    public void користувач_отримує_новий_email_для_активації_акаунту() {
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