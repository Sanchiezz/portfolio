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

        mainPage.selectTab("����� �����!");
        mainPage.selectTab("�����-�����");
        mainPage.selectTab("����� �����");

        mainPage.selectTab("�����-�����");
        mainPage.getCrossRateFor("����", "������ ���");
        mainPage.getCrossRateFor("������ ���", "����");
        mainPage.getCrossRateFor("10 �������� ������", "100 ���������� ������");
        mainPage.getCrossRateFor("����", "100 ���������� ������");
        mainPage.getCrossRateFor("������ ���", "10 �������� ������");

        mainPage.getCrossRateFor("10 �������� ������", "100 ���������� ������");
        mainPage.getCrossRateFor("����", "������ ���");
        mainPage.getCrossRateFor("����", "100 ���������� ������");
        mainPage.getCrossRateFor("������ ���", "����");
        mainPage.getCrossRateFor("������ ���", "����");

        mainPage.selectTab("����� �����");
        mainPage.selectTab("�����-�����");

        mainPage.selectTab("����� �����");
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
        mainPage.selectConverterCurrency("������������� ������");
        mainPage.selectConverterCurrency("����������� �����");
        mainPage.setCurrencyAmount("������������� ������", "200");
        mainPage.setCurrencyAmount("����������� �����", "500");
        mainPage.selectBaseCurrency("������������� ������");
        mainPage.selectBaseCurrency("����������� �����");
        mainPage.setCalendarDate("2014-11-23");
        mainPage.reselectConverterCurrency("������������� ������", "XDR");
        mainPage.reselectConverterCurrency("����������� �����", "����������� �����");
        mainPage.selectConverterCurrency("���������� �����");
        mainPage.reselectConverterCurrency("���������� �����", "������� �����");
    }
}