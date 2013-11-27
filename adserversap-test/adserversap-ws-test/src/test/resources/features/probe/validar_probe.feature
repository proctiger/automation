#language: pt
Funcionalidade: Validar Probe
  Testes para validar as URLs de probe do serviço

  Cenario: Validar Probe SLB
    Quando solicitar url do probe <slb-probe>
    Entao retorna status HTTP igual a <200>

  Cenario: Validar Probe Check
    Quando solicitar url do probe <check-probe>
    Entao retorna status HTTP igual a <200>

  Cenario: Validar Probe MONITOR
    Quando solicitar url do probe <monitor-probe>
    Entao retorna status HTTP igual a <200>

  Cenario: Validar Probe CHECK
    Quando solicitar url do probe <check-probe>
    Entao retorna status HTTP igual a <200>