@product
Feature: As an administrator I want to be able to add a new product via API connection.

Scenario: When a POST request is sent to /api/addProduct endpoint with valid authorization information and valid data,
it should be verified that the status code returned is 201 and the remark in the response body is "success".
The specific product information returned in the response body must be verified.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/addProduct" path parameters.
    # Api kullanicisi "api/addProduct" path parametrelerini olusturur
    * The api user prepares a valid post request body to be sent to the addProduct endpoint.
    # Api kullanicisi addProduct endpoint'ine gönderilmek üzere geçerli bir post request body hazırlar
    * The api user sends a product POST request and saves the returned response.
    # Api kullanicisi POST request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the product status code is 200.
    # Api kullanicisi status code'un 200 oldugunu doğrular
    * The api user verifies that the remark information in the response body is "success".
    # Api kullanicisi response body'deki remark bilgisinin "success" oldugunu doğrular
    * The api user verifies that the post response "id" information exists.
    # Api kullanicisi response body'deki "id" bilgisinin var oldugunu doğrular
    * The api user verifies that the post response "title" information exists.
    # Api kullanicisi response body'deki "title" bilgisinin var oldugunu doğrular
    * The api user verifies that the post response "remark" information exists.
    # Api kullanicisi response body'deki "remark" bilgisinin var oldugunu doğrular
    * The api user verifies that the post response "status" information exists.
    # Api kullanicisi response body'deki "status" bilgisinin var oldugunu doğrular
    * The api user verifies that the post response "Message" information exists.
    # Api kullanicisi response body'deki "Message" bilgisinin var oldugunu doğrular

Scenario: When a POST request is sent to /api/addProduct endpoint with invalid authorization information and valid data,
it should be verified that the status code returned is 401 and the remark in the response body is "failed".

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/addProduct" path parameters.
    # Api kullanicisi "api/addProduct" path parametrelerini olusturur
    * The api user prepares a valid post request body to be sent to the addProduct endpoint.
    # Api kullanicisi addProduct endpoint'ine gönderilmek üzere geçerli bir post request body hazırlar
    * The api user sends a product POST request and saves the returned response.
    # Api kullanicisi POST request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the product status code is 401.
    # Api kullanicisi status code'un 401 oldugunu doğrular
    * The api user verifies that the product "message" information in the response body is "Unauthenticated.".
    # Api kullanicisi response body'deki message bilgisinin "Unauthenticated." oldugunu doğrular

Scenario: When a POST request is sent to /api/addProduct endpoint with valid authorization information and invalid data (missing title),
it should be verified that the status code returned is 422 and the message in the response body is "The title field is required.".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/addProduct" path parameters.
    # Api kullanicisi "api/addProduct" path parametrelerini olusturur
    * The api user prepares a post request body without product title
    # Api kullanicisi product title içermeyen bir post request body hazırlar
    * The api user sends a product POST request and saves the returned response.
    # Api kullanicisi POST request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the product status code is 422.
    # Api kullanicisi status code'un 422 oldugunu doğrular
    * The api user verifies that the product "message" information in the response body is "The title field is required.".
    # Api kullanicisi response body'deki message bilgisinin "The title field is required." oldugunu doğrular

Scenario: When a POST request is sent to /api/addProduct endpoint with valid authorization information and invalid data (invalid category_id),
it should be verified that the status code returned is 500 and the response contains server error.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/addProduct" path parameters.
    # Api kullanicisi "api/addProduct" path parametrelerini olusturur
    * The api user prepares a post request body with invalid category_id
    # Api kullanicisi invalid category_id ile post request body hazırlar
    * The api user sends a product POST request and saves the returned response.
    # Api kullanicisi POST request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the product status code is 500.
    # Api kullanicisi status code'un 500 oldugunu doğrular

Scenario: Product ekleme ve Added Product ID ile verification testi
    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/addProduct" path parameters.
    # Api kullanicisi "api/addProduct" path parametrelerini olusturur
    * The api user prepares a valid post request body to be sent to the addProduct endpoint.
    # Api kullanicisi addProduct endpoint'ine gönderilmek üzere geçerli bir post request body hazırlar
    * The api user sends a product POST request and saves the returned response.
    # Api kullanicisi POST request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the product status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the remark information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin 'success' oldugunu dogrular
    * The api user verifies that the Message information in the response body is "Successfully Added.".
    # Api kullanicisi response bodydeki Message bilgisinin 'Successfully Added.' oldugunu dogrular
    * The api user verifies that the post response "Added Product ID" and "Added Product Title" information exists.
    # Api kullanicisi response bodydeki Added Product ID ve Added Product Title bilgilerinin mevcut oldugunu dogrular
    * The api user sends a GET request with the Added Product ID and verifies that the record is created.
    # Api kullanicisi Added Product ID ile GET request gonderip kaydin olusturuldugunu dogrular
