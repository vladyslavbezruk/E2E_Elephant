package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://127.0.0.1:7000/database/
public class DatabasePage {
    public SelenideElement ioniconHomeOutline = $("ion-icon[aria-label^='home']");

    public SelenideElement link = $("html > body > div > div > main > div:nth-of-type(2) > h1 > a:nth-of-type(2)");
}