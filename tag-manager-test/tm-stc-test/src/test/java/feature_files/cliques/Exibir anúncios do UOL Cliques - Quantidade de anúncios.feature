#language:pt
#author:dvrocha
Funcionalidade: Exibir anúncios do UOL Cliques - Quantidade de anúncios

  Cenário: Usuário insere apenas um anúncio de UOL Cliques
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou um anúncio de UOL Cliques na página
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe o anúncio de UOL Cliques do usuário

  Cenário: Usuário insere a quantidade máxima de anúncios de UOL Cliques
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou a quantidade máxima de anúncios de UOL Cliques na página
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe os anúncios de UOL Cliques do usuário

  Cenário: Usuário insere mais anúncios de UOL Cliques que a quantidade máxima permitida
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou mais anúncios de UOL Cliques que a quantidade máxima permitida
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe os anúncios de UOL Cliques do usuário
