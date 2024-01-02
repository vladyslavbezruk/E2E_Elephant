package steps;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.e2e.e2e_elephant.ResetPasswordPage;
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

public class TC024Definition {
    com.e2e.e2e_elephant.LoginPage loginPage = new com.e2e.e2e_elephant.LoginPage();
    ResetPasswordPage resetPasswordPage = new ResetPasswordPage();

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

    @Then("Перевірка наявності посилання яке буде відправлено на пошту.")
    public void перевірка_наявності_посилання_яке_буде_відправлено_на_пошту() {
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
}