package com.paytm.qa.testcases;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC003_UniqueMovieCode {

	
	@Test
	public void verifyUniqueMovieCode()  {
		
		//Specify base URI
		RestAssured.baseURI="https://apiproxy.paytm.com/v2/movies/upcoming";
		
		//Represent Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		// Response Object
		Response response=httpRequest.request(Method.GET,"/");
		
		//Print Response in console window
		String responseBody=response.getBody().asString(); // asString() will convert json response in string as we cannot print json response directly
	    System.out.println("Response Body is :"+responseBody);	
	   
	    //Paytm movie code: is unique
	    HashSet <String> uniqueCode= new HashSet <String> ();
	   JsonPath jsonpath= response.jsonPath();
	   int totalMovieEntries=0;
	    List<String> paytmMovieCode = jsonpath.getList("upcomingMovieData.paytmMovieCode");
	    Iterator <String> iterator= paytmMovieCode.iterator();
	    while(iterator.hasNext()) {
	    	totalMovieEntries++;
	    	uniqueCode.add(iterator.next().toString());
	    }
	    System.out.println("Size of HashSet :" + uniqueCode.size());
	    Assert.assertEquals(uniqueCode.size(), totalMovieEntries);
	    	
	    }
}
