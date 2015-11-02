package tutby.converter.pages.blocks.converter_block;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ExtendedRow extends SimpleRow {

	//button control element defined in base class

	@FindBy(xpath = ".//input[@type='text']")
	private WebElement inputElement;

	public String getInputElementValue() {
		return inputElement.getAttribute("value");
	}

	public void setCurrencyAmount(String currencyAmount) {
		inputElement.sendKeys(currencyAmount);
	}

	public void selectRow() {
		this.inputElement.click();
	}
}