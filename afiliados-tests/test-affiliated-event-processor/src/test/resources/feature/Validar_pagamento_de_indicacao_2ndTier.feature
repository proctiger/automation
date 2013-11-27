# language: pt
Funcionalidade: A Validar pagamento de indicacao 2ndTier para o afiliado pai

  Esquema do Cenario: Caminho perfeito do fluxo de um pagamento de indicacao 2ndTier por produtos de assinatura
    Dado que exista um evento de comissionamento com os dados <idtperson_parent>, <des_name>, <cod_trans> e <prd_source>
    E que exista uma relacao de indicacao entre afiliado pai <idtperson_parent> e afiliado filho <idtperson_son>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson_son>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se ha o idtperson do filho <idtperson_son>, e codigo de produto <prd_source> no registro de indicacao na tabela de eventos
    E verificar se no registro de indicacao do relatorio consta o valor de comissao <commission_value> para o afiliado pai <idtperson_parent>
    E verificar se no registro de indicacao do extrato consta o valor de comissao <commission_value> para o afiliado pai <idtperson_parent>
    E verificar se o saldo atual do afiliado pai <idtperson_parent> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson_son | idtperson_parent | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                                | value   | pay_order | cod_trans            | commission_value | status |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <11-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:2;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <2>       | <88311915x126057690> | <0,84>           | <1>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <12-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:12-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:3;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <3>       | <88311915x126057690> | <0,33>           | <1>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <13-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:13-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:4;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <4>       | <88311915x126057690> | <0,33>           | <1>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <15-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:15-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:6;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <6>       | <88311915x126057690> | <0,33>           | <1>    |
      | <131786278>   | <131786276>      | <48327214>     | <13>       | <Pagamento> | <16-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:16-09-2013;idtInscription:48327214;idtLabel:23656;idtUrl:71046;numPaymentOrder:1;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <1>       | <83839052x121529471> | <1,04>           | <1>    |
      | <131786278>   | <131786276>      | <48327214>     | <13>       | <Pagamento> | <08-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:08-09-2013;idtInscription:48327214;idtLabel:23656;idtUrl:71046;numPaymentOrder:2;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <2>       | <83839052x121529471> | <1,04>           | <1>    |
      | <131786278>   | <131786276>      | <48327214>     | <13>       | <Pagamento> | <19-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:19-09-2013;idtInscription:48327214;idtLabel:23656;idtUrl:71046;numPaymentOrder:3;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <3>       | <83839052x121529471> | <1,04>           | <1>    |

  Esquema do Cenario: numPayOrder maior do que o configurado para 2ndTier por produtos de assinatura
    Dado que exista um evento de comissionamento com os dados <idtperson_parent>, <des_name>, <cod_trans> e <prd_source>
    E que exista uma relacao de indicacao entre afiliado pai <idtperson_parent> e afiliado filho <idtperson_son>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson_son>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se no registro do relatorio nao consta o valor de comissao <commission_value> para o afiliado <idtperson_parent>
    E verificar se no registro do extrato nao consta o valor de comissao <commission_value> para o afiliado <idtperson_parent>
    E verificar se o saldo atual do afiliado  <idtperson_parent> nao foi atualizado com o valor de comissao <commission_value> sobre o valor <value>

    Exemplos: 
      | idtperson_son | idtperson_parent | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                                 | value   | pay_order | cod_trans            | commission_value | status |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:15;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <15>      | <88311915x126057690> | <0>              | <4>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <11-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:21;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <21>      | <88311915x126057690> | <0,84>           | <4>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <12-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:12-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:30;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <30>      | <88311915x126057690> | <0,33>           | <4>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <13-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:13-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:15;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <15>      | <88311915x126057690> | <0,33>           | <4>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <15-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:15-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:18;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <18>      | <88311915x126057690> | <0,33>           | <4>    |
      | <131786278>   | <131786276>      | <48327214>     | <13>       | <Pagamento> | <16-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:16-09-2013;idtInscription:48327214;idtLabel:23656;idtUrl:71046;numPaymentOrder:15;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <15>      | <83839052x121529471> | <1,04>           | <4>    |
      | <131786278>   | <131786276>      | <48327214>     | <13>       | <Pagamento> | <08-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:08-09-2013;idtInscription:48327214;idtLabel:23656;idtUrl:71046;numPaymentOrder:26;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <26>      | <83839052x121529471> | <1,04>           | <4>    |
      | <131786278>   | <131786276>      | <48327214>     | <13>       | <Pagamento> | <19-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:19-09-2013;idtInscription:48327214;idtLabel:23656;idtUrl:71046;numPaymentOrder:31;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <31>      | <83839052x121529471> | <1,04>           | <4>    |

  Esquema do Cenario: Caminho perfeito do fluxo de mais de um pagamento de indicacao 2ndTier por produtos de assinatura no mesmo dia
    Dado que exista um evento de comissionamento com os dados <idtperson_parent>, <des_name>, <cod_trans> e <prd_source>
    E que exista uma relacao de indicacao entre afiliado pai <idtperson_parent> e afiliado filho <idtperson_son>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson_son>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se ha o idtperson do filho <idtperson_son>, e codigo de produto <prd_source> no registro de indicacao na tabela de eventos
    E verificar se no registro de indicacao do relatorio consta o valor de comissao <commission_value> para o afiliado pai <idtperson_parent>
    E verificar se no registro de indicacao do extrato consta o valor de comissao <commission_value> para o afiliado pai <idtperson_parent>
    E verificar se o saldo atual do afiliado pai <idtperson_parent> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson_son | idtperson_parent | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                                | value   | pay_order | cod_trans            | commission_value | status |
      | <131786278>   | <131786276>      | <48327214>     | <13>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:09-09-2013;idtInscription:48423847;idtLabel:23656;idtUrl:71046;numPaymentOrder:1;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <1>       | <83839052x121529471> | <1,04>           | <1>    |
      | <131786278>   | <131786276>      | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:09-09-2013;idtInscription:48416417;idtLabel:23656;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90>     | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |

  Esquema do Cenario: Caminho perfeito do fluxo de um pagamento de indicacao 2ndTier por produtos one-shot
    Dado que exista um evento de comissionamento com os dados <idtperson_parent>, <des_name>, <cod_trans> e <prd_source>
    E que exista uma relacao de indicacao entre afiliado pai <idtperson_parent> e afiliado filho <idtperson_son>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson_son>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se ha o idtperson do filho <idtperson_son>, e codigo de produto <prd_source> no registro de indicacao na tabela de eventos
    E verificar se no registro de indicacao do relatorio consta o valor de comissao <commission_value> para o afiliado pai <idtperson_parent>
    E verificar se no registro de indicacao do extrato consta o valor de comissao <commission_value> para o afiliado pai <idtperson_parent>
    E verificar se o saldo atual do afiliado pai <idtperson_parent> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson_son | idtperson_parent | idtInscription | prd_source | des_name | date         | desgrouping                                                                                       | value    | pay_order | cod_trans                       | commission_value | status |
      | <131786278>   | <131786276>      | <123>          | <13>       | <Compra> | <10-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;idtUrl:71046;numPaymentOrder:1> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <5>              | <1>    |

  Esquema do Cenario: Caminho perfeito do fluxo de mais de um pagamento de indicacao 2ndTier por produtos de one-shot no mesmo dia
    Dado que exista um evento de comissionamento com os dados <idtperson_parent>, <des_name>, <cod_trans> e <prd_source>
    E que exista uma relacao de indicacao entre afiliado pai <idtperson_parent> e afiliado filho <idtperson_son>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson_son>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se ha o idtperson do filho <idtperson_son>, e codigo de produto <prd_source> no registro de indicacao na tabela de eventos
    E verificar se no registro de indicacao do relatorio consta o valor de comissao <commission_value> para o afiliado pai <idtperson_parent>
    E verificar se no registro de indicacao do extrato consta o valor de comissao <commission_value> para o afiliado pai <idtperson_parent>
    E verificar se o saldo atual do afiliado pai <idtperson_parent> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson_son | idtperson_parent | idtInscription | prd_source | des_name | date         | desgrouping                                                                                       | value    | pay_order | cod_trans                       | commission_value | status |
      | <131786278>   | <131786276>      | <123>          | <13>       | <Compra> | <10-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;idtUrl:71046;numPaymentOrder:1> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <5>              | <1>    |
      | <131786278>   | <131786276>      | <123>          | <13>       | <Compra> | <10-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;idtUrl:71046;numPaymentOrder:1> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <5>              | <1>    |

  Esquema do Cenario: Validar que afiliado pai não será processado se não tiver a url de afiliados e o filho será
    Dado que exista um evento de comissionamento com os dados <idtperson_parent>, <des_name>, <cod_trans> e <prd_source>
    E que exista uma relacao de indicacao entre afiliado pai <idtperson_parent> e afiliado filho <idtperson_son>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson_son>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se o evento de indicacao nao foi processado e o de pagamento foi com  o codigo da transacao <cod_trans>

    Exemplos: 
      | idtperson_son | idtperson_parent | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                   | value   | pay_order | cod_trans            | commission_value |
      | <131694745>   | <131694746>      | <48327214>     | <13>       | <Pagamento> | <10-09-2013> | <codPromotion:PROMOCONAVL;datSubscription:09-09-2013;idtInscription:48327214;idtLabel:22948;numPaymentOrder:1;offerDescription:UOL CURSOS ONLINE FIT R$34,90> | <34,90> | <1>       | <83839052x121529471> | <1,04>           |
