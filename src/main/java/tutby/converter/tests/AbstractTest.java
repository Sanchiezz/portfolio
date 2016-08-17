package tutby.converter.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import tutby.converter.utils.ReportListener;
import tutby.converter.utils.Utils;

//@Listeners(ReportListener.class)
public abstract class AbstractTest {

	private static WebDriver driver;


	@BeforeClass
	public void maximizeWindow() {
		driver = new FirefoxDriver();
		driver.get("http://finance.tut.by");
		driver.manage().window().maximize();
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() {
		driver.quit();
	}

	public static WebDriver getDriver() {
		return driver;
	}

	protected float getExpectedResult(float todayRate, String inputCurrencyAmount) {
		return todayRate * Utils.currencyString2Float(inputCurrencyAmount);
	}
}