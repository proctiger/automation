# language: pt
Funcionalidade: Validar Indicacao

Esquema do Cenario: Indicacao com idtLabel
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao nao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que o campo des_grouping para a inscricao <idtInscription> termina com <agrupador>

    Exemplos: 
    | code  | prd_source | idtInscription | hash                                   | infos          | dadosComplementares                                                                                                                      | agrupador | 
    | <204> | <4>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;abc;723;I> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> | <I:1>     |

Esquema do Cenario: Indicacao sem idtLabel
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao nao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que o campo des_grouping para a inscricao <idtInscription> termina com <agrupador>

    Exemplos: 
    | code  | prd_source | idtInscription | hash                                   | infos          | dadosComplementares                                                                                                                      | agrupador | 
    | <204> | <4>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;abc;I> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> | <I:1>     |
