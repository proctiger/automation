#language:pt
#author:dvrocha
@Self
Funcionalidade: Solicitar opt-in

  Cenario: Usuário novo
    Quando for requisitada a url de opt-in
    Entao o sistema retorna o código http <204>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenario: Usuário já possui cookie BTTRK
    Dado que o usuário já possui o cookie BTTRK
    Quando for requisitada a url de opt-in
    Entao o sistema retorna o código http <204>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenario: Usuário possui o DNT ativo
    Dado que o usuário possui o browser com DNT ativo
    Quando for requisitada a url de opt-in
    Entao o sistema retorna o código http <406>
    E o sistema não planta o cookie BTTRK

  Cenario: Usuário possui o DNT inativo
    Dado que o usuário possui o browser com DNT inativo
    Quando for requisitada a url de opt-in
    Entao o sistema retorna o código http <204>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenario: Usuário possui cookie de OPT-OUT
    Dado que o usuário possui o cookie de OPT-OUT
    Quando for requisitada a url de opt-in
    Entao o sistema retorna o código http <204>
    E o sistema planta um novo cookie BTTRK para o usuário
