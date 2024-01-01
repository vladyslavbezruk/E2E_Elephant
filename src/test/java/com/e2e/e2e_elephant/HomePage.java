package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://localhost:7000/home
public class HomePage {
    public SelenideElement linkResendMail = $("a[class*='btn-primary']");

    public SelenideElement linkLogout = $("a[class$='text-nowrap']");
}