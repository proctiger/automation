#language:pt
#author:dvrocha
@Self
Funcionalidade: Gerar UUID do usuário - Track de parceiros

  Cenário: Usuário novo
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenário: Usuário com cookie BTTRK inválido
    Dado que o usuário possui um cookie BTTRK inválido
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenário: Usuário com cookie BTTRK recente
    Dado que o usuário possui um cookie BTTRK mais recente do que o necessário para ser atualizado
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema não planta o cookie BTTRK

  Cenário: Usuário com cookie BTTRK a ser atualizado
    Dado que o usuário possui um cookie BTTRK que atinja a data necessária para ser atualizado
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta o cookie BTTRK mantendo o ID do usuário

  Cenário: Usuário com cookie BTTRK sem data
    Dado que o usuário possui um cookie BTTRK sem data definida
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta o cookie BTTRK mantendo o ID do usuário

  Cenário: Usuário com cookie BTTRK com data de ano anterior
    Dado que o usuário possui um cookie BTTRK cuja a data refere-se ao ano anterior
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta o cookie BTTRK mantendo o ID do usuário

  Cenário: Usuário com cookie BTTRK com data de mês anterior
    Dado que o usuário possui um cookie BTTRK cuja a data refere-se ao mês anterior
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta o cookie BTTRK mantendo o ID do usuário

  Cenário: Usuário com cookie BTTRK com data de dia anterior
    Dado que o usuário possui um cookie BTTRK cuja a data refere-se ao dia anterior
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta o cookie BTTRK mantendo o ID do usuário

  Cenário: Usuário com cookie BTTRK com data de hora anterior
    Dado que o usuário possui um cookie BTTRK cuja a data refere-se à hora anterior
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta o cookie BTTRK mantendo o ID do usuário

  Cenário: Usuário com cookie BTTRK com data inválida
    Dado que o usuário possui um cookie BTTRK com data inválida
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta o cookie BTTRK mantendo o ID do usuário

  Cenário: Usuário com cookie BTTRK com data no futuro
    Dado que o usuário possui um cookie BTTRK com data no futuro
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta o cookie BTTRK mantendo o ID do usuário

  Cenário: Usuário possui um browser com DNT ativo
    Dado que o usuário possui o browser com DNT ativo
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema não planta o cookie BTTRK

  Cenário: Usuário possui um browser com DNT inativo
    Dado que o usuário possui o browser com DNT inativo
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenário: usuário possui cookie de opt-out
    Dado que o usuário possui o cookie de OPT-OUT
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema não planta o cookie BTTRK

  Cenário: Requisição proveniente de um robô
    Dado que a requisição ao sistema é proveniente de um robô
    Quando for solicitada a requisição de trackeamento do usuário para parceiros
    Entao o sistema retorna o código http <200>
    E o sistema não planta o cookie BTTRK
