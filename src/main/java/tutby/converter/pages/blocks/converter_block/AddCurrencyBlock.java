package tutby.converter.pages.blocks.converter_block;

import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Add currency row container")
@FindBy(xpath = ".//div[@class='add_valuta']")
public class AddCurrencyBlock extends HtmlElement {

	@FindBy(xpath = ".//table")
	private SimpleRow addCurrencyElement;

	public void addCurrency(String currencyName) {
		addCurrencyElement.selectCurrencyByName(currencyName);
	}
}