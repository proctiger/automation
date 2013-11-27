#language:pt
#author:dvrocha
@Self
Funcionalidade: Solicitar opt-status

  Cenario: Usuário aceita ser trackeado
    Quando for requisitada a url de opt-status
    Entao o sistema retorna o código http <200>
    E o sistema retorna o status de opt-out igual a <in>
    E não retorna nenhum motivo de opt-out

  Cenario: Usuário possui cookie de OPT-OUT
    Dado que o usuário possui o cookie de OPT-OUT
    Quando for requisitada a url de opt-status
    Entao o sistema retorna o código http <200>
    E o sistema retorna o status de opt-out igual a <out>
    E o sistema retorna o motivo de opt-out igual a <cookie>

  Cenario: Usuário possui DNT ativo
    Dado que o usuário possui o browser com DNT ativo
    Quando for requisitada a url de opt-status
    Entao o sistema retorna o código http <200>
    E o sistema retorna o status de opt-out igual a <out>
    E o sistema retorna o motivo de opt-out igual a <header>

  Cenario: Usuário possui DNT inativo
    Dado que o usuário possui o browser com DNT inativo
    Quando for requisitada a url de opt-status
    Entao o sistema retorna o código http <200>
    E o sistema retorna o status de opt-out igual a <in>
    E não retorna nenhum motivo de opt-out

  Cenario: Usuário possui cookie de OPT-OUT e DNT ativo
    Dado que o usuário possui o cookie de OPT-OUT
    E que o usuário possui o browser com DNT ativo
    Quando for requisitada a url de opt-status
    Entao o sistema retorna o código http <200>
    E o sistema retorna o status de opt-out igual a <out>
    E o sistema retorna o motivo de opt-out igual a <header>

  Cenario: Usuário possui cookie de OPT-OUT e DNT inativo
    Dado que o usuário possui o cookie de OPT-OUT
    E que o usuário possui o browser com DNT inativo
    Quando for requisitada a url de opt-status
    Entao o sistema retorna o código http <200>
    E o sistema retorna o status de opt-out igual a <out>
    E o sistema retorna o motivo de opt-out igual a <cookie>
