#language:pt
#author:dvrocha
Funcionalidade: Exibir anúncios do UOL Cliques - Atributo siga inválido

  Cenário: Div com anuncio do UOL Cliques não possui atributo 'siga'
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário informou a tag de UOL Cliques sem o atributo 'siga'
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema não exibe o anúncio de UOL Cliques do usuário

  Cenário: Div com anuncio do UOL Cliques possui atributo 'siga' sem o parâmetro 'size'
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário informou a tag de UOL Cliques com o atributo 'siga' sem o parâmetro 'size'
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema não exibe o anúncio de UOL Cliques do usuário
    E o sistema registra no console do browser a seguinte mensagem de WARN 'Algum anúncio de UOL Cliques não possui a chave size no atributo [siga] e será descartado'

  Cenário: Div com anuncio do UOL Cliques possui atributo 'siga' com o parâmetro 'size' inexistente
    Dado que o script do UOL Tag Manager foi inserido no corpo do HTML
    E que o usuário possui o modulo de UOL Cliques habilitado
    E que o usuário informou a tag de UOL Cliques com o atributo 'siga' com o parâmetro 'size' inexistente
    Quando a página contendo o UOL Tag Manager for carregada
    Então o sistema não exibe o anúncio de UOL Cliques do usuário
    E o sistema registra no console do browser a seguinte mensagem de WARN 'O banner de UOL Cliques com size [1x1] é inválido e será descartado'
