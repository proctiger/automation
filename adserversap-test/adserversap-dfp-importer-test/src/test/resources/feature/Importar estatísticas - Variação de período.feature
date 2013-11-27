#language: pt
Funcionalidade: Importar estatísticas - Variação de período

  Cenário: Estatísticas de D-1
  Dado que o intervalo de datas considerado pelo sistema comece em <D-1> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-1> e termina em <D-1>
  Quando o dfp-importer for iniciado
  Então o sistema importa <1> registros do dfp
  E o sistema registra a execução na tabela de histórico
  

  Cenário: Estatísticas dos ultimos 3 dias
  Dado que o intervalo de datas considerado pelo sistema comece em <D-3> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-3> e termina em <D-1>
  Quando o dfp-importer for iniciado
  Então o sistema importa <3> registros do dfp
  E o sistema registra a execução na tabela de histórico


  Cenário: Estatísticas de D-2 a D-2
  Dado que o intervalo de datas considerado pelo sistema comece em <D-2> e termine em <D-2>
  E existem dados para o intervalo que comeca em <D-2> e termina em <D-2>
  Quando o dfp-importer for iniciado
  Então o sistema importa <1> registros do dfp
  E o sistema registra a execução na tabela de histórico  

  Cenário: Estatísticas de D-4 a D-2
  Dado que o intervalo de datas considerado pelo sistema comece em <D-4> e termine em <D-2>
  E existem dados para o intervalo que comeca em <D-4> e termina em <D-2>
  Quando o dfp-importer for iniciado
  Então o sistema importa <3> registros do dfp
  E o sistema registra a execução na tabela de histórico  
 