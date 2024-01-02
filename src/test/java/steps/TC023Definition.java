package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.ResetPasswordPage;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.Given;
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

public class TC023Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    com.e2e.e2e_elephant.HomePage homePage = new com.e2e.e2e_elephant.HomePage();
    com.e2e.e2e_elephant.ResetPasswordPage resetPasswordPage = new com.e2e.e2e_elephant.ResetPasswordPage();
    com.e2e.e2e_elephant.ResetPasswordPageToken resetPasswordPageToken = new com.e2e.e2e_elephant.ResetPasswordPageToken();

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

    @Then("Клацнути на посилання 'forgot password'.")
    public void клацнути_на_посилання_forgot_password() {
        loginPage.linkForgotPassword.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @When("Ввести у поле пошту та натинсути клавішу 'Send Reset Link'.")
    public void ввести_у_поле_пошту_та_натинсути_клавішу_Send_Reset_Link() {
        resetPasswordPage.inputWebEmail.setValue("bezrukvladislav@gmail.com");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        resetPasswordPage.buttonSendLinkForPassword.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("Перейти за посиланням яке буде відправлено на пошту.")
    public void перейти_за_посиланням_яке_буде_відправлено_на_пошту() {
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

                    open(resetLink);

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
    @When("Ввести валідні дані нового паролю та атиснути кнопку 'Change password'.")
    public void ввести_валідні_дані_нового_паролю_та_атиснути_кнопку_change_password() {
        resetPasswordPageToken.inputWebPassword.setValue("Newpass123!");

        resetPasswordPageToken.inputWebPassword2.setValue("Newpass123!");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        resetPasswordPageToken.buttonChangePassword.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("Переадресація на сторінку авторизації. Увійти з новим паролем.")
    public void переадресація_на_сторінку_авторизації_увійти_з_новим_паролем() {
        open("http://127.0.0.1:7000/login");

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));

        loginPage.inputFloating.setValue("bezrukvladislav@gmail.com");

        loginPage.inputFloatingPassword.setValue("Newpass123!");

        loginPage.buttonSign.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
    @Then("Перевірити вхід. Вийти з акаунту.")
    public void перевірити_вхід_вийти_з_акаунту() {
        Assertions.assertEquals(webdriver().driver().getCurrentFrameUrl(), "http://127.0.0.1:7000/home");

        homePage.linkLogout.click();

        screenshot("screenshots/" + this.getClass().getSimpleName() + "/" + java.time.LocalDateTime.now().toString().replace(":", "-"));
    }
}