# language: pt
Funcionalidade: Validar agrupadores e status

  Esquema do Cenario: Agrupadores de evento de Pagamento sem codPromotion
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro nao esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                              | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <10-09-2013> | <datSubscription:10-09-2013;idtInscription:48416417;idtLabel:23655;idtUrl:71048;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <1>       | <88311915x126057690> | <0>              | <4>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <11-09-2013> | <datSubscription:10-09-2013;idtInscription:48416417;idtLabel:23655;idtUrl:71048;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <2>       | <88311915x126057690> | <8,44>           | <4>    |

  Esquema do Cenario: Agrupadores de evento de Pagamento sem datSubscription
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                              | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <13-09-2013> | <codPromotion:PROMOWAV1M099;idtInscription:48416417;idtLabel:23655;idtUrl:71048;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <14-09-2013> | <codPromotion:PROMOWAV1M099;idtInscription:48416417;idtLabel:23655;idtUrl:71048;numPaymentOrder:2;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <2>       | <88311915x126057690> | <8,44>           | <1>    |

  Esquema do Cenario: Agrupadores de evento de Pagamento sem idtInscription
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                 | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <15-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtLabel:23655;idtUrl:71048;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <16-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtLabel:23655;idtUrl:71048;numPaymentOrder:2;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <2>       | <88311915x126057690> | <8,44>           | <1>    |

  Esquema do Cenario: Agrupadores de evento de Pagamento sem idtLabel
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                  | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <17-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtUrl:71048;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <18-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtUrl:71048;numPaymentOrder:2;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <2>       | <88311915x126057690> | <8,44>           | <1>    |

  Esquema do Cenario: Agrupadores de evento de Pagamento sem idtUrl
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                    | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <19-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtLabel:23655;numPaymentOrder:1;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <20-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtLabel:23655;numPaymentOrder:2;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <2>       | <88311915x126057690> | <8,44>           | <1>    |

  Esquema do Cenario: Agrupadores de evento de Pagamento sem numPaymentOrder
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                                                       | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <21-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtInscription:48416417;idtLabel:23655;idtUrl:71048;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <22-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtInscription:48416417;idtLabel:23655;idtUrl:71048;offerDescription:UOL ANTIVIRUS (1M R$0,99) NA R$16,90> | <16,90> | <2>       | <88311915x126057690> | <8,44>           | <1>    |

  Esquema do Cenario: Agrupadores de evento de Pagamento sem offerDescription
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name    | date         | desgrouping                                                                                                                   | value   | pay_order | cod_trans            | commission_value | status |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <23-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:10-09-2013;idtInscription:48416417;idtLabel:23655;idtUrl:71048;numPaymentOrder:1> | <16,90> | <1>       | <88311915x126057690> | <0>              | <1>    |
      | <131786276> | <48416417>     | <14>       | <Pagamento> | <24-09-2013> | <codPromotion:PROMOWAV1M099;datSubscription:11-09-2013;idtInscription:48416417;idtLabel:23655;idtUrl:71048;numPaymentOrder:2> | <16,90> | <2>       | <88311915x126057690> | <8,44>           | <1>    |

  Esquema do Cenario: Agrupadores de evento de Compra sem offerDescription
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name | date         | desgrouping                                                 | value    | pay_order | cod_trans                       | commission_value | status |
      | <131786276> | <123>          | <13>       | <Compra> | <25-09-2013> | <datSubscription:09-09-2013;idtUrl:71045;numPaymentOrder:1> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <50,04>          | <1>    |

  Esquema do Cenario: Agrupadores de evento de Compra sem datSubscription
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name | date         | desgrouping                                                            | value    | pay_order | cod_trans                       | commission_value | status |
      | <131786276> | <123>          | <13>       | <Compra> | <26-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;idtUrl:71045;numPaymentOrder:1> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <50,04>          | <1>    |

  Esquema do Cenario: Agrupadores de evento de Compra sem idtUrl
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name | date         | desgrouping                                                                          | value    | pay_order | cod_trans                       | commission_value | status |
      | <131786276> | <123>          | <13>       | <Compra> | <27-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;numPaymentOrder:1> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <50,04>          | <1>    |

  Esquema do Cenario: Agrupadores de evento de Compra sem numPaymentOrder
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name | date         | desgrouping                                                                     | value    | pay_order | cod_trans                       | commission_value | status |
      | <131786276> | <123>          | <13>       | <Compra> | <28-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;idtUrl:71045> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <50,04>          | <1>    |

    Esquema do Cenario: Agrupadores de evento de Compra sem numPaymentOrder
    Dado que exista um evento de comissionamento com os dados <idtperson>, <des_name>, <cod_trans> e <prd_source>
    Quando for simulado um pagamento de <des_name> no dia <date> com <prd_source> ,valor <value>, afiliado <idtperson>, agrupadores <desgrouping> , ordem de pagamento <pay_order> e codigo de transacao <cod_trans>
    E o processador de eventos for executado
    Entao verificar se os registros com  o codigo da transacao <cod_trans> foi processado com status <status>
    E verificar se o registro esta na fila com agrupadores <desgrouping>

    Exemplos: 
      | idtperson   | idtInscription | prd_source | des_name | date         | desgrouping                                                                     | value    | pay_order | cod_trans                       | commission_value | status |
      | <131786276> | <123>          | <13>       | <Compra> | <29-09-2013> | <offerDescription:CURSOS ONLINE COMPRA;datSubscription:09-09-2013;idtUrl:71045> | <200,20> | <1>       | <9235454355w24324333aa2432sss4> | <50,04>          | <1>    |
      