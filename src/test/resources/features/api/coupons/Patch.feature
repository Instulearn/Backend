Feature: Bir yönetici olarak API baglantisi üzerinden belirtilen id numarasına sahip coupon'un bilgilerini
  güncelleyebilmek istiyorum.

  @coupons13
  Scenario Outline: Geçerli yetkilendirme ve doğru veri
  (başlık) ile /api/updateCoupon/{id} adresine yapılan bir PATCH isteğinin durum 200, açıklama “success”, mesaj “Successfully Updated.” döndürdüğünü ve
  yanıtındaki Güncellenmiş Kategori Kimliğinin {id} yol parametresiyle eşleştiğini doğrulama.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/updateCoupon/<id>" path parametrelerini olusturur
    * Api kullanicisi api updateCoupon endpointine gondermek icin bir patch request body hazirlar
    * Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * Api kullanıcısı status code’un 200 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "success" olduğunu doğrular.
    * Api kullanıcısı response body’deki "Message" bilgisinin "Successfully Updated." olduğunu doğrular.
    * Api kullanıcısı response body icindeki "Updated Coupon ID" bilgisinin endpointde yazan id path parametresi ile ayni oldugunu dogrular.
    Examples:
      | id |
      | 230|

  @coupons14
  Scenario Outline: /api/updateCoupon/{id} endpoint'ine gecerli authorization bilgileri ile dogru (id) ve data icermeyen bir
  PATCH request gönderildiginde dönen status code'in 203, response body'deki remark bilgisinin "failed" ve message
  bilgisinin de "There is no information to update." oldugu dogrulanmali.

    * Api kullanicisi "admin" token ile base urli olusturur
    * Api kullanicisi "api/updateCoupon/<id>" path parametrelerini olusturur
    * Api kullanicisi api updateCoupon endpointine gondermek icin bos bir patch request body hazirlar
    * Api kullanicisi PATCH request gonderir ve donen response'i kaydeder.
    * Api kullanıcısı status code’un 203 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "failed" olduğunu doğrular.
    * Api kullanıcısı response body’deki "message" bilgisinin "There is no information to update." olduğunu doğrular.
    Examples:
      | id |
      |230 |

  @coupons15
  Scenario Outline: Geçerli authorization ve doğru data ile, sistemde kayıtlı olmayan bir id içeren PATCH request gönderildiğinde, status code 203, remark "failed" ve message "There is not coupon for this id." olmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/updateCoupon/<id>" path parametrelerini oluşturur
    * Api kullanicisi api updateCoupon endpointine gondermek icin bir patch request body hazirlar
    * Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "failed" olduğunu doğrular.
    * Api kullanıcısı response body’deki "data.message" bilgisinin "There is not coupon for this id." olduğunu doğrular.
    Examples:
      | id |
      |1236|

  @coupons16
  Scenario: Geçerli authorization ve doğru data ile, id olmadan PATCH request gönderildiğinde, status code 203, remark "failed" ve message "No id" olmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/updateCoupon" path parametrelerini oluşturur
    * Api kullanicisi api updateCoupon endpointine gondermek icin bir patch request body hazirlar
    * Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * Api kullanıcısı status code’un 203 olduğunu doğrular.
    * Api kullanıcısı response body’deki "remark" bilgisinin "failed" olduğunu doğrular.
    * Api kullanıcısı response body’deki "data.message" bilgisinin "No id" olduğunu doğrular.

  @coupons17
  Scenario Outline: Geçersiz (invalid) token ile, doğru id ve geçerli title içeren PATCH request gönderildiğinde, status code 401 ve message "Unauthenticated." olmalı.
    * Api kullanıcısı "invalid" token ile base urli oluşturur
    * Api kullanıcısı "api/updateCoupon/<id>" path parametrelerini oluşturur
    * Api kullanicisi api updateCoupon endpointine gondermek icin bir patch request body hazirlar
    * Api kullanicisi PATCH request gonderir ve donen responsei kaydeder
    * Api kullanıcısı status code’un 401 olduğunu doğrular.
    * Api kullanıcısı response body’deki "message" bilgisinin "Unauthenticated." olduğunu doğrular.
    Examples:
      | id |
      | 230 |

  @coupons18
  Scenario Outline: Güncellenen coupon kaydinin doğru şekilde güncellendiği, /api/updateCoupon/{id} endpoint’ine GET request gönderilerek doğrulanmalı.
    * Api kullanıcısı "admin" token ile base urli oluşturur
    * Api kullanıcısı "api/coupon/<id>" path parametrelerini oluşturur
    * Api kullanicisi GET request gonderir ve donen responsei kaydeder.
    * Api kullanıcısı title bilgisinin "My Coupon Updated" olduğunu doğrular.
    Examples:
      | id  |
      | 230|
