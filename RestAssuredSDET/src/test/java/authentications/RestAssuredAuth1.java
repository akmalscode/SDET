package authentications;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssuredAuth1 {
	@Test
	public void test1() {

		int code = RestAssured.given().when().auth().preemptive()
				.basic("ToolsQA", "TestPassword")
				.get("http://restapi.demoqa.com/authentication/CheckForAuthentication")
				.getStatusCode();

		System.out.println("Response code from server " + code);

	}

}
