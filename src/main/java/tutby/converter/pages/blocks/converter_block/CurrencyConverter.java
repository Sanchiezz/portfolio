package tutby.converter.pages.blocks.converter_block;

import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;

@Name("Currency converter block")
@FindBy(id = "currency_converter")
public class CurrencyConverter extends HtmlElement {

	private ConverterCalendar converterCalendar;

	private ConverterRows converterRows;

	private AddCurrencyBlock addCurrencyBlock;


	public int getConverterRowsNumber() {
		return converterRows.getRowsNumber();
	}

	public void setConverterCalendarDate(String dateString) {
		converterCalendar.open();
		converterCalendar.setDate(dateString);
	}

	public String getCalendarLinkText() {
		return converterCalendar.getCalendarLinkText();
	}

	public void reselectCurrency(String oldCurrencyName, String newCurrencyName) {
		converterRows.reselectCurrency(oldCurrencyName, newCurrencyName);

	}

	public void setCurrencyAmount(String currencyName, String currencyAmount) {
		converterRows.setCurrencyAmount(currencyName, currencyAmount);
	}

	public float getCurrencyAmount(String currencyName) {
		return converterRows.getCurrencyAmount(currencyName);
	}

	public void selectBaseCurrency(String currencyName) {
		converterRows.selectBaseCurrency(currencyName);
	}

	public void selectCurrency(int rowNumber, String currencyName) {
		converterRows.selectCurrency(rowNumber, currencyName);
		assertActualCurrencyAlias(currencyName, rowNumber, "Expected currency alias was not found in the converter row.");
	}

	public void addNewCurrency(String currencyName) {
		addCurrencyBlock.addCurrency(currencyName);
		assertActualCurrencyAlias(currencyName, converterRows.getRowsNumber(), "Expected currency alias was not found in the converter row.");
	}

	private void assertActualCurrencyAlias(String currencyName, int rowNumber, String message) {
		String actualAlias = converterRows.getCurrentConverterRow(rowNumber).getCurrencyAlias();
		String expectedAlias = converterRows.resolveAliasByName(currencyName, rowNumber);
		Assert.assertEquals(actualAlias, expectedAlias, message);
	}
}