Feature: As an administrator, I want to be able to create a new course category record via API connection.

  Scenario: Verify that a POST request to /api/addCategory with valid authorization and title returns status 200, remark
  “success”, and message “Successfully Added.”

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/addCategory" path parameters.
    * The api user prepares a post request body to send to the api addCategory endpoint.
    * The api user sends a POST request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "remark" information in the response body is "success".
    * The api user verifies that the "Message" information in the response body is "Successfully Added.".


  Scenario: Verify that a POST request to /api/addCategory with valid authorization but no data returns status 422 and
  message “The title field is required.”

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/addCategory" path parameters.
    * The api user prepares a post request without any data to send to the api addCategory endpoint.
    * The api user sends a POST request and saves the returned response.
    * The api user verifies that the status code is 422.
    * The api user verifies that the "message" information in the response body is "The title field is required.".


  Scenario: Verify that a POST request to /api/addCategory with invalid token and valid title returns status 401 and message
  “Unauthenticated.”

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/addCategory" path parameters.
    * The api user prepares a post request body to send to the api addCategory endpoint.
    * The api user sends a POST request and saves the returned response.
    * The api user verifies that the status code is 401.
    * The api user verifies that the "message" information in the response body is "Unauthenticated.".


  Scenario Outline: Verify that the newly created course category via API exists by sending a GET request to
  /api/category/{id} using the Added Category ID from the response.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/category/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "remark" information in the response body is "success".

    Examples:
      | id  |
      | 915 |

