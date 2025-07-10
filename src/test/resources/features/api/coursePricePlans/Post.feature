@kc3
Feature: [US_013] As an administrator I want to be able to create a new course price plan record via API connection.


  Scenario: When a POST body with valid authorization information and correct data
  (title, dateRange, discount, capacity, webinar_id) is sent to /api/addPricePlan endpoint,
  it should be verified that the status code returned is 200, the remark in the response body is “success” and the
  Message is “Successfully Added.”.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/addPricePlan" path parameters.
    # Api kullanicisi "api/addCategory" path parametrelerini olusturur
    * The API user prepares a POST request body to send to the addPricePlan endpoint with the "title" "Api_training", "dateRange" "2025-07-07 - 2025-08-02", "discount" 15, "capacity" 80, and "webinar_id" 2050.
    # Api kullanicisi api addCategory endpointine gondermek icin title "Api_training", dateRange "2025-07-07 - 2025-08-02", discount 10, capacity 150 ve webinar_id 2010 olarak bir post request body hazirlar
    * The API user sends a POST request to coursplanprice and records the returned response.
    # Api kullanicisi POST request gonderir ve donen responsei kaydeder.
    # Bu stepte kayıt edilen id, otomatik olarak son TC'de kullanılır. Birlikte çalıştırılmalıdır.
    * The API user verifies that the status code for courspriceplan is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * API user verifies that the "remark" information in the response body for courspriceplan is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * API user verifies that the "Message" in the response body for courspriceplan is "Successfully Added.".
    # Api kullanicisi response bodydeki Message bilgisinin "Successfully Added." oldugunu dogrular


  Scenario: Verify that a POST request to /api/addPricePlan with valid authorization but no data returns status 422 and
  message “The title field is required. (and 3 more errors)”

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/addPricePlan" path parameters.
    # Api kullanicisi "api/addCategory" path parametrelerini olusturur
    * The api user prepares a post request without any data to send to the api addPricePlan endpoint.
    # Api kullanicisi api addCategory endpointine gondermek için data içermeyen bir post request hazirlar
    * The API user sends a POST request to coursplanprice and records the returned response.
    # Api kullanicisi POST request gonderir ve donen responsei kaydeder
    * The API user verifies that the status code for courspriceplan is 422.
    # Api kullanicisi status codeun 422 oldugunu dogrular
    * API user verifies that the "message" in the response body for courspriceplan is "The title field is required. (and 3 more errors)".
    # Api kullanicisi response bodydeki message bilgisinin "The title field is required." oldugunu dogrular


  @kc
  Scenario: Verify that a POST body with invalid authorization information (invalid token) and correct data
  (title, dateRange, discount, capacity, webinar_id) is sent to /api/addPricePlan endpoint, it should be verified
  that the status code returned is 401 and the message information in the response body is “Unauthenticated.”

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/addPricePlan" path parameters.
    # Api kullanicisi "api/addCategory" path parametrelerini olusturur
    * The API user prepares a POST request body to send to the addPricePlan endpoint with the "title" "Api_training", "dateRange" "2025-07-07 - 2025-08-02", "discount" 10, "capacity" 150, and "webinar_id" 2010.
    # Api kullanicisi api addCategory endpointine gondermek icin bir post request body hazirlar
    * The API user sends a POST request to coursplanprice and records the returned response.
    # Api kullanicisi POST request gonderir ve donen responsei kaydeder
    * The API user verifies that the status code for courspriceplan is 401.
    # Api kullanicisi status codeun 401 oldugunu dogrular
    * API user verifies that the "message" in the response body for courspriceplan is "Unauthenticated.".
    # Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular


  Scenario: Verify that the newly created course price plan via API exists by sending a GET request to
  /api/pricePlan/{id} using the Added Category ID from the response.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The API user enters the id value she post into the "api/pricePlan" parameter.
    # Api kullanicisi "api/pricePlan" path parametrelerini olusturur, GET request gonderir ve donen responsei kaydeder
    * The API user verifies that the status code for courspriceplan is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * API user verifies that the "remark" in the response body for courspriceplan is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular

