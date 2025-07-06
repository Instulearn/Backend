@blogCategories
Feature: Bir yönetici olarak, belirtilen id numarasına sahip blog category bilgisini API bağlantısı üzerinden silebilmek istiyorum.

  Scenario Outline: Geçerli authorization ve doğru id ile /api/deleteBlogCategory/{id} endpoint’ine DELETE request gönderildiğinde,
  status 200, remark “success”, message “Successfully Deleted.” ve dönen Deleted Category Id, path parametresindeki id ile aynı olmalı.

    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/deleteBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular
    * Api kullanıcısı response body’deki "Message" bilgisinin "Successfully Deleted." olduğunu doğrular
    * Api kullanıcısı response body’deki "Deleted Blog Category Id" bilgisinin endpoint’teki "<id>" path parametresiyle aynı olduğunu doğrular
    Examples:
      | id |
      | 116|


  Scenario Outline: Geçerli authorization ile kayıtlı olmayan bir id içeren DELETE request gönderildiğinde,
  status code 203, remark “failed”, message “There is not category for this id.” doğrulanmalı.

    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/deleteBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular
    * Api kullanıcısı response body’deki "remark" bilgisinin "failed" olduğunu doğrular
    * Api kullanıcısı response body’deki "data.message" bilgisinin "There is not category for this id." olduğunu doğrular
    Examples:
      | id |
      | 115|


  Scenario: Geçerli authorization olmadan veya id belirtilmeden DELETE request gönderildiğinde,
  status code 203, remark “failed”, message “No id” doğrulanmalı.

    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/deleteBlogCategory" path parametrelerini oluşturur
    * Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular
    * Api kullanıcısı response body’deki "remark" bilgisinin "failed" olduğunu doğrular
    * Api kullanıcısı response body’deki "data.message" bilgisinin "No id" olduğunu doğrular


  Scenario Outline: Geçersiz token ile doğru id içeren DELETE request gönderildiğinde,
  status code 401 ve response body’deki message “Unauthenticated.” olmalı.

    * Api kullanıcısı "invalid" token ile base urli oluşturur
    * Api kullanıcısı "api/deleteBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı DELETE request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 401 olduğunu doğrular
    * Api kullanıcısı response body’deki "message" bilgisinin "Unauthenticated." olduğunu doğrular
    Examples:
      | id |
      |115 |


  Scenario Outline: Silinen blog category kaydının gerçekten silindiği, ilgili id ile GET request gönderilerek doğrulanmalı.
  (message: "There is not category for this id.")

    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/blogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı GET request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı response body’deki "data.message" bilgisinin "There is not category for this id." olduğunu doğrular

    Examples:
      | id  |
      | 115|