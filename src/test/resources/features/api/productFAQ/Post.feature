Feature: As an administrator I want to be able to create a new product faq record via API connection.

  Scenario: When a POST body with valid authorization information and correct data (title, answer, product_id) is
  sent to /api/addProductfaq endpoint, it should be verified that the status code returned is 200, the remark
  in the response body is “success” and the Message is “Successfully Added.”.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/addProductfaq" path parameters.
    # Api kullanicisi "api/addCategory" path parametrelerini olusturur
    * The api user prepares a post request body to send to the api addCategory endpoint for productFAQ.
    # Api kullanicisi api addCategory endpointine gondermek icin bir post request body hazirlar
    * The api user sends a POST request and saves the returned response for productFAQ.
    # Api kullanicisi POST request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200 for productFAQ.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success" for productFAQ.
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies that the "Message" information in the response body is "Successfully Added." for productFAQ.
    # Api kullanicisi response bodydeki Message bilgisinin "Successfully Added." oldugunu dogrular


  Scenario: When a POST request is sent to /api/addProductfaq endpoint without valid authorization information and data,
  it should be verified that the status code returned is 422 and the message information in the response body is
  “The product id field is required. (and 2 more errors)”

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/addProductfaq" path parameters.
    # Api kullanicisi "api/addCategory" path parametrelerini olusturur
    * The api user prepares a post request without any data to send to the api addCategory endpoint for productFAQ.
    # Api kullanicisi api addCategory endpointine gondermek için data içermeyen bir post request hazirlar
    * The api user sends a POST request and saves the returned response for productFAQ.
    # Api kullanicisi POST request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 422 for productFAQ.
    # Api kullanicisi status codeun 422 oldugunu dogrular
    * The api user verifies that the "message" information in the response body is "The product id field is required. (and 2 more errors)" for productFAQ.
    # Api kullanicisi response bodydeki message bilgisinin "The product id field is required. (and 2 more errors)" oldugunu dogrular


  Scenario: When sending a POST body with invalid authorization information (invalid token) and correct data
  (title, answer, product_id) to /api/addProductfaq endpoint, it should be verified that the status code returned is
  401 and the message information in the response body is “Unauthenticated.”

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/addProductfaq" path parameters.
    # Api kullanicisi "api/addCategory" path parametrelerini olusturur
    * The api user prepares a post request body to send to the api addCategory endpoint for productFAQ.
    # Api kullanicisi api addCategory endpointine gondermek icin bir post request body hazirlar
    * The api user sends a POST request and saves the returned response for productFAQ.
    # Api kullanicisi POST request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 401 for productFAQ.
    # Api kullanicisi status codeun 401 oldugunu dogrular
    * The api user verifies that the "message" information in the response body is "Unauthenticated." for productFAQ.
    # Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular


  Scenario Outline: The creation of the new product faq record to be created through the API must be verified
  through the API. (It can be verified that the record was created by sending a GET request to the
  /api/productfaq/{id} endpoint with the Added Product Faq ID returned in the response body).

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/productfaq/<id>" path parameters.
    # Api kullanicisi "api/category/<id>" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular

    Examples:
      | id  |
      | 67  |

