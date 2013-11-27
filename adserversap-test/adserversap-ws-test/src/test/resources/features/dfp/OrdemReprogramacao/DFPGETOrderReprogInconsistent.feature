# language: pt
Funcionalidade: Consulta de Ordem para Reprogramacao com Inconsistencias
  Testes relacionados ao servico de consulta de ordem de reprogramacao DFP - GET

 Cenario: Consultar ordem com PONumber NAO existente para nenhuma empresa
  	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para reprogramacao informando PoNumber nao existente
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.reprogramming.ponumber.not.exists>

  Cenario: Consultar ordem sem Empresa
	Dado criar ordem de reprogramacao 
	E criar item de linha
	E configurar ordem com empresa <VAZIA>
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.invalid.enterprise>	

 Cenario: Consultar ordem com status diferente de Pending for Approval 
	Dado criar ordem de reprogramacao
	E configurar ordem com Ponumber
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.invalid.status.for.reprogramming>

 Cenario: Consultar ordem que já possui PONumber 
	Dado criar ordem de reprogramacao
	E configurar ordem com Ponumber
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para reprogramacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.with.ponumber>
	
 Cenario: Consultar ordem arquivada
	Dado criar ordem de reprogramacao
	E configurar ordem como arquivada 
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.archived>    
 
 Cenario: Consultar ordem sem LineItem
	Dado criar ordem de reprogramacao sem item de linha
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.without.line.items>
    
  Cenario: Consultar ordem com todos item de linha arquivados
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha como arquivada
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.without.line.items>

  Cenario: Consultar ordem com Agencia de externalId nao existente no SAP
	Dado criar ordem de reprogramacao 
	E criar agencia nao existente no SAP
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.sap.agency.not.found>    

  Cenario: Consultar ordem free com todos item de linha arquivados
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha como arquivada
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.without.line.items>

  Cenario: Consultar ordem com Material do SAP Invalido
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <INVALIDO>
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.invalid.sap.product>
	    
  Cenario: Consultar ordem MAIS de um Item de Linha: um com Material outro Invalido
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E criar item de linha
	E configurar item de linha com material <INVALIDO>
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.invalid.sap.product>

  Cenario: Consultar ordem com faturamento inválido
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar ordem com billingTypeId <5>
    Quando solicitar servico de consulta de ordem para reprogramacao
    Entao retorna status HTTP igual a <200>
    E verifica inconsistencia <inconsistent.order.not.reprogramming>
    