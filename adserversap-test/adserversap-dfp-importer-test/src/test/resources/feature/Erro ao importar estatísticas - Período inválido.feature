#language: pt
Funcionalidade: Erro ao importar estatísticas - Período inválido

  Cenário: Data para início igual a zero
    Dado que a data de início do relatório esteja configurada como zero
    Quando o dfp-importer for iniciado
    Então o sistema registra no log de alarme o seguinte erro: <configuracao de inicio e fim invalida. ambos os valores tem que ser positivos e diferentes de zero>

  Cenário: Data para início menor que zero
    Dado que a data de início do relatório esteja configurada com um número negativo
    Quando o dfp-importer for iniciado
    Então o sistema registra no log de alarme o seguinte erro: <configuracao de inicio e fim invalida. ambos os valores tem que ser positivos e diferentes de zero>

  Cenário: Data para fim igual a zero
    Dado que a data de fim do relatório esteja configurada como zero
    Quando o dfp-importer for iniciado
    Então o sistema registra no log de alarme o seguinte erro: <configuracao de inicio e fim invalida. ambos os valores tem que ser positivos e diferentes de zero>

  Cenário: Data para fim igual menor que zero
    Dado que a data de fim do relatório esteja configurada com um número negativo
    Quando o dfp-importer for iniciado
    Então o sistema registra no log de alarme o seguinte erro: <configuracao de inicio e fim invalida. ambos os valores tem que ser positivos e diferentes de zero>

  Cenário: Data para fim maior que data de início
    Dado que a data de fim do relatório seja maior que a data de início
    Quando o dfp-importer for iniciado
    Então o sistema registra no log de alarme o seguinte erro: <configuracao de inicio e fim invalida. fim eh maior do que o inicio>
