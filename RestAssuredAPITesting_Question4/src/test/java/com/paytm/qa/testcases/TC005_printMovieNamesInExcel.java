package com.paytm.qa.testcases;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC005_printMovieNamesInExcel {

	@Test
	public void writeMovieNamesinExcel() throws IOException {

		RestAssured.baseURI = "https://apiproxy.paytm.com/v2/movies/upcoming";

		// what kind of request I going to send-GET,POST
		// Represent Request object
		RequestSpecification httpRequest = RestAssured.given();

		// Response Object
		Response response = httpRequest.request(Method.GET, "/");

		// Print Response in console window
		String responseBody = response.getBody().asString(); // asString() will convert json response in string as we
																// cannot print json response directly
		System.out.println("Response Body is :" + responseBody);
		
		int count=0;
		ArrayList<String> arr = new ArrayList<String>();
		// No movie code should have more than 1 language format
		JsonPath jsonpath = response.jsonPath();
		List<Integer> ContentAvailable=jsonpath.getList("upcomingMovieData.isContentAvailable");
		List<String> movieName = jsonpath.getList("upcomingMovieData.movie_name");
		Iterator<Integer> iterator = ContentAvailable.iterator();
		
		while (iterator.hasNext()) {
			
			
			if(iterator.next()== 0) {
				
				System.out.println(movieName.get(count).toString());
				arr.add(movieName.get(count).toString());
			}
			//String temp = iterator.next().toString();
			count++;
			System.out.println();
		}
		
		String projectPath = System.getProperty("user.dir");
	
		FileInputStream fis = new FileInputStream(projectPath + "\\excel\\MoviesName.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		

		for (int i = 0; i < arr.size(); i++) {
			Row row1 = sheet.createRow(i);
			Cell cell1 = row1.createCell(0);
			
			cell1.setCellValue(arr.get(i));
		}
		FileOutputStream fos = new FileOutputStream(projectPath + "\\excel\\MoviesName.xlsx");
		workbook.write(fos);
		fos.close();
		System.out.println("END OF WRITING DATA IN EXCEL");
	}

}
