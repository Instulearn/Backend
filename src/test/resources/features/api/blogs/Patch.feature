
Feature: Bir yönetici olarak API baglantisi üzerinden belirtilen id numarasına sahip blog'un bilgilerini güncelleyebilmek istiyorum.

  Scenario : /api/updateBlog/{id} endpoint'ine gecerli authorization bilgileri ile dogru (id) ve dogru datalar (title, category_id, description, content) iceren bir PATCH body gönderildiginde dönen status code'in 200,
  response body'deki remark bilgisinin "success" ve Message bilgisinin de "Successfully Updated." olduğu doğrulanmalı.

    * Api kullanıcısı updateBlog endpointine göndermek için post request body hazırlar
    * Api kullanicisi "admin" token ile base urli oluşturur
    * Api kullanicisi "api/updateBlog/170" path parametrelerini oluşturur
    * Api kullanicisi PATCH request gönderir ve dönen responsu kaydeder
    * Api kullanicisi status code'un 200 oldugunu dogrular
    * Api kullanicisi response body'deki "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanicisi,response body'deki "Message" bilgisinin "Successfully Updated." olduğunu doğrular




    Scenario: /api/updateBlog/{id} endpoint'ine geçerli authorization bilgileri ile dogru (id) ve data icermeyen bir PATCH request gönderildiginde dönen status code'in 203,
    response body'deki remark bilgisinin "failed" ve message bilgisinin de "There is no information to update." olduğu doğrulanmalı.

      * Api kullanicisi "admin" token ile base urli oluşturur
      * Api kullanicisi "api/updateBlog/170" path parametrelerini oluşturur
      * Api kullanicisi data içerme PATCH request gönderir ve dönen responsu kaydeder
      * Api kullanicisi status code'un 203 oldugunu dogrular
      * Api kullanicisi response body'deki "remark" bilgisinin "failed" oldugunu dogrular
      * Api kullanicisi,response body'deki "message" bilgisinin "There is no information to update." olduğunu doğrular


    Scenario:  /api/updateBlog/{id} endpoint'ine geçerli authorization bilgileri ile kaydı olmayan bir (id) ve doğru datalar (title, category_id, description, content) içeren bir PATCH body gönderildiğinde dönen status code'in 203,
    response body'deki remark bilgisinin "failed" ve message bilgisinin de "There is not blog for this id." olduğu doğrulanmalı.

      * Api kullanıcısı updateBlog endpointine göndermek için post request body hazırlar
      * Api kullanicisi "admin" token ile base urli oluşturur
      * Api kullanicisi "api/updateBlog/169" path parametrelerini oluşturur
      * Api kullanicisi PATCH request gönderir ve dönen responsu kaydeder
      * Api kullanicisi status code'un 203 oldugunu dogrular
      * Api kullanicisi response body'deki "remark" bilgisinin "failed" oldugunu dogrular
      * Api kullanicisi,response body'deki "data.message" bilgisinin "There is not blog for this id." olduğunu doğrular

    Scenario: /api/updateBlog/{id} endpoint'ine gecerli authorization bilgileri ile (id) icermeyen ve dogru datalar (title, category_id, description, content) iceren bir PATCH body gönderildiginde de dönen status code'in 203,
    response body'deki remark bilgisinin "failed" ve message bilgisinin de "No id" oldugu dogrulanmali.

      * Api kullanıcısı updateBlog endpointine göndermek için post request body hazırlar
      * Api kullanicisi "admin" token ile base urli oluşturur
      * Api kullanicisi "api/updateBlog" path parametrelerini oluşturur
      * Api kullanicisi PATCH request gönderir ve dönen responsu kaydeder
      * Api kullanicisi status code'un 203 oldugunu dogrular
      * Api kullanicisi response body'deki "remark" bilgisinin "failed" oldugunu dogrular
      * Api kullanicisi,response body'deki "data.message" bilgisinin "No id" olduğunu doğrular


    Scenario: /api/updateBlog/{id} endpoint'ine gecersiz (invalid token) authorization bilgileri ile dogru (id) ve dogru datalar (title, category_id, description, content) iceren bir PATCH body gönderildiginde
    dönen status code'in 401 oldugu ve response body'deki message bilgisinin "Unauthenticated." olduğu doğrulanmalı.

      * Api kullanıcısı updateBlog endpointine göndermek için post request body hazırlar
      * Api kullanicisi "invalid" token ile base urli oluşturur
      * Api kullanicisi "api/updateBlog/170" path parametrelerini oluşturur
      * Api kullanicisi PATCH request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular


    Scenario: API uzerinden güncellenmek istenen blog kaydinin güncellendigi, API uzerinden dogrulanmali.
    (Response body'de dönen Updated Blog Id ile /api/blog/{id} endpoint'ine GET request gönderilerek kaydın güncellendiği doğrulanabilir.)


      * Api kullanıcısı "admin" token ile base url'i oluşturur
      * Api kullanicisi "api/blog/170" path parametrelerini oluşturur
      * Api kullanicisi GET request gonderir ve donen responsu kaydeder
      * Api kullanicisi status code'un 200 oldugunu dogrular
      * Api kullanicisi response body'deki "remark" bilgisinin "success" oldugunu dogrular