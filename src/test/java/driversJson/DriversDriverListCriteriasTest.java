package driversJson;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.Arrays;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import utils.BaseClass;


public class DriversDriverListCriteriasTest extends BaseClass{
	
	private static Logger log = LogManager.getLogger(DriversDriverListCriteriasTest.class.getName());
	
	/*
	 * A list of drivers can be refined by adding one or more of the following criteria:
	 *
	 * 1. /circuits/<circuitId>
	 * http://ergast.com/api/f1/{season}/circuits/{circuitId}/drivers.json"
	 *
	 * 2. /constructors/<constructorId>
	 * http://ergast.com/api/f1/{season}/constructors/{constructorId}/drivers.json"
	 *
	 * 3. /drivers/<driverId>
	 * http://ergast.com/api/f1/{season}/drivers/{driverId}.json"
	 *
	 * 4. /grid/<position>
	 * http://ergast.com/api/f1/{season}/grid/{position}/drivers.json"
	 *
	 * 5. /results/<position>
	 * http://ergast.com/api/f1/{season}/results/{position}/drivers.json"
	 *
	 * 6. /fastest/<rank>
	 * http://ergast.com/api/f1/{season}/fastest/{rank}/drivers.json"
	 *
	 * 
	 */
	
	 // ----------------Data--------------------------- 
		
	// 1. 
		String season = "2018";
		String circuitId = "catalunya";
		String [] listCircuit = {"alonso","bottas", "vettel"};
		String drivers = "20";
	// 2. 
		String constructorId = "mercedes";
		String [] listConstructorDrivers = {"bottas", "hamilton"};
	// 3.	
		String driverId = "hamilton";
	// 4. 
		String gridPosition = "1";
		String [] listGrig = {"bottas", "hamilton", "raikkonen", "ricciardo", "vettel"};
	// 5.
		String resultsPosition = "1";
		String [] listResults = {"hamilton", "raikkonen", "ricciardo", "max_verstappen", "vettel"};
	// 6.
		String rank = "1";
		String [] listFastest = {"bottas", "hamilton", "kevin_magnussen", "raikkonen", "ricciardo", "max_verstappen", "vettel"};
		
	
		@Test
		public void driversSeasonCircuitsCriteriaTest(){
		
			log.info("-----------------driversSeasonCircuitsCreteriaTest-------------------------");	
		
			String recourceCircuit = "/{season}/circuits/{circuitId}/drivers.json";
				given().accept(ContentType.JSON).
				when().get(recourceCircuit, season, circuitId).
			then().
				statusCode(200).
				body("MRData.DriverTable.season", equalTo(season)).
				body("MRData.DriverTable.circuitId", equalTo(circuitId)).
				body("MRData.DriverTable.Drivers.driverId", hasItems (listCircuit));
		
		String s = String.format("Season : %s, circuitId : %s, drivers in list : %s", season, circuitId, Arrays.toString(listCircuit));
		log.info(s);
	
		}
		
		@Test
		public void driversSeasonConstructorsCriteriaTest(){
		
			log.info("-----------------driversSeasonConstructorsCriteriaTest-------------------------");	
		
			String recourceCircuit = "/{season}/constructors/{constructorId}/drivers.json";
				given().accept(ContentType.JSON).
				when().get(recourceCircuit, season, constructorId).
			then().
				statusCode(200).
				body("MRData.DriverTable.season", equalTo(season)).
				body("MRData.DriverTable.constructorId", equalTo(constructorId)).
				body("MRData.DriverTable.Drivers.driverId", hasItems (listConstructorDrivers));
		
		String s = String.format("Season : %s, constructorId : %s, drivers in list : %s", season, constructorId, Arrays.toString(listConstructorDrivers));
		log.info(s);
	
		}
		
		@Test
		public void driversSeasonDriverIdCriteriaTest(){
		
			log.info("-----------------driversSeasonDriverIdCriteriaTest-------------------------");	
		
			String recourceCircuit = "/{season}/drivers/{driverId}.json";
				given().accept(ContentType.JSON).
				when().get(recourceCircuit, season, driverId).
			then().
				statusCode(200).
				body("MRData.total", equalTo("1")).
				body("MRData.DriverTable.season", equalTo(season)).
				body("MRData.DriverTable.driverId", equalTo(driverId)).
				body("MRData.DriverTable.Drivers.driverId", hasItem(driverId));
		
		String s = String.format("Season : %s, driverId : %s, driver in list : %s", season, driverId, driverId);
		log.info(s);
	
		}
		
		@Test
		public void driversSeasonGridCriteriaTest(){
		
			log.info("-----------------driversSeasonGridCriteriaTest-------------------------");	
		
			String recourceCircuit = "/{season}/grid/{gridPosition}/drivers.json";
				given().accept(ContentType.JSON).
				when().get(recourceCircuit, season, gridPosition).
			then().
				statusCode(200).
				body("MRData.DriverTable.season", equalTo(season)).
				body("MRData.DriverTable.grid", equalTo(gridPosition)).
				body("MRData.DriverTable.Drivers.driverId", hasItems (listGrig));
		
		String s = String.format("Season : %s, grid position : %s, drivers in list : %s", season, gridPosition, Arrays.toString(listGrig));
		log.info(s);
	
		}
		
		@Test
		public void driversSeasonResultsCriteriaTest(){
		
			log.info("-----------------driversSeasonResultsCriteriaTest-------------------------");	
		
			String recourceCircuit = "/{season}/results/{resultsPosition}/drivers.json";
				given().accept(ContentType.JSON).
				when().get(recourceCircuit, season, resultsPosition).
			then().
				statusCode(200).
				body("MRData.DriverTable.season", equalTo(season)).
				body("MRData.DriverTable.position", equalTo(resultsPosition)).
				body("MRData.DriverTable.Drivers.driverId", hasItems (listResults));
		
		String s = String.format("Season : %s, results position: %s, drivers in list : %s", season, resultsPosition, Arrays.toString(listResults));
		log.info(s);
	
		}
		
		@Test
		public void driversSeasonFastestCriteriaTest(){
		
			log.info("-----------------driversSeasonFastestCriteriaTest-------------------------");	
		
			String recourceCircuit = "/{season}/fastest/{rank}/drivers.json";
				given().accept(ContentType.JSON).
				when().get(recourceCircuit, season, rank).
			then().
				statusCode(200).
				body("MRData.DriverTable.season", equalTo(season)).
				body("MRData.DriverTable.fastest", equalTo(rank)).
				body("MRData.DriverTable.Drivers.driverId", hasItems (listFastest));
		
		String s = String.format("Season : %s, fastest rank : %s, drivers in list : %s", season, rank, Arrays.toString(listFastest));
		log.info(s);
	
		}
	

}
