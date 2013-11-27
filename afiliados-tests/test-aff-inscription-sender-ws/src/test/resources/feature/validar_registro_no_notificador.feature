# language: pt
Funcionalidade: Validar registro no Notificador Cadastral
# validar se já existe uma inscriçao ativa do cliente no notificador
#? renovar inscricao e via token e subscription-id?

  Esquema do Cenario: Validar registro no Notificador Cadastral com Token
    Dado a existencia de um registro no notificador cadastral com callback URL <callbackURL>
    Quando validar registro no Notificador Cadastral
    Entao validar resultado do registro com subscription-status <status>
    
	Exemplos:
	| callbackURL                                                           | status |
	| <http://aff-inscription-consumer-ws.sys.srv.intranet/ebroker>         | <V>    |
	| <http://INVALID-aff-inscription-consumer-ws.sys.srv.intranet/ebroker> | <U>    |
	
  Esquema do Cenario: Validar registro invalido no NOtificador Cadastral com callback URL invalida
  	Dado a tentativa de primeiro registro no notificador cadastral com token dinamico e callback URL <callbackURL>
  	Quando Mapear o valor retornado
  	Entao validar resultado do registro com subscription-status <status>
  	
	Exemplos:
	| callbackURL                                                           | status |
	| <http://aff-inscription-consumer-ws.sys.srv.intranet/ebroker>         | <V>    |
	| <http://INVALID-aff-inscription-consumer-ws.sys.srv.intranet/ebroker> | <U>    |