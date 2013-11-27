# language: pt
Funcionalidade: Tester tratamento da notificacao

  Esquema do Cenario: Recebimento de um pagamento - Cenario perfeito com idturl
    Dado que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source> #verificar se a hash + prd_source, se existir, excluir e colocar o novo
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados <dadosDePagamento> com data e horario atual
    E que seja simulado o envio da hash <hash> pelo servico do billing
    Entao validar que os dados <dadosDePagamento> estejam persistidos na tabela de eventos

    Exemplos: 
      | applicationId | infos          | dadosDePagamento                                                                                                                                             | hash                                 | prd_source |
      | 141           | 13;83261;26592 | <{"idtTransaction":"1393174","idtInscriptionAccount":"50928278","numPaymentReceipt":"1","idtApplication":"155","numPaymentValue":"2.00","datTransaction":""]> | HAGASUHUSAHUSAGDUFASUGIKH           | 13         |

  Esquema do Cenario: Recebimento de um pagamento - Cenario perfeito com caf
    Dado que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source> #verificar se a hash + prd_source, se existir, excluir e colocar o novo
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados <dadosDePagamento> com data e horario atual
    E que seja simulado o envio da hash <hash> pelo servico do billing
    Entao validar que os dados <dadosDePagamento> estejam persistidos na tabela de eventos

    Exemplos: 
      | applicationId | infos                                     | dadosDePagamento                                                                                                                                             | hash                                 | prd_source | dataHora              |
      | 141           | 13;72579b9ae7f844fc9b9ded4b62d3e085;26592 | <{"idtTransaction":"1393174","idtInscriptionAccount":"50928278","numPaymentReceipt":"1","idtApplication":"155","numPaymentValue":"2.00","datTransaction":""] | HAGASUHUSAHUSAGDUFASUGIKH            | 13         | 2013-08-07 15:47:10.0 |

  Esquema do Cenario: Recebimento de um pagamento sem informacoes no Redis
    Dado que no servidor de sessao nao exista as informacoes de afiliados de hash <hash> e codigo de produto <prd_source>
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados <dadosDePagamento> com data e horario atual
    E que seja simulado o envio da hash <hash> pelo servico do billing
    Entao validar que os dados <dadosDePagamento> nao estejam persistidos na tabela de eventos

    Exemplos: 
      | applicationId | dadosDePagamento                                                                                                                                             | hash                                 | prd_source | dataHora              |
      | 141           | <{"idtTransaction":"1393174","idtInscriptionAccount":"50928278","numPaymentReceipt":"1","idtApplication":"155","numPaymentValue":"2.00","datTransaction":""] | HAGASUHUSAHUSAGDUFASUGIKH            | 13         | 2013-08-07 15:47:10.0 |

  Esquema do Cenario: Recebimento de um pagamento sem informacoes de afiliados (hash)
    Dado que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de pagamento com os dados <dadosDePagamento> com data e horario atual
    E que seja simulado o envio da hash <hash> pelo servico do billing
    Entao validar que os dados <dadosDePagamento> nao estejam persistidos na tabela de eventos

    Exemplos: 
      | applicationId | infos          | dadosDePagamento                                                                                                                                             | hash                                 | prd_source | dataHora              |
      | 141           | 13;83261;26592 | <{"idtTransaction":"1393174","idtInscriptionAccount":"50928278","numPaymentReceipt":"1","idtApplication":"141","numPaymentValue":"2.00","datTransaction":""] | HAGASUHUSAHUSAGDUFASUGIKH            | 13         | 2013-08-07 15:47:10.0 |
