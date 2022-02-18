package step_definitions;

import io.cucumber.java.en.When;
import static io.restassured.RestAssured.*;

import org.junit.Assert;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PetStoreSteps {
	
	Response response;
	
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
		
		Assert.assertEquals(response.path("name"), "Husky");
		Assert.assertEquals(response.path("status"), "sold");
	}

}


