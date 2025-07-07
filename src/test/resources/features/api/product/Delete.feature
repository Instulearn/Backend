@product
Feature: As an administrator I want to be able to delete a specific product via API connection.

Scenario: When a DELETE request is sent to /api/deleteProduct/{id} endpoint with valid authorization information and valid ID,
it should be verified that the status code returned is 200 and the remark in the response body is "success".
The specific product deletion information returned in the response body must be verified.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user fetches the current product IDs and prepares them for deletion tests
    # Mevcut product ID'lerini çeker ve silme testleri için hazırlar
    * The api user uses the next product ID for the deletion test
    # Silme testi için sıradaki product ID'sini kullanır
    * The api user sends a product DELETE request and saves the returned response.
    # Api kullanicisi DELETE request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the delete status code is 200.
    # Api kullanicisi status code'un 200 oldugunu doğrular
    * The api user verifies that the remark information in the response body is "success".
    # Api kullanicisi response body'deki remark bilgisinin "success" oldugunu doğrular
    * The api user verifies that the Message information in the response body is "Successfully Deleted.".
    # Api kullanicisi response body'deki Message bilgisinin "Successfully Deleted." oldugunu doğrular
    * The api user verifies that the response body Deleted Product Id matches the ID in the path parameter
    # Api kullanicisi response bodydeki Deleted Product Id bilgisinin path parametresindeki id ile ayni oldugunu dogrular

@API
Scenario: When a DELETE request is sent to /api/deleteProduct/{id} endpoint with invalid token authorization information,
it should be verified that the status code returned is 401 and the message information in the response body is "Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/deleteProduct/5" path parameters.
    # Api kullanicisi "api/deleteProduct/5" path parametrelerini olusturur
    * The api user sends a product DELETE request and saves the returned response.
    # Api kullanicisi DELETE request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the delete status code is 401.
    # Api kullanicisi status code'un 401 oldugunu doğrular
    * The api user verifies that the product "message" information in the response body is "Unauthenticated.".
    # Api kullanicisi response body'deki message bilgisinin "Unauthenticated." oldugunu doğrular

Scenario: When a DELETE request is sent to /api/deleteProduct/{id} endpoint with non-existent ID,
it should be verified that the status code returned is 203 and the message information in the response body is "There is not product for this id.".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/deleteProduct/999999" path parameters.
    # Api kullanicisi "api/deleteProduct/999999" path parametrelerini olusturur (olmayan ID)
    * The api user sends a product DELETE request and saves the returned response.
    # Api kullanicisi DELETE request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the delete status code is 203.
    # Api kullanicisi status code'un 203 oldugunu doğrular
    * The api user verifies that the remark information in the response body is "failed".
    # Api kullanicisi response body'deki remark bilgisinin "failed" oldugunu doğrular
    * The api user verifies that the delete message information in the response body is "There is not product for this id.".
    # Api kullanicisi response body'deki message bilgisinin "There is not product for this id." oldugunu doğrular

Scenario: When a DELETE request is sent to /api/deleteProduct/ endpoint without ID,
it should be verified that the status code returned is 203 and the message information in the response body is "No id".

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/deleteProduct/" path parameters.
    # Api kullanicisi "api/deleteProduct/" path parametrelerini olusturur (ID olmadan)
    * The api user sends a product DELETE request and saves the returned response.
    # Api kullanicisi DELETE request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the delete status code is 203.
    # Api kullanicisi status code'un 203 oldugunu doğrular
    * The api user verifies that the remark information in the response body is "failed".
    # Api kullanicisi response body'deki remark bilgisinin "failed" oldugunu doğrular
    * The api user verifies that the delete message information in the response body is "No id".
    # Api kullanicisi response body'deki message bilgisinin "No id" oldugunu doğrular

Scenario: Deleted Product ID ile path parametresindeki ID'nin aynı olduğunun doğrulanması
    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user fetches the current product IDs and prepares them for deletion tests
    # Mevcut product ID'lerini çeker ve silme testleri için hazırlar
    * The api user uses the next product ID for the deletion test
    # Silme testi için sıradaki product ID'sini kullanır
    * The api user sends a product DELETE request and saves the returned response.
    # Api kullanicisi DELETE request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the delete status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the remark information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin 'success' oldugunu dogrular
    * The api user verifies that the Message information in the response body is "Successfully Deleted.".
    # Api kullanicisi response bodydeki Message bilgisinin 'Successfully Deleted.' oldugunu dogrular
    * The api user verifies that the response bodydeki Deleted Product Id bilgisinin path parametresindeki id ile ayni oldugunu dogrular
    # Api kullanicisi response bodydeki Deleted Product Id bilgisinin path parametresindeki id ile ayni oldugunu dogrular

Scenario: Silinen product kaydının API üzerinden verification testi
    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user fetches the current product IDs and prepares them for deletion tests
    # Mevcut product ID'lerini çeker ve silme testleri için hazırlar
    * The api user uses the next product ID for the deletion test
    # Silme testi için sıradaki product ID'sini kullanır
    * The api user sends a product DELETE request and saves the returned response.
    # Api kullanicisi DELETE request gönderir ve dönen response'ı kaydeder
    * The api user verifies that the delete status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the remark information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin 'success' oldugunu dogrular
    * The api user verifies that the Message information in the response body is "Successfully Deleted.".
    # Api kullanicisi response bodydeki Message bilgisinin 'Successfully Deleted.' oldugunu dogrular
    * The api user verifies that the response bodydeki Deleted Product Id bilgisinin mevcut oldugunu dogrular
    # Api kullanicisi response bodydeki Deleted Product Id bilgisinin mevcut oldugunu dogrular
    * The api user sends a GET request with the Deleted Product Id and verifies that the record has been deleted
    # Api kullanicisi Deleted Product Id ile GET request gonderip kaydin silindigi dogrulanmali
