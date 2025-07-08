@kc5
Feature: [US_015] As an administrator, I want to be able to delete course price plan information with the specified id number via API connection.


  Scenario: Verify that a DELETE request to /api/deletePricePlan/{id} with valid authorization and correct id returns
  status 200, status “success”, message “Successfully Deleted.”, and that the Deleted Category Id in the response
  matches the {id} path parameter.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The API user sets "api/deletePricePlan" path parameters  sends a DELETE request for id 221 to coursplanprice and records the returned response.
    # Api kullanicisi "api/deleteCategory/id" path parametrelerini olusturur, DELETE request gonderir ve donen responsei kaydeder
    # ***Bu stepte yazdığınız id, son step ve TC'de otomatik kullanılır
    * The API user verifies that the status code for courspriceplan is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * API user verifies that the "remark" information in the response body for courspriceplan is "success".
    # Api kullanicisi response bodydeki status bilgisinin "success" oldugunu dogrular
    * API user verifies that the "Message" in the response body for courspriceplan is "Successfully Deleted.".
    # Api kullanicisi response bodydeki Message bilgisinin "Successfully Deleted." oldugunu dogrular
    * The API user verifies that the "Deleted Price Plan Id" in the response body for coursPricePlan is the same as the id path parameter in the endpoint.
    # Api kullanıcısı response body icindeki "Deleted Category Id" ile endpointde yazan id path parametresinin ayni oldugunu dogrular.


  Scenario: When a DELETE request is sent to the /api/deletePricePlan/{id} endpoint with valid authorization
  information and an id that does not have a record, it should be verified that the returned status code is 203, the
  remark information in the response body is "failed" and the message information is "There is not ticket for this id."

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The API user sets "api/deletePricePlan" path parameters  sends a DELETE request for id 14500 to coursplanprice and records the returned response.
    # Api kullanicisi "api/deleteCategory/id" path parametrelerini olusturur, DELETE request gonderir ve donen responsei kaydeder
    * The API user verifies that the status code for courspriceplan is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * API user verifies that the "remark" information in the response body for courspriceplan is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * API user verifies that the "data.message" in the response body for courspriceplan is "There is not ticket for this id.".
    # Api kullanicisi response bodydeki message bilgisinin "There is not ticket for this id." oldugunu dogrular


  Scenario: When a DELETE request is sent to the /api/deletePricePlan/{id} endpoint without valid authorization information
  and (id), it should be verified that the returned status code is 203, the remark information in the response body is
  "failed" and the message information is "No id".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/deletePricePlan" path parameters.
    # Api kullanicisi "api/deleteCategory/id" path parametrelerini olusturur
    * The api user sends a DELETE request and saves the returned response.
    # Api kullanicisi DELETE request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "data.message" information in the response body is "No id".
    # Api kullanicisi response bodydeki message bilgisinin "No id" oldugunu dogrular



  Scenario: Verify that a DELETE request to /api/deleteCategory/{id} with invalid token and correct id returns status 401
  and message “Unauthenticated.”

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/deletePricePlan/889" path parameters.
    # Api kullanicisi "api/deleteCategory/id" path parametrelerini olusturur

    #* The api user sends a DELETE request and saves the returned response.
    ## Api kullanicisi DELETE request gonderir ve donen responsei kaydeder
    #* The api user verifies that the status code is 401.
    ## Api kullanicisi status codeun 401 oldugunu dogrular
    #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
    ## Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

    * The api user sends a DELETE request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi DELETE request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular


  Scenario: Verify that the deleted course category via API is successfully deleted by sending a GET request to
  /api/deletePricePlan/{id} using the Deleted Category ID from the response.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The API user enters the data of the deleted ID into the "api/pricePlan" parameter with the Get method.
    # Api kullanicisi "api/PricePlan" path parametrelerini olusturur, GET request gonderir ve donen responsei kaydeder
    * API user verifies that the "data.message" information in the response body for courspriceplan is "There is not ticket for this id.".
    # Api kullanicisi response bodydeki message bilgisinin "There is not ticket for this id." oldugunu dogrular

