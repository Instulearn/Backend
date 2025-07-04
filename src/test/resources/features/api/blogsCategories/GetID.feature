Feature: Bir yönetici olarak API bağlantısı üzerinden belirtilen id numarasına sahip blog category'nin detaylı bilgilerine erişebilmeliyim.

  Scenario Outline: Geçerli authorization ve doğru id ile /api/blogCategory/{id} endpoint’ine bir GET request gönderildiğinde,
  response status kodunun 200, remark bilgisinin “success” olması ve dönen verilerin (id, title, slug) doğrulanması gerekir.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/blogCategory/<id>" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 200 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanicisi response bodydeki dataların <id>, "<title>", "<slug>" içeriklerini doğrular

    Examples:
      | id  | title | slug|
      | 37  |  News | new |

  Scenario: Geçerli authorization bilgileri ve sistemde kayıtlı olmayan bir id ile /api/blogCategory/{id} endpoint’ine
  bir GET request gönderildiğinde, status kodunun 203, remark bilgisinin "failed" ve message bilgisinin
  "There is not category for this id." olması gerekir.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/blogCategory/11" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 203 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi response bodydeki "data.message" bilgisinin "There is not category for this id." oldugunu dogrular


  Scenario: Geçerli authorization bilgileri ile id parametresi olmadan /api/blogCategory endpoint’ine bir GET request gönderildiğinde,
  status kodunun 203, remark bilgisinin "failed" ve message bilgisinin "No id" oldugu doğrulanmalıdır.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/blogCategory" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 203 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi response bodydeki "data.message" bilgisinin "No id" oldugunu dogrular

  Scenario Outline: Geçersiz (invalid token) authorization bilgileri ve doğru id ile /api/blogCategory/{id} endpoint’ine bir GET request gönderildiğinde,
  response status kodunun 401 ve message bilgisinin "Unauthenticated." olması gerekir.

    * Api kullanicisi "invalid" token ile base urli olusturur
    * Api kullanicisi "api/blogCategory/<id>" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir, donen responsei kaydeder, status codeun 401 ve "message" bilgisinin "Unauthenticated." oldugunu dogrular

    Examples:
      | id  |
      | 45  |
