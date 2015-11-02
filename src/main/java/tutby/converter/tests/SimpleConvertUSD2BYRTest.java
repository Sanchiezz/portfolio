package tutby.converter.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tutby.converter.pages.MainPage;

public class SimpleConvertUSD2BYRTest extends AbstractTest {

	@Test(description = "Scenario #1. Simple currency calculation test. Check correct calculation for 100 USD, for today date")
	@Parameters({"InputCurrencyName", "OutputCurrencyName", "InputCurrencyAmount"})
	public void Test(String inputCurrencyName, String outputCurrencyName, String inputCurrencyAmount) {

		float todayRateUSD, todayRateEUR;
		float expectedResultUSD, expectedResultEUR;
		float actualResultUSD, actualResultEuro;

		MainPage mainPage = new MainPage(getDriver());
		mainPage.assertIsOpened();// assert if, defined inside method, control appears on the page

		mainPage.selectTab("КУРСЫ ВАЛЮТ");// select "КУРСЫ ВАЛЮТ" tab

		mainPage.selectNbrbRatesTable(); //select table under "НБРБ" link
		todayRateUSD = mainPage.getTodayRate(inputCurrencyName); //get rate for USD and assigns its value to todayRateUSD variable
		expectedResultUSD = getExpectedResult(todayRateUSD, inputCurrencyAmount); //multiply todayRateUSD by 100 and assigns result to expectedResultUSD variable

		//1st part (simple positive actions)
		mainPage.selectConverterCurrency(inputCurrencyName); //1st invoke - set first row selector to USD
		mainPage.selectConverterCurrency(outputCurrencyName); //2nd invoke - set second row selector to BYR
		mainPage.setCurrencyAmount(inputCurrencyName, inputCurrencyAmount); //set 100 to USD row
		actualResultUSD = mainPage.getCurrencyAmount(outputCurrencyName); //assign result from BYR row to actualResultUSD variable
		Assert.assertEquals(actualResultUSD, expectedResultUSD); //compare actual result and expected result of  USD->BYR calculation
	}
}