Feature: As an administrator, I want to be able to update the information of the course
  with the specified id number via API connection.

  Scenario: Verify that a PATCH request to /api/updateCourse/{id} with valid authorization and correct data
  (duration, capacity, price, title) returns status 200, remark “success”, message “Successfully Updated.”, and the Updated Course Id in the
  response matches the {id} path parameter.

    * The api user prepares a patch request body to send to the api updateCourse endpoint.
    * The api user sends a PATCH request and saves the returned response
    * The api user verifies that the status code is 200
    * The api user verifies that the "remark" information in the response body is "success"
    * The api user verifies that the "Message" information in the response body is "Successfully Updated."
    * The api user verifies that the "Updated Course Id" information in the response body is the same as the id path parameter in the endpoint


  Scenario: When a PATCH body containing an unregistered (id) with valid authorization information and the correct
  data (title) is sent to the /api/updateCategory/{id} endpoint, it should be verified that the returned status code is 203,
  the remark information in the response body is "failed" and the message information is "There is not category for this id."

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/updateCategory/12547" path parameters.
    * The api user prepares a patch request body to send to the api updateCategory endpoint.
    * The api user sends a PATCH request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "remark" information in the response body is "failed".
    * The api user verifies that the "data.message" information in the response body is "There is not category for this id.".


  Scenario: When a PATCH body that does not contain valid authorization information (id) but contains correct data
  (title) is sent to the /api/updateCategory/{id} endpoint, it should be verified that the returned status code is 203, the
  remark information in the response body is "failed" and the message information is "No id".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/updateCategory" path parameters.
    * The api user prepares a patch request body to send to the api updateCategory endpoint.
    * The api user sends a PATCH request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "remark" information in the response body is "failed".
    * The api user verifies that the "data.message" information in the response body is "No id".


  Scenario: Verify that a PATCH request to /api/updateCategory/{id} with invalid token, correct id, and valid title
  returns status 401 and message “Unauthenticated.”

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/updateCategory/886" path parameters.
    * The api user prepares a patch request body to send to the api updateCategory endpoint.
    #* The api user sends a PATCH request and saves the returned response.
    #* The api user verifies that the status code is 401.
    #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
    * The api user sends a PATCH request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.


  Scenario Outline: Verify that the updated course category via API is correctly updated by sending a GET request to
  /api/category/{id} using the Updated Category ID from the response.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/category/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the title information is "Education and Training"

    Examples:
      | id  |
      | 911 |

