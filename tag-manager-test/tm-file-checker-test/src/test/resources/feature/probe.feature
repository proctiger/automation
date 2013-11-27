#language: pt
Funcionalidade: Validar Probe

  Cenário: Resposta OK para o SLB-PROBE
    Quando for solicitada a requição da URL do <slb-probe>
    Então a requisição retorna o status <200>

  Cenário: Resposta OK para o CHECK-PROBE
    Quando for solicitada a requição da URL do <check-probe>
    Então a requisição retorna o status <200>

  Cenário: Resposta OK para o MONITOR-PROBE
    Quando for solicitada a requição da URL do <monitor-probe>
    Então a requisição retorna o status <200>

  @MANUAL
  Cenário: Erro ao verificar arquivo a ser baixado para o SLB-PROBE
    Quando for solicitada a requição da URL do <slb-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido

  @MANUAL
  Cenário: Erro ao verificar arquivo a ser baixado para o CHECK-PROBE
    Quando for solicitada a requição da URL do <check-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido

  @MANUAL
  Cenário: Erro ao verificar arquivo a ser baixado para o MONITOR-PROBE
    Quando for solicitada a requição da URL do <monitor-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido

  @MANUAL
  Cenário: Erro ao verificar arquivo existente para o SLB-PROBE
    Quando for solicitada a requição da URL do <slb-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido

  @MANUAL
  Cenário: Erro ao verificar arquivo existente para o CHECK-PROBE
    Quando for solicitada a requição da URL do <check-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido

  @MANUAL
  Cenário: Erro ao verificar arquivo existente para o MONITOR-PROBE
    Quando for solicitada a requição da URL do <monitor-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido

  @MANUAL
  Cenário: Erro ao verificar SVN para o SLB-PROBE
    Quando for solicitada a requição da URL do <slb-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido

  @MANUAL
  Cenário: Erro ao verificar SVN para o CHECK-PROBE
    Quando for solicitada a requição da URL do <check-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido

  @MANUAL
  Cenário: Erro ao verificar SVN para o MONITOR-PROBE
    Quando for solicitada a requição da URL do <monitor-probe>
    Então a requisição retorna o status <200>
    E o sistema registra em log o erro ocorrido
