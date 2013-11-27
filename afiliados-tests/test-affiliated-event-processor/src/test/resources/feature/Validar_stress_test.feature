# language: pt
Funcionalidade: Teste de Stress

  Esquema do Cenario: Stressando o Processor
  	Dado que não exista eventos de <event_type>
  	E que a fila esteja em execução
  	E que não exista eventos na fila
  	E que o componente payoffice esteja fora
    E que <event> eventos sejam gerados , modelo <event_model>
    Quando o processador de eventos for executado
    Entao verificar se os <event> eventos de <event_type> foram processados
    E verificar se os <event> eventos de <event_type_son> foram processados
    E verificar se existem <event> de <event_type> na fila

    Exemplos: 
      | event  | event_type | event_type_son | event_model |
      | <10>   | <Pagamento>| <2ndTier>      | <{"product":14,"desName":"Pagamento","caf":"e585c84cf88e4d8eaf0892d2b1446f5e","numEventAmount":1,"numValue":16.9,"desGrouping":"codPromotion:PROMOWAV1M099;datSubscription:09-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90"}> |
