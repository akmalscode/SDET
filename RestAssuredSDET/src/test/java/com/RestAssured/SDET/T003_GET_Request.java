package com.RestAssured.SDET;

import org.hamcrest.core.Is;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class T003_GET_Request {

	@Test
	void getGoogleMapTest() {
		//specified the url
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//response object
		Response response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyDHtKFTH9t6IaII3wiAzBcb3quh0CZQfnE");
		
		//print response body
		String responBody=response.getBody().asString();
		System.out.println("Response Body is : "+responBody);
		
		//capture header detail from response
		String containType=response.header("Content-Type"); //contain type header
		System.out.println("Contain Type Is :"+containType);
		Assert.assertEquals(containType, "application/xml; charset=UTF-8");
		
		//contain encoding
		String containEncodeing=response.header("Content-Encoding"); //contain type header
		System.out.println("Contain Encoding Is :"+containEncodeing);
		Assert.assertEquals(containEncodeing, "gzip");
		
		
		
	}		
}
