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
    * Api kullanıcısı response body icindeki "Updated Coupon ID" bilgisinin endpointde yazan id path parametresi ile ayni oldugunu dogrular.

  @coupons14
  Scenario: /api/updateCoupon/{id} endpoint'ine gecerli authorization bilgileri ile dogru (id) ve data icermeyen bir
  PATCH request gönderildiginde dönen status code'in 203, response body'deki remark bilgisinin "failed" ve message
  bilgisinin de "There is no information to update." oldugu dogrulanmali.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/updateCoupon/230" path parametrelerini olusturur
    * Api kullanicisi api updateCoupon endpointine gondermek icin bos bir patch request body hazirlar
    * Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular.
    * Api kullanicisi response bodydeki "data.message" bilgisinin "There is no information to update." oldugunu dogrular

