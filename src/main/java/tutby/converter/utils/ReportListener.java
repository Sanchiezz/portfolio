package tutby.converter.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import tutby.converter.tests.AbstractTest;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.testng.TestNG.DEFAULT_OUTPUTDIR;

/*
 * метод makeScreenshot делает скриншот во время "падения" теста и возвращает объект типа ScreenshotDatePath, который содержит полный путь к файлу скриншота, и строку со датой и временем создания скриншота.
 * Строка с датой и временем может быть отформатированной, в соответствии с заданным параметром метода.
 */

public class ReportListener extends TestListenerAdapter {
	String dateFormat = "yyyy-MM-dd HH:mm:ss,SSSS";
	public static String customReportMessage = "custom reprot message!!";

	@Override
	public void onTestFailure(ITestResult tr) {
		ScreenshotDatePath screenshot = makeScreenshot("failure-screenshots", tr, AbstractTest.getDriver());
		Reporter.log("<a href=\"..\\" + screenshot.getScreenshotFullPath() + "\"><b>Screenshot</b></a> (Created on " + screenshot.getDate(this.dateFormat) + ")<br>");
		Reporter.log("<b>Assertion failure message:</b> " + screenshot.getAssertMessage() + "<br>");
		Reporter.log("<a href=\"..\\" + screenshot.getScreenshotFullPath() + "\"><img src=\"..\\" + screenshot.getScreenshotFullPath() + "\" width=\"800\"></a>");

		super.onTestFailure(tr);
	}

	@Override
	public void onFinish(ITestContext tc) {
		Reporter.log("<h1>onFinish(ITestContext tc)</h1>");
		super.onFinish(tc);
	}

	public ScreenshotDatePath makeScreenshot(String screenshotFolder, ITestResult tr, WebDriver driver) {
		Date d = new Date();
		SimpleDateFormat fileNameFormater = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss_SSSS");
		String destPath = new String(DEFAULT_OUTPUTDIR + "/" + screenshotFolder + "/" + tr.getName() + "_" + fileNameFormater.format(d.getTime()) + ".png");
		ScreenshotDatePath screenshotDatePath = new ScreenshotDatePath(new Long(d.getTime()).toString(), destPath, tr);

		if (driver != null) {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			try {
				FileUtils.copyFile(scrFile, new File(destPath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return screenshotDatePath;
	}
}