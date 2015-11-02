package tutby.converter.utils;

import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ScreenshotDatePath {

	private String screenshotDateTime;
	private String screenshotFullPath;
	private String assertMessage;

	public ScreenshotDatePath(String screenshotDateTime, String screenshotFullPath, ITestResult tr) {
		this.screenshotDateTime = screenshotDateTime;
		this.screenshotFullPath = screenshotFullPath;
		this.assertMessage = tr.getThrowable().getMessage();
	}

	public String getDate(String dateFormat) {
		return new SimpleDateFormat(dateFormat).format(new Date(Long.parseLong(this.screenshotDateTime)));
	}

	public String getScreenshotFullPath() {
		return screenshotFullPath;
	}

	public String getAssertMessage() {
		return this.assertMessage;
	}
}