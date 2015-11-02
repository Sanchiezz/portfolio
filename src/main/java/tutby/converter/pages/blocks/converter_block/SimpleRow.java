package tutby.converter.pages.blocks.converter_block;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.annotations.Timeout;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

public class SimpleRow extends HtmlElement {

	@FindBy(xpath = ".//button")
	private WebElement button;

	@Timeout(10)
	@Name("Currency names list")
	@FindBy(xpath = ".//div[@class='dropdown-menu open']")
	private CurrencyNamesListBlock currencyNamesListBlock;

	public void selectCurrencyByName(String currencyName) {
		this.button.click();
		Assert.assertTrue(currencyNamesListBlock.isDisplayed(), currencyNamesListBlock.getName() + " was not opened.");
		currencyNamesListBlock.selectCurrency(currencyName);
	}

	public String getCurrencyAlias() {
		return button.getAttribute("data-title");
	}

	public String resolveNameByAlias(String currencyAlias) {
		return currencyNamesListBlock.resolveNameByAlias(currencyAlias);
	}

	public String resolveAliasByName(String currencyName) {
		return currencyNamesListBlock.resolveAliasByName(currencyName);
	}
}