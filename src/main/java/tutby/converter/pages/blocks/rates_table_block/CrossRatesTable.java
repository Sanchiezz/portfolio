package tutby.converter.pages.blocks.rates_table_block;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Cross rates table")
@FindBy(xpath = ".//table[@class='k-table']")
public class CrossRatesTable extends HtmlElement {

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
	public float getCrossRateByNames(String requiredCurrencyName, String baseCurrencyName) {
		selectCurrencyByName(baseCurrencyName);//for example: click on dollar

		Assert.assertEquals(getRateByName(baseCurrencyName), 1.00f, 0f, "Cross rate for selected currency (" + baseCurrencyName + ") was not recalculated");
		return getRateByName(requiredCurrencyName);//for example: return amount of Euros
	}

	private void selectCurrencyByName(String currencyName) {
		String linkLocator = ".//tr/td/a[text()='" + currencyName + "']";
		this.findElement(By.xpath(linkLocator)).click();
		Assert.assertEquals(this.findElement(By.xpath(linkLocator)).findElement(By.xpath("../..")).getAttribute("class"), "active");
	}

	private float getRateByName(String currencyName) {
		String linkLocator = ".//tr/td/a[text()='" + currencyName + "']";

		String result = this.findElement(By.xpath(linkLocator)).findElement(By.xpath("../../td[1]")).getText();

		try {
			result = result.replaceAll("\\s", "");
			return Float.parseFloat(result);
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}