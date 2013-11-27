#language: pt
Funcionalidade: Importar estatísticas - Tentativas

  Cenário: DFP responde OK na primeira tentativa
  Dado que o intervalo de datas considerado pelo sistema comece em <D-3> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-3> e termina em <D-1>
  E o sistema faz <4> tentativas para obter o status do relatorio
  E o dfp devolve o status do relatorio como <inProgress> por <0> vezes
  E o dfp devolve o status do relatorio como <completed> por <1> vezes
  Quando o dfp-importer for iniciado
  Então o sistema importa <3> registros do dfp
  E o sistema registra a execução na tabela de histórico

  Cenário: DFP responde OK depois de receber pelo menos uma como resposta pendente
  Dado que o intervalo de datas considerado pelo sistema comece em <D-3> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-3> e termina em <D-1>
  E o sistema faz <4> tentativas para obter o status do relatorio
  E o dfp devolve o status do relatorio como <inProgress> por <1> vezes
  E o dfp devolve o status do relatorio como <completed> por <1> vezes
  Quando o dfp-importer for iniciado
  Então o sistema importa <3> registros do dfp
  E o sistema registra a execução na tabela de histórico

  Cenário: DFP responde OK na ultima tentativa valida
  Dado que o intervalo de datas considerado pelo sistema comece em <D-3> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-3> e termina em <D-1>
  E o sistema faz <4> tentativas para obter o status do relatorio
  E o dfp devolve o status do relatorio como <inProgress> por <3> vezes
  E o dfp devolve o status do relatorio como <completed> por <1> vezes
  Quando o dfp-importer for iniciado
  Então o sistema importa <3> registros do dfp
  E o sistema registra a execução na tabela de histórico