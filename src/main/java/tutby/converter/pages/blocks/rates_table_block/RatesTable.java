package tutby.converter.pages.blocks.rates_table_block;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import tutby.converter.utils.Utils;

public class RatesTable extends HtmlElement implements CommonTable {

	@FindBy(xpath = ".//table[@class='k-table']")
	private WebElement table;

	public float getCurrencyRateByName(String currencyName) {
		String result = table.findElement(By.xpath(".//tr/td/a[text()='" + currencyName + "']/../../td[2]")).getText();
		return Utils.currencyString2Float(result);
	}

	public void selectCurrencyByName(String currencyName) {
		WebElement currencyLink = table.findElement(By.xpath(".//tr/td/a[text()='" + currencyName + "']"));
		Assert.assertTrue((currencyLink.isDisplayed() && currencyLink.isEnabled()), "Link is disabled or absent");
		currencyLink.click();
	}
}