Feature: Token Create eden API

  Scenario:

    Given Token create etmek icin gerekli olan path parametresi set edilir
    Then  Get token Post methodu icin gerekli olan reqBody olusturulur
    Then  Get token icin gerekli olan request gönderilir
    Then Get token requesti icin donen Response bodysinden Token degeri alinir

