package tutby.converter.pages;

import org.openqa.selenium.WebDriver;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import tutby.converter.pages.blocks.converter_block.CurrencyConverter;
import tutby.converter.pages.blocks.rates_table_block.BWidget;

public class MainPage extends AbstractPage {

	private BWidget bWidget; // element for rates tables block

	@Timeout(20)//such huge timeout required, because visibility of this block is asserted on the page load
	private CurrencyConverter currencyConverter; //element for currency converter block itself

	private int currentRowPosition = 1;

	public MainPage(WebDriver driver) {
		super(driver);
	}

	/**
	 * Refresh current page and return new object
	 *
	 * @return
	 */
	public MainPage refresh() {
		super.driver.navigate().refresh();
		currentRowPosition = 1;
		return new MainPage(super.driver);
	}

	/**
	 * Method asserts if currency converter block {@code <div id="currency_converter">} is visible on the page.
	 */
	public void assertIsOpened() {
		assertPageIsOpened(currencyConverter);
	}

	/**
	 * Selects "КУРСЫ ВАЛЮТ" tab.
	 */
	@Deprecated
	public void selectRatesTab() {
		bWidget.selectRatesTab();
	}

	/**
	 * Selects "КРОСС-КУРСЫ" tab
	 */
	@Deprecated
	public void selectCrossRatesTab() {
		bWidget.selectCrossRatesTab();
	}

	public void selectTab(String tabName) {
		bWidget.selectTab(tabName);
	}

	/**
	 * Selects currency rates table under "НБРБ" link
	 */
	public void selectNbrbRatesTable() {
		bWidget.selectNbrbLink();
	}

	/**
	 * Selects currency rates under "БВФБ" link
	 */
	public void selectBvfbRatesTable() {
		bWidget.selectBvfbLink();
	}

	/**
	 * Selects currency rates under "ЦБРФ" link
	 */
	public void selectCbrfRatesTable() {
		bWidget.selectCbrfLink();
	}

	/**
	 * Selects currency rates under "Лучшие курсы" link
	 */
	public void selectBestRatesTable() {
		bWidget.selectBestRatesLink();
	}

	/**
	 * Gets rate of the specified currency, under "����" link, that is actual for current moment.
	 *
	 * @param currencyName name of the currency, which should be found in the rates table under "����" link.
	 *                     List of currency names can be found in drop-down list of any converter's row, or in current
	 *                     table, or in the extended table of currency rates.
	 * @return actual rate of the specified currency.
	 */
	public float getTodayRate(String currencyName) {
		selectNbrbRatesTable();
		return bWidget.getCurrencyRateByName(currencyName);
	}

	/**
	 * Selects "Лучшие курсы" table, and gets buy rate for specified currency
	 *
	 * @param currencyName name of the currency
	 * @return buy rate of the specified currency
	 */
	public float getBestBuyRateFor(String currencyName) {
		selectBestRatesTable();
		return bWidget.getBestBuyRate(currencyName);
	}


	/**
	 * Selects "Лучшие курсы" table, and gets sell rate for specified currency
	 *
	 * @param currencyName name of the currency
	 * @return sell rate of the specified currency
	 */
	public float getBestSellRateFor(String currencyName) {
		selectBestRatesTable();
		return bWidget.getBestSellRate(currencyName);
	}

	/**
	 * Answers to the question: how much returnCurrencyName would cost ONE unit of the baseCurrencyName?
	 * <p>
	 * Example case.
	 * <p>
	 * Question: How much Euros would cost one unit of Dollar currency?
	 * Answer: One Dollar should cost 0.91 Euros.
	 * <p>
	 * Dollar - base currency
	 * Euro - returned currency
	 * <p>
	 * quotient of division of Dollar by Euro should be found in row, which contains text "Euro"
	 *
	 * @param requiredCurrencyName name of the currency, which amount should be found
	 * @param baseCurrencyName     name of the currency, which one unit cost should be found in other currencies
	 * @return first cell value of the row that contains name of returnCurrencyName
	 */
	public float getCrossRateFor(String requiredCurrencyName, String baseCurrencyName) {
		return bWidget.getCrossRateByNames(requiredCurrencyName, baseCurrencyName);
	}

	public AllRatesPage openAllRatesPage() {
		bWidget.selectAllRatesLink();
		return new AllRatesPage(driver);
	}

	public AllCrossRatesPage openAllCrossRatesPage() {
		bWidget.selectAllCrossRatesLink();
		return new AllCrossRatesPage(driver);
	}


	///////////////////////////////////////////////////////////////////////////////////////////////////////////////

	/**
	 * Sets specified date in converter calendar.
	 *
	 * @param date date of rates, which should be considered during conversion.
	 */
	public void setCalendarDate(String date) {
		currencyConverter.setConverterCalendarDate(date);
	}

	/**
	 * Selects specified currency consequentially in existing currency converter rows or adds new one
	 *
	 * @param currencyName
	 */
	public void selectConverterCurrency(String currencyName) {

		if (currentRowPosition <= currencyConverter.getConverterRowsNumber()) {
			currencyConverter.selectCurrency(currentRowPosition, currencyName);
			currentRowPosition++;
			return;
		}

		currencyConverter.addNewCurrency(currencyName);
		currentRowPosition++;
		return;
	}

	/**
	 * Selects new currency name instead of existing
	 *
	 * @param oldCurrencyName old name of currency (existing)
	 * @param newCurrencyName new name of currency
	 */
	public void reselectConverterCurrency(String oldCurrencyName, String newCurrencyName) {
		currencyConverter.reselectCurrency(oldCurrencyName, newCurrencyName);
	}

	/**
	 * Sets value of currency amount for specified currency
	 *
	 * @param currencyName   name of currency
	 * @param currencyAmount amount of specified currency
	 */
	public void setCurrencyAmount(String currencyName, String currencyAmount) {
		currencyConverter.setCurrencyAmount(currencyName, currencyAmount);
	}

	/**
	 * Clicks on input control of specified currency field
	 *
	 * @param currencyName name of the currency
	 */
	public void selectBaseCurrency(String currencyName) {
		currencyConverter.selectBaseCurrency(currencyName);
	}

	/**
	 * Gets amount of specified currency
	 *
	 * @param currencyName name of currency
	 * @return amount of specified currency
	 */
	public float getCurrencyAmount(String currencyName) {
		return currencyConverter.getCurrencyAmount(currencyName);
	}
}