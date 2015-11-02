package tutby.converter.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;

public abstract class AbstractPage {

	protected final WebDriver driver;

	public AbstractPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(new HtmlElementDecorator(driver), this);
		//HtmlElementLoader.populatePageObject(this, driver);
	}


	protected void assertPageIsOpened(HtmlElement element) {
		Assert.assertTrue(element.isDisplayed(), "Element not found");
	}

	public void pause(int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}