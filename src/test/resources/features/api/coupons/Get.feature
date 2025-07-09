@couponsGet
Feature: Bir yönetici olarak API bağlantısı üzerinden coupons'a erişebilmek istiyorum.


  Scenario: gecerli authorization bilgileri ile bir GET request gönderildiginde dönen status code'in 200
  ve response body'deki remark bilgisinin "success" oldugu dogrulanmali.
    * Api kullanicisi "admin" tokeniyla base urli olusturur
    * Api kullanicisi "api/coupons" ile path parametrelerini olusturur.
    * Api kullanicisi GET request gonderir ve donen responsei da kaydeder
    * Api kullanicisi status codeunun 200 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "success" oldugunu dogrular.

  Scenario Outline: Geçerli yetkilendirme ile  response body'de donen bilgiler (creator_id, title, discount_type,
  source, code, percent, amount, max_amount, minimum_order, count, user_type, product_type, for_first_purchase,
  status, expired_at, created_at) doğrulanmalı.
    * Api kullanicisi "admin" tokeniyla base urli olusturur
    * Api kullanicisi "api/coupons" ile path parametrelerini olusturur.
    * Api kullanicisi GET request gonderir ve donen responsei da kaydeder
    * Api kullanicisi response bodydeki <dataIndex> indexe sahip olan dataların <creator_id>, "<title>", "<discount_type>", "<source>", "<code>", <percent>, <amount>, <max_amount>, <minimum_order> içeriklerini doğrular
    * Api kullanicisi response bodydeki <dataIndex> indexe sahip olan <count>, "<user_type>", "<product_type>", <for_first_purchase>, "<status>", <expired_at>, <created_at> içeriklerini doğrular

    Examples:
      |dataIndex  | creator_id | title        | discount_type | source | code             | percent | amount | max_amount | minimum_order | count | user_type | product_type | for_first_purchase | status | expired_at  | created_at  |
      | 3         | 1349       | Test Coupon  | percentage    | course | TST1752002528895 | 15      | 10     | 200        | 1             | 50    | all_users | all          | 0                  | active | 1767157200  | 1752002527 |


  Scenario: Geçersiz (invalid token) authorization bilgileri ile /api/categories endpoint’ine bir GET request gönderildiğinde,
  401 status kodu dönmelidir. Ayrıca, response body’deki message bilgisinin "Unauthenticated." olduğu doğrulanmalıdır.

    * Api kullanicisi "invalid" tokeniyla base urli olusturur
    * Api kullanicisi "api/coupons" ile path parametrelerini olusturur.
    * Api kullanicisi GET request gonderir ve donen responsei da kaydeder
    * Api kullanicisi status codeunun 401 oldugunu dogrular
    * Api kullanicisi response bodydeki "message" bilgisinin "Unauthenticated." oldugunu dogrular.
