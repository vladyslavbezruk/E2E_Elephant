package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://127.0.0.1:7000/login/reset?token=
public class ResetPasswordPageToken {
    public SelenideElement inputWebPassword = $("input[aria-describedby='web-password-help']");

    public SelenideElement inputWebPassword2 = $("input[aria-describedby='web-password-c-help']");

    public SelenideElement buttonChangePassword = $("button[class='btn']");

}