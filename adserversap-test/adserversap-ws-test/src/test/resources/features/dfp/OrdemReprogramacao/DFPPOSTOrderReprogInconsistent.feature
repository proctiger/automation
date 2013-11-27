# language: pt
Funcionalidade: Reprogramacao de Ordem com Inconsistencias
  Testes relacionados ao servico de consulta de ordem de reprogramacao DFP - GET

 Cenario: Reprogramar ordem com status diferente de Pending for Approval 
	Dado criar ordem de reprogramacao
	E configurar ordem com Ponumber
	Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao

 Cenario: Reprogramar ordem que já possui PONumber 
	Dado criar ordem de reprogramacao
	E configurar ordem com Ponumber
	Quando solicitar servico de reprogramacao de ordem com inconsistencia
	Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao
	
 Cenario: Reprogramar ordem arquivada
	Dado criar ordem de reprogramacao
	E configurar ordem como arquivada 
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao
 
 Cenario: Reprogramar ordem sem LineItem
	Dado criar ordem de reprogramacao sem item de linha
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao
    
  Cenario: Reprogramar ordem com todos item de linha arquivados
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha como arquivada
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao

  Cenario: Reprogramar ordem com Agencia de externalId nao existente no SAP
	Dado criar ordem de reprogramacao 
	E criar agencia nao existente no SAP
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao
	
  Cenario: Reprogramar ordem sem Empresa
	Dado criar ordem de reprogramacao 
	E configurar ordem com empresa <VAZIA>
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao

  Cenario: Reprogramar ordem free com todos item de linha arquivados
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha como arquivada
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao

  Cenario: Reprogramar ordem com Material do SAP Invalido
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <INVALIDO>
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao
	    
  Cenario: Reprogramar ordem MAIS de um Item de Linha: um com Material outro Invalido
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E criar item de linha
	E configurar item de linha com material <INVALIDO>
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao

  Cenario: Reprogramar ordem com PONumber NAO existente para nenhuma empresa
  	Dado criar ordem de reprogramacao
	E criar item de linha
	E criar item de linha
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao

  Cenario: Reprogramar ordem com faturamento inválido
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar ordem com billingTypeId <5>
    Quando solicitar servico de reprogramacao de ordem com inconsistencia
    Entao retorna status HTTP igual a <406>
    E verifica inconsistencia do POST para reprogramacao
