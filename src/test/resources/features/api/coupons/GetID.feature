@couponsGetID
Feature: Bir yönetici olarak API baglantisi üzerinden belirtilen id numarasına sahip coupon'un detaylı bilgilerine erisebilmeliyim.

  Scenario Outline: Geçerli authorization ve doğru id ile /api/coupon/{id} endpoint’ine bir GET request gönderildiğinde,
  response status kodunun 200, remark bilgisinin “success” oldugu doğrulanir.
    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupon/<id>" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 200 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "success" oldugunu dogrular
    Examples:
      | id  |
      | 198  |

  Scenario Outline: Geçerli authorization ve doğru id ile /api/coupon/{id} endpoint’ine bir GET request gönderildiğinde,
  dönen veriler (id, creator_id, title, discount_type, source, code, percent, amount, max_amount, minimum_order, count, user_type, product_type,
  for_first_purchase, status, expired_at, created_at) dogrulanir.
    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupon/<id>" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi response bodydeki dataların <dataIndex>, <id>, <creator_id>, "<title>", "<discount_type>", "<source>", "<code>", <percent>, <amount>, <max_amount>, <minimum_order> içeriklerini doğrular
    * Api kullanicisi response bodydeki dataların <dataIndex>, <count>, "<user_type>", "<product_type>", <for_first_purchase>, "<status>", <expired_at>, <created_at> içeriklerini doğrular
    Examples:
    |dataIndex   |id    | creator_id | title              | discount_type | source     | code         | percent | amount | max_amount | minimum_order | count | user_type | product_type | for_first_purchase | status | expired_at  | created_at  |
    |0           | 198  | 1670       | My Coupon Updated  | percentage    | course     | TST7894459   | 5      | 10     | 200        | 1             | 50    | all_users | all          | 0                  | active | 1717646400  | 1751707183  |


  Scenario: Geçerli authorization bilgileri ve sistemde kayıtlı olmayan bir id ile /api/coupon/{id} endpoint’ine
  bir GET request gönderildiğinde, status kodunun 203, remark bilgisinin "failed" ve message bilgisinin
  "There is not coupon for this id." olması gerekir.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupon/11" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 203 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi response bodydeki "data.message" bilgisinin "There is not coupon for this id." oldugunu dogrular


  Scenario: Geçerli authorization bilgileri ile id parametresi olmadan /api/coupon endpoint’ine bir GET request gönderildiğinde,
  status kodunun 203, remark bilgisinin "failed" ve message bilgisinin "No id" oldugu doğrulanmalıdır.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupon" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 203 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi response bodydeki "data.message" bilgisinin "No id" oldugunu dogrular

  Scenario Outline: Geçersiz (invalid token) authorization bilgileri ve doğru id ile /api/coupons/{id} endpoint’ine bir GET request gönderildiğinde,
  response status kodunun 401 ve message bilgisinin "Unauthenticated." olması gerekir.

    * Api kullanicisi "invalid" token ile base urli olusturur
    * Api kullanicisi "api/coupon/<id>" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun 401 ve "message" bilgisinin "Unauthenticated." oldugunu dogrular
    Examples:
      | id  |
      | 198  |