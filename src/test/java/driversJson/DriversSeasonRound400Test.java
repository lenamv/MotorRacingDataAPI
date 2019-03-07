package driversJson;

import static io.restassured.RestAssured.*;
import java.io.IOException;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.BaseClass;
import utils.UtilDataProvider;

public class DriversSeasonRound400Test extends BaseClass{

	private static Logger log= LogManager.getLogger(DriversSeasonRound400Test.class.getName());
	
	@DataProvider
	public Object [][] getTestData(){
		Object data [][] = UtilDataProvider.getTestData("Seasons_Inv");
		return data;
	}
	
	/* 
	* --------DriversSeasonRoundInvalidParametersTest---------
	* 
	* Given Accept the content in JSON format
	* When User perform the GET request  with invalid season and/or round values
	* Then response status code 400 Bad Request is returned
	*/	
	
	@Test(dataProvider = "getTestData")
	public void driversSeasonRoundInvalidParametersTest(String season, String round, String total) throws IOException{
		log.info("-----------------driversSeasonRoundInvalidParametersTest-------------------------");	
	
		String resource = "/{season}/{round}/drivers.json";
				
		Response response = 
				given().accept(ContentType.JSON).
				when().get(resource, season, round);
		
		String s = String.format("/%s/%s/drivers.json", season, round );		
		log.info(baseURI+s);
		log.info("Season: " + season + ", round : " + round);
				
		Assert.assertTrue(HttpStatus.SC_BAD_REQUEST == response.getStatusCode());
		log.info(response.getStatusCode());
	}
}
