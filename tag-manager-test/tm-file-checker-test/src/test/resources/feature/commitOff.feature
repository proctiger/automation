#language: pt
Funcionalidade: Não comitar alteração

  @MANUAL
  Cenário: Checar arquivo com commit desativado
    Dado que o commit no svn está desabilitado
    E algum arquivo checado possui alguma atualização
    Quando for iniciada a checagem de arquivos
    Então o arquivo não é comitado no svn

  @MANUAL
  Cenário: Solicitar SLB-PROBE com commit desativado
    Dado que o commit no svn está desabilitado
    Quando for solicitada a requição da URL do <slb-probe>
    Então a requisição retorna o status <200>
    E o svn não é checado

  @MANUAL
  Cenário: Solicitar CHECK-PROBE com commit desativado
    Dado que o commit no svn está desabilitado
    Quando for solicitada a requição da URL do <check-probe>
    Então a requisição retorna o status <200>
    E o svn não é checado

  @MANUAL
  Cenário: Solicitar MONITOR-PROBE com commit desativado
    Dado que o commit no svn está desabilitado
    Quando for solicitada a requição da URL do <monitor-probe>
    Então a requisição retorna o status <200>
    E o svn não é checado
