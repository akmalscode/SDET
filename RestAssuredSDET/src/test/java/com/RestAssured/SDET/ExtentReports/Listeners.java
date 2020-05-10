package com.RestAssured.SDET.ExtentReports;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {

	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;

	public ExtentTest test;

//	@BeforeTest
//	public void setExtent() {
//		// specify location of the report
//		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
//
//		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
//		htmlReporter.config().setReportName("Functional Testing"); // Name of the report
//		htmlReporter.config().setTheme(Theme.DARK);
//
//		extent = new ExtentReports();
//		extent.attachReporter(htmlReporter);
//
//		// Passing General information
//		extent.setSystemInfo("Host name", "localhost");
//		extent.setSystemInfo("Environemnt", "QA");
//		extent.setSystemInfo("user", "pavan");
//	}
//
//	public void onTestSuccess(ITestResult tr) {
//		logger = extent.createTest(tr.getName()); // create new entry in th report
//		logger.log(Status.PASS, MarkupHelper.createLabel(tr.getName(), ExtentColor.GREEN)); // send the passed
//																							// information to the report
//																							// with GREEN color
//																							// highlighted
//	}
//
//	public void onTestFailure(ITestResult tr) {
//		logger = extent.createTest(tr.getName()); // create new entry in th report
//		logger.log(Status.FAIL, MarkupHelper.createLabel(tr.getName(), ExtentColor.RED)); // send the passed information
//																							// to the report with GREEN
//																							// color highlighted
//
//		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + tr.getName() + ".png";
//		try {
//			logger.fail("Screenshot is below:" + logger.addScreenCaptureFromPath(screenshotPath));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	@AfterTest
//	public void endReport() {
//		extent.flush();
//	}

	//////

	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/Reports/myReport.html");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report
		htmlReporter.config().setReportName("Rest API Testing Report"); // name of the report
		// htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP); //for
		// version 3 of the chart
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "Employee Database API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Akmal");

	}

	public void onTestSuccess(ITestResult result) {
		test = extent.createTest(result.getClass().getName());
		test.createNode(result.getName());
		test = extent.createTest(result.getName()); // create new entry in th report

		test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
	}

	public void onTestFailure(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in th report
		// to add name in extent report
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName());
		// to add error/exception in extent report
		test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable());
		test.log(Status.FAIL, MarkupHelper.createLabel(result.getName(), ExtentColor.RED));
		// screenshot
		String screenshotPath = System.getProperty("user.dir") + "\\Screenshots\\" + result.getName() + ".png";
		try {
			test.fail("Screenshot is below:" + test.addScreenCaptureFromPath(screenshotPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName()); // create new entry in th report
		test.log(Status.SKIP,MarkupHelper.createLabel(result.getName(),ExtentColor.ORANGE));
	}

	public void onFinish(ITestContext testContext) {
		extent.flush();
	}

}
