#language:pt
#author:dvrocha
@Self
Funcionalidade: Solicitar opt-out

  Cenario: Usuário novo
    Quando for requisitada a url de opt-out
    Entao o sistema retorna o código http <204>
    E o sistema planta o cookie de OPT-OUT

  Cenario: Usuário já possui cookie BTTRK
    Dado que o usuário já possui o cookie BTTRK
    Quando for requisitada a url de opt-out
    Entao o sistema retorna o código http <204>
    E o sistema planta o cookie de OPT-OUT

  Cenario: Usuário possui o DNT ativo
    Dado que o usuário possui o browser com DNT ativo
    Quando for requisitada a url de opt-out
    Entao o sistema retorna o código http <204>
    E o sistema planta o cookie de OPT-OUT

  Cenario: Usuário possui o DNT inativo
    Dado que o usuário possui o browser com DNT inativo
    Quando for requisitada a url de opt-out
    Entao o sistema retorna o código http <204>
    E o sistema planta o cookie de OPT-OUT
