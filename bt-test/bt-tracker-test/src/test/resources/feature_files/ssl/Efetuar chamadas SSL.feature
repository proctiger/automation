#language:pt
#author:dvrocha
Funcionalidade: Efetuar chamadas SSL

  Cenario: SLB Probe
    Quando for requisitada a url de <slb-probe> via ssl
    Entao o sistema retorna o código http <200>

  Cenario: Check Probe
    Quando for requisitada a url de <check-probe> via ssl
    Entao o sistema retorna o código http <200>

  Cenario: Monitor Probe
    Quando for requisitada a url de <monitor-probe> via ssl
    Entao o sistema retorna o código http <200>

  Cenário: Track
    Quando for solicitada a requisição de trackeamento do usuário via ssl
    Entao o sistema retorna o código http <204>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenário: Partner
    Quando for solicitada a requisição de trackeamento do usuário para parceiros via ssl
    Entao o sistema retorna o código http <200>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenario: Opt In
    Quando for requisitada a url de opt-in via ssl
    Entao o sistema retorna o código http <204>
    E o sistema planta um novo cookie BTTRK para o usuário

  Cenario: Opt Out
    Quando for requisitada a url de opt-out via ssl
    Entao o sistema retorna o código http <204>
    E o sistema planta o cookie de OPT-OUT

  Cenario: Opt Status
    Quando for requisitada a url de opt-status via ssl
    Entao o sistema retorna o código http <200>
    E o sistema retorna o status de opt-out igual a <in>
    E não retorna nenhum motivo de opt-out
