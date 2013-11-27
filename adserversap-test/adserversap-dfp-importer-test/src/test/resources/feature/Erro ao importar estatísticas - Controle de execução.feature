#language: pt
Funcionalidade: Erro ao importar estatísticas - Controle de execução

  Cenário: Já existe um processo de execução iniciado a menos de 2 horas
    Dado que existe um processo de execução iniciado a menos de 2 horas
    Quando o dfp-importer for iniciado
    Então o sistema registra no log da aplicação o seguinte erro: <intervalo entre execucoes tem menos de 2 horas>

  Cenário: Já existe um processo de execução finalizado a menos de 2 horas
    Dado que existe um processo de execução finalizado a menos de 2 horas
    Quando o dfp-importer for iniciado
    Então o sistema registra no log da aplicação o seguinte erro: <hoje o job ja foi executado com sucesso>

  Cenário: Já existe um processo de execução finalizado a mais de 2 horas
    Dado que existe um processo de execução finalizado a mais de 2 horas
    Quando o dfp-importer for iniciado
    Então o sistema registra no log da aplicação o seguinte erro: <hoje o job ja foi executado com sucesso>

  @MANUAL
  Cenário: Erro ao verificar processos em execução no banco de dados
    _Dado que existe um processo de execução finalizado a mais de 2 horas
    _Quando o dfp-importer for iniciado
    _Então o sistema registra no log da aplicação o seguinte erro: <hoje o job ja foi executado com sucesso>

