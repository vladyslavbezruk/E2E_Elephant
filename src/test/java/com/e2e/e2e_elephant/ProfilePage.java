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

    public SelenideElement h3UserPreset = $("html > body > div > div > main > div:nth-of-type(2) > h3");

    public SelenideElement divUserPresetYourPrefered = $("html > body > div > div > main > div:nth-of-type(2)");

    public SelenideElement linkEnglish = $("html > body > div > div > main > div:nth-of-type(2) > a:nth-of-type(1)");

    public SelenideElement linkUkraine2 = $("html > body > div > div > main > div:nth-of-type(2) > a:nth-of-type(2)");

    public SelenideElement h3ApiKeys = $("html > body > div > div > main > div:nth-of-type(3) > h3");

    public SelenideElement labelPublicApiKey = $("label[for='public-api']");

    public SelenideElement labelPrivateApiKey = $("label[for='private-api']");

    public SelenideElement buttonGenerate = $("html > body > div > div > main > div:nth-of-type(3) > form > div:nth-of-type(2) > button");

    public SelenideElement h3DatabaseUser = $("html > body > div > div > main > div:nth-of-type(4) > h3");

    public SelenideElement buttonChangePassword = $("html > body > div > div > main > div:nth-of-type(4) > form > div:nth-of-type(2) > button");
    
    
}