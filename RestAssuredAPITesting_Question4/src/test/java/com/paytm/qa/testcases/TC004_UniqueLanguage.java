package com.paytm.qa.testcases;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC004_UniqueLanguage {

	@Test
	public void verifyUniqueLanguage() {

		// Specify base URI
		RestAssured.baseURI = "https://apiproxy.paytm.com/v2/movies/upcoming";

		// Represent Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/");

		// Print Response in console window
		String responseBody = response.getBody().asString(); // asString() will convert json response in string as we
																// cannot print json response directly
		System.out.println("Response Body is :" + responseBody);

		// No movie code should have more than 1 language format
		JsonPath jsonpath = response.jsonPath();
		List<String> movielanguages = jsonpath.getList("upcomingMovieData.language");
		Iterator<String> iterator = movielanguages.iterator();
		while (iterator.hasNext()) {
			String uniqueLanguage = iterator.next().toString();
			Assert.assertNotNull(uniqueLanguage);
		
		}
	}
}
