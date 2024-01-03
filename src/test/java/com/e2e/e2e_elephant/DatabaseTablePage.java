package com.e2e.e2e_elephant;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// page_url = http://127.0.0.1:7000/database/bxmivcytpu/table/example_table
public class DatabaseTablePage {
    public SelenideElement tdLast = $("html > body > div > div > main > table > tbody > tr:nth-of-type(11) > td:nth-of-type(1)");
}