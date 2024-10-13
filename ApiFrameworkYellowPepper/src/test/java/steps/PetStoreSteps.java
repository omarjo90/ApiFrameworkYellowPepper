package steps;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class PetStoreSteps {

    private Response response;
    private Map<String, Object> payload;

    @Given("the petstore API is available")
    public void the_petstore_api_is_available() {
        String apiUrl = System.getenv("PETSTORE_API_URL");
        if (apiUrl == null) {
            // Default to localhost if the environment variable is not set
            apiUrl = "http://localhost:8080/api/v3"; // Use this for local testing
        }
        RestAssured.baseURI = apiUrl;
    }

    @When("I send a GET request to {string}")
    public void i_send_a_GET_request_to(String endpoint) {
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get(endpoint)
                .then()
                .extract()
                .response();
    }

    @When("I send a POST request to {string} with the JSON payload from file {string}")
    public void i_send_a_POST_request_to_with_json_payload_from_file(String endpoint, String fileName) {
        try {
            // Read the JSON file
            String jsonPayload = new String(Files.readAllBytes(Paths.get("src/test/resources/payloads/" + fileName)));

            // Send the POST request with the JSON payload
            response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(jsonPayload)
                    .when()
                    .post(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to read JSON payload from file: " + e.getMessage());
        }
    }


    @When("I send a PUT request to {string} with the JSON payload from file {string}")
    public void i_send_a_PUT_request_to_with_json_payload_from_file(String endpoint, String fileName) {
        try {
            // Read the JSON file
            String jsonPayload = new String(Files.readAllBytes(Paths.get("src/test/resources/payloads/" + fileName)));

            // Send the PUT request with the JSON payload
            response = RestAssured
                    .given()
                    .contentType(ContentType.JSON)
                    .body(jsonPayload)
                    .when()
                    .put(endpoint)
                    .then()
                    .extract()
                    .response();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to read JSON payload from file: " + e.getMessage());
        }
    }


    @When("I send a DELETE request to {string}")
    public void i_send_a_DELETE_request_to(String endpoint) {
        response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .delete(endpoint)
                .then()
                .extract()
                .response();
    }

    @Then("the response status should be {int}")
    public void the_response_status_should_be(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain a list of pets")
    public void the_response_should_contain_a_list_of_pets() {
        response.then().body("id", notNullValue());
        // Additional assertions can be added here
    }

    @Then("the response should contain the pet details")
    public void the_response_should_contain_the_pet_details() {
        assertNotNull(response.jsonPath().get("id"), "Pet ID should not be null");
        assertEquals("doggie", response.jsonPath().get("name"), "Pet name should be 'doggie'");
        assertEquals("available", response.jsonPath().get("status"), "Pet status should be 'available'");

        // Assert category details
        assertNotNull(response.jsonPath().get("category"), "Category should not be null");
        assertEquals(1, (int) response.jsonPath().get("category.id"), "Category ID should be 1"); // Cast to int
        assertEquals("Dogs", response.jsonPath().get("category.name"), "Category name should be 'Dogs'");

        // Assert photoUrls
        assertNotNull(response.jsonPath().get("photoUrls"), "Photo URLs should not be null");

        // Assert tags
        assertNotNull(response.jsonPath().get("tags"), "Tags should not be null");
    }

    @Then("the response should reflect the updated pet information")
    public void the_response_should_reflect_the_updated_pet_information() {
        assertNotNull(response.jsonPath().get("id"));
        assertEquals("UpdatedPet", response.jsonPath().get("name"));
        assertEquals("sold", response.jsonPath().get("status"));
    }

    @Then("the pet with ID {int} should no longer exist")
    public void the_pet_with_ID_should_no_longer_exist(Integer petId) {
        // Attempt to retrieve the deleted pet
        Response getResponse = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .when()
                .get("/pet/" + petId)
                .then()
                .extract()
                .response();

        // Expecting a 404 Not Found status
        System.out.println("Response Body: " + getResponse.getBody().asString());

        // Expecting a 404 Not Found status
        getResponse.then().statusCode(404);

        // Check for specific error message
        String responseBody = getResponse.getBody().asString();
        assertEquals("Pet not found", responseBody, "Expected response body when pet is not found");
    }
}
