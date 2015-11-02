package tutby.converter.pages.blocks.rates_table_block;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

@FindBy(xpath = "//div[@class='b-widget']")//default locator (at finance.tut.by page)
public class BWidget extends HtmlElement {

	@Name("Tabs container")
	@FindBy(xpath = "ul[@class='b-widget-tabs']")
	private WebElement widgetTabs;

	@Name("'КУРСЫ ВАЛЮТ' tab")
	@FindBy(xpath = ".//a[@href='#tab-kurs']")
	private Link ratesTabLink;

	@Name("'КРОСС-КУРСЫ' tab")
	@FindBy(xpath = ".//a[@href='#tab-cross']")
	private Link crossRatesTabLink;

	private RatesTablesBlock ratesTablesBlock;

	private CrossRatesTableBlock crossRatesTableBlock;


	/**
	 * Clicks on "КУРСЫ ВАЛЮТ" tab
	 */
	@Deprecated
	public void selectRatesTab() {
		Assert.assertTrue((ratesTabLink.isDisplayed() && ratesTabLink.isEnabled()), ratesTabLink.getName() + " is disabled or absent.");
		ratesTabLink.click();
		Assert.assertTrue(this.findElement(By.xpath("ul/li[1]")).getAttribute("class").equals("widget-tabs__li active"), ratesTabLink.getName() + " was not selected.");
		Assert.assertTrue(ratesTablesBlock.getAttribute("class").equals("tab-pane active"), "Cross rates table has not appeared.");
	}

	/**
	 * Clicks on "КРОСС-КУРСЫ" tab
	 */
	@Deprecated
	public void selectCrossRatesTab() {
		Assert.assertTrue((crossRatesTabLink.isDisplayed() && crossRatesTabLink.isEnabled()), crossRatesTabLink.getName() + " is disabled or absent.");
		crossRatesTabLink.click();
		Assert.assertTrue(this.findElement(By.xpath("ul/li[2]")).getAttribute("class").equals("widget-tabs__li active"), crossRatesTabLink.getName() + " was not selected.");
		Assert.assertTrue(crossRatesTableBlock.getAttribute("class").equals("tab-pane active"), "Cross rates table has not appeared.");
	}

	/**
	 * Selects tab by specified name
	 *
	 * @param tabName name of the tab in "b-widget" block
	 */
	public void selectTab(String tabName) {
		Assert.assertTrue((widgetTabs.findElement(By.linkText(tabName)).isDisplayed() && widgetTabs.findElement(By.linkText(tabName)).isEnabled()), tabName + "tab link is disabled or not displayed on the page.");
		widgetTabs.findElement(By.linkText(tabName)).click();
		Assert.assertTrue(widgetTabs.findElement(By.linkText(tabName)).findElement(By.xpath("..")).getAttribute("class").equals("widget-tabs__li active"), tabName + " tab was not selected.");
	}

	/**
	 * Selects "НБРБ" link of current block
	 */
	public void selectNbrbLink() {
		ratesTablesBlock.selectNbrbLink();
	}

	/**
	 * Selects "БВФБ" link of current block
	 */
	public void selectBvfbLink() {
		ratesTablesBlock.selectBvfbLink();
	}

	/**
	 * Selects "ЦБРФБ" link of current block
	 */
	public void selectCbrfLink() {
		ratesTablesBlock.selectCbrfLink();
	}

	/**
	 * Selects "Лучшие курсы" link of current block
	 */
	public void selectBestRatesLink() {
		ratesTablesBlock.selectBestRatesLink();
	}

	/**
	 * Gets rate of the specified currency in visible table, by invoking according method in RatesTableBlock class
	 *
	 * @param currencyName name of the currency
	 * @return rate of the specified currency
	 */
	public float getCurrencyRateByName(String currencyName) {
		return ratesTablesBlock.getCurrencyRateByName(currencyName);
	}

	/**
	 * Gets buy rate of the specified currency in "Лучшие курсы" table
	 *
	 * @param currencyName name of the currency
	 * @return buy rate of the specified currency
	 */
	public float getBestBuyRate(String currencyName) {
		return ratesTablesBlock.getBestBuyRate(currencyName);
	}

	/**
	 * Gets sell rate for specified currency in "Лучшие курсы" table
	 *
	 * @param currencyName name of the currency
	 * @return sell rate of the specified currency
	 */
	public float getBestSellRate(String currencyName) {
		return ratesTablesBlock.getBestSellRate(currencyName);
	}

	/**
	 * Clicks on currency name, which represented as link, and appropriate page opens as result
	 *
	 * @param currencyName name of the currency
	 */
	public void selectCurrencyByName(String currencyName) {
		ratesTablesBlock.selectCurrencyByName(currencyName);
	}

	/**
	 * Selects "Все курсы" link of the selected table in case if this link is visible
	 */
	public void selectAllRatesLink() {
		ratesTablesBlock.selectAllRatesLink();
	}

	/**
	 * @param requiredCurrencyName
	 * @param baseCurrencyName
	 * @return
	 */
	public float getCrossRateByNames(String requiredCurrencyName, String baseCurrencyName) {
		return crossRatesTableBlock.getCrossRateByNames(requiredCurrencyName, baseCurrencyName);
	}

	/**
	 * Clicks on "Все курсы" link, if this link is present
	 */
	public void selectAllCrossRatesLink() {
		crossRatesTableBlock.selectAllRatesLink();
	}
}