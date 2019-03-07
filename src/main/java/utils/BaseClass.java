package utils;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeMethod;

public class BaseClass {
	
	@BeforeMethod
	public static void setUp(){
		baseURI = "http://ergast.com/api/f1";
		/*
		 Rest Assured environment variables
		 baseURI : port basePath
		 
		 baseURI =;
		 port = ;
		 basePath = "";
		 * 
		 */
	}
	
	

}
