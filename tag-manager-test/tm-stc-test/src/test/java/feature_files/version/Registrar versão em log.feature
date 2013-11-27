#language: pt
#author: dvrocha
Funcionalidade: Registrar versão em log

  Cenário: Script do Tag Manager inserido no corpo do HTML
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema rigistra a versão do UOL Tag Manager no console do browser

  Cenário: Script do Tag Manager inserido no cabeçalho do HTML
    Dado que o script do UOL Tag Manager foi inserido no cabeçalho do HTML
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema rigistra a versão do UOL Tag Manager no console do browser
