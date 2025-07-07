Feature: As an administrator, I want to be able to update the information of the product faq with the specified
  id number via the API connection.


  Scenario: When a PATCH body with valid authorization information and correct (id) and correct data
  (title, answer, product_id) is sent to the /api/updateProductfaq/{id} endpoint, it should be verified that
  the status code returned is 200, the remark in the response body is “success” and the Message is
  “Successfully Updated.”.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProductfaq/69" path parameters.
    # Api kullanicisi "api/updateProductfaq/69" path parametrelerini olusturur
    * The api user prepares a patch request body to send to the api updateCategory endpoint.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The api user sends a PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies that the "Message" information in the response body is "Successfully Updated.".
    # Api kullanicisi response bodydeki Message bilgisinin "Successfully Updated." oldugunu dogrular
    * The api user verifies that the "updated Product Faq ID" information in the response body is the same as the id path parameter in the endpoint.
    # Api kullanıcısı response body icindeki "Updated Category Id" bilgisinin endpointde yazan id path parametresi ile ayni oldugunu dogrular.


  Scenario: When a PATCH body containing an unregistered (id) and correct data (title, answer, product_id) is
  sent to the endpoint /api/updateProductfaq/{id} with valid authorization information, the status code returned is 203,
  the remark in the response body is “failed” and the message is "There is not product faq for this id.",


    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProductfaq/212" path parameters.
    # Api kullanicisi "api/updateProductfaq/id" path parametrelerini olusturur
    * The api user prepares a patch request body to send to the api updateCategory endpoint.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The api user sends a PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "data.message" information in the response body is "There is not product faq for this id.".
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular


 #Scenario: when sending a PATCH body that does not contain (id) and contains the correct data
 #(title, answer, product_id), it should be verified that the status code returned is 203, the remark in the
 #response body is ‘failed’ and the message is ”No id".

 #  * The api user constructs the base url with the "admin" token.
 #  # Api kullanicisi "admin" token ile base urli olusturur
 #  * The api user sets "api/Productfaq/" path parameters.
 #  # Api kullanicisi "api/updateCategory/id" path parametrelerini olusturur
 #  * The api user prepares a patch request body to send to the api updateCategory endpoint.
 #  # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
 #  * The api user sends a PATCH request and saves the returned response.
 #  # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
 #  * The api user verifies that the status code is 203.
 #  # Api kullanicisi status codeun 203 oldugunu dogrular
 #  * The api user verifies that the "remark" information in the response body is "failed".
 #  # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
 #  * The api user verifies that the "data.message" information in the response body is "No id".
 #  # Api kullanicisi response bodydeki message bilgisinin "No id" oldugunu dogrular


 #Scenario: Verify that a PATCH request to /api/updateCategory/{id} with invalid token, correct id, and valid title
 #returns status 401 and message “Unauthenticated.”

 #  * The api user constructs the base url with the "invalid" token.
 #  # Api kullanicisi "invalid" token ile base urli olusturur
 #  * The api user sets "api/updateCategory/892" path parameters.
 #  # Api kullanicisi "api/updateCategory/id" path parametrelerini olusturur
 #  * The api user prepares a patch request body to send to the api updateCategory endpoint.
 #  # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar

 #  #* The api user sends a PATCH request and saves the returned response.
 #  ## Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
 #  #* The api user verifies that the status code is 401.
 #  ## Api kullanicisi status codeun 401 oldugunu dogrular
 #  #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
 #  ## Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

 #  * The api user sends a PATCH request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
 #  # Api kullanicisi PATCH request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular


 #Scenario Outline: Verify that the updated course category via API is correctly updated by sending a GET request to
 #/api/category/{id} using the Updated Category ID from the response.

 #  * The api user constructs the base url with the "admin" token.
 #  # Api kullanicisi "admin" token ile base urli olusturur
 #  * The api user sets "api/category/<id>" path parameters.
 #  # Api kullanicisi "api/category/<id>" path parametrelerini olusturur
 #  * The api user sends a GET request and saves the returned response.
 #  # Api kullanicisi GET request gonderir ve donen responsei kaydeder
 #  * The api user verifies that the title information is "Education and Training"
 #  # Api kullanıcısı title bilgisinin "Education and Training" olduğunu doğrular

 #  Examples:
 #    | id  |
 #    | 892 |
