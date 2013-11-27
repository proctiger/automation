#language:pt
#author:dvrocha
@Self
Funcionalidade: Validar Probe

  Cenario: Validar slb-probe
    Quando for requisitada a url de <slb-probe>
    Entao o sistema retorna o código http <200>

  Cenario: Validar check-probe
    Quando for requisitada a url de <check-probe>
    Entao o sistema retorna o código http <200>

  Cenario: Validar monitor-probe
    Quando for requisitada a url de <monitor-probe>
    Entao o sistema retorna o código http <200>
