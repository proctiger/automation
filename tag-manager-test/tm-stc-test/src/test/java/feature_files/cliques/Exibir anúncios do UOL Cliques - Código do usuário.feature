#language:pt
#author:dvrocha
Funcionalidade: Exibir anúncios do UOL Cliques - Código do usuário

  Cenário: Código do usuário não foi definido
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o código do usuário para o UOL Cliques não foi definido
    E que o usuário adicionou um anúncio de UOL Cliques na página
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema não exibe o anúncio de UOL Cliques do usuário
    E o sistema registra no console do browser a seguinte mensagem de WARN 'Código do usuário não definido para os anúncios de UOL Cliques'

  Cenário: Código do usuário definido na configuração da página
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o código do usuário para o UOL Cliques está definido na configuração da página
    E que o usuário adicionou um anúncio de UOL Cliques na página
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe o anúncio de UOL Cliques do usuário

  Cenário: Código do usuário definido na configuração do Tag Manager
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o código do usuário para o UOL Cliques está definido na configuração do Tag Manager
    E que o usuário adicionou um anúncio de UOL Cliques na página
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe o anúncio de UOL Cliques do usuário

  Cenário: Código do usuário definido no atributo 'siga'
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário adicionou um anúncio de UOL Cliques na página com o código do usuário no atributo 'siga'
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema exibe o anúncio de UOL Cliques do usuário
