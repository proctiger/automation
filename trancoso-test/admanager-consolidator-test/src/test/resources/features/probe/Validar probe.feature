#language:pt
#author:dvrocha
@Self
Funcionalidade: Validar probe

  Cenario: Validar probe SLB
    Quando for solicitada a URL do probe SLB
    Então o sistema retorna o status HTTP 200

  Cenario: Validar probe CHECK
    Quando for solicitada a URL do probe CHECK
    Então o sistema retorna o status HTTP 200

  Cenario: Validar probe MONITOR
    Quando for solicitada a URL do probe MONITOR
    Então o sistema retorna o status HTTP 200
