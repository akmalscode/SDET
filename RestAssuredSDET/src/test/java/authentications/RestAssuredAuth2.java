package authentications;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class RestAssuredAuth2 extends AuthBase {
	@Test
	public void test2() {
		int code = RestAssured.given()
				.get() // will get from base class
				.getStatusCode();

		System.out.println("Response code from server " + code);

	}
}
