package stepdefinations;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.given;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.APIResources;
import resources.TestData;
import testrunner.Utilities;
import static org.junit.Assert.*;
import java.io.IOException;

public class AddPlaceStepDefination extends Utilities {

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response resp;
	static String place_id;
	TestData td = new TestData();

	@Given("Add place payload with {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		reqSpec = given().spec(requestSpecification()).body(td.addPlaceJson(name, language, address));
	}

	@When("User Calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
		APIResources resources = APIResources.valueOf(resource);
		System.out.println(resources.getResource());
		if (httpMethod.equalsIgnoreCase("POST")) {
			resp = reqSpec.when().post(resources.getResource());
		} else if (httpMethod.equalsIgnoreCase("GET")) {
			resp = reqSpec.when().get(resources.getResource());
		}
	}

	@Then("API call got success with status code {int}")
	public void api_call_got_success_with_status_code(Integer statusCode) {
		assertEquals(resp.getStatusCode(), 200);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String key, String value) {
		assertEquals(getJsonPath(resp, key), value);
	}

	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String resource) throws IOException {
		place_id = getJsonPath(resp, "place_id").toString();
		reqSpec = given().spec(requestSpecification()).queryParam("place_id", place_id);
		user_calls_with_http_request(resource, "GET");
		String actualName = getJsonPath(resp, "name").toString();
		assertEquals(actualName, expectedName);
	}

	@Given("DeletePlace Payload")
	public void delete_place_payload() throws IOException {
		reqSpec = given().spec(requestSpecification()).body(td.deletePlacePayload(place_id));
	}

}