@blogCategories
Feature: Bir yönetici olarak, belirtilen id numarasına sahip blog category bilgisini API bağlantısı üzerinden silebilmek istiyorum.

  Scenario Outline: Geçerli authorization ve doğru id ile /api/deleteBlogCategory/{id} endpoint’ine DELETE request gönderildiğinde,
  status 200, remark “success”, message “Successfully Deleted.” ve dönen Deleted Category Id, path parametresindeki id ile aynı olmalı.

    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/deleteBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular
    * Api kullanicisi response bodyde "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanicisi response bodyde "Message" bilgisinin "Successfully Deleted." oldugunu dogrular
    Examples:
      | id |
      | 121|


  Scenario Outline: Geçerli authorization ile kayıtlı olmayan bir id içeren DELETE request gönderildiğinde,
  status code 203, remark “failed”, message “There is not category for this id.” doğrulanmalı.

    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/deleteBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular
    * Api kullanicisi response bodyde "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi response bodyde "data.message" bilgisinin "There is not category for this id." oldugunu dogrular
    Examples:
      | id |
      | 121|


  Scenario: Geçerli authorization olmadan veya id belirtilmeden DELETE request gönderildiğinde,
  status code 203, remark “failed”, message “No id” doğrulanmalı.

    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/deleteBlogCategory" path parametrelerini oluşturur
    * Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular
    * Api kullanicisi response bodyde "remark" bilgisinin "failed" oldugunu dogrular
    * Api kullanicisi response bodyde "data.message" bilgisinin "No id" oldugunu dogrular


  Scenario Outline: Geçersiz token ile doğru id içeren DELETE request gönderildiğinde,
  status code 401 ve response body’deki message “Unauthenticated.” olmalı.

    * Api kullanıcısı "invalid" token ile base urli oluşturur
    * Api kullanıcısı "api/deleteBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 401 olduğunu doğrular
    * Api kullanicisi response bodyde "message" bilgisinin "Unauthenticated." oldugunu dogrular
    Examples:
      | id |
      |115 |


  Scenario Outline: Silinen blog category kaydının gerçekten silindiği, ilgili id ile GET request gönderilerek doğrulanmalı.
  (message: "There is not category for this id.")

    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/blogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı GET request gönderir ve dönen response’ı kaydeder
    * Api kullanicisi response bodyde "data.message" bilgisinin "There is not category for this id." oldugunu dogrular

    Examples:
      | id  |
      | 115|