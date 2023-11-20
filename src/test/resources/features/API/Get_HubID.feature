Feature: Idsi verilen Hub'in bilgilerini dondurur

  Scenario: 1 numarali id'ye sahip Hub'in bilgilerini Assert etme

    Given Get Hub id api icin gerekli path parametreleri set edilir
    Then Get Hub id api icin Expected Data hazirlanir
    Then Get Hub id icin get Request yapilir
    Then Get Hub id icin donen response'in dogrulamasi yapilir


  git init
   README.md
  git commit -m "first commit"
  git branch -M main
  git remote add origin https://github.com/TCengiz57/API_Cucumber.git
  git push -u origin main