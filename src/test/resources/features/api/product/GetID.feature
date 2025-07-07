@product
Feature: As an administrator I want to be able to access a specific product via API connection.

Scenario: When a GET request is sent to /api/product/{id} endpoint with valid authorization information,
it should be verified that the status code returned is 200 and the remark in the response body is "success".
The specific product information returned in the response body must be verified.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/product/5" path parameters.
    # Api kullanicisi "api/product/5" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies that the "creator_id" information in the response body exists.
    # Api kullanicisi response bodydeki creator_id bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "type" information in the response body exists.
    # Api kullanicisi response bodydeki type bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "slug" information in the response body exists.
    # Api kullanicisi response bodydeki slug bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "category_id" information in the response body exists.
    # Api kullanicisi response bodydeki category_id bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "price" information in the response body exists.
    # Api kullanicisi response bodydeki price bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "point" information in the response body exists.
    # Api kullanicisi response bodydeki point bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "unlimited_inventory" information in the response body exists.
    # Api kullanicisi response bodydeki unlimited_inventory bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "ordering" information in the response body exists.
    # Api kullanicisi response bodydeki ordering bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "inventory" information in the response body exists.
    # Api kullanicisi response bodydeki inventory bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "id" information in the response body exists.
    # Api kullanicisi response bodydeki id bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "product_id" information in the response body exists.
    # Api kullanicisi response bodydeki product_id bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "locale" information in the response body exists.
    # Api kullanicisi response bodydeki locale bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "title" information in the response body exists.
    # Api kullanicisi response bodydeki title bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "seo_description" information in the response body exists.
    # Api kullanicisi response bodydeki seo_description bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "summary" information in the response body exists.
    # Api kullanicisi response bodydeki summary bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "description" information in the response body exists.
    # Api kullanicisi response bodydeki description bilgisinin mevcut oldugunu dogrular
    * The api user verifies the contents of the translations list data in the response body
    # Api kullanicisi response bodydeki translations list datalarinin iceriklerini dogrular

@API
Scenario: When a GET request is sent to the /api/product/{id} endpoint with invalid token authorization information,
it should be verified that the status code returned is 401 and the message information in the response body is
"Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/product/5" path parameters.
    # Api kullanicisi "api/product/5" path parametrelerini olusturur
    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular

Scenario: Geçerli token ve geçerli ID ile product detayı ve translations bilgileri alma
    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/product/5" path parameters.
    # Api kullanicisi "api/product/5" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies that the "creator_id" information in the response body exists.
    # Api kullanicisi response bodydeki creator_id bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "type" information in the response body exists.
    # Api kullanicisi response bodydeki type bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "slug" information in the response body exists.
    # Api kullanicisi response bodydeki slug bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "category_id" information in the response body exists.
    # Api kullanicisi response bodydeki category_id bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "price" information in the response body exists.
    # Api kullanicisi response bodydeki price bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "point" information in the response body exists.
    # Api kullanicisi response bodydeki point bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "unlimited_inventory" information in the response body exists.
    # Api kullanicisi response bodydeki unlimited_inventory bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "ordering" information in the response body exists.
    # Api kullanicisi response bodydeki ordering bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "inventory" information in the response body exists.
    # Api kullanicisi response bodydeki inventory bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "id" information in the response body exists.
    # Api kullanicisi response bodydeki id bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "product_id" information in the response body exists.
    # Api kullanicisi response bodydeki product_id bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "locale" information in the response body exists.
    # Api kullanicisi response bodydeki locale bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "title" information in the response body exists.
    # Api kullanicisi response bodydeki title bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "seo_description" information in the response body exists.
    # Api kullanicisi response bodydeki seo_description bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "summary" information in the response body exists.
    # Api kullanicisi response bodydeki summary bilgisinin mevcut oldugunu dogrular
    * The api user verifies that the "description" information in the response body exists.
    # Api kullanicisi response bodydeki description bilgisinin mevcut oldugunu dogrular
    * The api user verifies the contents of the translations list data in the response body
    # Api kullanicisi response bodydeki translations list datalarinin iceriklerini dogrular

Scenario: Geçersiz ID ile request gönderme senaryosu
    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/product/999999" path parameters.
    # Api kullanicisi "api/product/999999" path parametrelerini olusturur (geçersiz ID)
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the message information in the response body is "There is not product for this id.".
    # Api kullanicisi response bodydeki data.message bilgisinin "There is not product for this id." oldugunu dogrular

Scenario: Geçersiz token ile request gönderme senaryosu
    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/product/5" path parameters.
    # Api kullanicisi "api/product/5" path parametrelerini olusturur
    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular

Scenario: ID olmadan request gönderme senaryosu
    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/product/" path parameters.
    # Api kullanicisi "api/product/" path parametrelerini olusturur (ID olmadan)
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the message information in the response body is "No id".
    # Api kullanicisi response bodydeki message bilgisinin "No id" oldugunu dogrular
