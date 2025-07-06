Feature: Bir yönetici olarak API bağlantısı üzerinden yeni bir coupon kaydı oluşturabilmek istiyorum.

  @coupons9
  Scenario: Geçerli yetkilendirme ve geçerli title içeren POST request ile coupon başarıyla oluşturulmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/addCoupon" path parametrelerini oluşturur
    * Api kullanıcısı addCoupon endpoint’ine gönderilmek üzere geçerli bir post request body hazırlar.
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder.
    * Api kullanıcısı status code’un 200 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular.
    * Api kullanıcısı response body’deki "Message" bilgisinin "Successfully Added." olduğunu doğrular.


  @coupons10
  Scenario: Daha önce kullanılmış bir code ile POST request gönderildiğinde status 422 ve uygun mesaj dönmeli.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/addCoupon" path parametrelerini oluşturur
    * Api kullanıcısı daha önce kullanılmış bir code ile post request body hazırlar
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder.
    * Api kullanıcısı status code’un 422 olduğunu doğrular.
    * Api kullanıcısı response body’deki "message" bilgisinin "The code has already been taken." olduğunu doğrular.

  @coupons11
  Scenario: Geçersiz token ve geçerli title ile istek gönderildiğinde 401 ve “Unauthenticated.” mesajı dönmeli.
    * Api kullanıcısı "invalid" token ile base urli oluşturur
    * Api kullanıcısı "api/addCoupon" path parametrelerini oluşturur
    * Api kullanıcısı addCoupon endpoint’ine gönderilmek üzere geçerli bir post request body hazırlar.
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder
    * Api kullanıcısı status code’un 401 olduğunu doğrular
    * Api kullanıcısı response body’deki "message" bilgisinin "Unauthenticated." olduğunu doğrular

  @coupons12
  Scenario Outline: Oluşturulan coupon kaydinin gerçekten oluşup oluşmadığı GET request ile doğrulanmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/coupon/<id>" path parametrelerini oluşturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular

    Examples:
      | id  |
      | 198 |