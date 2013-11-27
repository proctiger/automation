#language: pt
Funcionalidade: Aprovacao de Ordem com Sucesso
  Testes relacionados ao servico de aprovacao de ordem DFP - POST

Cenario: Aporte valido: Ordem com item de linha <CPC>
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com custo <CPC>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica item de linha com campo <costType> correto
 
 Cenario: Aporte valido: Ordem com item de linha <CPM>
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com custo <CPM>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica item de linha com campo <costType> correto
       
Cenario: Aporte valido: Ordem com item de linha <CPD>
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com custo <CPD>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica item de linha com campo <costType> correto
    
Cenario: Aporte Valido: Ordem para empresa BOLB
	Dado criar ordem
	E criar item de linha
	E configurar ordem com empresa <BOLB>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso

Cenario: Aporte valido: Ordem com 2 itens de linha com valores diferentes / mesmo material SAP
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica item de linha com campo <material> correto
    E verifica aporte da ordem com sucesso

Cenario: Aporte valido: Ordem com item de linha com material igual a DPUBLI
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E configurar ordem com status PENDING_APPROVAL 
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <material> correto

Cenario: Aporte valido: Ordem com item de linha com material igual a DECOMM
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com material <DECOMM>
	E configurar ordem com status PENDING_APPROVAL 
	Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <material> correto

Cenario: Aporte valido: Ordem com item de linha com material igual a DLINKP
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com material <DLINKP>
	E configurar ordem com status PENDING_APPROVAL	 
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <material> correto

Cenario: Aporte Valido: ordem com sucesso
	Dado criar ordem
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso

Cenario: Aporte Valido: Ordem para empresa UOLB
   Dado criar ordem
   E criar item de linha
   E configurar ordem com empresa <UOLB>
   E configurar ordem com status PENDING_APPROVAL
   Quando solicitar servico de aprovacao de ordem
   Entao retorna status HTTP igual a <200>
   E verifica ordem com campo ponumber
   E verifica status da ordem no DFP igual a <Approved>
   E verifica aporte da ordem com sucesso

Cenario: Aporte valido: Ordem com Faturamento “Agencia – Imediato Interconexao [14]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <14>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto

Cenario: Aporte valido: Ordem com Faturamento “Agencia – Imediato Parcelamento [10]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <10>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto

Cenario: Aporte valido: Ordem com Faturamento “Agencia – Imediato[9]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <9>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto
    
Cenario: Aporte valido: Ordem com Faturamento “Agencia – Permuta Boa[12]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <12>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto

Cenario: Aporte valido: Ordem com Faturamento “Agencia – Permuta Ruim[13]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <13>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto

Cenario: Aporte valido: Ordem com Faturamento “Agencia – Utilizacao[11]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <11>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto

Cenario: Aporte valido: Ordem com Faturamento “Anunciante – Imediato Interconexao [8]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <8>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto
    
Cenario: Aporte valido: Ordem com Faturamento “Anunciante – Imediato Parcelamento [4]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <4>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto
    
Cenario: Aporte valido: Ordem com Faturamento “Anunciante – Imediato[3]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <3>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto
     
Cenario: Aporte valido: Ordem com Faturamento “Anunciante – Permuta Boa[6]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <6>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto
    
Cenario: Aporte valido: Ordem com Faturamento “Anunciante – Permuta Ruim[7]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <7>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto
    
Cenario: Aporte valido: Ordem com Faturamento “Anunciante – Utilizacao[5]”
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <5>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica ordem com campo <paymentCondition> correto
    
Cenario: Aporte valido: Ordem com 2 itens de linha com material diferentes
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com material <DPUBLI>
	E criar item de linha
	E configurar item de linha com material <DLINKP>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica item de linha com campo <material> correto
    E verifica aporte da ordem com sucesso

Cenario: Aporte valido: somente com Advertiser existente no SAP. Sem agencia informada
	Dado criar ordem
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica aporte da ordem com sucesso

Cenario: Aporte valido: Ordem com item de linha <CPC>
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com custo <CPC>
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso
    E verifica valores de sumarizacao da ordem
    E verifica ordem com campo <paymentCondition> correto

Cenario: Aporte valido: Ordem com 2 itens de linha 1 Draft e 1 Archieved
	Dado criar ordem
	E criar item de linha 
	E criar item de linha 
	E configurar item de linha como arquivada
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica valores de sumarizacao da ordem
    E verifica aporte da ordem com sucesso


    