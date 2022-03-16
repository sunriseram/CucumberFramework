package step_definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.File;

import org.hamcrest.Matchers;
import org.junit.Assert;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreSteps {
	
	Response response;
	private String requestURL;
	File requestBody;
	int petId;
	
	
	// in RestAssured, we can do the data validation in many different ways
	// we can use JUnit or TestNG assertions as we learned
	// and also we can use build in RestAssured Assertions
	
	
	@When("I am able to perform Post request to create a pet with valid request body")
	public void i_am_able_to_perform_post_request_to_create_a_pet_with_valid_request_body() {
		String URL = "https://petstore.swagger.io/v2/pet";
		String requestBody = "{\n"
				+ "  \"id\": 1239902,\n"
				+ "  \"category\": {\n"
				+ "    \"id\": 220,\n"
				+ "    \"name\": \"Dog\"\n"
				+ "  },\n"
				+ "  \"name\": \"Husky\",\n"
				+ "  \"photoUrls\": [\n"
				+ "    \"string\"\n"
				+ "  ],\n"
				+ "  \"tags\": [\n"
				+ "    {\n"
				+ "      \"id\": 330,\n"
				+ "      \"name\": \"dog\"\n"
				+ "    }\n"
				+ "  ],\n"
				+ "  \"status\": \"sold\"\n"
				+ "}";
		
// constructing the request in restAssured and storing the response into response object
		response = given().contentType(ContentType.JSON).accept(ContentType.JSON)
				.body(requestBody)
		        .when().post(URL);
		
		response.prettyPrint();
		// accessing the id of the response
		int id = response.path("id");
		System.out.println(id);
		System.out.println(response.path("name").toString());
		System.out.println(response.path("status").toString());
		System.out.println(response.getContentType());
		System.out.println(response.getStatusCode());
		
		System.out.println(response.path("category.id").toString());
		System.out.println(response.path("category.name").toString());
		
		System.out.println(response.path("tags[0].id").toString());
		System.out.println(response.path("tags[0].name").toString());
		
		Assert.assertEquals(response.getContentType(),"application/json");
		Assert.assertEquals(response.getStatusCode(), 200);
		// Assert.assertEquals(id, 123678);
		Assert.assertEquals(response.path("name"), "Husky");
		Assert.assertEquals(response.path("status"), "sold");
		
	}
		// post to add a pet Scenario #Starts
		
		@Given("I have the POST request URL and valid request body")
		public void i_have_the_post_request_url_and_valid_request_body() {
			requestURL = "https://petstore.swagger.io/v2/pet";
			requestBody = new File("./src/test/resources/JsonFiles");
		}
		@When("I perform POST request to URL with request body")
		public void i_perform_post_request_to_url_with_request_body() {
			response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody)
		    .when().post(requestURL);
		}
		@Then("Response status code should be {int}")
		public void response_status_code_should_be(Integer expectedStatusCode) {
		    response.then().assertThat().statusCode(expectedStatusCode);
		}
		@Then("Content type is {string}")
		public void content_type_is(String expectedContentType) {
		    response.then().assertThat().contentType(expectedContentType);
		}
		@Then("Response body match the request body")
		public void response_body_match_the_request_body() {
			petId = response.jsonPath().getInt("id");
			// there is something called chain validation in restAssured
		   response.then()
		   .assertThat().statusCode(200)
		   .and().assertThat().contentType("application/json")
		   .and().assertThat().body("id", equalTo(667788))
		   .and().assertThat().body("name", equalTo("Fluffy"))
		   .and().assertThat().body("status", equalTo("available"))
		   .and().assertThat().body("category.name", equalTo("Cat"))
		   .and().assertThat().body("tags[0].name", equalTo("dog"));
		}
		
		
		
		// invalid post request body scenario #Starts 
		@Given("I have the POST request URL and invalid request body")
		public void i_have_the_post_request_url_and_invalid_request_body() {
			requestURL = "https://petstore.swagger.io/v2/pet";
			requestBody = new File("./src/test/resources/jsonFiles/invalidRequestBody.json");
		}
		@Then("Response status code should be {int} invalid input")
		public void response_status_code_should_be_invalid_input(Integer expectedStatusCode) {
			response.then().assertThat().statusCode(expectedStatusCode);
		}
		@Then("message should be {string}")
		public void message_should_be(String expectedMessageValue) {
			response.prettyPrint();
		    response.then().assertThat().body("message", equalTo(expectedMessageValue));
		}
		
		
		// get request by status scenario outline #Starts
		
		@Given("valid endpoint exist")
		public void valid_endpoint_exist() {
			requestURL = "https://petstore.swagger.io/v2/pet/findByStatus";
		}
		@When("I send a GET request by status {string} to valid endpoint")
		public void i_send_a_get_request_by_status_to_valid_endpoint(String actualStatus) {
		   response = given().accept(ContentType.JSON).queryParam("status", actualStatus)
		   .when().get(requestURL);
		   
		   response.prettyPrint();
		   
		}
		@Then("Content type should be {string}")
		public void content_type_should_be(String actualContentType) {
		    response.then().assertThat().contentType(actualContentType);
		}
		
		// invalid URL get request scenario #Starts
		
		@Given("Invalid endpoint exist")
		public void invalid_endpoint_exist() {
			// this is invalid URL
		    requestURL = "https://petstore.swagger.io/v2/pet/findBy";
		}
		@When("I send a GET request by status {string} to invalid endpoint")
		public void i_send_a_get_request_by_to_valid_endpoint(String expectedStatus) {
		   response = given().accept(ContentType.JSON).queryParam("status", expectedStatus)
		   .when().get(requestURL);
		}
		
		
		// get by pet id scenario #Starts 
		@Given("I have the GET request URL")
		public void i_have_the_get_request_url() {
		   requestURL = "https://petstore.swagger.io/v2/pet/";
		}
		@When("I perform GET request to URL with pet id {int}")
		public void i_perform_get_request_to_url_with_pet_id(Integer petID) {
		    response = given().accept(ContentType.JSON)
		    .when().get(requestURL + petID);
		    
		    response.prettyPrint();
		}
		@Then("Pet id is {int}, pet name is {string} status is {string}")
		public void pet_id_is_pet_name_is_status_is(Integer petID, String petName, String petStatus) {
		    response.then().assertThat().body("id", equalTo(petID))
		    .and().assertThat().body("name", equalTo(petName))
		    .and().assertThat().body("status", equalTo(petStatus));
		}
		@Then("Category id is {int}, category name is {string}")
		public void category_id_is_category_name_is(Integer categoryID, String categoryName) {
		    response.then().assertThat().body("category.id", equalTo(categoryID))
		    .and().assertThat().body("category.name", equalTo(categoryName));
		}
		@Then("Tags id is {int}, and tags name is {string}")
		public void tags_id_is_and_tags_name_is(Integer tagsID, String tagsName) {
			response.then().assertThat().body("tags[0].id", equalTo(tagsID))
		    .and().assertThat().body("tags[0].name", equalTo(tagsName))
			.and().assertThat().body("tags[1].id", equalTo(22002))
			.and().assertThat().body("tags[1].name", equalTo("husky"));
		}
		
		// get a pet by invalid scenario #Starts
		@When("I perform GET request to URL with invalid id {int}")
		public void i_perform_get_request_to_url_with_invalid_id(Integer petID) {
			response = given().accept(ContentType.JSON)
				    .when().get(requestURL + petID);
				    
				    response.prettyPrint();
		}
		
		// delete by per id #Starts
		@Given("I have the Delete request URL")
		public void i_have_the_delete_request_url() {
		    requestURL = "https://petstore.swagger.io/v2/pet/";
		}
		@When("I perform DELETE request to URL with valid id {int}")
		public void i_perform_delete_request_to_url_with_valid_id(Integer petID) {
		    response = given().accept(ContentType.JSON)
		    .when().delete(requestURL + petID);
		    
		    response.prettyPrint();
		}

		// update petID 
		
		@Given("I have the Update request URL")
		public void i_have_the_update_request_url() {
		    requestURL = "https://petstore.swagger.io/v2/pet";
		}
		@When("I perform UPDATE request to update Name to existing Pet")
		public void i_perform_update_request_to_update_name_with_valid() {
			requestBody = new File("./src/test/resources/JsonFiles/updateAPet.json");
			response = given().contentType(ContentType.JSON).accept(ContentType.JSON).body(requestBody)
			.when().put(requestURL);
		}
		@Then("Id is {int} and is {string}")
		public void id_is_and_is(Integer petID, String petName) {
		   response.then().assertThat().body("id", equalTo(petID))
		   .and().assertThat().body("name", equalTo(petName));
		}


	}




