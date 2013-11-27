# language: pt
Funcionalidade: Consulta de Ordem para Aprovacao
  Testes relacionados ao servico de consulta de ordem DFP - GET /approval/order

  Cenario: Criar ordem DRAFT com 1 item de linha
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <advertiser> correto
    E verifica ordem com campo <agency> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem PENDING FOR APPROVAL com 1 item de linha
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem PENDING FOR APPROVAL com 2 itens de linha com mesmo material
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <lineItems> correto

  Cenario: Consultar ordem com 1 item de linha dlink
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DLINKP>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <lineItems> correto

  Cenario: Consultar ordem com 2 itens de linha com material diferentes
    Dado criar ordem
    E criar agencia <UOLB> existente no SAP
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DECOMM>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com mesmo material
    Dado criar ordem
    E criar agencia <UOLB> existente no SAP
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DECOMM>
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com mesmo material
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com diferente material
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DECOMM>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com diferente material
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DECOMM>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 15
    Dado criar ordem
    E configurar ordem com billingTypeId <15>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 4
    Dado criar ordem
    E configurar ordem com billingTypeId <4>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 5
    Dado criar ordem
    E configurar ordem com billingTypeId <5>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 6
    Dado criar ordem
    E configurar ordem com billingTypeId <6>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 7
    Dado criar ordem
    E configurar ordem com billingTypeId <7>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 8
    Dado criar ordem
    E configurar ordem com billingTypeId <8>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 9
    Dado criar ordem
    E configurar ordem com billingTypeId <9>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 10
    Dado criar ordem
    E configurar ordem com billingTypeId <10>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 11
    Dado criar ordem
    E configurar ordem com billingTypeId <11>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 12
    Dado criar ordem
    E configurar ordem com billingTypeId <12>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 13
    Dado criar ordem
    E configurar ordem com billingTypeId <13>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com Billing Type 14
    Dado criar ordem
    E configurar ordem com billingTypeId <14>
    E criar item de linha
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com mesmo material DPUBLI
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com mesmo material DLINKP
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DLINKP>
    E criar item de linha
    E configurar item de linha com material <DLINKP>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com mesmo material DECOMM
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DECOMM>
    E criar item de linha
    E configurar item de linha com material <DECOMM>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com material diferente: DLINKP e DECOMM
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DLINKP>
    E criar item de linha
    E configurar item de linha com material <DECOMM>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com material diferente: DPUBLI e DECOMM
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DECOMM>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <lineItems> correto

  Cenario: Criar ordem com 2 itens de linha com material diferente: DPUBLI e DLINKP
    Dado criar ordem
    E criar item de linha
    E configurar item de linha com material <DPUBLI>
    E criar item de linha
    E configurar item de linha com material <DLINKP>
    E configurar ordem com status PENDING_APPROVAL
    Quando solicitar servico de consulta de ordem para aprovacao
    Entao retorna status HTTP igual a <200>
    E verifica ordem com campo <billingType> correto
    E verifica ordem com campo <advertiser> correto
    E verifica ordem com campo <agency> correto
    E verifica ordem com campo <lineItems> correto
