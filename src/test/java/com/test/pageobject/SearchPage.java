package com.test.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class SearchPage {
    final Logger logger = LoggerFactory.getLogger(SearchPage.class);
    public ElementsCollection results = $$("#res .r > a");
    public SelenideElement nextPageBtn = $("#pnnext");

    // navigate through result pages
    private void nextPage() {
        logger.info("Come to next result page");
        $("#pnnext").click();
    }

    public SelenideElement searchSite(String url) {
        return searchSite(url, 1);
    }

    public SelenideElement searchSite(String url, Integer count) {
        logger.info("Try to search {} on {} result page", url, count);
        for (SelenideElement resultRow : this.results) {
            String ref = resultRow.attr("href");
            if (ref != null && ref.contains(url)) {
                return resultRow;
            }
        }
        try {
            this.nextPage();
        } catch (ElementNotFound e) {
            logger.error("Not such URL in results");
            throw new RuntimeException(e);
        }
        return this.searchSite(url, ++count);
    }

}
