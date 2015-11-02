package tutby.converter.pages.blocks.converter_block;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

import java.util.List;


public class CurrencyNamesListBlock extends HtmlElement {

	@FindBy(xpath = ".//ul/li/a/span")
	private List<WebElement> currencyList;


	/**
	 * Clicks on currency name in dropdown menu
	 *
	 * @param currencyName name or alias of currency
	 */
	public void selectCurrency(String currencyName) {

		for (WebElement name : currencyList) {
			String str = name.getText();
			if (str.contains(currencyName)) {
				name.click();
				break;
			}
		}
		Assert.assertFalse(this.isDisplayed(), "Currency names list is displayed. No currency with name \"" + currencyName + "\" was found.");
	}

	/**
	 * Resolves full name for specified currency alias.
	 *
	 * @param currencyAlias currency alias
	 * @return name of currency
	 */
	public String resolveNameByAlias(String currencyAlias) {

		for (WebElement currency : currencyList) {
			String mixedName = getCurrencyMixedName(currency);
			String parsedAlias = mixedName.substring(0, 3);

			if (parsedAlias.equals(currencyAlias)) {
				return mixedName.substring(3);
			}
		}
		return null;
	}

	/**
	 * Resolver alias for specified currency name
	 *
	 * @param currencyName name of the currency
	 * @return currency alias
	 */
	public String resolveAliasByName(String currencyName) {
		for (WebElement currency : currencyList) {
			String mixedName = getCurrencyMixedName(currency);
			String parsedName = mixedName.substring(3);

			if (parsedName.equals(currencyName)) {
				return mixedName.substring(0, 3);
			}
		}
		return null;
	}

	private String getCurrencyMixedName(WebElement element) {
		String innerHtml = element.getAttribute("innerHTML");
		return innerHtml.replaceAll("<[^>]*>", "");//remove all html tags in text and return currency alias + currency name string
	}
}