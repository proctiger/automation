# language: pt
Funcionalidade: Consulta de Ordem para Reprogramacao
  Testes relacionados ao servico de consulta de ordem DFP - GET /approval/order

Cenario: Criar ordem PENDING FOR APPROVAL com 1 item de linha
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <lineItems> correto
	
Cenario: Criar ordem PENDING FOR APPROVAL com 2 itens de linha com mesmo material
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <lineItems> correto
	
Cenario: Consultar ordem com 1 item de linha dlink
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DLINKP> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <lineItems> correto

Cenario: Consultar ordem com 2 itens de linha com material diferentes
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E criar item de linha
	E configurar item de linha com material <DECOMM> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <lineItems> correto

Cenario: Criar ordem com 2 itens de linha com mesmo material
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <DECOMM> 
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <lineItems> correto

Cenario: Criar ordem com 2 itens de linha com mesmo material
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <lineItems> correto
	
Cenario: Criar ordem com 2 itens de linha com diferente material
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <DECOMM> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <lineItems> correto
	
Cenario: Criar ordem com 2 itens de linha com diferente material
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <DECOMM>
	E configurar ordem com status PENDING_APPROVAL	 
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <lineItems> correto

Cenario: Criar ordem com 2 itens de linha com mesmo material DPUBLI
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <billingType> correto
	E verifica ordem com campo <lineItems> correto

Cenario: Criar ordem com 2 itens de linha com mesmo material DLINKP
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DLINKP> 
	E criar item de linha
	E configurar item de linha com material <DLINKP> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <billingType> correto
	E verifica ordem com campo <lineItems> correto

Cenario: Criar ordem com 2 itens de linha com mesmo material DECOMM
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DECOMM> 
	E criar item de linha
	E configurar item de linha com material <DECOMM> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <billingType> correto
	E verifica ordem com campo <lineItems> correto

Cenario: Criar ordem com 2 itens de linha com material diferente: DLINKP e DECOMM
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DLINKP> 
	E criar item de linha
	E configurar item de linha com material <DECOMM> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <billingType> correto
	E verifica ordem com campo <lineItems> correto

Cenario: Criar ordem com 2 itens de linha com material diferente: DPUBLI e DECOMM
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <DECOMM> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <billingType> correto
	E verifica ordem com campo <lineItems> correto

Cenario: Criar ordem com 2 itens de linha com material diferente: DPUBLI e DLINKP
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <DLINKP> 
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica ordem com campo <billingType> correto
	E verifica ordem com campo <advertiser> correto
	E verifica ordem com campo <agency> correto
	E verifica ordem com campo <lineItems> correto
	


	
		
	
    