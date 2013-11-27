#language: pt
Funcionalidade: Erro ao importar estatísticas - Horário

  Cenário: Não está na hora de executar
    Dado que não está na hora de execução do sistema
    Quando o dfp-importer for iniciado
    Então o sistema registra no log da aplicação o seguinte erro: <instancia nao autorizada para execucao nesse momento>
  