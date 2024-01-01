package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://localhost:7000/registration
public class RegistrationPage {
    public SelenideElement buttonSign = $("button[class^='w-100']");

    public SelenideElement divLoginValidMail = $("div[class*='fade']");

    public SelenideElement inputPassword = $("input[aria-label='Password']");

    public SelenideElement inputConformation = $("#conformation");

    public SelenideElement inputEmail = $("#email");

    public SelenideElement divOrgExceptionErrorExecute = $("div[class*='fade']");

    public SelenideElement divPasswordCanEmptyShould = $("div[class*='fade']");

    public SelenideElement divPasswordShouldLeastSymbols = $("div[class*='fade']");

    public SelenideElement linkPassShowHide = $("#pass-show-hide");
}