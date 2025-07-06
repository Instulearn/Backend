Feature: Bir yönetici olarak API bağlantısı üzerinden yeni bir coupon kaydı oluşturabilmek istiyorum.

  @coupons9
  Scenario: Geçerli yetkilendirme ve geçerli title içeren POST request ile coupon başarıyla oluşturulmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur.
    * Api kullanıcısı "api/addCoupon" path parametrelerini oluşturur.
    * Api kullanıcısı addCoupon endpoint’ine gönderilmek üzere geçerli bir post request body hazırlar.
    * Api kullanıcısı POST request gönderir ve dönen response’ı kaydeder.
    * Api kullanıcısı status code’un 200 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular.
    * Api kullanıcısı response body’deki "Message" bilgisinin "Successfully Added." olduğunu doğrular.
