package driversJson;

import static io.restassured.RestAssured.*;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.List;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseClass;
import utils.UtilDataProvider;


public class DriversSeasonRound200Test extends BaseClass {
	private static Logger log= LogManager.getLogger(DriversSeasonRound400Test.class.getName());
	
	@DataProvider
	public Object [][] getTestData(){
		Object data [][] = UtilDataProvider.getTestData("Seasons");
		return data;
	}
	
	/* 
	* --------DriversSeasonRoundValidParametersTest---------
	* 
	* Given Accept the content in JSON format
	* When User perform the GET request  with valid season and round values
	* Then response status code 200 OK is returned
	* And response contains the relevant season, round and total values
	* And size of the list of drivers is equal to total
	*/	
	

	@Test(dataProvider = "getTestData")
	public void driversSeasonRoundValidParametersTest(String season, String round, String total) throws IOException{
		log.info("-----------------driversSeasonRoundValidParametersTest-------------------------");
		
		String resource = "/{season}/{round}/drivers.json";

		Response response = 
				given().accept(ContentType.JSON).
				when().get(resource, season, round);
		String jsonRespStr = response.asString();
		JsonPath json = new JsonPath(jsonRespStr);
		
		String resSeason = json.getString("MRData.DriverTable.season");
		String resRound = json.getString("MRData.DriverTable.round");
		String resTotal = json.getString("MRData.total");
		
		Assert.assertTrue(HttpStatus.SC_OK == response.getStatusCode());
			
		String s = String.format("/%s/%s/drivers.json", season, round);
		log.info(baseURI + s);
		log.info(response.getStatusCode());
		
		Assert.assertEquals(resSeason, season);		
		Assert.assertEquals(resRound, round);
		Assert.assertEquals(resTotal, total);
		String ss = String.format("Season: %s, round : %s, total : %s", resSeason, resRound, resTotal);
		log.info(ss);
				
		List <String> list = json.getList("MRData.DriverTable.Drivers.driverId");
		Assert.assertEquals(Integer.toString(list.size()), total);
		log.info("Total Drivers :" + list.size());
		
	}
	
}
	
	
