@blogCategories
Feature: Bir yönetici olarak, belirtilen id numarasına sahip blog kategorisinin bilgilerini API bağlantısı üzerinden güncelleyebilmek istiyorum.


  Scenario Outline: Geçerli authorization ve doğru data (title) ile PATCH request gönderildiğinde, status code 200, remark "success", message "Successfully Updated." ve dönen id, path parametresindeki id ile aynı olmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/updateBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı updateBlogCategory endpoint’ine gönderilmek üzere bir patch request body hazırlar
    * Api kullanıcısı PATCH request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "remark" bilgisinin "success" olduğunu doğrular
    * Api kullanıcısı response bodydekiii "Message" bilgisinin "Successfully Updated." olduğunu doğrular
    Examples:
      | id |
      | 130|

  Scenario Outline: Geçerli authorization ve geçerli id ile boş veri gönderildiğinde status 203 ve “There is no information to update.” mesajı dönmeli.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/updateBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı boş bir patch request body hazırlar
    * Api kullanıcısı PATCH request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "remark" bilgisinin "failed" olduğunu doğrular
    * Api kullanıcısı response bodydekiii "message" bilgisinin "There is no information to update." olduğunu doğrular
    Examples:
      | id |
      |115 |

  Scenario Outline: Geçerli authorization ve doğru data ile, sistemde kayıtlı olmayan bir id içeren PATCH request gönderildiğinde, status code 203, remark "failed" ve message "There is not category for this id." olmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/updateBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı updateBlogCategory endpoint’ine gönderilmek üzere bir patch request body hazırlar
    * Api kullanıcısı PATCH request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "remark" bilgisinin "failed" olduğunu doğrular
    * Api kullanıcısı response bodydekiii "data.message" bilgisinin "There is not category for this id." olduğunu doğrular
    Examples:
      | id |
      |4546|

  Scenario: Geçerli authorization ve doğru data ile, id olmadan PATCH request gönderildiğinde, status code 203, remark "failed" ve message "No id" olmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/updateBlogCategory" path parametrelerini oluşturur
    * Api kullanıcısı updateBlogCategory endpoint’ine gönderilmek üzere bir patch request body hazırlar
    * Api kullanıcısı PATCH request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "remark" bilgisinin "failed" olduğunu doğrular
    * Api kullanıcısı response bodydekiii "data.message" bilgisinin "No id" olduğunu doğrular

  Scenario Outline: Geçersiz (invalid) token ile, doğru id ve geçerli title içeren PATCH request gönderildiğinde, status code 401 ve message "Unauthenticated." olmalı.
    * Api kullanıcısı "invalid" token ile base urli oluşturur
    * Api kullanıcısı "api/updateBlogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı updateBlogCategory endpoint’ine gönderilmek üzere bir patch request body hazırlar
    * Api kullanıcısı PATCH request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 401 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "message" bilgisinin "Unauthenticated." olduğunu doğrular
    Examples:
      | id |
      | 116 |

  Scenario Outline: Güncellenen blog kategorisinin doğru şekilde güncellendiği, /api/blogCategory/{id} endpoint’ine GET request gönderilerek doğrulanmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/blogCategory/<id>" path parametrelerini oluşturur
    * Api kullanıcısı GET request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı title bilgisinin "DersDeneme" olduğunu doğrular

    Examples:
      | id  |
      | 130 |
