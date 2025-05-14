package com.abhishekvermaa10.steps;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;

import com.abhishekvermaa10.util.CucumberUtil;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

/**
 * @author abhishekvermaa10
 */
public class OwnerSteps {

	@LocalServerPort
	private int port;
	private String urlEndpoint;
	private HttpMethod httpMethod;
	private Response response;

	@Given("the REST API service {string}")
	public void the_rest_api_service(String endpoint) {
		urlEndpoint = endpoint;
	}

	@Given("the HTTP method is {word}")
	public void the_http_method_is(String method) {
		httpMethod = HttpMethod.valueOf(method);
	}

	@When("make the REST API call with body")
	public void make_the_rest_api_call_with_body(String docString) {
		String requestBody = CucumberUtil.cleanJson(docString);
		response = CucumberUtil.executeApiCall(port, urlEndpoint, httpMethod, requestBody);
	}

	@Then("the REST API response has status as {int}")
	public void the_rest_api_response_has_status_as(int expectedStatusCode) {
		assertNotNull(response, "Response is null");
		assertEquals(expectedStatusCode, response.getStatusCode(), "Mismatch in status code of response");
	}

	@Then("the REST API response contains owner ID")
	public void the_rest_api_response_contains_owner_id() {
		assertNotNull(response, "Response is null");
		int ownerId = Integer.parseInt(response.getBody().asString().trim());
		assertTrue(ownerId > 0, "Owner ID should be a positive number");
	}
	
	@Then("the REST API response contains first errorMessage as {string}")
	public void the_rest_api_response_contains_first_error_message_as(String errorMessage) {
		assertNotNull(response, "Response is null");
		JsonPath jsonPath = response.jsonPath();
		String actualError = jsonPath.getString("[0].message");
		assertEquals(errorMessage, actualError, "Mismatch in first error message");
	}
	
	@When("make the REST API call without body")
	public void make_the_rest_api_call_without_body() {
		response = CucumberUtil.executeApiCall(port, urlEndpoint, httpMethod, null);
	}

	@Then("the REST API response contains owner data")
	public void the_rest_api_response_contains_owner_data(DataTable dataTable) {
		assertNotNull(response, "Response is null");
		JsonPath jsonPath = response.jsonPath();
		List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
		for (Map<String, String> row : rows) {
			assertEquals(row.get("id"), jsonPath.getString("id"), "Mismatch in id of owner.");
			assertEquals(row.get("firstName"), jsonPath.getString("firstName"), "Mismatch in firstName of owner.");
			assertEquals(row.get("lastName"), jsonPath.getString("lastName"), "Mismatch in lastName of owner.");
			assertEquals(row.get("gender"), jsonPath.getString("gender"), "Mismatch in gender of owner.");
			assertEquals(row.get("city"), jsonPath.getString("city"), "Mismatch in city of owner.");
			assertEquals(row.get("state"), jsonPath.getString("state"), "Mismatch in state of owner.");
			assertEquals(row.get("mobileNumber"), jsonPath.getString("mobileNumber"), "Mismatch in mobileNumber of owner.");
			assertEquals(row.get("emailId"), jsonPath.getString("emailId"), "Mismatch in emailId of owner.");
			assertEquals(row.get("petDTO.category"), jsonPath.getString("petDTO.category"), "Mismatch in category of pet.");
			assertEquals(row.get("petDTO.name"), jsonPath.getString("petDTO.name"), "Mismatch in name of pet.");
			assertEquals(row.get("petDTO.gender"), jsonPath.getString("petDTO.gender"), "Mismatch in gender of pet.");
			assertEquals(row.get("petDTO.type"), jsonPath.getString("petDTO.type"), "Mismatch in type of pet.");
			if (row.get("petDTO.category").equals("Domestic")) {
				assertEquals(row.get("petDTO.birthDate"), jsonPath.getString("petDTO.birthDate"),
						"Mismatch in date of birth of pet.");
			}
			if (row.get("petDTO.category").equals("Wild")) {
				assertEquals(row.get("petDTO.birthPlace"), jsonPath.getString("petDTO.birthPlace"),
						"Mismatch in place of birth of pet.");
			}
		}
	}
	
	@Then("the REST API response contains errorMessage as {string}")
	public void the_rest_api_response_contains_error_message_as(String errorMessage) {
		assertNotNull(response, "Response is null");
		JsonPath jsonPath = response.jsonPath();
		String actualError = jsonPath.getString("message");
		assertEquals(errorMessage, actualError, "Mismatch in error message");
	}

}
