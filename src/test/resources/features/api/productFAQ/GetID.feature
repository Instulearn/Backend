Feature: As an administrator, I should be able to access the detailed information of the product faq with the
  specified id number via the API connection.


  Scenario Outline: When a GET request with valid authorization information and correct data (id) is sent to the
  /api/productfaq/{id} endpoint, it should be verified that the status code returned is 200 and the remark in the
  response body is “success”. The contents of the list data (id, creator_id, product_id, order, created_at,
  title, answer, id, product_faq_id, locale, title, answer) in the response body must be validated.

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
    * The api user verifies the <data_id>, <creator_id>, <product_id>, "<order>", <created_at>, "<title>", "<answer>", <translations_id>, <product_faq_id>, "<locale>", "<translations_title>" and "<translations_answer>" contents in the response body data.
    # Api kullanıcısı response bodydeki dataların <data_id>, <product_id>, <order>, <created_at>, "<title>", "<answer>", <translations_id>, <product_faq_id>, "<locale>", "<translations_title>" ve"<translations_answer>" içeriklerini doğrular.


    Examples:
       | id | data_id | creator_id | product_id | order | created_at | title | answer | translations_id | product_faq_id | locale | translations_title                                       | translations_answer                                                                                                  |
       | 15 | 15      | 1349       | 10         |  null | 1717712054 | null  | null   | 16              | 15             | en     | What payment methods do you accept for online purchases? | We accept major credit cards such as Visa, Mastercard, and American Express, as well as PayPal for online purchases. |


  Scenario Outline: When a GET request is sent to the /api/category/{id} endpoint with valid authorization information
  and an id that does not have a record, it should be verified that the returned status code is 203, the remark
  information in the response body is "failed" and the message information is "There is not category for this id."

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/category/<id>" path parameters.
    # Api kullanicisi "api/category/<id>" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed".
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "data.message" information in the response body is "There is not category for this id.".
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular

    Examples:
      | id    |
      | 25416 |


#Scenario: When a GET request is sent to the /api/category/{id} endpoint without valid authorization information and (id),
#it should be verified that the returned status code is 203, the remark information in the response body is "failed" and
#the message information is "No id".

#  * The api user constructs the base url with the "admin" token.
#  # Api kullanicisi "admin" token ile base urli olusturur
#  * The api user sets "api/category" path parameters.
#  # Api kullanicisi "api/category/<id>" path parametrelerini olusturur
#  * The api user sends a GET request and saves the returned response.
#  # Api kullanicisi GET request gonderir ve donen responsei kaydeder
#  * The api user verifies that the status code is 203.
#  # Api kullanicisi status codeun 203 oldugunu dogrular
#  * The api user verifies that the "remark" information in the response body is "failed".
#  # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
#  * The api user verifies that the "data.message" information in the response body is "No id".
#  # Api kullanicisi response bodydeki message bilgisinin "No id" oldugunu dogrular


#Scenario Outline: When a GET request is sent to the /api/category/{id} endpoint with invalid (invalid token) authorization
#credentials and correct data (id), it should return a status code of 401. Additionally, it should be verified that the
#message field in the response body is "Unauthenticated."

#  * The api user constructs the base url with the "invalid" token.
#  # Api kullanicisi "invalid" token ile base urli olusturur
#  * The api user sets "api/category/<id>" path parameters.
#  # Api kullanicisi "api/category/<id>" path parametrelerini olusturur

#  #* The api user sends a GET request and saves the returned response.
#  ## Api kullanicisi GET request gonderir ve donen responsei kaydeder
#  #* The api user verifies that the status code is 401.
#  ## Api kullanicisi status codeun 401 oldugunu dogrular
#  #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
#  ## Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

#  * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
#  # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular

#  Examples:
#    | id  |
#    | 883 |




