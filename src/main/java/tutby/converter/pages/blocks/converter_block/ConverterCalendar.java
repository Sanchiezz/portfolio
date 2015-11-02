package tutby.converter.pages.blocks.converter_block;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.Select;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Name("Calendar block for currency converter")
@FindBy(id = "convert_calendar")
public class ConverterCalendar extends HtmlElement {


	@FindBy(xpath = ".//a[@class='init-calendar']")
	Link calendarLink;

	@Name("Year selector control")
	@FindBy(xpath = ".//select[@data-calendar='y']")
	Select yearSelector;

	@Name("Month selector control")
	@FindBy(xpath = ".//select[@data-calendar='m']")
	Select monthSelector;

	@FindBy(xpath = ".//table[@class='ac-calendar']//span/a")
	List<WebElement> daysList;


	public void open() {
		calendarLink.click();
		Assert.assertTrue(this.isDisplayed(), "Calendar is not displayed");
	}

	public String getCalendarLinkText() {
		return calendarLink.getText();
	}

	public void setDate(String dateString) {

		Calendar calendarObject = parseStringToCalendar("yyyy-MM-dd", dateString);

		isDateCorrect(calendarObject);

		//parse date object to three separate part;
		int year = calendarObject.get(Calendar.YEAR);
		int month = calendarObject.get(Calendar.MONTH);
		int day = calendarObject.get(Calendar.DAY_OF_MONTH);

		setYear(year);
		setMonth(month);
		setDay(day);
	}

	private boolean isDateCorrect(Calendar dateObject) {
		Calendar today = Calendar.getInstance();
		today.setTime(new Date());

		//only 10 previous years could be selected in the control
		int yearDifference = today.get(Calendar.YEAR) - dateObject.get(Calendar.YEAR);
		Assert.assertTrue(yearDifference < 10, "Year selection error. Year should be equals or greater than " + (Calendar.getInstance().get(Calendar.YEAR) - 10) + " year");

		//check if date in future (future dates not allowed)
		Assert.assertTrue(today.compareTo(dateObject) > 0, "The date should not be from future");

		return true;
	}

	private Calendar parseStringToCalendar(String format, String dateString) {
		DateFormat dateFormat = new SimpleDateFormat(format);
		Calendar result = Calendar.getInstance();
		Date temp = null;

		try {
			temp = dateFormat.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		result.setTime(temp);

		return result;
	}

	private void setYear(int year) {
		yearSelector.selectByValue(String.valueOf(year));
		Assert.assertEquals(Integer.parseInt(yearSelector.getFirstSelectedOption().getText()), year, "Incorrect year displayed in year selector.");
	}

	/**
	 * Selects month in selector by month number
	 *
	 * @param month number of the month
	 */
	private void setMonth(int month) {
		monthSelector.selectByValue(String.valueOf(month));
		Assert.assertEquals(Integer.parseInt(monthSelector.getFirstSelectedOption().getAttribute("value")), month, "Incorrect month displayed in month selector.");
	}

	/**
	 * Selects month in selector by month name in same control
	 *
	 * @param monthName name of month
	 */
	private void setMonth(String monthName) {
		setMonth(resolveMonthNumberByName(monthName));
	}

	private void setDay(int day) {

		for (WebElement anyDay : daysList) {
			if (anyDay.getText().equals(String.valueOf(day))) {
				anyDay.click();
				Assert.assertTrue(this.calendarLink.getText().contains(String.valueOf(day)), "Incorrect day displayed in calendar link.");
				return;
			}
		}
	}

	private int resolveMonthNumberByName(String monthName) {
		List<WebElement> monthsList = monthSelector.getOptions();

		for (WebElement month : monthsList) {
			if (month.getText().equals(monthName)) {
				return Integer.parseInt(month.getAttribute("value")) + 1;
			}
		}
		return -1;
	}
}