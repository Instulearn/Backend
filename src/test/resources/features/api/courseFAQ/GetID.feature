Feature: As an administrator, I should be able to access the detailed information of the course coursefaq with
  the specified id number through the API connection.


  Scenario Outline: When a GET request with valid authorization and correct id is sent to the /api/coursefaq/{id} endpoint,
  the response status code should be 200, the remark should be “success”, and the list data (id, slug, parent_id, icon,
  order, title, category_id, locale) in the response body should be validated.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coursefaq/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "remark" information in the response body is "success".
    * The api user verifies the <id>, <created_id>, <webinar_id>, <created_at>, <updated_at>,contents in the response body data.

    Examples:
      | id | created_id  |   webinar_id |  created_at | updated_at   |
      |   14    |    1016     |   1995     |  1624908812 |  1718059480  |


  Scenario Outline: When a GET request is sent to the /api/coursefaq/{id} endpoint with valid authorization information
  and an id that does not have a record, it should be verified that the returned status code is 203, the remark
  information in the response body is "failed" and the message information is "There is not category for this id."

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coursefaq/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "remark" information in the response body is "failed".
    * The api user verifies that the "data.message" information in the response body is "There is not coursefaq for this id.".

    Examples:
      | id    |
      | 20000 |


  Scenario: When a GET request is sent to the /api/coursefaq/{id} endpoint without valid authorization information and (id),
  it should be verified that the returned status code is 203, the remark information in the response body is "failed" and
  the message information is "No id".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/coursefaq" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "remark" information in the response body is "failed".
    * The api user verifies that the "data.message" information in the response body is "No id".


  Scenario Outline: When a GET request is sent to the /api/coursefaq/{id} endpoint with invalid (invalid token) authorization
  credentials and correct data (id), it should return a status code of 401. Additionally, it should be verified that the
  message field in the response body is "Unauthenticated."

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/coursefaq/<id>" path parameters.
    #* The api user sends a GET request and saves the returned response.
    #* The api user verifies that the status code is 401.
    #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.

    Examples:
      | id  |
      | 14 |