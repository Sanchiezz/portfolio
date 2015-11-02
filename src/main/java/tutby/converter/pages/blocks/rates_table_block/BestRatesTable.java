package tutby.converter.pages.blocks.rates_table_block;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import tutby.converter.utils.Utils;

public class BestRatesTable extends HtmlElement implements CommonTable {

	private static final byte BANK_BUYS_RATE_CELL_NUMBER = 2;
	private static final byte BANK_SELLS_RATE_CELL_NUMBER = 3;

	@FindBy(xpath = ".//table[@class='k-table']")
	private WebElement table;

	public float getBestBuyRate(String currencyName) {

		String result = findCellByRowParameters(currencyName, BANK_BUYS_RATE_CELL_NUMBER);

		return Utils.currencyString2Float(result);
	}

	public float getBestSellRate(String currencyName) {

		String result = findCellByRowParameters(currencyName, BANK_SELLS_RATE_CELL_NUMBER);

		return Utils.currencyString2Float(result);
	}

	public void selectCurrencyByName(String currencyName) {
		WebElement currencyLink = table.findElement(By.xpath(".//tr/td/a[text()='" + currencyName + "']"));
		Assert.assertTrue((currencyLink.isDisplayed() && currencyLink.isEnabled()), "Link is disabled or absent");
		currencyLink.click();
	}

	private String findCellByRowParameters(String currencyName, int cellNumber) {
		return table.findElement(By.xpath(".//tr/td[text()='" + currencyName + "']/../td[" + cellNumber + "]")).getText();
	}
}