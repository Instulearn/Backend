Feature: As an administrator I want to be able to access courses categories via API connection.

  Scenario Outline: When a GET request is sent to the /api/categories endpoint with valid authorization,
  the response status code should be 200, the remark should be “success”, and the information of id(x)
  (slug, parent_id, icon, order, title, category_id, locale) should be validated.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/categories" path parameters.
    # Api kullanicisi "api/categories" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies the "<slug>", "<icon>", <order>, <id>, <category_id>, "<locale>" and "<title>" information of the item at <dataIndex> in the response body.
    # Api kullanıcısı response body icindeki <dataIndex> indexe sahip olanin "<slug>", "<icon>", <order>, <id>, <category_id>, "<locale>" ve "<title>" bilgilerini doğrular.

    Examples:
      | dataIndex | slug                 | icon                                              | order | id  | category_id | locale | title            |
      | 1         | Online-Education-871 | /store/1/default_images/categories_icons/code.png | 216   | 326 | 883         | en     | Online Education |


  Scenario: When a GET request is sent to the /api/categories endpoint with invalid (invalid token) authorization credentials,
  it should return a 401 status code. Additionally, it should be verified that the message field in the response body is
  "Unauthenticated."

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/categories" path parameters.
    # Api kullanicisi "api/categories" path parametrelerini olusturur

   #* The api user sends a GET request and saves the returned response.
   ## Api kullanicisi GET request gonderir ve donen responsei kaydeder
   #* The api user verifies that the status code is 401.
   ## Api kullanicisi status codeun 401 oldugunu dogrular
   #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
   ## Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrulargit a