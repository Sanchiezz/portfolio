package tutby.converter.tests;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import tutby.converter.pages.MainPage;
import tutby.converter.utils.DateUtils;

import java.util.List;

public class WeekendRateTest extends AbstractTest {

	@Test(description = "Scenario #2.Check that rate did not change on the last weekend (i.e. currency rate for saturday equals to sunday's rate)")
	@Parameters({"InputCurrencyName2", "OutputCurrencyName2", "InputCurrencyAmount2"})
	public void Test(String inputCurrencyName, String outputCurrencyName, String inputCurrencyAmount) {
		MainPage mainPage = new MainPage(getDriver());
		mainPage.assertIsOpened();
		List<String> weekendDates = DateUtils.getLastWeekEndDates();
		mainPage.setCalendarDate(weekendDates.get(0));
		mainPage.selectConverterCurrency(inputCurrencyName);
		mainPage.selectConverterCurrency(outputCurrencyName);
		mainPage.setCurrencyAmount(inputCurrencyName, inputCurrencyAmount);
		float saturdayRate = mainPage.getCurrencyAmount(outputCurrencyName);

		mainPage.setCalendarDate(weekendDates.get(1));
		float sundayRate = mainPage.getCurrencyAmount(outputCurrencyName);

		Assert.assertEquals(saturdayRate, sundayRate);
	}
}