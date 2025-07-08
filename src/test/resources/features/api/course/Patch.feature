Feature: As an administrator, I want to be able to update the information of the course
  with the specified id number via API connection.

  Scenario: Verify that a PATCH request to /api/updateCourse/{id} with valid authorization and correct data
  (duration, capacity, price, title) returns status 200, remark “success”, message “Successfully Updated.”, and the Updated Course Id in the
  response matches the {id} path parameter.

    * The api user prepares a patch request body to send to the api updateCourse endpoint.
    * The api user sends a PATCH request for id 2664 and saves the returned response
    * The api user verifies that the status code is 200
    * The api user verifies that the "remark" information in the response body is "success"
    * The api user verifies that the "Message" information in the response body is "Successfully Updated."
    * The api user verifies that the "Updated Course ID" information in the response body is the same as the id path parameter in the endpoint


  Scenario: When a PATCH body containing an (id) and correct data (duration, capacity, price, title) is sent to the endpoint
  /api/updateCourse/{id} with valid authorization information, the status code returned is 203, the remark in the response
  body is “failed” and the message is "There is not course for this id.“

    * The api user prepares a patch request body to send to the api updateCourse endpoint.
    * The api user sends a PATCH request for id 266477 and saves the returned response
    * The api user verifies that the status code is 203
    * The api user verifies that the "remark" information in the response body is "failed"
    * The api user verifies that the "data.message" information in the response body is "There is not course for this id."


  Scenario: When a PATCH body that does not contain (id) and contains the correct data
  (duration, capacity, price, title), it should be verified that the status code returned is 203, the
  remark in the response body is ‘failed’ and the message is ”No id".

    * The api user prepares a patch request body to send to the api updateCourse endpoint.
    * The api user sends a PATCH request for id 0 and saves the returned response
    * The api user verifies that the status code is 203
    * The api user verifies that the "remark" information in the response body is "failed"
    * The api user verifies that the "data.message" information in the response body is "No id"


  Scenario: Verify that a PATCH request to /api/updateCourse/{id} with invalid token, correct id, and correct data
  (duration, capacity, price, title) returns status 401 and message “Unauthenticated.”

    * The api user prepares a patch request body to send to the api updateCourse endpoint.
    * The api user sends a PATCH request with "invalid" token and id 2664 and saves the returned response.
    * The api user verifies that the status code is 401
    * The api user verifies that the "message" information in the response body is "Unauthenticated."
    #* The api user sends a PATCH request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.


  Scenario Outline: "The course record that is requested to be updated through the API must be verified that it has been
  updated through the API. (With the Updated Course ID returned in the response body, it can be verified that the record
  has been updated by sending a GET request to the /api/course/{id} endpoint)."

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/course/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the title information is "Web Development 101"

    Examples:
      | id  |
      | 2664 |

