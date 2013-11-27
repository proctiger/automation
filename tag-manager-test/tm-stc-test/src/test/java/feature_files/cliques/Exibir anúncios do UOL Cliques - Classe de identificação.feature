#language:pt
#author:dvrocha
Funcionalidade: Exibir anúncios do UOL Cliques - Classe de identificação

  Cenário: Div com anuncio do UOL Cliques não possui a classe 'tm-ads'
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou um anúncio de UOL Cliques na página sem a classe 'tm-ads'
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema não exibe o anúncio de UOL Cliques do usuário

  Cenário: Div com anuncio do UOL Cliques possui apenas a classe 'tm-ads'
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou um anúncio de UOL Cliques na página
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe o anúncio de UOL Cliques do usuário

  Cenário: Div com anuncio do UOL Cliques possui a classe 'tm-ads' e alguma outra classe
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou um anúncio de UOL Cliques na página com mais de uma classe
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe o anúncio de UOL Cliques do usuário
