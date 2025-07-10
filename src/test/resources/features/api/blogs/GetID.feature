Feature: Bir yönetici olarak API bağlantısı üzerinden belirtilen id numarasına sahip blog'un detaylı bilgilerine erişebilmeliyim.

  Scenario Outline: /api/blog/{id} endpoint'ine geçerli authorization bilgileri ve dogru data (id) iceren bir GET request gönderildiğinde
  dönen status code'in 200 ve response body'deki remark bilgisinin "success" oldugu, Response body icindeki list datalarının
  (id, category_id, author_id, slug, image, visit_count, enable_comment, status, created_at, updated_at, id, blog_id, locale, title) içerikleri doğrulanmalı

    * Api kullanicisi "admin" token ile base urli oluşturur
    * Api kullanicisi "api/blog/<id>" path parametrelerini oluşturur
    * Api kullanicisi GET request gönderir ve dönen responsu kaydeder
    * Api kullanicisi status code'un 200 oldugunu dogrular
    * Api kullanicisi response body'deki "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanıcısı response body icindeki  <id>, <category_id>, <author_id>, "<slug>", "<image>", <visit_count>, <enable_comment>, "<status>", <created_at>, <updated_at>, bilgilerini doğrular.

    Examples:
         |id  | category_id | author_id  |                       slug                    |               image          | visit_count |    enable_comment     |   status | created_at  |   updated_at  |
         |168 |     34      |   1349     | The-Growing-Impact-of-Online-Education-42     |   /store/1/blog-default.jpg  |      12     |           0           |  publish |  1723373188 |   1723373188  |


  Scenario:

  /api/blog/{id} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir GET request gönderildiginde dönen status code'in 203,
  response body'deki remark bilgisinin "failed" ve message bilgisinin de "There is not blog for this id." oldugu doğrulanmalı

    * Api kullanicisi "admin" token ile base urli oluşturur
    * Api kullanicisi "api/blog/15" path parametrelerini oluşturur
    * Api kullanicisi GET request gönderir ve dönen responsu kaydeder
    * Api kullanicisi status code'un 203 oldugunu dogrular
    * Api kullanicisi response body'deki "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi,response body'deki "data.message" bilgisinin "There is not blog for this id." olduğunu doğrular


  Scenario:  (id) içermeyen bir GET request gönderildiğinde de dönen status code'un 203, response body'deki remark bilgisinin
  "failed" ve message bilgisinin de "No id" oldugu doğrulanmalı.

    * Api kullanicisi "admin" token ile base urli oluşturur
    * Api kullanicisi "api/blog" path parametrelerini oluşturur
    * Api kullanicisi GET request gönderir ve dönen responsu kaydeder
    * Api kullanicisi status code'un 203 oldugunu dogrular
    * Api kullanicisi response body'deki "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi,response body'deki "data.message" bilgisinin "No id" olduğunu doğrular


  Scenario: /api/blog/{id} endpoint'ine geçersiz (invalid token) authorization bilgileri ve doğru data (id) içeren
  bir GET request gönderildiginde dönen status code'in 401 ve response body'deki message bilgisinin "Unauthenticated." oldugu doğrulanmalı.

    * Api kullanicisi "invalid" token ile base urli oluşturur
    * Api kullanicisi "api/blog/170" path parametrelerini oluşturur
    * Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular

