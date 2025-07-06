Feature: As an administrator, I should be able to access the detailed information of the course price plan with
  the specified id number via the API connection.


  Scenario: When a GET request with valid authorization and correct id is sent to the /api/category/{id} endpoint,
  the response status code should be 200, the remark should be “success”, and the list data (id, creator_id, webinar_id,
  bundle_id, start_date, end_date, discount, capacity, order, created_at, updated_at, deleted_at, title, id, ticket_id,
  locale, title) in the response body must be verified.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/pricePlan/125" path parameters.
    # Api kullanicisi "api/category/<id>" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * And The api user verifies the list data with id 125, creator_id 1330, webinar_id 2002, bundle_id null, start_date 1717200000, end_date 1719705600, discount 20, capacity 50, order null, created_at 1717713962, updated_at null, deleted_at null, ticket_id 125, locale "en", title "Test Price Plans" in the response body.
    # Api kullanicisi response bodydeki dataların <data_id>, "<slug>", "<icon>", <order>, <translations_id>, <category_id>, "<locale>" ve "<title>" içeriklerini doğrular.


  Scenario: When a GET request is sent to the /api/pricePlan/{id} endpoint with valid authorization information
  and an id that does not have a record, it should be verified that the returned status code is 203, the remark
  information in the response body is "failed" and the message information is "There is not category for this id."

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/pricePlan/12500" path parameters.
    # Api kullanicisi "api/category/<id>" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "data.message" information in the response body is "There is not ticket for this id.".
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular



  Scenario: When a GET request is sent to the /api/pricePlan/{id} endpoint without valid authorization information and (id),
  it should be verified that the returned status code is 203, the remark information in the response body is "failed" and
  the message information is "No id".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/pricePlan" path parameters.
    # Api kullanicisi "api/category/<id>" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "data.message" information in the response body is "No id".
    # Api kullanicisi response bodydeki message bilgisinin "No id" oldugunu dogrular

  @kc
  Scenario: When a GET request is sent to the /api/pricePlan/{id} endpoint with invalid (invalid token) authorization
  credentials and correct data (id:125), it should return a status code of 401. Additionally, it should be verified that the
  message field in the response body is "Unauthenticated."

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/pricePlan/125" path parameters.
    # Api kullanicisi "api/category/<id>" path parametrelerini olusturur

    #* The api user sends a GET request and saves the returned response.
    ## Api kullanicisi GET request gonderir ve donen responsei kaydeder
    #* The api user verifies that the status code is 401.
    ## Api kullanicisi status codeun 401 oldugunu dogrular
    #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
    ## Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular
