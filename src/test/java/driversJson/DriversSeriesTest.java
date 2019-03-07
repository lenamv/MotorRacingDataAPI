package driversJson;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;
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

public class DriversSeriesTest extends BaseClass{

	private static Logger log = LogManager.getLogger(DriversSeriesTest.class.getName());
	
		//Both the round and the season elements may be skip in requests concerning the whole series
		//http://ergast.com/api/f1/drivers.json
		
		/* 
		 * --------DriversWholeSeriesTest1---------
		 * 
		 * Given Accept the content in JSON format
		 * When User perform the GET request
		 * Then response status code 200 OK is returned
		 * And response contains the relevant total number of drivers
		 * And driverIds of the first, second and last drivers on the first page of the driver list ("limit" set by default to 30) are "dr1", "dr2", "dr3" 
		 */
		
		@Test
		public void driversWholeSeriesTest1(){
			log.info("-----------------driversWholeSeriesTest1-------------------------");	
			String recource = "/drivers.json";
		
			// Data : the total number of drivers, driverIds of the first, second and last drivers	
			String totalDrivers = "847";
			String dr1 = "abate";
			String dr2 = "abecassis";
			String dr3 = "arundell";	
			
			given().
				accept(ContentType.JSON).
			when().
				get(recource).
			then().
				assertThat().
					statusCode(200).
					body("MRData.total", equalTo(totalDrivers)).
					body("MRData.DriverTable.Drivers.driverId", hasItems(dr1, dr2, dr3));
			
			String s = String.format("Total drivers : %s, driver IDs : %s, %s, %s", totalDrivers, dr1, dr2, dr3);
			log.info(s);
		}
		
		
		
			/* 
			 * --------DriversWholeSeriesTest2---------
			 * 
			 * Given Accept the content in JSON format
			 * When User perform the GET request
			 * Then response status code 200 OK is returned
			 * And there are 30 driverId are on the page ("limit" set by default to 30)
			 * 
			 */
				
			@Test
			public void driversWholeSeriesTest2(){
				log.info("-----------------driversWholeSeriesTest2-------------------------");	
			
				String recource = "/drivers.json";
				
				Response response = 
						given().accept(ContentType.JSON).
						when().get(recource);
				String jsonRespStr = response.asString();
				JsonPath json = new JsonPath(jsonRespStr);
				List<String> list=json.getList("MRData.DriverTable.Drivers.driverId");
				
				Assert.assertTrue(HttpStatus.SC_OK == response.getStatusCode());
				Assert.assertEquals(list.size(), 30);
				
				String s = String.format("Total drivers : %s", list.size());
				log.info(s);
			}

}
