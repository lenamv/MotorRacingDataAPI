package driversJson;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.path.json.JsonPath.from;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import utils.BaseClass;

public class DriversSeasonTest extends BaseClass{
	
	private static Logger log = LogManager.getLogger(DriversSeasonTest.class.getName());

	//The "round" element may be skip in requests concerning a whole season
	//http://ergast.com/api/f1/{season}/drivers.json
		
	
	String resource = "/{season}/drivers.json";
	
	// Test Data : season, the total number of drivers, driverIds of the first, second and last drivers		
	String season = "2005";
	String totalDrivers = "27";
	String dr1 = "albers";
	String dr2 = "alonso";
	String dr3 = "zonta";
	
	
		/* 
		 * ----------DriversWholeSeasonTest1------------
		 * 
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season in range from 1950 to current year
		 * Then response status code 200 OK is returned
		 */
	
		@Test 
		public void driversWholeSeasonTest1(){
			log.info("-----------------driversWholeSeasonTest1-------------------------");		
			given().accept(ContentType.JSON).
			when().get(resource, season).
			then().assertThat().statusCode(200);
			log.info("Status code : 200");
		}
	
	
		/*
		 * ----------DriversWholeSeasonTest2---------------
		 * 
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season in range from 1950 to current year
		 * Then response contains the relevant season
		 */
	
		@Test 
		public void driversWholeSeasonTest2(){
			log.info("-----------------driversWholeSeasonTest2-------------------------");		
			given().accept(ContentType.JSON).
			when().get(resource, season).
			then().assertThat().
				body("MRData.DriverTable.season", equalTo(season));
			log.info("Season : " + season);
		}
		
		
		/*
		 * DriversWholeSeasonTest3
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season in range from 1950 to current year
		 * Then response contains the relevant total number of drivers 
		 */
		
		@Test 
		public void driversWholeSeasonTest3(){
			log.info("-----------------driversWholeSeasonTest3-------------------------");			
			given().accept(ContentType.JSON).
			when().get(resource, season).
			then().assertThat().				
				body("MRData.total", equalTo(totalDrivers));
			log.info("Total Drivers : " + totalDrivers);
		}
		
		/*	
		 * DriversWholeSeasonTest4
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season in range from 1950 to current year
		 * Then the total number of drivers in list of drivers is equal to total value
		 */
		 
		@Test 
		public void driversWholeSeasonTest4(){
			log.info("-----------------driversWholeSeasonTest4-------------------------");		
			String response =
			given().accept(ContentType.JSON).
			when().get(resource, season).asString();
			
			List<String> ls=from(response).getList("MRData.DriverTable.Drivers.driverId");
			Assert.assertEquals(Integer.toString(ls.size()), totalDrivers);
			log.info("Total Drivers in the driver list : " + totalDrivers);
		
		}
		 
		/*
		 * DriversWholeSeasonTest5
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season in range from 1950 to current year
		 * Then the driverIds of the first, second and last drivers in the list of drivers are d1, d2, and d3
		 * 
		 */
		
		 @Test
		 public void driversWholeSeasonTest5(){
			log.info("-----------------driversWholeSeasonTest5-------------------------");					
			given().accept(ContentType.JSON).
			when().get(resource, season).
			then().assertThat().
				body("MRData.DriverTable.Drivers.driverId", hasItems(dr1, dr2, dr3));
			
			String s = String.format("Drivers Ids : %s, %s, %s", dr1, dr2, dr3);
			log.info(s);	
			}
		 
}
