# language: pt
Funcionalidade: Testar Servico DAS

  Esquema do Cenario: Verificar persistencia do DAS
    Dado que a variavel <variavel>, codigo <codigoVar>, esteja vazia para a assinatura <idtInscription>
    Quando salvar o valor <valor> na variavel <variavel> para a assinatura <idtInscription>
    Entao verifique se o resultado obtido pela variavel <variavel> para a assinatura <idtInscription> e igual a <valor>

    Exemplos: 
      | variavel         | codigoVar | idtInscription | valor  |
      | <affiliatedInfo> | <8>       | <2037474>      | <FAKE> |

  