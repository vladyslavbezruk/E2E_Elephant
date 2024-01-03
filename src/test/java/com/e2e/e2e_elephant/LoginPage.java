package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://127.0.0.1:7000/login
public class LoginPage {
    public SelenideElement inputFloating = $("#floatingInput");

    public SelenideElement inputFloatingPassword = $("input[aria-label='Password']");

    public SelenideElement linkPassShowHide = $("#pass-show-hide");

    public SelenideElement buttonSign = $("button[class^='w-100']");

    public SelenideElement divUserPasswordNotKnown = $("div[class*='fade']");

    public SelenideElement linkForgotPassword = $("html > body > main > div:nth-of-type(1) > div > div:nth-of-type(2) > form > p > a");

    public static SelenideElement linkTextNowrap = $("html > body > header > div > a:nth-of-type(1)");

    public SelenideElement linkTextNowrap2 = $("html > body > header > div > a:nth-of-type(2)");

    public SelenideElement linkTextNowrap3 = $("html > body > header > div > a:nth-of-type(3)");

    public SelenideElement inputFloating2 = $("#floatingInput");

    public SelenideElement inputFloatingPassword2 = $("input[aria-label='Password']");

    public SelenideElement label = $("div[class^='checkbox'] label");

    public SelenideElement button = $("button[class^='w-100']");

    public SelenideElement smallTextMuted = $("small");

    public SelenideElement link = $("html > body > main > div:nth-of-type(1) > div > div:nth-of-type(2) > form > p > a");

    public SelenideElement linkTextNowrap4 = $("html > body > header > div > a:nth-of-type(1)");
}