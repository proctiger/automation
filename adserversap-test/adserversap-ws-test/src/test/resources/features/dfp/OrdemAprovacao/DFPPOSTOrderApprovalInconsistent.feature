# language: pt
Funcionalidade: Aprovacao de Ordem com Inconsistencias
  Testes relacionados ao servico de aprovacao de ordem DFP - POST
	    
 Cenario: Aporte invalido: ordem com status diferente de Pending for Approval
	Dado criar ordem
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
	
 Cenario: Aporte invalido: ordem arquivada
	Dado criar ordem
	E configurar ordem como arquivada 
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
 
 Cenario: Aporte invalido: ordem sem LineItem
	Dado criar ordem sem item de linha
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
    
  Cenario: Aporte invalido: ordem com todos line itens arquivados
	Dado criar ordem
	E criar item de linha
	E configurar item de linha como arquivada
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
	    	    
  Cenario: Aporte invalido: ordem com Advertiser sem externalId
	Dado criar ordem 
	E criar advertiser advertiser nao existente no SAP
	E configurar advertiser com external id<"">
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
    	
   Cenario: Aporte invalido: ordem com Advertiser nao existente no SAP
	Dado criar ordem
	E criar advertiser nao existente no SAP
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
 
  Cenario: Aporte invalido: ordem com Agencia de externalId nao existente no SAP
	Dado criar ordem 
	E criar agencia nao existente no SAP
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
     
  Cenario: Aporte invalido: ordem sem Billing Type configurado
	Dado criar ordem sem billingTypeId
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
     
  Cenario: Aporte invalido: ordem sem Empresa
	Dado criar ordem 
	E configurar ordem com empresa <VAZIA>
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
	    
  Cenario: Aporte invalido: ordem com Empresa inválida
	Dado criar ordem
	E configurar ordem com empresa <INVALIDA>
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
	    
  Cenario: Aporte invalido: ordem free com 100% de desconto PERCENTUAL
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com desconto <PERCENTUAL>
	E configurar item de linha com desconto total
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E retorna inconsistencia <inconsistent.order.for.free>	

  Cenario: Aporte invalido: ordem free com 100% de desconto ABSOLUTO
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com desconto <ABSOLUTO>
	E configurar item de linha com desconto total
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval

  Cenario: Aporte invalido: ordem free com item de linha com custo nulo
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com valor <0>
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval

  Cenario: Aporte invalido: ordem free com todos item de linha arquivados
	Dado criar ordem
	E criar item de linha
	E configurar item de linha como arquivada
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval

  Cenario: Aporte invalido: ordem com Material do SAP Invalido
	Dado criar ordem
	E criar item de linha 
	E configurar item de linha com material <INVALIDO>
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval
	    
  Cenario: Aporte invalido: ordem MAIS de um Item de Linha: um com Material outro Invalido
	Dado criar ordem
	E criar item de linha 
	E configurar item de linha com material <DPUBLI>
	E criar item de linha 
	E configurar item de linha com material <INVALIDO>
    Quando solicitar servico de aprovacao de ordem Post com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do post for approval

     
