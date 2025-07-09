
  Feature: Bir yönetici olarak API baglantisi üzerinden belirtilen id numarasına sahip blog bilgilerini silebilmek istiyorum.



    Scenario: /api/deleteBlog/{id} endpoint'ine gecerli authorization bilgileri ve kaydı olmayan bir (id) iceren bir DELETE request gönderildiginde dönen status code'in 203,
    response body'deki remark bilgisinin "failed" ve message bilgisinin de "There is not blog for this id." oldugu,


      * Api kullanicisi "admin" token ile base urli oluşturur
      * Api kullanicisi "api/deleteBlog/191" path parametrelerini oluşturur
      * Api kullanicisi DELETE request gönderir ve dönen responsu kaydeder
      * Api kullanicisi status code'un 203 oldugunu dogrular
      * Api kullanicisi response body'deki "remark" bilgisinin "failed" oldugunu dogrular
      * Api kullanicisi,response body'deki "data.message" bilgisinin "There is not blog for this id." olduğunu doğrular


    Scenario:  /api/deleteBlog/{id} endpoint'ine gecerli authorization bilgileri ve (id) icermeyen bir DELETE request gönderildiginde de dönen status code'in 203,
    response body'deki remark bilgisinin "failed" ve message bilgisinin de "No id" oldugu doğrulanmalı

      * Api kullanicisi "admin" token ile base urli oluşturur
      * Api kullanicisi "api/deleteBlog" path parametrelerini oluşturur
      * Api kullanicisi DELETE request gönderir ve dönen responsu kaydeder
      * Api kullanicisi status code'un 203 oldugunu dogrular
      * Api kullanicisi response body'deki "remark" bilgisinin "failed" oldugunu dogrular
      * Api kullanicisi,response body'deki "data.message" bilgisinin "No id" olduğunu doğrular


    Scenario: /api/deleteBlog/{id} endpoint'ine gecersiz (invalid token) authorization bilgileri ile dogru (id) iceren bir DELETE request gönderildiginde
    dönen status code'in 401 oldugu ve response body'deki message bilgisinin "Unauthenticated." olduğu doğrulanmalı

      * Api kullanicisi "invalid" token ile base urli oluşturur
      * Api kullanicisi "api/deleteBlog/155" path parametrelerini oluşturur
      * Api kullanicisi DELETE request gönderir ve dönen responsu kaydeder
      * Api kullanicisi DELETE request gonderir, donen responsei kaydeder, status codeun '401' ve reason phrase bilgisinin Unauthorized oldugunu dogrular




