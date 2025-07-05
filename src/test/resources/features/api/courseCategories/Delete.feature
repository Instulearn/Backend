Feature: As an administrator, I want to be able to delete course category information with the specified id number via API connection.

  Scenario: Verify that a DELETE request to /api/deleteCategory/{id} with valid authorization and correct id returns
  status 200, status “success”, message “Successfully Deleted.”, and that the Deleted Category Id in the response
  matches the {id} path parameter.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/deleteCategory/914" path parameters.
    * The api user sends a DELETE request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "data.status" information in the response body is "success".
    * The api user verifies that the "Message" information in the response body is "Successfully Deleted.".
    * The api user verifies that the "Deleted Category Id" in the response body is the same as the id path parameter in the endpoint.


  Scenario: When a DELETE request is sent to the /api/deleteCategory/{id} endpoint with valid authorization
  information and an id that does not have a record, it should be verified that the returned status code is 203, the
  remark information in the response body is "failed" and the message information is "There is not category for this id."

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/deleteCategory/82639" path parameters.
    * The api user sends a DELETE request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "remark" information in the response body is "failed".
    * The api user verifies that the "data.message" information in the response body is "There is not category for this id.".


  Scenario: When a DELETE request is sent to the /api/deleteCategory/{id} endpoint without valid authorization information
  and (id), it should be verified that the returned status code is 203, the remark information in the response body is
  "failed" and the message information is "No id".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/deleteCategory" path parameters.
    * The api user sends a DELETE request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "remark" information in the response body is "failed".
    * The api user verifies that the "data.message" information in the response body is "No id".


  Scenario: Verify that a DELETE request to /api/deleteCategory/{id} with invalid token and correct id returns status 401
  and message “Unauthenticated.”

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/deleteCategory/889" path parameters.
    #* The api user sends a DELETE request and saves the returned response.
    #* The api user verifies that the status code is 401.
    #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
    * The api user sends a DELETE request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.


  Scenario Outline: Verify that the deleted course category via API is successfully deleted by sending a GET request to
  /api/category/{id} using the Deleted Category ID from the response.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/category/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the "data.message" information in the response body is "There is not category for this id.".

    Examples:
      | id  |
      | 522|


