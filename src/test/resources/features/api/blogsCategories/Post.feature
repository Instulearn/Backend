@blogCategories
Feature: Bir yönetici olarak API bağlantısı üzerinden yeni bir blog category kaydı oluşturabilmek istiyorum.

  Scenario: Geçerli authorization ve geçerli title içeren POST request ile blog kategorisi başarıyla oluşturulmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/addBlogCategory" path parametrelerini oluşturur
    * Api kullanıcısı addBlogCategory endpoint’ine gönderilmek üzere geçerli bir post request body hazırlar
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "remark" bilgisinin "success" olduğunu doğrular
    * Api kullanıcısı response bodydekiii "Message" bilgisinin "Successfully Added." olduğunu doğrular


  Scenario: Geçerli authorization ile boş veri (title olmadan) gönderildiğinde status 422 ve uygun mesaj dönmeli.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/addBlogCategory" path parametrelerini oluşturur
    * Api kullanıcısı title içermeyen bir post request body hazırlar
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 422 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "message" bilgisinin "The title field is required." olduğunu doğrular

  Scenario: Daha önce kullanılmış bir title ile POST request gönderildiğinde status 422 ve uygun mesaj dönmeli.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/addBlogCategory" path parametrelerini oluşturur
    * Api kullanıcısı daha önce kullanılmış bir title ile post request body hazırlar
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 422 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "message" bilgisinin "The title has already been taken." olduğunu doğrular

  Scenario: Geçersiz token ve geçerli title ile istek gönderildiğinde 401 ve “Unauthenticated.” mesajı dönmeli.
    * Api kullanıcısı "invalid" token ile base urli oluşturur
    * Api kullanıcısı "api/addBlogCategory" path parametrelerini oluşturur
    * Api kullanıcısı geçerli title içeren bir post request body hazırlar
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 401 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "message" bilgisinin "Unauthenticated." olduğunu doğrular


  Scenario Outline: Oluşturulan blog kategorisinin gerçekten oluşup oluşmadığı GET request ile doğrulanmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/blogCategory/<id>" path parametrelerini oluşturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular
    * Api kullanıcısı response bodydekiii "remark" bilgisinin "success" olduğunu doğrular

    Examples:
      | id  |
      | 130 |