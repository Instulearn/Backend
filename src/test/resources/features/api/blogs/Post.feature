
Feature: Bir yönetici olarak API bağlantısı üzerinden yeni bir blog kaydı olusturabilmek istiyorum.

  Scenario:/api/addBlog endpoint'ine gecerli authorization bilgileri ve dogru datalar (title, category_id, description, content) iceren bir POST body gönderildiginde dönen status code'in 200,
  response body'deki remark bilgisinin "success" ve Message bilgisinin de "Successfully Added." oldugu doğrulanmalı.

    * Api kullanıcısı addBlog endpointine göndermek için post request body hazırlar
    * Api kullanicisi "admin" token ile base urli oluşturur
    * Api kullanicisi "api/addBlog" path parametrelerini oluşturur
    * Api kullanicisi POST request gonderir ve donen responsu kaydeder
    * Api kullanicisi status code'un 200 oldugunu dogrular
    * Api kullanicisi response body'deki "remark" bilgisinin "success" oldugunu dogrular
    * Api kullanicisi,response body'deki "Message" bilgisinin "Successfully Added." olduğunu doğrular

  Scenario: /api/addBlog endpoint'ine gecerli authorization bilgileri ve data icermeyen bir POST request gönderildiginde dönen
  status code'in 422 oldugu ve response body'deki message bilgisinin "The title field is required. (and 3 more errors)" oldugu doğrulanmalı

    * Api kullanicisi "admin" token ile base urli oluşturur
    * Api kullanicisi "api/addBlog" path parametrelerini oluşturur
    * Api kullanicisi data içermeyen POST request gonderir ve donen responsu kaydeder
    * Api kullanicisi status code'un 422 oldugunu dogrular
    * Api kullanicisi,response body'deki "message" bilgisinin "The title field is required. (and 3 more errors)" olduğunu doğrular

  Scenario: /api/addBlog endpoint'ine gecersiz (invalid token) authorization bilgileri ile dogru datalar (title, category_id, description, content) içeren bir
  POST body gönderildiginde dönen status code'in 401 oldugu ve response body'deki message bilgisinin "Unauthenticated." olduğu doğrulanmalı

    * Api kullanıcısı addBlog endpointine göndermek için post request body hazırlar
    * Api kullanicisi "invalid" token ile base urli oluşturur
    * Api kullanicisi "api/addBlog" path parametrelerini oluşturur
    * Api kullanicisi POST request gonderir ve donen responsu kaydeder
    * Api kullanicisi status code'un 401 oldugunu dogrular
    * Api kullanicisi,response body'deki "message" bilgisinin "Unauthenticated." olduğunu doğrular

  Scenario: API üzerinden oluşturulmak istenen yeni blog kaydının oluştuğu API üzerinden doğrulanmalı.
  (Response bodyde dönen Added Blog ID ile /api/blog/{id} endpoint'ine GET request gönderilerek kayıt oluşturulduğu doğrulanabilir.)

    * Api kullanicisi "admin" token ile base urli oluşturur
    * Api kullanicisi "api/blog/172" path parametrelerini oluşturur
    * Api kullanicisi GET request gönderir ve dönen responsu kaydeder
    * Api kullanicisi status code'un 200 oldugunu dogrular
    * Api kullanicisi response body'deki "remark" bilgisinin "success" oldugunu dogrular

