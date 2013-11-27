# language: pt
Funcionalidade: Validar pagamento

  Esquema do Cenario: Caminho perfeito do fluxo de um pagamento  por produtos de assinatura
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se no registro do relatorio consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se no registro do extrato consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se o saldo atual do afiliado  <idtperson> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                                | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtInscription:48416417;idtLabel:23655;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtInscription:48416417;idtLabel:23655;numPaymentOrder:2;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <2>       | <88311915x126057690> | <8,44>           | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:12-09-2013;idtInscription:48416417;idtLabel:23655;numPaymentOrder:3;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <3>       | <88311915x126057690> | <3,37>           | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:13-09-2013;idtInscription:48416417;idtLabel:23655;numPaymentOrder:4;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <4>       | <88311915x126057690> | <3,37>           | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:14-09-2013;idtInscription:48416417;idtLabel:23655;numPaymentOrder:5;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <5>       | <88311915x126057690> | <3,37>           | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:15-09-2013;idtInscription:48416417;idtLabel:23655;numPaymentOrder:6;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <6>       | <88311915x126057690> | <3,37>           | <1>    |
      | <131786276> | <48327214>     | <13>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:16-09-2013;idtInscription:48327214;idtLabel:23655;idtUrl:71045;numPaymentOrder:1;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <1>       | <83839052x121529471> | <10,46>          | <1>    |
      | <131786276> | <48327214>     | <13>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:08-09-2013;idtInscription:48327214;idtLabel:23655;idtUrl:71045;numPaymentOrder:2;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <2>       | <83839052x121529471> | <10,46>          | <1>    |
      | <131786276> | <48327214>     | <13>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:19-09-2013;idtInscription:48327214;idtLabel:23655;idtUrl:71045;numPaymentOrder:3;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <3>       | <83839052x121529471> | <10,46>          | <1>    |

  Esquema do Cenario: Caminho perfeito do fluxo de mais de um pagamento  por produtos de assinatura no mesmo dia
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se no registro do relatorio consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se no registro do extrato consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se o saldo atual do afiliado  <idtperson> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                                | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48327214>     | <13>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:09-09-2013;idtInscription:48423847;idtLabel:23655;idtUrl:71045;numPaymentOrder:1;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <1>       | <83839052x121529471> | <10,46>          | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:15-09-2013;idtInscription:48416417;idtLabel:23655;numPaymentOrder:6;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <6>       | <88311915x126057690> | <3,37>           | <1>    |

  Esquema do Cenario: Caminho perfeito do fluxo de compra por produtos one-shot
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se no registro do relatorio consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se no registro do extrato consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se o saldo atual do afiliado  <idtperson> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name | date         | desgrouping                                                                                       | value    | pay_order | cod_trans                       | commission_value | status |
      | <131786276> | <123>          | <13>       | <Compra> | <10-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;idtUrl:71045;numPaymentOrder:1> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <50,04>          | <1>    |

  Esquema do Cenario: Caminho perfeito do fluxo de compra por produtos one-shot no mesmo dia
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se no registro do relatorio consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se no registro do extrato consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se o saldo atual do afiliado  <idtperson> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name | date         | desgrouping                                                                                       | value | pay_order | cod_trans                       | commission_value | status |
      | <131786276> | <123>          | <13>       | <Compra> | <10-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;idtUrl:71045;numPaymentOrder:1> | <100> | <1>       | <9235454355w24324333aa2432sss4> | <25>             | <1>    |
      | <131786276> | <123>          | <13>       | <Compra> | <10-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;idtUrl:71045;numPaymentOrder:1> | <200> | <1>       | <9235454355w24324333aa2432sss4> | <50>             | <1>    |

  Esquema do Cenario: Inoperabilidade da Fila em eventos de Pagamento
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E a fila estiver inoperavel
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> nao foi processado
    E verificar se no registro do relatorio nao consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se no registro do extrato nao consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se o saldo atual do afiliado  <idtperson> nao foi atualizado com o valor de comissao <commission_value> sobre o valor <value>

    Exemplos: 
      | idtperson  | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                            | value   | pay_order | cod_trans            | commission_value |
      | <11831687> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtInscription:48416417;idtLabel:90000;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <1>       | <88311915x126057690> | <0>              |
      | <11831687> | <48416417>     | <14>       | <Pagamento> | <11-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtInscription:48416417;idtLabel:90000;numPaymentOrder:2;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <2>       | <88311915x126057690> | <8,44>           |

  #Esquema do Cenario: Rollback de um unico registro errado entre varios outros
   # Dado que a fila esteja em execução
   # E que não exista eventos de <event_type>
   # E que <event> eventos sejam gerados , modelo <event_model>
   # Quando o processador de eventos for executado
   # Quando o processador de eventos for executado de forma paralela
   # E a fila estiver inoperavel depois de <milisegundos> milisegundos
   # Entao verificar se a quantidade de eventos de <event_type> processados e menor que <event>

    #Exemplos: 
    #  | event  | event_type  | milisegundos | event_model                                                                                                                                                                                                                                                                                             |
    #  | <100> | <Pagamento> | <1000>         | <{"product":14,"desName":"Pagamento","caf":"e585c84cf88e4d8eaf0892d2b1446f5e","numEventAmount":1,"numValue":16.9,"desGrouping":"codPromotion:PROMOWAV1M099;datSubscription:09-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90"}> |
