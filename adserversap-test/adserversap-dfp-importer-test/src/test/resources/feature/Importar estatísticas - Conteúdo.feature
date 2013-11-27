#language: pt
Funcionalidade: Importar estatísticas - Conteúdo

  Cenário: Relatório possui estatísticas para todos os dias
  Dado que o intervalo de datas considerado pelo sistema comece em <D-3> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-3> e termina em <D-1>
  Quando o dfp-importer for iniciado
  Então o sistema importa <3> registros do dfp
  E o sistema registra a execução na tabela de histórico

  Cenário: Relatório vazio
  Dado que o intervalo de datas considerado pelo sistema comece em <D-3> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-0> e termina em <D-0>
  Quando o dfp-importer for iniciado
  Então o sistema importa <0> registros do dfp
  E o sistema registra a execução na tabela de histórico

  Cenário: Relatório não possui estatísticas para algum dia
  Dado que o intervalo de datas considerado pelo sistema comece em <D-3> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-3> e termina em <D-2>
  Quando o dfp-importer for iniciado
  Então o sistema importa <2> registros do dfp
  E o sistema registra a execução na tabela de histórico