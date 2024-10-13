Feature: Swagger Petstore API Test

  Scenario: Verify fetching pets by status
    Given the petstore API is available
    When I send a GET request to "/pet/findByStatus?status=available"
    Then the response status should be 200
    And the response should contain a list of pets

  Scenario: Add a new pet to the store
    Given the petstore API is available
    When I send a POST request to "/pet" with the JSON payload from file "pet_payload.json"
    Then the response status should be 200
    And the response should contain the pet details

  Scenario: Update an existing pet's information
    Given the petstore API is available
    When I send a PUT request to "/pet" with the JSON payload from file "update_pet_payload.json"
    Then the response status should be 200
    And the response should reflect the updated pet information

  Scenario: Delete a pet from the store
    Given the petstore API is available
    When I send a DELETE request to "/pet/10"
    Then the response status should be 200
    And the pet with ID 10 should no longer exist