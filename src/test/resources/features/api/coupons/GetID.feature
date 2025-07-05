Feature: Bir yönetici olarak API bağlantısı üzerinden belirtilen id numarasına sahip blog category'nin detaylı bilgilerine erişebilmeliyim.

  @coupons4
  Scenario Outline: Geçerli authorization ve doğru id ile /api/blogCategory/{id} endpoint’ine bir GET request gönderildiğinde,
  response status kodunun 200, remark bilgisinin “success” olması ve dönen verilerin (id, title, slug) doğrulanması gerekir.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupons/<id>" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 200 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanicisi response bodydeki dataların <id>, "<title>", "<slug>" içeriklerini doğrular

    Examples:
      | id  | title | slug|
      | 198  |  News | new |

  @coupons5
  Scenario: Geçerli authorization bilgileri ve sistemde kayıtlı olmayan bir id ile /api/blogCategory/{id} endpoint’ine
  bir GET request gönderildiğinde, status kodunun 203, remark bilgisinin "failed" ve message bilgisinin
  "There is not category for this id." olması gerekir.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupons/11" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 203 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi response bodydeki "data.message" bilgisinin "There is not coupon for this id." oldugunu dogrular


  @coupons6
  Scenario: Geçerli authorization bilgileri ile id parametresi olmadan /api/blogCategory endpoint’ine bir GET request gönderildiğinde,
  status kodunun 203, remark bilgisinin "failed" ve message bilgisinin "No id" oldugu doğrulanmalıdır.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/coupons" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 203 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi response bodydeki "data.message" bilgisinin "No id" oldugunu dogrular

  @coupons7
  Scenario Outline: Geçersiz (invalid token) authorization bilgileri ve doğru id ile /api/blogCategory/{id} endpoint’ine bir GET request gönderildiğinde,
  response status kodunun 401 ve message bilgisinin "Unauthenticated." olması gerekir.

    * Api kullanicisi "invalid" token ile base urli olusturur
    * Api kullanicisi "api/coupons/<id>" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun 401 ve "message" bilgisinin "Unauthenticated." oldugunu dogrular

    Examples:
      | id  |
      | 198  |