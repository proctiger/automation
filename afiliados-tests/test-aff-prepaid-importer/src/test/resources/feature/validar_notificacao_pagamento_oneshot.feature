# language: pt
Funcionalidade: Testar notificacao One-Shot do Account Collector

  Esquema do Cenario: Recebimento de um pagamento One-Shot passando o id do site
    Dado que o id da aplicacao no servico de configuracao remota seja alterado para <applicationId>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados <dadosDePagamento> com data e horario atual
    E que seja simulado o envio da hash <hash> pelo servico do billing com os dados <dadosDePagamento>
    Entao validar que os dados <dadosDePagamento> estejam persistidos na tabela de eventos

    Exemplos: 
      | applicationId | infos            | dadosDePagamento                                                                                                                                              | hash                                   | prd_source |
      | <155>         | <13;83261;26592> | <{"idtTransaction":"3651978","idtInscriptionAccount":"50928278","numPaymentReceipt":"1","idtApplication":"141","numPaymentValue":"2.00","datTransaction":""}> | <0000013f-a528-1088-0000-013fa6593d81> | <13>       |

  Esquema do Cenario: Recebimento de um pagamento não One-Shot
    Dado que o id da aplicacao no servico de configuracao remota seja alterado para <applicationId>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados <dadosDePagamento> com data e horario atual
    E que seja simulado o envio da hash <hash> pelo servico do billing com os dados <dadosDePagamento>
    Entao validar que os dados <dadosDePagamento> não estejam persistidos na tabela de eventos

    Exemplos: 
      | applicationId | infos            | dadosDePagamento                                                                                                                                              | hash                                   | prd_source |
      | <155>         | <13;83261;26592> | <{"idtTransaction":"3651978","idtInscriptionAccount":"50928278","numPaymentReceipt":"1","idtApplication":"141","numPaymentValue":"2.00","datTransaction":""}> | <0000013f-a528-1088-0000-013fa6593d81> | <13>       |
