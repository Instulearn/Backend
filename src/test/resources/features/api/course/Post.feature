Feature: As an administrator, I want to be able to create a new course record via API connection.

  Scenario: Verify that a POST request to /api/addCourse with valid authorization and correct data (title, type, slug,
  start_date, duration, capacity, price, description, teacher_id) the status code returned is 200, the remark in the
  response body is “success” and the Message is “Successfully Added.”.

    * The api user prepares a post request body to send to the api addCourse endpoint.
    * The api user sends a POST request with "admin" token and saves the returned response.
    * The api user verifies that the status code is 200
    * The api user verifies that the "remark" information in the response body is "success"
    * The api user verifies that the "Message" information in the response body is "Successfully Added."


  Scenario: Verify that a POST request to /api/addCourse with valid authorization and correct data (title, type, slug,
  start_date, duration, capacity, price, description) and missing data (teacher_id) the status code returned is 422 and
  the message in the response body is "The teacher id field is required.“, and when a POST request without data is sent,
  it should be verified that the status code returned is 422 and the message in the response body is
  ”The teacher id field is required. (and 2 more errors)"

    * The api user prepares a post request body with missing data to send to the api addCourse endpoint.
    * The api user sends a POST request with "admin" token and saves the returned response.
    * The api user verifies that the status code is 422
    * The api user verifies that the "message" information in the response body is "The teacher id field is required."
    * The api user prepares and posts request body with no data to send to the api addCourse endpoint.
    * The api user verifies that the "message" information in the response body is "The teacher id field is required. (and 2 more errors)"



  Scenario: Verify that a POST request to /api/addCourse with invalid token and valid data returns status 401 and message
  “Unauthenticated.”

    * The api user prepares a post request body to send to the api addCourse endpoint.
    * The api user sends a POST request with "invalid" token and saves the returned response.
    * The api user verifies that the status code is 401
    * The api user verifies that the "message" information in the response body is "Unauthenticated."


  Scenario Outline: Verify that the newly created course via API exists by sending a GET request to
  /api/course/{id} using the Added Course ID from the response.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/course/<id>" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "remark" information in the response body is "success".

    Examples:
      | id  |
      | 2676 |

