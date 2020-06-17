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

public class TC002_MoviePosterURL {

	
	@Test
	public void verifyMoviePosterURL()  {
		
		//Specify base URI
		RestAssured.baseURI="https://apiproxy.paytm.com/v2/movies/upcoming";
		
		//Represent Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		// Response Object
		Response response=httpRequest.request(Method.GET,"/");
		
		//Print Response in console window
		String responseBody=response.getBody().asString(); // asString() will convert json response in string as we cannot print json response directly
	    System.out.println("Response Body is :"+responseBody);	
	   
	    //Movie Poster URL : should only have .jpg format
	   JsonPath jsonpath= response.jsonPath();
	    List<String> moviePosterUrls = jsonpath.getList("upcomingMovieData.moviePosterUrl");
	    Iterator <String> iterator= moviePosterUrls.iterator();
	    while(iterator.hasNext()) {
	    	String moviePosterurl=iterator.next().toString();
	    	Assert.assertTrue(moviePosterurl.endsWith("jpg"));
	    	
	    }
	}
}
