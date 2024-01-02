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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Selenide.*;

public class TC027Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
    ResetPasswordPageToken resetPasswordPageToken = new ResetPasswordPageToken();
    String myFirstResetLink = "";

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

    @Then("Запам'ятати посилання яке було відправлено на пошту.")
    public void a() {
        String filePath = "C:\\ProjectElephant\\messages.txt";
        String emailToCheck = "bezrukvladislav@gmail.com";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            // Check if the email is present in the content
            if (content.toString().contains(emailToCheck)) {
                // Use regular expression to extract the link
                Pattern pattern = Pattern.compile("Hello you reset link ,\\s+(http://\\S+)");
                Matcher matcher = pattern.matcher(content);

                if (matcher.find()) {
                    String resetLink = matcher.group(1);
                    System.out.println("Reset link: " + resetLink);

                    myFirstResetLink = resetLink;

                    return;
                } else {
                    throw new AssertionError("Reset link not found.");
                }
            } else {
                throw new AssertionError("Email not found in the content.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Then("Перейти за запам'ятованим посиланням яке було відправлено на пошту.")
    public void b() {
        open(myFirstResetLink);

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @When("Ввести валідні дані нового паролю новий пароль '2' та натиснути кнопку 'Change password'.")
    public void c() {
        resetPasswordPageToken.inputWebPassword.setValue("NewNewNewpass123!");

        resetPasswordPageToken.inputWebPassword2.setValue("NewNewNewpass123!");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        resetPasswordPageToken.buttonChangePassword.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }

    @Then("Переадресація на сторінку авторизації. Увійти з новим паролем новий пароль '2'.")
    public void d() {
        open("http://127.0.0.1:7000/login");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        loginPage.inputFloating.setValue("bezrukvladislav@gmail.com");

        loginPage.inputFloatingPassword.setValue("NewNewNewpass123!");

        loginPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}