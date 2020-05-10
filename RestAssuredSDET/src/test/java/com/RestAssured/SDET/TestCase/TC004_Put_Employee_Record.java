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

public class TC004_Put_Employee_Record extends TestBase {

	RequestSpecification httpRequest;
	Response response;

	String empName = RestUtilities.empName();
	String empSalary = RestUtilities.empSal();
	String empAge = RestUtilities.empAge();

	@BeforeClass
	public void createEmployee() throws InterruptedException {
		logger.info("*****Started TC003_Post_Employee_Record *****");

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		httpRequest = RestAssured.given();

		JSONObject requestParem = new JSONObject();
		requestParem.put("name", empName);
		requestParem.put("salary", empSalary);
		requestParem.put("age", empAge);
		// Add Header string the request body
		httpRequest.header("Content-Type", "application/json");

		// Add json to the body of request
		httpRequest.body(requestParem.toJSONString());

		response = httpRequest.request(Method.PUT, "/update/empID");

		Thread.sleep(9000);
	}

	@Test
	public void checkResponseBody() {
		String responseBody = response.getBody().asString();

		Assert.assertEquals(responseBody.contains(empName), true);
		Assert.assertEquals(responseBody.concat(empSalary), true);
		Assert.assertEquals(responseBody.contains(empAge), true);
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
}