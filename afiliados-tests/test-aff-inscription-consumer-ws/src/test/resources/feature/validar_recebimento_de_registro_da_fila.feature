# language: pt
Funcionalidade: Validar recebimento de registro da fila

Esquema do Cenario: O registro chega no componente consumidor quando o mesmo se encontra inoperante
	Dado que nao exista o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dados>
    E o componente consumidor estiver inoperante
    Entao validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados
    E verificar se nao ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento

    Exemplos: 
      | idtInscription | prd_source | dados                                                                                                                                  |
      | <48342239>     |	<13>    | <{"codOffer":"5155","username":"usuario1","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: O registro chega no componente consumidor quando o registrador de evento se encontra inoperante
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E que o registrador de evento esteja inoperante
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dados>
    Entao validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados
    E verificar se nao ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento

    Exemplos: 
      | idtInscription | prd_source | hash                                   |infos|dados                                                                                                                                  |
      | <988044>       | <13>       | <0000013f-a528-1088-0000-013fa6593d81> |<4;69118;7951>|<{"codOffer":"5155","username":"usuario1","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |
