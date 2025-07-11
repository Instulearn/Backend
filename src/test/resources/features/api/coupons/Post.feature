@couponsPost
Feature: Bir yönetici olarak API bağlantısı üzerinden yeni bir coupon kaydı oluşturabilmek istiyorum.

  Scenario: Geçerli yetkilendirme ve geçerli title içeren POST request ile coupon başarıyla oluşturulmalı.
    * Api kullanicisi "admin" tokeniyla base urli olusturur
    * Api kullanicisi "api/addCoupon" ile path parametrelerini olusturur.
    * Api kullanıcısı addCoupon endpoint’ine gönderilmek üzere geçerli bir post request body hazırlar.
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder.
    * Api kullanıcısı status code’un 200 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular.
    * Api kullanıcısı response body’deki "Message" bilgisinin "Successfully Added." olduğunu doğrular.


  Scenario: Daha önce kullanılmış bir code ile POST request gönderildiğinde status 422 ve uygun mesaj dönmeli.
    * Api kullanicisi "admin" tokeniyla base urli olusturur
    * Api kullanicisi "api/addCoupon" ile path parametrelerini olusturur.
    * Api kullanıcısı daha önce kullanılmış bir code ile post request body hazırlar
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder.
    * Api kullanıcısı status code’un 422 olduğunu doğrular.
    * Api kullanıcısı response body’deki "message" bilgisinin "The code has already been taken." olduğunu doğrular.


  Scenario: Geçersiz token ve geçerli title ile istek gönderildiğinde 401 ve “Unauthenticated.” mesajı dönmeli.
    * Api kullanicisi "invalid" tokeniyla base urli olusturur
    * Api kullanicisi "api/addCoupon" ile path parametrelerini olusturur.
    * Api kullanıcısı addCoupon endpoint’ine gönderilmek üzere geçerli bir post request body hazırlar.
    * Api kullanıcısı POST request gönderir ve de dönen response’ı kaydeder
    * Api kullanıcısı status code’un 401 olduğunu doğrular.
    * Api kullanıcısı response body’deki "message" bilgisinin "Unauthenticated." olduğunu doğrular.


  Scenario Outline: Oluşturulan coupon kaydinin gerçekten oluşup oluşmadığı GET request ile doğrulanmalı.
    * Api kullanicisi "admin" tokeniyla base urli olusturur
    * Api kullanicisi "api/coupon/<id>" ile path parametrelerini olusturur.
    * Api kullanicisi GET request gonderir ve donen responsei da kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular.
    Examples:
      | id  |
      | 198 |