package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://localhost:7000/home
public class HomePage {
    public SelenideElement linkResendMail = $("a[class*='btn-primary']");

    public SelenideElement linkLogout = $("a[class$='text-nowrap']");

    public SelenideElement pCheckYourMailAnd = $("p[class='lead']");

    public SelenideElement linkProfile = $("a[class='nav-link']");

    public SelenideElement span = $("html > body > div > div > main > div:nth-of-type(2) > div > div:nth-of-type(1) > div > div > div:nth-of-type(1) > h2 > span:nth-of-type(1)");

    public SelenideElement span2 = $("html > body > div > div > main > div:nth-of-type(2) > div > div:nth-of-type(2) > div > div > div:nth-of-type(1) > h2 > span:nth-of-type(1)");

    public SelenideElement smallUsedFromTotalSpace = $("html > body > div > div > main > div:nth-of-type(2) > div > div:nth-of-type(1) > div > div > div:nth-of-type(1) > small");

    public SelenideElement smallCreatedFromYourPlan = $("html > body > div > div > main > div:nth-of-type(2) > div > div:nth-of-type(2) > div > div > div:nth-of-type(1) > small");

    public SelenideElement h3Databases = $("h3");

    public SelenideElement h6 = $("h6");

    public SelenideElement divUserPassword = $("div[aria-labelledby='jdbc-bxmivcytpu']");

    public SelenideElement button = $("button[class='btn']");

    public SelenideElement buttonCreateNewDatabase = $("button[class^='w-100']");

    public SelenideElement divDatabaseHasBeenDropped = $("div[class$='show']");

    public SelenideElement button2 = $("form[action='/database/qbrmiquyqp/delete'] button");
}