package api.com.tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import api.com.util.Helper;
import api.com.util.ReadTestData;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ApiTestScripts {
	//@TestNG
	
	private String accessToken;
	
	@Test(description="Validate the status code for GET users endpoints", groups="SmokeSuite")
	public void ValidateStatusCodeGetUsers() throws IOException, ParseException {
		System.out.println("*******"+ReadTestData.getTestData("uriGetUsers")+"*********");
		Response res = given()
				.when()
				.get(ReadTestData.getTestData("uriGetUsers"));//RestAssured
		int actualStatusCode = res.getStatusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
	}
	
	@Test(description="Validate the status code for GET users endpoints using query param", groups="RegressionSuite")
	public void ValidateStatusCodeGetUsersWithQueryParam() throws IOException, ParseException {
		
		Response res = given()
				.queryParam("page", 2)
				.when()
				.get(Helper.getUri());//RestAssured
		int actualStatusCode = res.getStatusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
	}
	
	@Test(description="Validate fetched value from JSON response based on key")
	public void ValidateTotalFieldValue() {
		
		Response res = given()
				.queryParam("page", 2)
				.when()
				.get("https://reqres.in/api/users");//RestAssured
		int actualStatusCode = res.getStatusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
		
		int actualValue = res.path("total"); // It helps to fetch values from JSON based on key
		assertEquals(actualValue, 12);
	}
	
	@Test(description="Validate Response Body")
	public void ValidateResponseBodyGetUs() throws IOException, ParseException {
		
		Response res = given()
				.queryParam("page", 2)
				.when()
				.get("https://reqres.in/api/users");//RestAssured
		int actualStatusCode = res.statusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
		
		String actualValue = res.path("data[1].email"); // It helps to fetch values from JSON based on key
		assertEquals(actualValue, ReadTestData.getTestData("ExpectedEmail"));
		
		String actualValue1 = res.path("support.url"); // It helps to fetch values from JSON based on key
		assertEquals(actualValue1, "https://reqres.in/#support-heading");
	}
	
	@Test(description="Pass header for GET method in Rest Assured")
	public void ValidateResponseBodyGetHeader() {
		
		Response res = given()
				.headers("Content-Type","application/json")
				.when()
				.get("https://gorest.co.in/public-api/users");//RestAssured
		int actualStatusCode = res.statusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
	}
	
	@Test(description="Validate Response Body Get Path Param")
	public void ValidateResponseBodyGetPathParam() {
		
		String raceSeasonValue = "2017";
		Response res = given()
				.pathParam("raceSeason", raceSeasonValue)
				//.pathParam("raceSeason1", raceSeasonValue1) Can add multiple pathParam
				.when()
				.get("https://ergast.com/api/f1/{raceSeason}/circuits.json");//RestAssured
		int actualStatusCode = res.statusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
	}
	
	//Authentication Examples
	
	@Test(description="1. Basic Auth")
	public void ValidateResponseBodyGetBasicAuth() {
		
		Response res = given()
				.auth()
				.basic("postman", "password")
				.when()
				.get("https://postman-echo.com/basic-auth");//RestAssured
		int actualStatusCode = res.statusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
		accessToken = res.path("token");
	}
	
	//Digest Auth is time consuming as compared to basic auth
	@Test(description="2. Digest Auth")
	public void ValidateResponseBodyGetDigestAuth() {
		
		Response res = given()
				.auth()
				.digest("postman", "password")
				.when()
				.get("https://postman-echo.com/basic-auth");//RestAssured
		int actualStatusCode = res.statusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
	}
	
	@Test(description="3. OAuth 1.0")
	public void ValidateResponseBodyGetOAuth1Auth() {
		
		Response res = given()
				.auth()
				.oauth("consumerKey", "consumerSecret", "accessToken", "secretToken")
				.when()
				.get("https://reqres.in/api/users/2");//RestAssured
		int actualStatusCode = res.statusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
	}
	
	@Test(description="4. OAuth 2.0")
	public void ValidateResponseBodyGetOAuth2Auth() {
		
		Response res = given()
				.auth()
				.oauth2(accessToken)
				.when()
				.get("https://reqres.in/api/users/2");//RestAssured
		int actualStatusCode = res.statusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
	}
	
	//More effitient way to use OAuth. We always use this way for automation.
	@Test(description="4. OAuth 2.0")
	public void ValidateResponseBodyGetOAuth2WithHeader() {
		
		Response res = given()
				.header("Authorization",accessToken)
				.when()
				.get("https://reqres.in/api/users/2");//RestAssured
		int actualStatusCode = res.statusCode(); //RestAssured
		assertEquals(actualStatusCode, 200); //TestNG
		System.out.println(res.body().asString());
	}
	
}
