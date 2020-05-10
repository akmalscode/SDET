package com.RestAssured.SDET.TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class T001_GET_Request {

	
	@Test
	void getWeatherDetails() {
		//specified the url
		RestAssured.baseURI="http://restapi.demoqa.com/utilities/weather/city/";
		//request object
		RequestSpecification httpRequest=RestAssured.given();
		//response object
		Response response=httpRequest.request(Method.GET,"/Hyderabad");
		//print response
		String responBody=response.getBody().asString();
		System.out.println("Response Body is : "+responBody);
		//status code validation 
		int statusCode=response.getStatusCode();
		System.out.println("Status code is : "+statusCode);
		Assert.assertEquals(statusCode, 200);
		//status line
		String statusLine=response.getStatusLine();
		System.out.println("Status line :"+statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");	
		
		
	}
	
}
