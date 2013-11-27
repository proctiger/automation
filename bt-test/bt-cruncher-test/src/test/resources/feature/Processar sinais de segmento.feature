#language:pt
#author:dvrocha
Funcionalidade: Processar sinais de segmento

  Cenário: Não existe collection de segmentos
    Dado que não existe a collection de segmentos para o mês atual
    E que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
    Quando o sistema for iniciado
    Então a collection de segmentos do mês atual será criada
    E os eventos processados serão inseridos na collection de segmentos do mês atual
  
  Cenário: Não há nenhum registro para o UUID
    Dado que já existe a collection de segmentos para o mês atual
    E que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
    E que não existe nenhum registro de segmento para um usuário expecífico
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de segmentos do mês atual

  Cenário: Já existe registro para o UUID, para um dia diferente
    Dado que já existe a collection de segmentos para o mês atual
    E que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
    E que já existe algum registro de segmento para um usuário expecífico para um dia diferente
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de segmentos do mês atual

  Cenário: Já existe registro para o UUID, para um cliente diferente
    Dado que já existe a collection de segmentos para o mês atual
    E que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
    E que já existe algum registro de segmento para um usuário expecífico com mesmo dia e para um cliente diferente
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de segmentos do mês atual
    
  Cenário: Já existe registro para o UUID
    Dado que já existe a collection de segmentos para o mês atual
    E que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
    E que já existe algum registro de segmento para um usuário expecífico com mesmo dia e mesmo cliente
    Quando o sistema for iniciado
    Então os eventos processados serão inseridos na collection de segmentos do mês atual
