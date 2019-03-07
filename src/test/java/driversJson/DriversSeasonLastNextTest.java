package driversJson;

import static io.restassured.RestAssured.*;
import org.apache.http.HttpStatus;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utils.BaseClass;


public class DriversSeasonLastNextTest extends BaseClass{

	private static Logger log = LogManager.getLogger(DriversSeasonLastNextTest.class.getName());
		
		//To specify the current season the "season" field may be set to "current"
		//http://ergast.com/api/f1/current/drivers.json
		
			
		/* 
		 * --------DriversSeasonLastTest---------
		 * 
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season and "last" option
		 * Then response status code 200 OK is returned
		 * And response contains the relevant season
		 * And response contains the relevant round
		 */
	
		//String currentSeason = "";
		String season = "2017";
		String lastRound = "20";
		String nextSeason = "2018";
		String nextRound = "1";
	
		@Test
		public void driversSeasonLastTest(){
				
			log.info("-----------------driversSeasonLastTest-------------------------");	
			
				String recource = "/{season}/last.json";
				
				Response response = 
						given().accept(ContentType.JSON).
						when().get(recource, season);
				String jsonRespStr = response.asString();
				JsonPath json = new JsonPath(jsonRespStr);
				
				String respSeason = json.getString("MRData.RaceTable.season");
				String respRound = json.getString("MRData.RaceTable.round");
				
				Assert.assertTrue(HttpStatus.SC_OK == response.getStatusCode());
				Assert.assertEquals(respSeason, season);
				Assert.assertEquals(respRound, lastRound);
				
				String s = String.format("Season : %s, last season : %s, last round : %s", season, respSeason, respRound);
				log.info(s);
				
		}
				
		/* 
		 * --------DriversSeasonNextTest---------
		 * 
		 * Given Accept the content in JSON format
		 * When User perform the GET request with season and "next" option
		 * Then response status code 200 OK is returned
		 * And response contains the relevant season
		 * And response contains the relevant round
		 */
		
		@Test
		public void driversSeasonNextTest(){
						
			log.info("-----------------driversSeasonNextTest-------------------------");	
					
				String recource = "/{season}/next.json";
						
				Response response = 
						given().accept(ContentType.JSON).
						when().get(recource, season);
				String jsonRespStr = response.asString();
				JsonPath json = new JsonPath(jsonRespStr);
						
				String respSeason = json.getString("MRData.RaceTable.season");
				String respRound = json.getString("MRData.RaceTable.round");
						
				Assert.assertTrue(HttpStatus.SC_OK == response.getStatusCode());
				Assert.assertEquals(respSeason, nextSeason);
				Assert.assertEquals(respRound, nextRound);
						
				String s = String.format("Season : %s, next season : %s, next round : %s", season, respSeason, respRound);
				log.info(s);
				

		}
}
