package com.RestAssured.SDET.TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class T005_GET_JSON_RESPONSE {

	@Test
	void getWeatherDetails() {
		// specified the url
		RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		// request object
		RequestSpecification httpRequest = RestAssured.given();
		
		// response object
		Response response = httpRequest.request(Method.GET, "/Delhi");
		
		// print response
		String responBody = response.getBody().asString();
		System.out.println("Response Body is : " + responBody);
		
		Assert.assertEquals(responBody.contains("Delhi"), true);
		
	}
}