package tutby.converter.tests;

import org.testng.annotations.Test;
import tutby.converter.pages.MainPage;

public class JustClicksOnAllControlsTest extends AbstractTest {

    @Test(description = "Scenario #0."
            + " Test performs clicks on rates tab and cross rates tab. Also test performs clicks on all currencies in cross rates tab, and all types of rates links in rates tab."
            + " Also test performs actions of date setup, currency selection currency amount input, existing currency reselection, and finally third currency row addition to currency converter form.")

    public void Test() {
        MainPage mainPage = new MainPage(getDriver());
        mainPage.assertIsOpened();

        mainPage.selectTab("КУРСЫ ВАЛЮТ!");
        mainPage.selectTab("КРОСС-КУРСЫ");
        mainPage.selectTab("КУРСЫ ВАЛЮТ");

        mainPage.selectTab("КРОСС-КУРСЫ");
        mainPage.getCrossRateFor("Евро", "Доллар США");
        mainPage.getCrossRateFor("Доллар США", "Евро");
        mainPage.getCrossRateFor("10 польских злотых", "100 российских рублей");
        mainPage.getCrossRateFor("Евро", "100 украинских гривен");
        mainPage.getCrossRateFor("Доллар США", "10 польских злотых");

        mainPage.getCrossRateFor("10 польских злотых", "100 российских рублей");
        mainPage.getCrossRateFor("Евро", "Доллар США");
        mainPage.getCrossRateFor("Евро", "100 украинских гривен");
        mainPage.getCrossRateFor("Доллар США", "Евро");
        mainPage.getCrossRateFor("Доллар США", "Евро");

        mainPage.selectTab("КУРСЫ ВАЛЮТ");
        mainPage.selectTab("КРОСС-КУРСЫ");

        mainPage.selectTab("КУРСЫ ВАЛЮТ");
        mainPage.selectNbrbRatesTable();
        mainPage.selectBvfbRatesTable();
        mainPage.selectCbrfRatesTable();
        mainPage.selectBestRatesTable();

        mainPage.selectBvfbRatesTable();
        mainPage.selectBvfbRatesTable();
        mainPage.selectCbrfRatesTable();
        mainPage.selectNbrbRatesTable();

        mainPage.setCalendarDate("2015-09-12");
        mainPage.setCalendarDate("2014-10-09");
        mainPage.selectConverterCurrency("Австралийский доллар");
        mainPage.selectConverterCurrency("Швейцарский франк");
        mainPage.setCurrencyAmount("Австралийский доллар", "200");
        mainPage.setCurrencyAmount("Швейцарский франк", "500");
        mainPage.selectBaseCurrency("Австралийский доллар");
        mainPage.selectBaseCurrency("Швейцарский франк");
        mainPage.setCalendarDate("2014-11-23");
        mainPage.reselectConverterCurrency("Австралийский доллар", "XDR");
        mainPage.reselectConverterCurrency("Швейцарский франк", "Белорусский рубль");
        mainPage.selectConverterCurrency("Исландская крона");
        mainPage.reselectConverterCurrency("Исландская крона", "Датская крона");
    }
}