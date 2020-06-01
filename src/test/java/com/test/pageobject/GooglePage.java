package com.test.pageobject;


import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class GooglePage {
    final Logger logger = LoggerFactory.getLogger(GooglePage.class);
    final String url = "https://google.com/ncr";
    public SelenideElement form = $(By.name("q"));

    public GooglePage openPage() {
        logger.info("Open: {}", url);
        open(url);
        return this;
    }

    public void searchFor(String text) {
        logger.info("Searching for: {}", text);
        form.val(text).pressEnter();
    }
}
