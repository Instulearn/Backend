Feature: As an administrator I want to be able to access the product faqs via API connection.


  Scenario Outline: When a GET request is sent to /api/productfaqs endpoint with valid authorization information,
  it should be verified that the status code returned is 200 and the remark in the response body is “success”.
  The information returned in the response body for id(x) (creator_id, product_id, order, created_at, title, answer, id,
  product_faq_id, locale, title, answer) must be verified.


    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/productfaqs" path parameters.
    # Api kullanicisi "api/categories" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies the <creator_id>, <product_id>, "<order>", <created_at>, "<title>", "<answer>", <translations_id>, <product_faq_id>, "<locale>", "<translations_title>" and "<translations_answer>" information of the item at <dataIndex> in the response body.
    # Api kullanıcısı response body icindeki <dataIndex> indexe sahip olanin <product_id>, <order>, <created_at>, "<title>", "<answer>", <translations_id>, <product_faq_id>, "<locale>", "<translations_title>" ve"<translations_answer>" bilgilerini doğrular.

    Examples:
      | creator_id | product_id | order | created_at | title | answer | translations_id | product_faq_id | locale | translations_title                                       | translations_answer                                                                                                  | dataIndex |
      | 1349       | 10         |       | 1715347861 |       |        | 9               | 8              | en     | What payment methods do you accept for online purchases? | We accept major credit cards such as Visa, Mastercard, and American Express, as well as PayPal for online purchases. | 0         |

  @API
  Scenario: When a GET request is sent to the /api/productfaqs endpoint with invalid token authorization information,
  it should be verified that the status code returned is 401 and the message information in the response body is
  “Unauthenticated.”.

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/productfaqs" path parameters.
    # Api kullanicisi "api/categories" path parametrelerini olusturur

   #* The api user sends a GET request and saves the returned response.
   ## Api kullanicisi GET request gonderir ve donen responsei kaydeder
   #* The api user verifies that the status code is 401.
   ## Api kullanicisi status codeun 401 oldugunu dogrular
   #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
   ## Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular
