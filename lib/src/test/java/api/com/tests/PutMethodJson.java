package api.com.tests;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import api.com.pojo.pojoClass;
import io.restassured.response.Response;

public class PutMethodJson {

	@Test(groups="RegressionSuite")
	public static void putmethodpojo()
	{	
		String job = "morpheus2";
		String name = "zion resident2";
		pojoClass obj = new pojoClass();
		obj.setJob(job);
		obj.setName(name);
		
		Response resp = given()
				.header("Content-Type","application/json")
				.body(obj)
				.when()
				.put("https://reqres.in/api/users/2");
		
		assertEquals(resp.getStatusCode(),200);
		System.out.println("The status code for post method is:" + resp.getStatusCode());
		System.out.println("The response body of the post method is:" + resp.getBody().asString());
		assertEquals(resp.path("job"),job);
		
	}
	@Test(groups="RegressionSuite")
	public static void patchmethodpojo()
	{	
		String job = "morpheus1";
		String name = "zion resident1";
		pojoClass obj = new pojoClass();
		obj.setJob(job);
		obj.setName(name);
		
		Response resp = given()
				.header("Content-Type","application/json")
				.body(obj)
				.when()
				.patch("https://reqres.in/api/users/2");
		
		assertEquals(resp.getStatusCode(),200);
		System.out.println("The status code for post method is:" + resp.getStatusCode());
		System.out.println("The response body of the post method is:" + resp.getBody().asString());
		assertEquals(resp.path("job"),job);
		
	}
}
