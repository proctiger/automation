#language:pt
#author:dvrocha
Funcionalidade: Registrar sinais de impressão - Track simples

  Cenário: Usuário novo
    Quando for solicitada a requisição de trackeamento do usuário
    Entao o sistema retorna o código http <204>
    E o sistema registra em log os eventos da requisição

  Cenario: Usuário já possui cookie BTTRK
    Dado que o usuário já possui o cookie BTTRK
    Quando for solicitada a requisição de trackeamento do usuário
    Entao o sistema retorna o código http <204>
    E o sistema registra em log os eventos da requisição

  Cenário: Usuário possui um browser com DNT ativo
    Dado que o usuário possui o browser com DNT ativo
    Quando for solicitada a requisição de trackeamento do usuário
    Entao o sistema retorna o código http <204>
    E o sistema não registra em log os eventos da requisição

  Cenário: Usuário possui um browser com DNT inativo
    Dado que o usuário possui o browser com DNT inativo
    Quando for solicitada a requisição de trackeamento do usuário
    Entao o sistema retorna o código http <204>
    E o sistema registra em log os eventos da requisição

  Cenário: Usuário possui cookie de opt-out
    Dado que o usuário possui o cookie de OPT-OUT
    Quando for solicitada a requisição de trackeamento do usuário
    Entao o sistema retorna o código http <204>
    E o sistema não registra em log os eventos da requisição

  Cenário: Requisição proveniente de um robô
    Dado que a requisição ao sistema é proveniente de um robô
    Quando for solicitada a requisição de trackeamento do usuário
    Entao o sistema retorna o código http <204>
    E o sistema não registra em log os eventos da requisição

  Cenário: Requisição não possui um referer
    Dado que a requisição ao sistema não possui um referer
    Quando for solicitada a requisição de trackeamento do usuário
    Entao o sistema retorna o código http <204>
    E o sistema não registra em log os eventos da requisição

  Cenário: Referer está na blacklist
    Dado que a requisição ao sistema possui um referer existente na blacklist
    Quando for solicitada a requisição de trackeamento do usuário
    Entao o sistema retorna o código http <204>
    E o sistema não registra em log os eventos da requisição
