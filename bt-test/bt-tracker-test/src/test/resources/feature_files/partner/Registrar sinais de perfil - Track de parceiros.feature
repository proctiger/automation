#language:pt
#author:dvrocha
@Web
Funcionalidade: Registrar sinais de perfil - Track de parceiros

  Cenário: Usuário novo
    Quando for solicitada a requisição de trackeamento do usuário para parceiros para um usuário autenticado
    Entao o sistema registra em log os eventos do usuário autenticado

  Cenario: Usuário já possui cookie BTTRK
    Dado que o usuário autenticado já possui o cookie BTTRK
    Quando for solicitada a requisição de trackeamento do usuário para parceiros para um usuário autenticado
    Entao o sistema registra em log os eventos do usuário autenticado

  Cenário: Usuário possui cookie de opt-out
    Dado que o usuário autenticado possui o cookie de OPT-OUT
    Quando for solicitada a requisição de trackeamento do usuário para parceiros para um usuário autenticado
    Entao o sistema não registra em log os eventos do usuário autenticado

  @Manual
  Cenário: Requisição proveniente de um robô
    Dado que a requisição ao sistema é proveniente de um robô
    Quando for solicitada a requisição de trackeamento para um usuário autenticado
    Entao o sistema não registra em log os eventos do usuário autenticado

  @Manual
  Cenário: Requisição não possui um referer
    Dado que a requisição ao sistema não possui um referer
    Quando for solicitada a requisição de trackeamento para um usuário autenticado
    Entao o sistema não registra em log os eventos do usuário autenticado

  @Manual
  Cenário: Usuário possui um browser com DNT ativo
    Dado que o usuário possui o browser com DNT ativo
    Quando for solicitada a requisição de trackeamento para um usuário autenticado
    Entao o sistema não registra em log os eventos do usuário autenticado

  @Manual
  Cenário: Usuário possui um browser com DNT inativo
    Dado que o usuário possui o browser com DNT inativo
    Quando for solicitada a requisição de trackeamento para um usuário autenticado
    Entao o sistema registra em log os eventos do usuário autenticado

  @Manual
  Cenário: Referer está na blacklist
    Dado que a requisição ao sistema possui um referer existente na blacklist
    Quando for solicitada a requisição de trackeamento para um usuário autenticado
    Entao o sistema não registra em log os eventos do usuário autenticado
