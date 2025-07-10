Feature: As an administrator I want to be able to access coursefaqs via API connection.


  Scenario Outline: When a GET request is sent to the /api/coursefaqs endpoint with valid authorization,
  the response status code should be 200, the remark should be “success”, and the information (teacher_id, creator_id,
  category_id, type, private, slug, start_date, duration, timezone) of id(x) in the response body should be verified.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/coursefaqs" path parameters.
    # Api kullanicisi "api/courses" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies the <creator_id>, <webinar_id>, <created_at>, <updated_at>,information of the item at <dataIndex> in the response body.
   # Api kullanıcısı response body icindeki <dataIndex> indexe sahip olanin <creator_id>, <ebinar_id>, <created_at>, <updated_at>,  bilgilerini doğrular.
    Examples:
      | dataIndex | creator_id |  webinar_id |   created_at  | updated_at |

      |     0     |     1016   |     1995    |    1624908812 | 1718059480 |



  Scenario: When a GET request is sent to the /api/coursefaqs endpoint with invalid (invalid token) authorization credentials,
  it should return a 401 status code. Additionally, it should be verified that the message field in the response body is
  "Unauthenticated."

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/coursefaqs" path parameters.
    # Api kullanicisi "api/courses" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 401.
    # Api kullanicisi status codeun 401 oldugunu dogrular
    * The api user verifies that the "message" information in the response body is "Unauthenticated.".
    # Api kullanicisi response bodydeki message bilgisinin "Unauthenticated." oldugunu dogrular

    #* The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular