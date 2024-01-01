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
}