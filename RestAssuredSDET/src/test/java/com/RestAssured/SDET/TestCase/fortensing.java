
package com.RestAssured.SDET.TestCase;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.RestAssured.SDET.BASE.TestBase;
import com.RestAssured.SDET.Utilities.RestUtilities;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class fortensing extends TestBase {

	@BeforeClass
	public void createEmployee() throws InterruptedException {
		logger.info("*****Started TC003_Post_Employee_Record *****");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		RequestSpecification httpRequest = RestAssured.given();

		JSONObject requestParem = new JSONObject();
		requestParem.put("name", "rony");
		requestParem.put("salary", "2111");
		requestParem.put("age", "2333");

		// Add Header string the request body
		httpRequest.header("Content-Type", "application/json");
		// Add json to the body of request
		httpRequest.body(requestParem.toJSONString());

		Response response = httpRequest.request(Method.POST, "/create");

		Thread.sleep(9000);

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
	public void checkContaintType() {
		logger.info("***Checking Content Type ****");
		String contentType = response.header("Content-Type");
		logger.info("Content Time " + contentType);
		Assert.assertEquals(contentType, "application/json;charset=utf-8");

	}

	@Test
	public void checkServerType() {
		logger.info("***Checking Server Type ****");
		String contentType = response.header("Server");
		logger.info("Content Time " + contentType);
		Assert.assertEquals(contentType, "nginx/1.16.0");

	}

	@Test
	public void tearDown() {
		logger.info("***Finishd  TC001_Get_All_Employees****");
	}

}
