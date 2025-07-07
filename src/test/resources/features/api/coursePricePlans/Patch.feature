Feature: As an administrator, I want to be able to update the information of the course price plan with the specified
  id number via API connection.


  Scenario: When a PATCH body containing the correct (id) and correct data (title, dateRange, discount, capacity, webinar_id)
  with valid authorization information is sent to the /api/updatePricePlan/{id} endpoint, it should be verified that
  the status code returned is 200, the remark in the response body is “success” and the Message is “Successfully Updated.”
  and The Updated Price Plans ID in the response body returned from the /api/updatePricePlan/{id} endpoint must be
  verified to be the same as the id path parameter in the /api/updatePricePlan/{id} endpoint..

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updatePricePlan" path parameters.
    # Api kullanicisi "api/updatePricePlan/id" path parametrelerini olusturur
    * The API user prepares a POST request body to send to the addPricePlan endpoint with the "title" "Change_Api", "dateRange" "2025-07-07 - 2025-08-02", "discount" 5, "capacity" 200, and "webinar_id" 2002.
    # Api kullanicisi api updatePricePlan endpointine gondermek icin bir patch request body hazirlar, Post Body Request Methodu kullanıldı
    * The API user sends a PATCH request for id 214 to coursplanprice and records the returned response.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder. Bu stepte yazılan id otomatik olarak son TC'de kullanılır test edilir
    # **Son TC ile Birlikte çalıştırılmalıdır
    * The API user verifies that the status code for courspriceplan is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * API user verifies that the "remark" information in the response body for courspriceplan is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * API user verifies that the "Message" in the response body for courspriceplan is "Successfully Updated.".
    # Api kullanicisi response bodydeki Message bilgisinin "Successfully Updated." oldugunu dogrular
    * The api user verifies that the "Updated Price Plans ID" in the response body for courspriceplan is the same as the 214 id path parameter.
    # Api kullanıcısı response body icindeki "Updated Category Id" bilgisinin endpointde yazan id path parametresi ile ayni oldugunu dogrular.



  Scenario: When a PATCH request is sent to the /api/updatePricePlan/{id} endpoint with valid authorization information,
  it should be verified that the status code returned is 203, the remark in the response body is “failed” and
  the message is “There is no information to update.”.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updatePricePlan/150" path parameters.
    # Api kullanicisi "api/updateCategory/id" path parametrelerini olusturur
    * The api user prepares a post request without any data to send to the api addPricePlan endpoint.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The API user sends a PATCH request for id 150 to coursplanprice and records the returned response.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The API user verifies that the status code for courspriceplan is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * API user verifies that the "remark" in the response body for courspriceplan is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * API user verifies that the "message" in the response body for courspriceplan is "There is no information to update.".
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular


  Scenario: When a PATCH body containing an unregistered (id) and correct data (title, dateRange, discount, capacity,
  webinar_id) with valid authorization information is sent to the /api/updatePricePlan/{id} endpoint, the status code returned is 203,
  the remark in the response body is “failed” and the message is "There is not ticket for this id."

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updatePricePlan/14256" path parameters.
    # Api kullanicisi "api/updateCategory/id" path parametrelerini olusturur
    * The API user prepares a POST request body to send to the addPricePlan endpoint with the "title" "Change_Api", "dateRange" "2025-07-07 - 2025-08-02", "discount" 5, "capacity" 200, and "webinar_id" 2002.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The API user sends a PATCH request for id 14256 to coursplanprice and records the returned response.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The API user verifies that the status code for courspriceplan is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * API user verifies that the "remark" in the response body for courspriceplan is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * API user verifies that the "data.message" in the response body for courspriceplan is "There is not ticket for this id.".
    # Api kullanicisi response bodydeki message bilgisinin "No id" oldugunu dogrular



  Scenario: When a PATCH body that does not contain (id) and contains the correct data (title, dateRange, discount, capacity, webinar_id),
  it should be verified that the status code returned is 203, the remark in the response body is ‘failed’ and the message is ”No id".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updatePricePlan" path parameters.
    # Api kullanicisi "api/updateCategory/id" path parametrelerini olusturur
    * The API user prepares a POST request body to send to the addPricePlan endpoint with the "title" "Change_Api", "dateRange" "2025-07-07 - 2025-08-02", "discount" 5, "capacity" 200, and "webinar_id" 2002.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The API user sends a PATCH request for not contain id to coursplanprice and records the returned response.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The API user verifies that the status code for courspriceplan is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * API user verifies that the "remark" in the response body for courspriceplan is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * API user verifies that the "data.message" in the response body for courspriceplan is "No id".
    # Api kullanicisi response bodydeki message bilgisinin "No id" oldugunu dogrular


  Scenario: When a PATCH body is sent to /api/updatePricePlan/{id} endpoint with invalid token authorization information
  and correct (id) and correct data (title, dateRange, discount, capacity, webinar_id), it should be verified that
  the status code returned is 401 and the message information in the response body is “Unauthenticated.”

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/updatePricePlan/125" path parameters.
    # Api kullanicisi "api/updateCategory/id" path parametrelerini olusturur
    * The api user prepares a patch request body to send to the api updateCategory endpoint.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The API user prepares a POST request body to send to the addPricePlan endpoint with the "title" "Change_Api", "dateRange" "2025-07-07 - 2025-08-02", "discount" 5, "capacity" 200, and "webinar_id" 2002.
    #* The API user sends a PATCH request for not contain id to coursplanprice and records the returned response.
    ## Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    #* The API user verifies that the status code for courspriceplan is 401.
    ## Api kullanicisi status codeun 401 oldugunu dogrular
    #* API user verifies that the "message" in the response body for courspriceplan is "Unauthenticated.".
    ## Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

    * The API user sends a PATCH request to courspriceplan, records the returned response, and verifies that the status code is '401' with the reason Unauthorized.
    # Api kullanicisi PATCH request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular




  Scenario: Verify that the updated course price plan via API is correctly updated by sending a GET request to
  /api/pricePlan/{id} using the Updated Category ID from the response.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The API user enters the id value she patch into the "api/pricePlan" parameter.
    # Api kullanicisi "api/pricePlan" path parametrelerini olusturur GET request gonderir ve donen responsei kaydeder
    * The API user verifies that the status code for courspriceplan is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * API user verifies that the "remark" in the response body for courspriceplan is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular