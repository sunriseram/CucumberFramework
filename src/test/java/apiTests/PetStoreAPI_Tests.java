package apiTests;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class PetStoreAPI_Tests {
	
	
	/* 
	 * Scenario: As a user, I should be able to perform POST request to add new pet to store 
	 * Given I have the POST request URL and valid request body 
	 * When I perform POST request to URL with request body 
	 * Then Response status code should be 200 
	 * And content type is application.json 
	 * And response body match the request body
	 */
	
	String requestURL;
	File requestBody;
	Response response;
	FileReader readJsonFile;
	JSONParser parser;
	JSONObject jsonObj;
    int petID;
    
    
    @BeforeMethod
    public void beforeMethod() throws IOException, ParseException {
       requestBody = new File("./src/test/resources/jsonFiles/addAPet.json");
       readJsonFile = new FileReader(requestBody);
  	   parser = new JSONParser();
  	   jsonObj = (JSONObject) parser.parse(readJsonFile);
  	  Long longPetId = (Long) jsonObj.get("id");
  	  petID = longPetId.intValue();
    }
	
  @Test
  public void createAPet() throws IOException, ParseException {
	  requestURL = "https://petstore.swagger.io/v2/pet";
	  System.out.println(jsonObj.get("name"));
	  System.out.println(jsonObj.get("status"));
	  response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody)
	  .when().post(requestURL);
	  
	  response.then().assertThat().contentType("application/json")
	  .and().assertThat().statusCode(200)
	  .and().assertThat().body("id", Matchers.equalTo(petID))
	  .and().assertThat().body("name", Matchers.equalTo(jsonObj.get("name")))
	  .and().assertThat().body("status", Matchers.equalTo(jsonObj.get("status")));
  }
  
  @Test()
  public void getPetByID() {
	  requestURL = "https://petstore.swagger.io/v2/pet/";
	  
	  given().accept(ContentType.JSON)
	  .when().get(requestURL + petID)
	  .then().assertThat().contentType("application/json")
	  .and().assertThat().statusCode(200)
	  .and().assertThat().body("id", Matchers.equalTo(petID))
	  .and().assertThat().body("name", Matchers.equalTo("Fluffy"))
	  .and().assertThat().body("status", Matchers.equalTo("available"));
	    
  }
  
  
  @Test()
  public void justPrintingSomehing() {
	  
	  requestURL = "https://petstore.swagger.io/v2/pet/";
	  
	  response = given().accept(ContentType.JSON).auth().oauth2("helil-prime")
	  .when().get(requestURL + petID);
	  
	  response.prettyPrint();
	  
  }
  
  
  
  
}