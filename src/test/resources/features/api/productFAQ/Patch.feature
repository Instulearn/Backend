Feature: As an administrator, I want to be able to update the information of the product faq with the specified
  id number via the API connection.


  Scenario Outline: When a PATCH body with valid authorization information and correct (id) and correct data
  (title, answer, product_id) is sent to the /api/updateProductfaq/{id} endpoint, it should be verified that
  the status code returned is 200, the remark in the response body is “success” and the Message is
  “Successfully Updated.”.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProductfaq/<id>" path parameters.
    # Api kullanicisi "api/updateProductfaq/<id>" path parametrelerini olusturur
    * The api user prepares a patch request body to send to the api updateCategory endpoint with the "<title>", "<answer>", <product_id>.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The api user sends a PATCH request and saves the returned response for productFAQ.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200 for productFAQ.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success" for productFAQ.
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies that the "Message" information in the response body is "Successfully Updated." for productFAQ.
    # Api kullanicisi response bodydeki Message bilgisinin "Successfully Updated." oldugunu dogrular



    Examples:
      | id | title                                                     | answer                                                                                                                  | product_id |
      | 69 | What payment methods do you accept for online purchases?  | We accept major credit cards such as Visa, Mastercard, and American Express, as well as PayPal for online purchases.    | 10         |


  Scenario Outline: When a PATCH request is sent to the /api/updateProductfaq/{id} endpoint with valid authorization
  information, it should be verified that the status code returned is 203, the remark in the response body is
  “failed” and the message is “There is no information to update.”.


    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProductfaq/<id>" path parameters.
    # Api kullanicisi "api/updateProductfaq/id" path parametrelerini olusturur
    * The api user prepares an empty patch request body to send to the api updateCategory endpoint.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The api user sends a PATCH request and saves the returned response for productFAQ.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203 for productFAQ.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed" for productFAQ.
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "message" information in the response body is "There is no information to update." for productFAQ.
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular
  Examples:
      | id |
      | 67 |


 Scenario Outline: When a PATCH body containing an unregistered (id) and correct data (title, answer, product_id)
 is sent to the endpoint /api/updateProductfaq/{id} with valid authorization information, the status code returned is
 203, the remark in the response body is “failed”

   * The api user constructs the base url with the "admin" token.
   # Api kullanicisi "admin" token ile base urli olusturur
   * The api user sets "api/updateProductfaq/<id>" path parameters.
    # Api kullanicisi "api/updateProductfaq/<id>" path parametrelerini olusturur
   * The api user prepares a patch request body to send to the api updateCategory endpoint with the "<title>", "<answer>", <product_id>.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
   * The api user sends a PATCH request and saves the returned response for productFAQ.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
   * The api user verifies that the status code is 203 for productFAQ.
    # Api kullanicisi status codeun 203 oldugunu dogrular
   * The api user verifies that the "remark" information in the response body is "failed" for productFAQ.
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
   * The api user verifies that the "data.message" information in the response body is "There is not product faq for this id." for productFAQ.
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular

   Examples:
     | id  | title                                                     | answer                                                                                                                  | product_id |
     | 1986 | What payment methods do you accept for online purchases?  | We accept major credit cards such as Visa, Mastercard, and American Express, as well as PayPal for online purchases.    | 10         |



  Scenario Outline: when sending a PATCH body that does not contain (id) and contains the correct data
  (title, answer, product_id), it should be verified that the status code returned is 203, the remark in the
  response body is ‘failed’ and the message is ”No id".

    * The api user constructs the base url with the "admin" token.
   # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/updateProductfaq" path parameters.
    # Api kullanicisi "api/updateProductfaq/<id>" path parametrelerini olusturur
    * The api user prepares a patch request body to send to the api updateCategory endpoint with the "<title>", "<answer>", <product_id>.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The api user sends a PATCH request and saves the returned response for productFAQ.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203 for productFAQ.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed" for productFAQ.
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "data.message" information in the response body is "No id" for productFAQ.
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular

    Examples:
      | title                                                     | answer                                                                                                                  | product_id |
      | What payment methods do you accept for online purchases?  | We accept major credit cards such as Visa, Mastercard, and American Express, as well as PayPal for online purchases.    | 10         |


 Scenario Outline: When a PATCH body is sent to /api/updateProductfaq/{id} endpoint with invalid token authorization
 information and correct (id) and correct data (title, answer, product_id), it should be verified that the status
 code returned is 401 and the message information in the response body is “Unauthenticated.”

   * The api user constructs the base url with the "invalid" token.
   # Api kullanicisi "invalid" token ile base urli olusturur
   * The api user sets "api/updateProductfaq/<id>" path parameters.
    # Api kullanicisi "api/updateProductfaq/<id>" path parametrelerini olusturur
   * The api user prepares a patch request body to send to the api updateCategory endpoint with the "<title>", "<answer>", <product_id>.
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar

   #* The api user sends a PATCH request and saves the returned response.
   ## Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
   #* The api user verifies that the status code is 401.
   ## Api kullanicisi status codeun 401 oldugunu dogrular
   #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
   ## Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

   * The api user sends a PATCH request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
   # Api kullanicisi PATCH request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular

   Examples:
     | id | title                                                     | answer                                                                                                                  | product_id |
     | 69 | What payment methods do you accept for online purchases?  | We accept major credit cards such as Visa, Mastercard, and American Express, as well as PayPal for online purchases.    | 10         |

#Sıradaki///////////////////////////////////////////////
 Scenario Outline: It should be verified that the product faq record requested to be updated via API has been updated via
 API. (It can be verified that the record has been updated by sending a GET request to the /api/productfaq/{id}
 endpoint with the updated Product Faq ID returned in the response body).

   * The api user constructs the base url with the "admin" token.
   # Api kullanicisi "admin" token ile base urli olusturur
   * The api user sets "api/productfaq/<id>" path parameters.
    # Api kullanicisi "api/updateProductfaq/<id>" path parametrelerini olusturur
   * The api user sends a GET request and saves the returned response.
   # Api kullanicisi GET request gonderir ve donen responsei kaydeder
   * The api user verifies that the title information is "Education and Training"
   # Api kullanıcısı title bilgisinin "Education and Training" olduğunu doğrular

   Examples:
     | id  |
     | 69  |
