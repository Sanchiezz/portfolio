package tutby.converter.pages.blocks.rates_table_block;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;

@FindBy(id = "tab-cross")
public class CrossRatesTableBlock extends HtmlElement {

	private CrossRatesTable crossRatesTable;

	@FindBy(xpath = ".//p[@class = 'all']")
	private Link allCrossRatesLink;

	public float getCrossRateByNames(String requiredCurrencyName, String baseCurrencyName) {
		return crossRatesTable.getCrossRateByNames(requiredCurrencyName, baseCurrencyName);
	}

	public void selectAllRatesLink() {
		if (allCrossRatesLink.isDisplayed()) allCrossRatesLink.click();
		//no assertions needed here, because new page opens, and assertion of opened page provided by page class
	}
}