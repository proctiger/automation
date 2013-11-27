#language:pt
#author:dvrocha
Funcionalidade: Processar sinais de impressão

  Cenário: Não existe collection de impressões
    Dado que não existe a collection de impressões para o mês atual
    E que exite um arquivo com eventos de impressão para um usuário expecífico
    Quando o sistema for iniciado
    Então a collection de impressões do mês atual será criada
    E os eventos processados serão inseridos na collection de impressões do mês atual

  Cenário: Não há nenhum registro para o UUID processado
    Dado que já existe a collection de impressões para o mês atual
    E que não existe nenhum registro de impressão para um usuário expecífico
    E que exite um arquivo com eventos de impressão para um usuário expecífico
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de impressões do mês atual

  Cenário: Já existe registro para o UUID processado
    Dado que já existe a collection de impressões para o mês atual
    E que exite um arquivo com eventos de impressão para um usuário expecífico
    E que já existe algum registro de impressão para um usuário expecífico
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de impressões do mês atual

  Cenário: Já existe registro para o UUID processado, para um dia diferente
    Dado que já existe a collection de impressões para o mês atual
    E que exite um arquivo com eventos de impressão para um usuário expecífico
    E que já existe algum registro de impressão para um usuário expecífico para um dia diferente
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de impressões do mês atual

  Cenário: Já existe registro para o UUID processado, para um sinal diferente
    Dado que já existe a collection de impressões para o mês atual
    E que exite um arquivo com eventos de impressão para um usuário expecífico
    E que já existe algum registro de impressão para um usuário expecífico para um sinal diferente
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de impressões do mês atual

  Cenário: URL acessada não se enquadra a nenhum sinal cadastrado
    Dado que já existe a collection de impressões para o mês atual
    E que exite um arquivo com eventos de impressão para um usuário expecífico com URLs não classificadas
    Quando o sistema for iniciado
    Então os eventos processados não serão inseridos na collection de impressões do mês atual
