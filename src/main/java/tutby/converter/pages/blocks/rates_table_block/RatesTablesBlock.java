package tutby.converter.pages.blocks.rates_table_block;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

import java.util.List;

@FindBy(id = "tab-kurs")
public class RatesTablesBlock extends HtmlElement {

	@Name("'Все курсы' link")
	@FindBy(xpath = "div[@id]//p[@class = 'all']")
	private List<Link> allRatesLinkList;


	// four links of rate types
	@Name("NBRB link")
	@FindBy(xpath = ".//a[@href='#k-nbrb']")
	private Link nbrbLink;

	@Name("BVFB link")
	@FindBy(xpath = ".//a[@href='#k-bvfb']")
	private Link bvfbLink;

	@Name("CBRF link")
	@FindBy(xpath = ".//a[@href='#k-cbrf']")
	private Link cbrfLink;

	@Name("Best rates link")
	@FindBy(xpath = ".//a[@href='#k-best']")
	private Link bestRatesLink;


	//four tables of rate types
	@Name("Table of NBRB currency rates")
	@FindBy(xpath = "div[@id='k-nbrb']")
	private RatesTable nbrbRatesTable;

	@Name("Table of BVBF currency rates")
	@FindBy(xpath = "div[@id='k-bvfb']")
	private RatesTable bvfbRatesTable;

	@Name("Table of CBRF currency rates")
	@FindBy(xpath = "div[@id='k-cbrf']")
	private RatesTable cbrfRatesTable;

	@Name("Table of best currency rates")
	@FindBy(xpath = "div[@id='k-best']")
	private BestRatesTable bestRatesTable;

	//buffer field that represents any kind of table with rates
	@Name("Selected table")
	private CommonTable currentTable = nbrbRatesTable;

	public void selectAllRatesLink() {
		for (Link allRatesLink : allRatesLinkList) {
			if (allRatesLink.isDisplayed() && allRatesLink.isEnabled()) allRatesLink.click();
		}
	}

	public void selectNbrbLink() {
		Assert.assertTrue((nbrbLink.isDisplayed() & nbrbLink.isEnabled()), nbrbLink.getName() + " is absent or disabled");
		nbrbLink.click();
		currentTable = nbrbRatesTable;
		Assert.assertTrue(this.findElement(By.xpath("ul/li[1]")).getAttribute("class").equals("active"), nbrbLink.getName() + " was not selected");
		Assert.assertTrue(nbrbRatesTable.isDisplayed(), nbrbRatesTable.getName() + " is not displayed");
	}

	public void selectBvfbLink() {
		Assert.assertTrue((bvfbLink.isDisplayed() & bvfbLink.isEnabled()), bvfbLink.getName() + " is absent or disabled");
		bvfbLink.click();
		currentTable = bvfbRatesTable;
		Assert.assertTrue(this.findElement(By.xpath("ul/li[2]")).getAttribute("class").equals("active"), bvfbLink.getName() + " was not selected");
		Assert.assertTrue(bvfbRatesTable.isDisplayed(), bvfbRatesTable.getName() + " is not displayed");
	}

	public void selectCbrfLink() {
		Assert.assertTrue((cbrfLink.isDisplayed() & cbrfLink.isEnabled()), cbrfLink.getName() + " is absent or disabled");
		cbrfLink.click();
		currentTable = cbrfRatesTable;
		Assert.assertTrue(this.findElement(By.xpath("ul/li[3]")).getAttribute("class").equals("active"), cbrfLink.getName() + " was not selected");
		Assert.assertTrue(cbrfRatesTable.isDisplayed(), cbrfRatesTable.getName() + " is not displayed");
	}

	public void selectBestRatesLink() {
		Assert.assertTrue((bestRatesLink.isDisplayed() & bestRatesLink.isEnabled()), bestRatesLink.getName() + " is absent or disabled");
		bestRatesLink.click();
		currentTable = bestRatesTable;
		Assert.assertTrue(this.findElement(By.xpath("ul/li[4]")).getAttribute("class").equals("active"), bestRatesLink.getName() + " was not selected");
		Assert.assertTrue(bestRatesTable.isDisplayed(), bestRatesTable.getName() + " is not displayed");
	}


	public float getCurrencyRateByName(String currencyName) {
		return ((RatesTable) currentTable).getCurrencyRateByName(currencyName);
	}

	public float getBestBuyRate(String currencyName) {
		return bestRatesTable.getBestBuyRate(currencyName);
	}

	public float getBestSellRate(String currencyName) {
		return bestRatesTable.getBestSellRate(currencyName);
	}

	public void selectCurrencyByName(String currencyName) {
		currentTable.selectCurrencyByName(currencyName);
		//no asserts required here, because new page opens, and assertion of opened page provided by page class itself
	}
}