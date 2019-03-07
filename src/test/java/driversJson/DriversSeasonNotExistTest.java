package driversJson;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import utils.BaseClass;

public class DriversSeasonNotExistTest extends BaseClass{
	
	private static Logger log = LogManager.getLogger(DriversSeasonNotExistTest.class.getName());
	//The "round" element may be skip in requests concerning a whole season
	//http://ergast.com/api/f1/{season}/drivers.json
		
	
	String resource = "/{season}/drivers.json";
	
	// Test Data : season
	
	String seasonNotExist = "2020";
	
	
	// --------------------Negative Scenario---------------
		 /*
		 * 
		 * DriversWholeSeasonNotExistTest1
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season that is not in range from 1950 to current year
		 * Then response status code 200 OK is returned
		 */
		 
		 @Test
		 public void driversWholeSeasonNotExistTest1(){
			 log.info("-----------------driversWholeSeasonNotExistTest1-------------------------");		
			 
			 given().accept(ContentType.JSON).
			 when().get(resource, seasonNotExist).
			 then().assertThat().statusCode(200);
			 
			 log.info("Status code : 200");
		 } 
		 
		 /*		 
		 * DriversWholeSeasonNotExistTest2
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season not in range from 1950 to current year
		 * Then response contains the relevant season
		 */
		 
		 @Test
		 public void driversWholeSeasonNotExistTest2(){
			 log.info("-----------------driversWholeSeasonNotExistTest2-------------------------");				
			 
			 given().accept(ContentType.JSON).
			 when().get(resource, seasonNotExist).
			 then().assertThat().
					body("MRData.DriverTable.season", equalTo(seasonNotExist));
			 
			 log.info("Season : " + seasonNotExist);
		 } 
		 
		 /* 
		 * DriversWholeSeasonNotExistTest3
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season not in range from 1950 to current year
		 * Then the total number of drivers is equal to 0 
		 */
		 
		 @Test
		 public void driversWholeSeasonNotExistTest3(){
			 log.info("-----------------driversWholeSeasonNotExistTest3-------------------------");				
			 
			 given().accept(ContentType.JSON).
			 when().get(resource, seasonNotExist).
			 then().assertThat().
					body("MRData.total", equalTo("0"));
			 log.info("Total Drivers : 0");
		 } 

		 /*
		 * DriversWholeSeasonTest4
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season not in range from 1950 to current year
		 * Then list of drivers is empty 
		 */
	
		 @Test
		 public void driversWholeSeasonNotExistTest4(){
			 log.info("-----------------driversWholeSeasonNotExistTest4-------------------------");	
			 
			 given().accept(ContentType.JSON).
			 when().get(resource, seasonNotExist).
			 then().assertThat().
					body("MRData.DriverTable.Drivers", hasSize(0));
			 
			 log.info("Driver list is empty");
			} 
			
				
}
