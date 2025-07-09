Feature: As an administrator, I want to be able to update the information of the course faq with the specified
  id number via the API connection.


  Scenario Outline: When a PATCH body with valid authorization information and correct (id) and correct data
  (title,answer) is sent to the /api/updateCoursefaq/{id} endpoint, it should be verified that
  the status code returned is 200, the remark in the response body is “success” and the Message is
  “Successfully Updated.”.

    * The api user prepares a patch request body to send to the api updateCourseFAQ <id> endpoint with the "<title>", "<answer>".
    * The api user sends a PATCH request and saves the returned response for Coursefaq.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 200 for Coursefaq.
    # Api kullanicisi status codeun 200 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "success" for Coursefaq.
    # Api kullanicisi response bodydeki remark bilgisinin "success" oldugunu dogrular
    * The api user verifies that the "Message" information in the response body is "Successfully Updated." for Coursefaq.
    # Api kullanicisi response bodydeki Message bilgisinin "Successfully Updated." oldugunu dogrular



    Examples:
      | id |                                                   title                                       |                                                       answer                                                                                                                                                                                                                                                   |
      | 473|What are the key features that differentiate your online learning platform from others? Updated|The key features that distinguish our online learning platform from others include: a wide range of course content, interactive learning tools, student support services, personalized learning paths that provide a customized learning experience, and a team of up-to-date and qualified instructors. Updated|




  Scenario Outline: When a PATCH request is sent to the /api/updateCoursefaq/{id} endpoint with valid authorization
  information, it should be verified that the status code returned is 203, the remark in the response body is
  “failed” and the message is “There is no information to update.”.


    * The api user prepares an empty patch request body to send to the api updateCoursefaq <id> endpoint
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The api user sends a PATCH request and saves the returned response for Coursefaq.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203 for Coursefaq.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed" for Coursefaq.
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "message" information in the response body is "There is no information to update." for Coursefaq.
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular
    Examples:
      | id |
      | 473 |


  Scenario Outline: When a PATCH body containing an unregistered (id) and correct data (title, answer)
  is sent to the endpoint /api/updateCoursefaq/{id} with valid authorization information, the status code returned is
  203, the remark in the response body is “failed”

    * The api user prepares a patch request body to send to the api updateCourseFAQ <id> endpoint with the "<title>", "<answer>".
    * The api user sends a PATCH request and saves the returned response for Coursefaq.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203 for Coursefaq.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed" for Coursefaq.
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "data.message" information in the response body is "There is not course faq for this id." for Coursefaq.
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular

    Examples:
      | id   |                                               title                                              |                                                  answer                                                                                                                  |
      | 4632 | What are the key features that differentiate your online learning platform from others? Updated  | The key features that distinguish our online learning platform from others include: a wide range of course content, interactive learning tools, student support services, personalized learning paths that provide a customized learning experience, and a team of up-to-date and qualified instructors. Updated   |



  Scenario Outline: when sending a PATCH body that does not contain (id) and contains the correct data
  (title, answer), it should be verified that the status code returned is 203, the remark in the
  response body is ‘failed’ and the message is ”No id".

    * The api user prepares a patch request body to send to the api updateCourseFAQ 0 endpoint with the "<title>", "<answer>".
    * The api user sends a PATCH request and saves the returned response for Coursefaq.
    # Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * The api user verifies that the status code is 203 for Coursefaq.
    # Api kullanicisi status codeun 203 oldugunu dogrular
    * The api user verifies that the "remark" information in the response body is "failed" for Coursefaq.
    # Api kullanicisi response bodydeki remark bilgisinin "failed" oldugunu dogrular
    * The api user verifies that the "data.message" information in the response body is "No id" for Coursefaq.
    # Api kullanicisi response bodydeki message bilgisinin "There is not category for this id." oldugunu dogrular

    Examples:
      |                                        title                                                     | answer                                                                                                                  |
      |  What are the key features that differentiate your online learning platform from others? Updated |   The key features that distinguish our online learning platform from others include: a wide range of course content, interactive learning tools, student support services, personalized learning paths that provide a customized learning experience, and a team of up-to-date and qualified instructors. Updated  |


  Scenario Outline: When a PATCH body is sent to /api/updateCoursefaq endpoint with invalid token authorization
  information and correct (id) and correct data (title, answer), it should be verified that the status
  code returned is 401 and the message information in the response body is “Unauthenticated.”

    * The api user prepares a patch request body to send to the api updateCourseFAQ <id> endpoint with the "<title>", "<answer>".
    # Api kullanicisi api updateCategory endpointine gondermek icin bir patch request body hazirlar
    * The api user sends a PATCH request with invalid token and saves the returned response for Coursefaq.
    * The api user verifies that the status code is 401 and the "message" information in the response body is "Unauthenticated.".

    Examples:
      | id |                                          title                                                     |                                                                              answer                                                                                                                  |
      | 473 | What are the key features that differentiate your online learning platform from others? Updated  |  The key features that distinguish our online learning platform from others include: a wide range of course content, interactive learning tools, student support services, personalized learning paths that provide a customized learning experience, and a team of up-to-date and qualified instructors. Updated     |

#Sıradaki///////////////////////////////////////////////
  Scenario Outline: It should be verified that the course faq record requested to be updated via API has been updated via
  API. (It can be verified that the record has been updated by sending a GET request to the /api/Coursefaq/{id}
  endpoint with the updated course Faq ID returned in the response body).

    * The api user constructs the base url with the "admin" token.
   # Api kullanicisi "admin" token ile base urli olusturur
    * The api user sets "api/coursefaq/<id>" path parameters.
    # Api kullanicisi "api/updateCoursefaq/<id>" path parametrelerini olusturur
    * The api user sends a GET request and saves the returned response.
   # Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * The api user verifies that the title information is "What are the key features that differentiate your online learning platform from others? Updated"
   # Api kullanıcısı title bilgisinin "Education and Training" olduğunu doğrular

    Examples:
      | id  |
      | 473 |