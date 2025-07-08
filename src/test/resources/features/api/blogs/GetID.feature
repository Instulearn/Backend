Feature: Bir yönetici olarak API bağlantısı üzerinden belirtilen id numarasına sahip blog'un detaylı bilgilerine erişebilmeliyim.

  Scenario Outline: /api/blog/{id} endpoint'ine geçerli authorization bilgileri ve dogru data (id) iceren bir GET request gönderildiğinde
  dönen status code'in 200 ve response body'deki remark bilgisinin "success" oldugu, Response body icindeki list datalarının
  (id, category_id, author_id, slug, image, visit_count, enable_comment, status, created_at, updated_at, id, blog_id, locale, title) içerikleri doğrulanmalı

    * Api kullanicisi "admin" token ile base urli oluşturur
    * Api kullanicisi "api/blogs/<id>" path parametrelerini oluşturur
    * Api kullanicisi GET request gönderir ve dönen responsu kaydeder
    * Api kullanicisi status code'un 200 oldugunu dogrular
    * Api kullanicisi response body'deki "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanıcısı response body icindeki <id>, <category_id>, <author_id>, "<slug>", "<image>", <visit_count>, <enable_comment>, "<status>", <created_at>, <updated_at>, <id>, <dataIndex>, <blog_id>, "<locale>", "<title>" bilgilerini doğrular.

    Examples:
      | dataIndex |id  | category_id | author_id  |                       slug                    |               image          | visit_count |    enable_comment     |   status | created_at  |   updated_at  | id  | blog_id  | locale  | title            |
      |     0     |168 |     34      |   1349     | The-Growing-Impact-of-Online-Education-42     |   /store/1/blog-default.jpg  |      12     |           0           |  publish |  1723373188 |   1723373188  | 143 |  168     |  en     | Online Education |
