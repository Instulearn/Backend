Feature: As an administrator I want to be able to access courses categories via API connection.
  @API
  Scenario Outline: When a GET request is sent to the /api/categories endpoint with valid authorization,
  the response status code should be 200, the remark should be “success”, and the information of id(x)
  (slug, parent_id, icon, order, title, category_id, locale) should be validated.

    * The api user constructs the base url with the "admin" token.
    * The api user sets "api/categories" path parameters.
    * The api user sends a GET request and saves the returned response.
    * The api user verifies that the status code is 200.
    * The api user verifies that the "remark" information in the response body is "success".
    * The api user verifies the "<slug>", "<icon>", <order>, <id>, <category_id>, "<locale>" and "<title>" information of the item at <dataIndex> in the response body.

    Examples:
      | dataIndex | slug                      | icon                                              | order | id  | category_id | locale | title                  |
      | 0         | Education-and-Training-53 | /store/1/default_images/categories_icons/code.png | 237   | 354 | 911         | en     | Education and Training |


  Scenario: When a GET request is sent to the /api/categories endpoint with invalid (invalid token) authorization credentials,
  it should return a 401 status code. Additionally, it should be verified that the message field in the response body is
  "Unauthenticated."

    * The api user constructs the base url with the "invalid" token.
    * The api user sets "api/categories" path parameters.
   #* The api user sends a GET request and saves the returned response.
   #* The api user verifies that the status code is 401.
   #* The api user verifies that the "message" information in the response body is "Unauthenticated.".
    * The api user sends a GET request, saves the returned response, and verifies that the status code is '401' with the reason phrase Unauthorized.


