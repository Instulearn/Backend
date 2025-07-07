@product
Feature: As an administrator I want to be able to update a specific product via API connection.

Scenario Outline: When a PATCH request is sent to /api/updateProduct/{id} endpoint with valid authorization information and valid data,
it should be verified that the status code returned is 200 and the remark in the response body is "success".
The specific product information returned in the response body must be verified.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProduct/<id>" path parameters.
    # Api kullanicisi "api/updateProduct/<id>" path parametrelerini olusturur
    * The api user prepares a valid patch request body to be sent to the updateProduct endpoint.
    # Api kullanicisi updateProduct endpoint'ine gönderilmek üzere geçerli bir patch request body hazırlar
    * The api user sends a product PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the patch status code is 200.
    # Api kullanicisi status code'un 200 oldugunu doğrular
    * The api user verifies that the remark information in the response body is "success".
    # Api kullanicisi response body'deki remark bilgisinin "success" oldugunu doğrular
    * The api user verifies that the Message information in the response body is "Successfully Updated.".
    # Api kullanicisi response body'deki Message bilgisinin "Successfully Updated." oldugunu doğrular
    * The api user verifies that the "Updated Product ID" information in the response body exists.
    # Api kullanicisi response body'deki "Updated Product ID" bilgisinin var oldugunu doğrular

    Examples:
      | id | type      | price | category_id | title                    | summary                                                           | description                                                                                    |
      | 1  | physical  | 1500  | 2           | Updated Product Title    | Updated summary for the product                                  | Updated description for the product                                                             |
      | 2  | digital   | 2500  | 1           | Another Updated Title    | Another updated summary for the product                          | Another updated description for the product                                                     |

Scenario: When a PATCH request is sent to /api/updateProduct/{id} endpoint with valid authorization information but no data,
it should be verified that the status code returned is 203 and the remark in the response body is "failed".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProduct/1" path parameters.
    # Api kullanicisi "api/updateProduct/1" path parametrelerini olusturur
    * The api user prepares an empty patch request body.
    # Api kullanicisi data icermeyen bir patch request body hazırlar
    * The api user sends a product PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the patch status code is 203.
    # Api kullanicisi status code'un 203 oldugunu doğrular
    * The api user verifies that the remark information in the response body is "failed".
    # Api kullanicisi response body'deki remark bilgisinin "failed" oldugunu doğrular
    * The api user verifies that the patch message information in the response body is "There is no information to update.".
    # Api kullanicisi response body'deki message bilgisinin "There is no information to update." oldugunu doğrular

@API
Scenario: When a PATCH request is sent to /api/updateProduct/{id} endpoint with invalid token authorization information,
it should be verified that the status code returned is 401 and the message information in the response body is "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/updateProduct/1" path parameters.
    # Api kullanicisi "api/updateProduct/1" path parametrelerini olusturur
    * The api user prepares a valid patch request body to be sent to the updateProduct endpoint.
    # Api kullanicisi updateProduct endpoint'ine gönderilmek üzere geçerli bir patch request body hazırlar
    * The api user sends a product PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the patch status code is 401.
    # Api kullanicisi status code'un 401 oldugunu doğrular
    * The api user verifies that the product "message" information in the response body is "Unauthenticated.".
    # Api kullanicisi response body'deki message bilgisinin "Unauthenticated." oldugunu doğrular

Scenario: When a PATCH request is sent to /api/updateProduct/{id} endpoint with non-existent ID,
it should be verified that the status code returned is 203 and the message information in the response body is "There is not product for this id.".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProduct/999999" path parameters.
    # Api kullanicisi "api/updateProduct/999999" path parametrelerini olusturur (olmayan ID)
    * The api user prepares a valid patch request body to be sent to the updateProduct endpoint.
    # Api kullanicisi updateProduct endpoint'ine gönderilmek üzere geçerli bir patch request body hazırlar
    * The api user sends a product PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the patch status code is 203.
    # Api kullanicisi status code'un 203 oldugunu doğrular
    * The api user verifies that the remark information in the response body is "failed".
    # Api kullanicisi response body'deki remark bilgisinin "failed" oldugunu doğrular
    * The api user verifies that the patch message information in the response body is "There is not product for this id.".
    # Api kullanicisi response body'deki message bilgisinin "There is not product for this id." oldugunu doğrular

Scenario: When a PATCH request is sent to /api/updateProduct/ endpoint without ID,
it should be verified that the status code returned is 203 and the message information in the response body is "No id".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProduct/" path parameters.
    # Api kullanicisi "api/updateProduct/" path parametrelerini olusturur (ID olmadan)
    * The api user prepares a valid patch request body to be sent to the updateProduct endpoint.
    # Api kullanicisi updateProduct endpoint'ine gönderilmek üzere geçerli bir patch request body hazırlar
    * The api user sends a product PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the patch status code is 203.
    # Api kullanicisi status code'un 203 oldugunu doğrular
    * The api user verifies that the remark information in the response body is "failed".
    # Api kullanicisi response body'deki remark bilgisinin "failed" oldugunu doğrular
    * The api user verifies that the patch message information in the response body is "No id".
    # Api kullanicisi response body'deki message bilgisinin "No id" oldugunu doğrular

Scenario: Updated Product ID ile path parametresindeki ID'nin aynı olduğunun doğrulanması
    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProduct/1" path parameters.
    # Api kullanicisi "api/updateProduct/1" path parametrelerini olusturur
    * The api user prepares a valid patch request body to be sent to the updateProduct endpoint.
    # Api kullanicisi updateProduct endpoint'ine gönderilmek üzere geçerli bir patch request body hazırlar
    * The api user sends a product PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the patch status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the remark information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin 'success' oldugunu dogrular
    * The api user verifies that the Message information in the response body is "Successfully Updated.".
    # Api kullanicisi response bodydeki Message bilgisinin 'Successfully Updated.' oldugunu dogrular
    * The api user verifies that the response bodydeki Updated Product ID bilgisinin path parametresindeki id ile ayni oldugunu dogrular
    # Api kullanicisi response bodydeki Updated Product ID bilgisinin path parametresindeki id ile ayni oldugunu dogrular

Scenario: Güncellenen product kaydının API üzerinden verification testi
    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProduct/1" path parameters.
    # Api kullanicisi "api/updateProduct/1" path parametrelerini olusturur
    * The api user prepares a valid patch request body to be sent to the updateProduct endpoint.
    # Api kullanicisi updateProduct endpoint'ine gönderilmek üzere geçerli bir patch request body hazırlar
    * The api user sends a product PATCH request and saves the returned response.
    # Api kullanicisi PATCH request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the patch status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the remark information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin 'success' oldugunu dogrular
    * The api user verifies that the Message information in the response body is "Successfully Updated.".
    # Api kullanicisi response bodydeki Message bilgisinin 'Successfully Updated.' oldugunu dogrular
    * The api user verifies that the response bodydeki Updated Product ID bilgisinin mevcut oldugunu dogrular
    # Api kullanicisi response bodydeki Updated Product ID bilgisinin mevcut oldugunu dogrular
    * Api kullanicisi Updated Product ID ile GET request gonderip kaydin guncellendigi dogrulanmali
    # Api kullanicisi Updated Product ID ile GET request gonderip kaydin guncellendigi dogrulanmali
