package com.RestAssured.SDET.TestCase;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.PreemptiveAuthProvider;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class T007_GET_Autherization {

	@Test
	public void AuthorizationTest() {
		RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";

		//Basic Authentication using PreemptiveBasicAuthScheme
		PreemptiveBasicAuthScheme authType=new PreemptiveBasicAuthScheme();
		authType.setUserName("ToolsQA");
		authType.setPassword("TestPassword");
			
		RestAssured.authentication=authType;
		
		// Request object
		RequestSpecification httpRequest = RestAssured.given();
				
		//Response Object
		Response response = httpRequest.request(Method.GET, "/");

		// print response body
		String responBody = response.getBody().asString();
		System.out.println("Response Body is : " + responBody);
		
		// status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status code is : " + statusCode);
		Assert.assertEquals(statusCode, 200);
		
		// status line
		String statusLine = response.getStatusLine();
		System.out.println("Status line :" + statusLine);
				
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	}

}
