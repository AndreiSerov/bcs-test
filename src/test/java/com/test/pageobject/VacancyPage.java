package com.test.pageobject;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.test.exceptions.NotSuchVacancy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class VacancyPage {
    final Logger logger = LoggerFactory.getLogger(VacancyPage.class);
    public ElementsCollection listOfVacancies =
            $$(".b-vacancy-list a[href=\"#\"]");
    public SelenideElement city = $("#city");
    public SelenideElement sector = $("#sector");
    public SelenideElement vacancyBtn = $("#vacancy button");


    public SelenideElement findVacancy(String vacancyName) {
        logger.info("Get vacancy {}", vacancyName);
        for (SelenideElement elem : this.listOfVacancies) {
            String elemText = elem.innerText();
            if (elemText != null && elemText.contains(vacancyName)) {
                return elem;
            }
        }
        logger.error("Not such vacancy on page");
        throw new NotSuchVacancy(vacancyName);
    }

    public void chooseCityAndSector(String city, String sector) {
        logger.info("Choosing city: {} and sector: {}", city, sector);
        this.city.selectOptionContainingText(city);
        this.sector.selectOptionContainingText(sector);
        this.vacancyBtn.click();
    }

    public ElementsCollection getJobResponsibilities(SelenideElement elem) {
        logger.info("Get responsibilities of chosen vacancy");
        return elem.$$x(".//following-sibling::div/ul[1]/li");
    }

}
