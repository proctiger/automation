#language:pt
#author:dvrocha
Funcionalidade: Processar sinais de perfil

  Cenário: Não há nenhum registro para o UUID processado
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico
    E que não existe nenhum registro de perfil para um usuário expecífico
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de perfil

  Cenário: Já existe registro para o UUID processado
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico
    E que já existe algum registro de perfil para um usuário expecífico
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de perfil

  Cenário: Perfil não se enquadra a nenhum segmento
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico com dados não configurados
    Quando o sistema for iniciado
    Então os eventos processados não serão inseridos na collection de perfil

  Cenário: Perfil possui algum segmento de ramo de atuação
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico com dados de <ramo de atuação>
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de perfil

  Cenário: Perfil possui algum segmento de interesse
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico com dados de <interesse>
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de perfil

  Cenário: Perfil possui algum segmento de profissão
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico com dados de <profissão>
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de perfil

  Cenário: Perfil possui algum segmento de sexo
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico com dados de <sexo>
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de perfil

  Cenário: Perfil possui algum sinal de idade
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico com dados de <idade>
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de perfil
