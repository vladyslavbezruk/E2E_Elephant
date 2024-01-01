package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://127.0.0.1:7000/login/reset-password
public class ResetPasswordPage {
    public SelenideElement inputWebEmail = $("input[aria-describedby='web-email-help']");

    public SelenideElement buttonSendLinkForPassword = $("button[class='btn']");

}