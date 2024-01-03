package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://127.0.0.1:7000/profile
public class ProfilePage {
    public SelenideElement buttonOutlinePrimary = $("html > body > div > div > main > div:nth-of-type(5) > div > div:nth-of-type(1) > form > div > div:nth-of-type(2) > button");

    public SelenideElement buttonOutlinePrimary2 = $("html > body > div > div > main > div:nth-of-type(5) > div > div:nth-of-type(2) > form > div > div:nth-of-type(2) > button");

    public SelenideElement linkUkraine = $("html > body > div > div > main > div:nth-of-type(2) > a:nth-of-type(2)");
}