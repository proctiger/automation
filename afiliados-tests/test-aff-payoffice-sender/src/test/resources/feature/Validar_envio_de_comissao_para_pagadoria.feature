# language: pt
Funcionalidade: Validar envio de evento de comissao para pagadoria
 

  Esquema do Cenario: Caminho perfeito do fluxo de envio de comissao de pagamento para pagadoria
  	Dado que seja simulado um pagamento com os dados <event_content>
  	E que a data do sistema seja levada em consideracao como idtEventLog 
  	E a fila esteja em execucao
  	E o consumidor da fila esteja em execucao
  	E que nao exista eventos na fila
	Quando o evento for enviado para a pagadoria
    Entao verificar o se foi logado o pagamento 
    E o evento nao voltara para a fila
    Exemplos: 
      | event_content								                                                                                              |
      | <{"idtPerson":3442140,"idtEventLog":323550506,"idtInscriptionAccount":9407,"codCenterCost":10460,"datEvent":2013-21-10,"numValue":10.00}> |