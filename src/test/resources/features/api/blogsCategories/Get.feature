Feature: Bir yönetici olarak API bağlantısı üzerinden blog categories'e erişebilmek istiyorum.


  Scenario Outline:Geçerli yetkilendirme ve doğru id ile /api/category/{id} endpoint’ine bir GET request gönderildiğinde,
  dönen response status kodunun 200, remark bilgisinin "success" olması ve response body’deki verilerin (title, slug, blog_count) doğruluğu doğrulanmalıdır.


    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/blogCategories" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 200 oldugunu dogrular
    * Api kullanicisi response bodydeki "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanicisi response bodydeki dataların "<data>", "<pageTitle>", "<blogCategories>", <id>,"<title>","<slug>", <blog_count> içeriklerini doğrular.

    Examples:
      | data     | pageTitle       | blogCategories | id | title | slug | blog_count |
      | not null | Blog Categories | not empty      | 34 | Blog  | blog | 50         |


  Scenario: Geçersiz (invalid token) authorization bilgileri ile /api/categories endpoint’ine bir GET request gönderildiğinde,
  401 status kodu dönmelidir. Ayrıca, response body’deki message bilgisinin "Unauthenticated." olduğu doğrulanmalıdır.

    * Api kullanicisi "invalid" token ile base urli olusturur
    * Api kullanicisi "api/blogCategories" path parametrelerini olusturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanicisi status codeun 401 oldugunu dogrular
    * Api kullanicisi response bodydeki "message" bilgisinin "Unauthenticated." oldugunu dogrular