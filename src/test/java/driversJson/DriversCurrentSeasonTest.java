package driversJson;

import static io.restassured.RestAssured.*;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseClass;


public class DriversCurrentSeasonTest extends BaseClass{

	private static Logger log = LogManager.getLogger(DriversCurrentSeasonTest.class.getName());
	
	//To specify the current season the "season" field may be set to "current"
	//http://ergast.com/api/f1/current/drivers.json
		
		// Get current season value if race season has started
		Year year = Year.now();
		String currentSeason = year.toString();
		
		// Get current season value if race season has not started
		LocalDate currentDate = LocalDate.now();
		LocalDate firstRace = LocalDate.of(2019, Month.MARCH, 17);
		int currentSeasonInt = currentDate.getYear()-1;
		String currentSeasonPast = Integer.toString(currentSeasonInt);
		
	/* 
	* --------DriversCurentSeasonTest---------
	* 
	* Given Accept the content in JSON format
	* When User perform the GET request with "current" season 
	* And User perform the GET request with numeric season value converted from "current" season
	* Then both response status code 200 OK are returned
	* And  season values are equal
	* And sizes of lists of drivers are equal
	* And content of the lists of drivers are equal 
	*/
	
			@Test
			public void driversCurrentSeasonDriversListTest(){
				
				log.info("-----------------driversCurrentSeasonDriversListTest-------------------------");
				
				if (currentDate.isBefore(firstRace)){
					currentSeason = currentSeasonPast;
				}
				System.out.println(currentSeason);
				
				String recourceCurentSeason = "/current/drivers.json";
				String recourceCurentSeasonYear = "/{currentSeason}/drivers.json";
				
				// 	"/current/drivers.json"
				Response response1 = 
					given().accept(ContentType.JSON).
					when().get(recourceCurentSeason);
				String jsonRespStr1 = response1.asString();
				JsonPath json1 = new JsonPath(jsonRespStr1);
				String season1 =json1.getString("MRData.DriverTable.season");
				String drivers1 =json1.getString("MRData.DriverTable.Drivers");
				
				log.info(json1.getString("MRData.url"));
				log.info("Season : " +season1);
				
				List <String> ls1 = json1.getList("MRData.DriverTable.Drivers.driverId");
					log.info("Total Drivers : " + ls1.size());
					log.info(ls1);
						
				
				
				// 	"/{currentSeason}/drivers.json"
						
				Response response2 = 
					given().accept(ContentType.JSON).
					when().get(recourceCurentSeasonYear, currentSeason);
				String jsonRespStr2 = response2.asString();
				JsonPath json2 = new JsonPath(jsonRespStr2);
				String season2 =json2.getString("MRData.DriverTable.season");
				String drivers2 =json2.getString("MRData.DriverTable.Drivers");
				
				log.info(json2.getString("MRData.url"));
				log.info("Season : " +season2);
				
				List <String> ls2 = json2.getList("MRData.DriverTable.Drivers.driverId");
					log.info("Total Drivers : " + ls2.size());
					log.info(ls2);
				
				Assert.assertTrue(HttpStatus.SC_OK == response1.getStatusCode());
				Assert.assertTrue(HttpStatus.SC_OK == response2.getStatusCode());
				Assert.assertEquals(season1, season2);
				Assert.assertEquals(ls1.size(), ls2.size());
				Assert.assertEquals(drivers1, drivers2);
							
				
			}	
	
}		
		

