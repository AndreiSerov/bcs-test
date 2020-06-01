package com.test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.test.pageobject.GooglePage;
import com.test.pageobject.SearchPage;
import com.test.pageobject.VacancyPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;


public class ResponsibilitiesCountTest {

    @BeforeAll
    public static void openInbox() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        // start google and search
        new GooglePage()
                .openPage()
                .searchFor("брокеркредитсервис вакансии");
        // navigate through result pages until find with necessary link
        new SearchPage()
                .searchSite("https://bcs.ru/vacancy").click();
    }

    @AfterAll
    public static void logout() {
        closeWebDriver();
        SelenideLogger.removeListener("allure");
    }


    @Test
    public void userCanSearch() {
        VacancyPage vacancyPage = new VacancyPage();
        // choosing city and sector in vacancy form
        vacancyPage.chooseCityAndSector("Новосибирск",
                "Информационные технологии");
        // find and click on chosen vacancy
        SelenideElement vacancy =
                vacancyPage.findVacancy("QA микросервисов");
        vacancy.click();
        // get collection of li elements
        ElementsCollection responsibilities =
                vacancyPage.getJobResponsibilities(vacancy);
        // check that 7 responsibilities for QA
        responsibilities.shouldHaveSize(7);
    }
}