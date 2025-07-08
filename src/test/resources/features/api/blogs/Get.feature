
Feature: Bir yönetici olarak API bağlantısı üzerinden blogs'a erişebilmek istiyorum.

  Scenario Outline: /api/blogs endpoint'ine geçerli authorization bilgileri ile bir GET request gönderildiğinde,
  dönen status code'in 200 ve response body'deki remark bilgisinin "success" olduğu ve response body'de dönen bilgiler
  (category_id, author_id, slug, image, visit_count, enable_comment, status, created_at, updated_at, comments_count) doğrulanmalı.


    * Api kullanıcısı "admin" token ile base url'i oluşturur
    * Api kullanicisi "api/blogs" path parametrelerini oluşturur
    * Api kullanicisi GET request gonderir ve donen responsu kaydeder
    * Api kullanicisi status code'un 200 oldugunu dogrular
    * Api kullanicisi response body'deki "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanıcısı response body icindeki <category_id>, <author_id>, "<slug>", "<image>", <visit_count>, <enable_comment>, "<status>", <created_at>, <updated_at>, <comments_count>  <dataIndex>  bilgilerini doğrular.

    Examples:
      | dataIndex | category_id | author_id  |                       slug                    |               image          | visit_count |    enable_comment     |   status | created_at  |   updated_at  |   comments_count  |
      |     0     |     34      |   1349     | The-Growing-Impact-of-Online-Education-42     |   /store/1/blog-default.jpg  |      12     |           0           |  publish |  1723373188 |   1723373188  |         0         |


  Scenario: /api/blogs endpoint'ine geçersiz (invalid token) authorization bilgileri ile bir GET request gönderildiğinde,
  dönen status code'in 401 ve response body'deki message bilgisinin "Unauthenticated." oldugu doğrulanmalı.


    * Api kullanicisi "invalid" token ile base urli oluşturur
    * Api kullanicisi "api/blogs" path parametrelerini oluşturur
    * Api kullanicisi GET request gönderir ve dönen responsu kaydeder
    * Api kullanicisi status code'un 401 oldugunu dogrular
    * Api kullanicisi response bodydeki "message" bilgisinin "Unauthenticated." oldugunu dogrular
    * Api seni seviyorumsdasda