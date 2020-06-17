package com.paytm.qa.testcases;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC001_GETStatusCode {

	
	@Test
	public void verifyStatusDetails() {
		
		//Specify base URI
		RestAssured.baseURI="https://apiproxy.paytm.com/v2/movies/upcoming";
		
		//Represent Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		// Response Object
		Response response=httpRequest.request(Method.GET,"/");
		
		//Print Response in console window
		String responseBody=response.getBody().asString(); // asString() will convert json response in string as we cannot print json response directly
	 System.out.println("Response Body is :"+responseBody);	
	 
	 
	 
	 //Status Code Validation
	 
	int statusCode= response.getStatusCode();
	System.out.println("Status Code is :"+statusCode);
	Assert.assertEquals(statusCode, 200);
	
	//Status Line Verification
	String statusLine=response.getStatusLine();
	System.out.println(statusLine);
	Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
	}
}
