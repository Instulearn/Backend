@product
Feature: As an administrator I want to be able to access the products via API connection.

Scenario Outline: When a GET request is sent to /api/products endpoint with valid authorization information,
it should be verified that the status code returned is 200 and the remark in the response body is "success".
The information returned in the response body for id(x) (creator_id, product_id, order, created_at, title, answer, id,
product_faq_id, locale, title, answer) must be verified.

    * The api user constructs the base url with the "admin" token.
    # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/products" path parameters.
    # Api kullanicisi "api/products" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
    # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success".
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies that the response contains product data.
    # Api kullanıcısı response'da product data olduğunu doğrular

    Examples:
      | creator_id | product_id | order | created_at | title | answer | translations_id | product_faq_id | locale | translations_title                                       | translations_answer                                                                                                  | dataIndex |
      | 1015       | 5         | 1     | 1655671880 |       |        | 5               | 5              | en     | Introduction to English Language: A Beginner's Guide Video Course | Business software is any software or set of computer programs used by business users to perform various business functions. | 0         |

@API
Scenario: When a GET request is sent to the /api/products endpoint with invalid token authorization information,
it should be verified that the status code returned is 401 and the message information in the response body is
"Unauthenticated.".

    * The api user constructs the base url with the "invalid" token.
    # Api kullanicisi "invalid" token ile base urli olusturur
    * The api user sets "api/products" path parameters.
    # Api kullanicisi "api/products" path parametrelerini olusturur
    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.
    # Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular
