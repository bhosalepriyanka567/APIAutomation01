package api.com.tests;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import api.com.pojo.cityModel;
import api.com.pojo.pojoClass;
import api.com.pojo.pojoListClass;
import api.com.pojo.pojoNestedModelClass;
import api.com.util.ExtentReport;
import api.com.util.RandomUtils;
import api.com.util.ReadTestData;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class PostMethodJson {

	@Test(description = "Automate post method for users using IOUtils")
	public static void postAutoExtFile() throws IOException,ParseException
	{
		//ExtentReport.extentlog = ExtentReport.extentreport.startTest("CreateUserPostMethod", " validate post method");
		FileInputStream file= new FileInputStream(new File(System.getProperty("user.dir") + "/Resources/TestData/PostRequest.json"));
		Response resp = given()
				.header("Content-Type","application/json")
				.body(IOUtils.toString(file,ReadTestData.getTestData("encodingType")))
				.when()
				.post("https://reqres.in/api/users");
		
		assertEquals(resp.getStatusCode(),201);
		System.out.println("The status code for post method is:" + resp.getStatusCode());
		System.out.println("The response body of the post method is:" + resp.getBody().asString());
	}
	
	@Test(description="Automate post method for users using POJO class")
	public static void postmethodwithpojoclass()
	{
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("postmethodwithpojoclass", 
				"Automate post method for users using POJO class");
		//String job = RandomUtils.generateRandomString(5);
		//String name = RandomUtils.generateRandomNumericString(8);
		
		List<String> lst = new ArrayList<String>();
		lst.add("developer");
		lst.add("designer");
		lst.add("support");
		
		String job = "tester";
		String name = "janet";
		pojoListClass obj = new pojoListClass();
		obj.setJob(job);
		obj.setName(name);
		obj.setJobs(lst);
		
		Response resp = given()
				.header("Content-Type","application/json")
				.body(obj)
				.when()
				.post("https://reqres.in/api/users");
		
		assertEquals(resp.getStatusCode(),201);
		System.out.println("The status code for post method is:" + resp.getStatusCode());
		System.out.println("The response body of the post method is:" + resp.getBody().asString());
		assertEquals(resp.path("job"),job);
		assertEquals(resp.path("name"),name);
		
	}
	
	@Test(description="Automate post method for users using POJO class with nested model class")
	public static void postmethodwithpojoclasswithnestedmodel()
	{
		ExtentReport.extentlog = ExtentReport.extentreport.startTest("Execute post method", " validate post method");
		//String job = RandomUtils.generateRandomString(5);
		//String name = RandomUtils.generateRandomNumericString(8);
		
		List<cityModel> cityModels = new ArrayList<cityModel>();
		cityModel cityBang = new cityModel();
		cityBang.setName("Banglore");
		cityBang.setTemperature("30");
		
		cityModel cityPune = new cityModel();
		cityPune.setName("Pune");
		cityPune.setTemperature("27");
		
		cityModels.add(cityBang);
		cityModels.add(cityPune);
		
		String job = RandomUtils.generateRandomString(5);
		pojoNestedModelClass obj = new pojoNestedModelClass();
		obj.setJob(job);
		obj.setCitiModels(cityModels);
		
		Response resp = given()
				.header("Content-Type","application/json")
				.body(obj)
				.when()
				.post("https://reqres.in/api/users");
		
		assertEquals(resp.getStatusCode(),201);
		System.out.println("The status code for post method is:" + resp.getStatusCode());
		System.out.println("The response body of the post method is:" + resp.getBody().asString());
		assertEquals(resp.path("job"),job);
		
	}
}
