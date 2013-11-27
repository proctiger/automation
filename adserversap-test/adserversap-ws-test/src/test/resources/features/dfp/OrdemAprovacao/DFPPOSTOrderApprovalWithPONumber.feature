#language: pt
Funcionalidade: Aprovacao de Ordem com Sucesso com Id e PONumber
  Testes relacionados ao servico de aprovacao de ordem DFP - POST

Cenario: Aprovar Ordem <UOLB> informando PoNumber
	Dado criar ordem para empresa <UOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
	E aprovar ordem
	E criar ordem para empresa <BOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem pelo PONUmber com sucesso
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso

Cenario: Aprovar Ordem <BOLB> informando PoNumber
	Dado criar ordem para empresa <BOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
	E aprovar ordem
	E criar ordem para empresa <UOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem pelo PONUmber com sucesso
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo ponumber
    E verifica status da ordem no DFP igual a <Approved>
    E verifica aporte da ordem com sucesso


Cenario: Inconsistencia: Aprovar ordem <UOLB> com PONumber NAO existente para nenhuma empresa
	Dado criar ordem para empresa <UOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem pelo PONUmber com inconsistencia: PONUmber <INEXISTENTE>
    Entao retorna status HTTP igual a <406>
	E verifica inconsistencia do post for approval
	
Cenario: Inconsistencia: Aprovar ordem <BOLB> com PONumber NAO existente para nenhuma empresa
	Dado criar ordem para empresa <BOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem pelo PONUmber com inconsistencia: PONUmber <INEXISTENTE>
    Entao retorna status HTTP igual a <406>
	E verifica inconsistencia do post for approval	

Cenario: Inconsistencia: Aprovar ordem <UOLB> com PONumber existente para <mesma> empresa
	Dado criar ordem para empresa <UOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
	E aprovar ordem
	E criar ordem para empresa <UOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem pelo PONUmber com inconsistencia: PONUmber <EXISTENTE>
    Entao retorna status HTTP igual a <406>
	E verifica inconsistencia do post for approval	
    
Cenario: Inconsistencia: Aprovar ordem <BOLB> com PONumber existente para <mesma> empresa
	Dado criar ordem para empresa <BOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
	E aprovar ordem
	E criar ordem para empresa <BOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de aprovacao de ordem pelo PONUmber com inconsistencia: PONUmber <EXISTENTE>
    Entao retorna status HTTP igual a <406>
	E verifica inconsistencia do post for approval	
	
### implementar testes com as inconsistencias de aprovação de ordem 	
Cenario: Inconsistencia: Aprovar ordem <UOLB> com PONumber com status inválido
	Dado criar ordem para empresa <UOLB>
	E criar item de linha
	E configurar ordem com status PENDING_APPROVAL
	E aprovar ordem
	E criar ordem para empresa <BOLB>
	E criar item de linha
    Quando solicitar servico de aprovacao de ordem pelo PONUmber com inconsistencia: PONUmber <EXISTENTE>
    Entao retorna status HTTP igual a <406>
	E verifica inconsistencia do post for approval			

