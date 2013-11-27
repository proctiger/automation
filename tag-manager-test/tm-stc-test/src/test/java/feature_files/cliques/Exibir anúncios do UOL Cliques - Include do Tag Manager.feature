#language:pt
#author:dvrocha
Funcionalidade: Exibir anúncios do UOL Cliques - Include do Tag Manager

  Cenário: Script do Tag Manager inserido no corpo do HTML
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou um anúncio de UOL Cliques na página
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe o anúncio de UOL Cliques do usuário

  Cenário: Script do Tag Manager inserido no cabeçalho do HTML
    Dado que o script do UOL Tag Manager foi inserido no cabeçalho do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou um anúncio de UOL Cliques na página
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe o anúncio de UOL Cliques do usuário
