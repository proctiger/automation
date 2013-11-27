#language: pt
Funcionalidade: Reprogramacao de Ordem com Sucesso
  Testes relacionados ao servico de aprovacao de ordem DFP - POST

Cenario: Reprogramacao com Sucesso: Ordem com item de linha <CPC>
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com custo <CPC>
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    E verifica item de linha com campo <costType> correto
	
Cenario: Reprogramacao com Sucesso: Ordem com item de linha <CPM>
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com custo <CPM>
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    E verifica item de linha com campo <costType> correto

Cenario: Reprogramacao com Sucesso: Ordem com item de linha <CPD>
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com custo <CPD>
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    E verifica item de linha com campo <costType> correto
	
	
Cenario: Reprogramacao com Sucesso: Empresa <UOLB>
	Dado criar ordem de reprogramacao
	E configurar ordem com empresa <UOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    
Cenario: Reprogramacao com Sucesso: Empresa <BOLB>
	Dado criar ordem de reprogramacao
	E configurar ordem com empresa <BOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    
Cenario: Reprogramacao com Sucesso: Ordem com item de linha com material <DPUBLI>
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    E verifica ordem com campo <material> correto   
   
Cenario: Reprogramacao com Sucesso: Ordem com item de linha com material <DECOMM>
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    E verifica ordem com campo <material> correto   
    
Cenario: Reprogramacao com Sucesso: Ordem com item de linha com material <DLINKP>
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    E verifica ordem com campo <material> correto         
    
Cenario: Reprogramacao com Sucesso: Ordem com mais de 1 item de linha com material <DPUBLI>
	Dado criar ordem de reprogramacao
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    E verifica ordem com campo <material> correto         
    
Cenario: Reprogramacao com Sucesso: Ordem com item de linha arquivado
	Dado criar ordem de reprogramacao
	E criar item de linha
	E criar item de linha
	E configurar item de linha como arquivada
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de reprogramacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica status da ordem no DFP igual a <Approved>
    E verifica ordem com campo <material> correto    
 
 
