# language: pt
Funcionalidade: Consulta de Ordem para Aprovacao com Inconsistencias
  Testes relacionados ao servico de consulta de ordem DFP - GET


Cenario: Inconsistencia: ordem com advertiser invalida no SAP <inconsistent.order.sap.advertiser.not.found>
	Dado criar ordem com anunciante invalido
	E criar item de linha
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.sap.advertiser.not.found>

Cenario: Inconsistencia: ordem com status diferente de Pending for Approval <inconsistent.invalid.status.for.approving>
	Dado criar ordem
	E criar item de linha
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.invalid.status.for.approving>

Cenario: Inconsistencia: ordem que já possui PO NUmber <inconsistent.order.with.ponumber>
	Dado criar ordem
	E configurar ordem com Ponumber
	E criar item de linha
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.with.ponumber>

Cenario: Inconsistencia: ordem com desconto total mas faturamento diferente de bonificação <inconsistent.order.for.free>
	Dado criar ordem
	E configurar ordem com billingTypeId <7>
	E configurar item de linha com desconto total 
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.for.free>

Cenario: Inconsistencia: ordem com mais de um item de linha um com Material outro sem <inconsistent.order.invalid.sap.product>
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com material <DPUBLI> 
	E criar item de linha
	E configurar item de linha com material <INVALIDO>
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.invalid.sap.product>

Cenario: Inconsistencia: ordem com item de linha com Material invalido <inconsistent.order.invalid.sap.product>
	Dado criar ordem
	E criar item de linha
	E configurar item de linha com material <INVALIDO>
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.invalid.sap.product>

Cenario: Inconsistencia: ordem sem empresa configurada <inconsistent.order.invalid.enterprise>
	Dado criar ordem
	E criar item de linha
	E configurar ordem com empresa <VAZIA>
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.invalid.enterprise>

Cenario: Inconsistencia: ordem com billingType invalido <inconsistent.order.invalid.billing.type>
	Dado criar ordem
	E criar item de linha
	E configurar ordem com billingTypeId <VAZIO>
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.invalid.billing.type>

Cenario: Inconsistencia: ordem com agencia invalida no SAP <inconsistent.order.sap.agency.not.found>
	Dado criar ordem
	E criar item de linha
	E criar agencia nao existente no SAP
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.sap.agency.not.found>

Cenario: Inconsistencia: ordem com advertiser invalida no SAP <inconsistent.order.sap.advertiser.not.found>
	Dado criar ordem
	E configurar advertiser com external id <000>
	E criar item de linha
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.sap.advertiser.not.found>

Cenario: Inconsistencia: ordem arquivada <inconsistent.order.archived>
	Dado criar ordem
	E criar item de linha
	E configurar ordem como arquivada
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.archived>
	E verifica inconsistencia <inconsistent.invalid.status.for.approving>

Cenario: Inconsistencia: ordem com item de linha arquivado <inconsistent.order.for.free>
	Dado criar ordem
	E criar item de linha
	E configurar item de linha como arquivada
	E configurar ordem com status PENDING_APPROVAL
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.for.free>
	E verifica inconsistencia <inconsistent.order.without.line.items>

Cenario: Inconsistencia: ordem sem item de linha configurado <inconsistent.order.without.line.items>
	Dado criar ordem
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.without.line.items>
	E verifica inconsistencia <inconsistent.invalid.status.for.approving>
	E verifica inconsistencia <inconsistent.order.for.free>	
	
Cenario: Inconsistencia: ordem arquivada <inconsistent.order.archived>
	Dado criar ordem
	E criar item de linha
	E configurar ordem como arquivada
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.archived>

Cenario: Inconsistencia: ordem com desconto parcial sem agencia configurada <inconsistent.order.with.discount.without.agency>
	Dado criar ordem sem agencia
	E criar item de linha
	E configurar item de linha com desconto parcial 
	Quando solicitar servico de consulta de ordem para aprovacao
	Entao retorna status HTTP igual a <200>
	E verifica inconsistencia <inconsistent.order.with.discount.without.agency>



#
#Cenario: Consultar ordem com PONumber NAO existente para nenhuma empresa
#	Dado conexao ao DFP estabelecida
#	E criar ordem
#	Quando solicitar servico de consulta de ordem para aprovacao pelo PONumber <INEXISTENTE>
#	Entao retorna status HTTP igual a <200>
#	E retorna dados da ordem
#	E retorna inconsistencia <inconsistent.order.approval.ponumber.not.exists>
#
#Cenario: Consultar ordem com PONumber existente para mesma empresa UOLB
#	Dado conexao ao DFP estabelecida
#	E ordem empresa <UOLB> aprovada
#	E criar ordem
#	E configurar ordem com empresa <UOLB>
#	Quando solicitar servico de consulta de ordem para aprovacao pelo PONumber <EXISTENTE>
#	Entao retorna status HTTP igual a <200>
#	E retorna dados da ordem
#	E retorna inconsistencia <inconsistent.order.approval.ponumber.in.use>
#
#Cenario: Consultar ordem com PONumber existente para mesma empresa BOLB
#	Dado conexao ao DFP estabelecida
#	E ordem empresa <BOLB> aprovada
#	E criar ordem
#	E configurar ordem com empresa <BOLB>
#	Quando solicitar servico de consulta de ordem para aprovacao pelo PONumber <EXISTENTE>
#	Entao retorna status HTTP igual a <200>
#	E retorna dados da ordem
#	E retorna inconsistencia <inconsistent.order.approval.ponumber.in.use>