Feature: As an administrator, I should be able to access the detailed information of the course with the specified id number through the API connection.


  Scenario Outline: When a GET request with valid authorization information and correct data (id) is sent to the /api/course/{id} endpoint,
  it should be verified that the status code returned is 200 and the remark in the response body is “success”,
  and the list data (id, teacher_id, creator_id, category_id, type, private, slug, start_date, duration, timezone) in the response body must be validated.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/course/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "remark" information in the response body is "success".
    * The api user verifies the <id>, <teacher_id>, <creator_id>, <category_id>, "<type>", <private>, "<slug>", <duration>, "<timezone>" contents in the response body data.

    Examples:
      | id   | teacher_id | creator_id | category_id | type   | private | slug                     | duration | timezone         |
      | 1995 | 1016       | 1016       | 611         | course | 0       | Become-a-Project-Manager | 150      | America/New_York |


  Scenario Outline: When a GET request is sent to the /api/course/{id} endpoint with valid authorization information
  and an id that does not have a record, it should be verified that the returned status code is 203, the remark
  information in the response body is "failed" and the message information is "There is not course for this id."

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/course/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "remark" information in the response body is "failed".
    * The api user verifies that the "data.message" information in the response body is "There is not course for this id.".

    Examples:
      | id    |
      | 25416 |


  Scenario: When a GET request is sent to the /api/course/{id} endpoint with valid authorization information and no (id),
  it should be verified that the returned status code is 203, the remark information in the response body is "failed" and
  the message information is "No id".

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/course" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 203.
    * The api user verifies that the "remark" information in the response body is "failed".
    * The api user verifies that the "data.message" information in the response body is "No id".


  Scenario Outline: When a GET request is sent to the /api/course/{id} endpoint with invalid (invalid token) authorization
  credentials and correct data (id), it should return a status code of 401. Additionally, it should be verified that the
  message field in the response body is "Unauthenticated."

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/course/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 401.
    * The api user verifies that the "message" information in the response body is "Unauthenticated.".
    # * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.

    Examples:
      | id  |
      | 1995 |

