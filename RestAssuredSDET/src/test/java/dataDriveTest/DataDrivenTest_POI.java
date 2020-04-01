package dataDriveTest;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class DataDrivenTest_POI {
	@Test(dataProvider = "empDataProvider")
	public void addPostNewEmpoyee(String ename, String esal, String eage) {

		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";

		RequestSpecification httpRequest = RestAssured.given();

		// send the request with body
		JSONObject requestPrem = new JSONObject();
		requestPrem.put("name", ename);
		requestPrem.put("salary", esal);
		requestPrem.put("age", eage);

		httpRequest.header("Contant-Type", "application/json");

		// add json to the body of the request
		httpRequest.body(requestPrem.toJSONString());

		// POST request
		Response response = httpRequest.request(Method.POST, "/create");

		// capture response body to perfom varification
		String responceBody = response.getBody().asString();

		System.out.println("Response Body is: " + responceBody);

		Assert.assertEquals(responceBody.contains(ename), true);
		Assert.assertEquals(responceBody.contains(esal), true);
		Assert.assertEquals(responceBody.contains(eage), true);

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);

	}

	@DataProvider(name = "empDataProvider")

	String[][] getEmployee() throws IOException {

		String path = System.getProperty("user.dir") + "/src/test/java/dataDriveTest/empData.xlsx";

		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colnum = XLUtils.getCellCount(path, "Sheet1", 1);
		String empdata[][] = new String[rownum][colnum];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colnum; j++) {
				empdata[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);

			}
		}

		return (empdata);

	}

}
