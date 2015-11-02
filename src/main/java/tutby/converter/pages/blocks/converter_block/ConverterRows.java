package tutby.converter.pages.blocks.converter_block;

import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import tutby.converter.utils.Utils;

import java.util.List;

@Name("Currency converter rows container")
@FindBy(xpath = ".//div[@class='converterRows']")
public class ConverterRows extends HtmlElement {

	@FindBy(xpath = ".//table")
	private List<ExtendedRow> extendedRowList;

	public void setCurrencyAmount(String currencyName, String currencyAmount) {
		int rowNumber = resolveRowByName(currencyName);
		ExtendedRow currentConverterRow = getCurrentConverterRow(rowNumber);
		currentConverterRow.setCurrencyAmount(currencyAmount);
	}

	public int getRowsNumber() {
		return extendedRowList.size();
	}

	public ExtendedRow getCurrentConverterRow(int rowNumber) {
		return this.extendedRowList.get(rowNumber - 1);
	}

	public String resolveAliasByName(String currencyName, int rowNumber) {
		return extendedRowList.get(rowNumber - 1).resolveAliasByName(currencyName);
	}

	/**
	 * Returns value of row input element.
	 *
	 * @param currencyName name of the currency
	 * @return value of {@code <input type="text">} input control
	 */
	public float getCurrencyAmount(String currencyName) {
		int rowNumber = resolveRowByName(currencyName);
		String inputValueString = getInputElementValue(rowNumber);
		return Utils.currencyString2Float(inputValueString);
	}

	public void reselectCurrency(String oldCurrencyName, String newCurrencyName) {
		int rowNumber = resolveRowByName(oldCurrencyName);
		selectCurrency(rowNumber, newCurrencyName);

	}

	public void selectCurrency(int rowNumber, String currencyName) {
		ExtendedRow currentConverterRow = getCurrentConverterRow(rowNumber);
		currentConverterRow.selectCurrencyByName(currencyName);
	}

	private String getInputElementValue(int rowNumber) {
		ExtendedRow currentConverterRow = getCurrentConverterRow(rowNumber);
		return currentConverterRow.getInputElementValue();
	}

	/**
	 * Resolves row number by specified name.
	 *
	 * @param currencyName name of the currency
	 * @return
	 */
	private int resolveRowByName(String currencyName) {
		int size = extendedRowList.size();
		for (int i = 0; i < size; i++) {
			String tempAlias = extendedRowList.get(i).getCurrencyAlias();
			String tempName = extendedRowList.get(i).resolveNameByAlias(tempAlias);

			if (tempName.equals(currencyName)) {
				return i + 1;
			}
		}
		return -1;
	}

	public void selectBaseCurrency(String currencyName) {
		int rowNumber = resolveRowByName(currencyName);
		extendedRowList.get(rowNumber - 1).selectRow();

		Assert.assertEquals(extendedRowList.get(rowNumber - 1).getAttribute("class"), "valSelect CWA", "Currency amount input control was not selected");
	}
}