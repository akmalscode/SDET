package com.RestAssured.SDET.TestCase;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class T004_GET_All_Headers {

	@Test
	public void getWeatherDetails() {

		// specified the url
		// RestAssured.baseURI="https://maps.googleapis.com";
		RestAssured.baseURI = "https://www.google.com/";

		// request object
		RequestSpecification httpRequest = RestAssured.given();

		// response object
		// Response
		// response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyDHtKFTH9t6IaII3wiAzBcb3quh0CZQfnE");
		Response response = httpRequest.request(Method.GET,
				"/maps/embed/v1/MODE?key=AIzaSyDHtKFTH9t6IaII3wiAzBcb3quh0CZQfnE&parameters");
		// print response body
		String responBody = response.getBody().asString();
		System.out.println("Response Body is : " + responBody);

		// capture All header detail from response
		Headers allHeader = response.headers();

		for (Header header : allHeader) {
			System.out.println(header.getName() + " - > " + header.getValue());
		}
	}
}
