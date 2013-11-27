#language: pt
Funcionalidade: Erro ao importar estatísticas - Tentativas

  Cenário: DFP não responde com o máximo de tentativas excedidas
  Dado que o intervalo de datas considerado pelo sistema comece em <D-4> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-0> e termina em <D-0>
  E o dfp nao retorne o relatorio dentro da quantidade de retentativas configuradas
  Quando o dfp-importer for iniciado
  Então o sistema registra no log de alarme o seguinte erro: <job de report no DFP retornou status [IN_PROGRESS]>
  
  Cenário: DFP responde algum erro
  Dado que o intervalo de datas considerado pelo sistema comece em <D-4> e termine em <D-1>
  E existem dados para o intervalo que comeca em <D-0> e termina em <D-0>
  E o dfp retorne erro quando requisitar o relatorio
  Quando o dfp-importer for iniciado
  Então o sistema registra no log de alarme o seguinte erro: <erro ao esperar pela execucao do job de relatorio>
  
  Cenário: Timeout ao solicitar DFP -- o mocker deve estar atras de um ngingx
  _Dado que o intervalo de datas considerado pelo sistema comece em <D-4> e termine em <D-1>
  _E existem dados para o intervalo que comeca em <D-0> e termina em <D-0>
  _E o dfp demore <121> segundos para retornar o status do relatorio
  _Quando o dfp-importer for iniciado
  _Então o sistema registra no log de alarme o seguinte erro: <(504)Gateway Time-out>