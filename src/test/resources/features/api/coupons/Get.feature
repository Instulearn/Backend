Feature: Bir yönetici olarak API bağlantısı üzerinden coupons'a erişebilmek istiyorum.

  @coupons
  Scenario: gecerli authorization bilgileri ile bir GET request gönderildiginde dönen status code'in 200
  ve response body'deki remark bilgisinin "success" oldugu dogrulanmali.
    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupons" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 200 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "success" oldugunu dogrular

  @coupons2
  Scenario Outline: Geçerli yetkilendirme ile  response body'de donen bilgiler (creator_id, title, discount_type,
  source, code, percent, amount, max_amount, minimum_order, count, user_type, product_type, for_first_purchase,
  status, expired_at, created_at) doğrulanmalı.
    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupons" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi response bodydeki <dataIndex> indexe sahip olan dataların <creator_id>, "<title>", "<discount_type>", "<source>", "<code>", <percent>, <amount>, <max_amount>, <minimum_order> içeriklerini doğrular
    * Api kullanicisi response bodydeki <dataIndex> indexe sahip olan <count>, "<user_type>", "<product_type>", <for_first_purchase>, "<status>", <expired_at>, <created_at> içeriklerini doğrular

    Examples:
      |dataIndex  | creator_id | title        | discount_type | source | code       | percent | amount | max_amount | minimum_order | count | user_type | product_type | for_first_purchase | status | expired_at  | created_at  |
      | 0         | 1670       | Test Coupon  | percentage    | course | TST7894459 | 15      | 10     | 200        | 1             | 50    | all_users | all          | 0                  | active | 1717646400  | 1751707183 |

    @coupons3
  Scenario: Geçersiz (invalid token) authorization bilgileri ile /api/categories endpoint’ine bir GET request gönderildiğinde,
  401 status kodu dönmelidir. Ayrıca, response body’deki message bilgisinin "Unauthenticated." olduğu doğrulanmalıdır.

    * Api kullanicisi "invalid" token ile base urli olusturur
    * Api kullanicisi "api/coupons" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 401 oldugunu dogrular
    * Api kullanicisi response bodydeki "message" bilgisinin "Unauthenticated." oldugunu dogrular
