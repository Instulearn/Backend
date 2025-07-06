Feature: Bir yönetici olarak API baglantisi üzerinden belirtilen id numarasına sahip coupon'un bilgilerini
  güncelleyebilmek istiyorum.

  @coupons13
  Scenario: Geçerli yetkilendirme ve doğru veri
  (başlık) ile /api/updateCoupon/{id} adresine yapılan bir PATCH isteğinin durum 200, açıklama “success”, mesaj “Successfully Updated.” döndürdüğünü ve
  yanıtındaki Güncellenmiş Kategori Kimliğinin {id} yol parametresiyle eşleştiğini doğrulama.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/updateCoupon/230" path parametrelerini olusturur
    * Api kullanicisi api updateCoupon endpointine gondermek icin bir patch request body hazirlar
    * Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular.
    * Api kullanıcısı response body’deki "Message" bilgisinin "Successfully Updated." olduğunu doğrular.