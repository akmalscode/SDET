package com.RestAssured.SDET.TestCase;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssured.SDET.BASE.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC002_Get_Single_Employee_Record extends TestBase {

	@BeforeClass
	public void getAllEmployees() throws InterruptedException {
		logger.info(" Started TC001_Get_All_Employees ");
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET, "/employee/" + empID);

		Thread.sleep(6000);
	}

	@Test
	public void checkResponseBody() {
		logger.info("***Checking response body****");
		String responseBody = response.getBody().asString();

		logger.info("***Response Body ****" + responseBody);
		Assert.assertEquals(responseBody.contains(empID), true);

	}

	@Test
	public void checkStatusCode() {
		logger.info("***Checking status code ****");

		int statusCode = response.getStatusCode();
		logger.info("statuscode is " + statusCode);
		Assert.assertEquals(statusCode, 200);

	}

	@Test
	public void checkStatusLine() {
		logger.info("***Checking status code ****");

		String statusLine = response.getStatusLine();
		logger.info("statuscode is " + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");

	}

	@Test
	public void checkResponseTime() {
		logger.info("***Checking Response Time ****");
		long responseTime = response.getTime();
		logger.info("Response Time " + responseTime);

		if (responseTime > 2000) {
			logger.warn("Response time is grater then 2000");

			Assert.assertTrue(responseTime < 2000);
		}
	}

	@Test
	public void checkServerType() {
		logger.info("***Checking Server Type ****");
		String contentType = response.header("Server");
		logger.info("Content Time " + contentType);
		Assert.assertEquals(contentType, "nginx/1.16.0");

	}

	@Test
	public void checkContentType() {
		logger.info("***Checking Content  Type ****");
		String serverType = response.header("Content-Type");
		logger.info("Server Time " + serverType);
		Assert.assertEquals(serverType, "text/html;charset=UTF-8");

	}

	@Test
	public void checkingEncoding() {
		logger.info("***Checking Server Type ****");
		String encodingType = response.header("Content-Encoding");
		logger.info("Encoding Type is  " + encodingType);
		Assert.assertEquals(encodingType, "gzip");
	}

	@Test
	public void getCookies() {
		logger.info("***Checking Cookies ****");
		String cookies = response.getCookie("PHPSESSID");
		System.out.println(cookies);
	}

	@Test
	public void tearDown() {
		logger.info("***Finishd  TC001_Get_All_Employees****");
	}

}
