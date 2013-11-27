# language: pt
Funcionalidade: Validar Mapa Produto

  #casos de testes de validacao das mensagens recebidas pela fila
  Esquema do Cenario: Mapeamento UOL Assinaturas X  UOL Assinaturas
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E verificar se nao ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento

    Exemplos: 
      | product       | prd_source | code  | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                      |
      | <Assinaturas> | <4>        | <204> | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;2038;7951> | <{"codOffer":"1926","username":"ypl75Gw7Rc","idtPerson":"5644970","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Mapeamento UOL Assinaturas X Produtos UOL
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E verificar se nao ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento

    Exemplos: 
      | product         | prd_source | code  | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                                                                |
      | <Host>          | <4>        | <204> | <45857891>     | <0000013f-a528-1088-0000-013fa6593d82> | <4;1838;7951> | <{"codOffer":"2717","username":"cFxR5yyi2QYAMO","idtPerson":"129618597","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"45857891"}>                                   |
      | <Namoro>        | <4>        | <204> | <40353542>     | <0000013f-a528-1088-0000-013fa6593d83> | <4;4250;7951> | <{"codOffer":"2165","username":"ohXJTDJe0k05pg6erX4syxVssSi4CB8xqxtSiQkt7DJ8Wy6M","idtPerson":"124825816","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"40353542"}> |
      | <Cursos>        | <4>        | <204> | <48342239>     | <0000013f-a528-1088-0000-013fa6593d85> | <4;6961;7951> | <{"codOffer":"5520","username":"aff.cp01@aff.com.br","idtPerson":"131719081","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48342239"}>                              |
      | <Emprego Certo> | <4>        | <204> | <48295125>     | <0000013f-a528-1088-0000-013fa6593d84> | <4;6955;7951> | <{"codOffer":"7089","username":"hugo@normandia.com.br","idtPerson":"131695311","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48295125"}>                            |

  Esquema do Cenario: Mapeamento UOL Host X UOL Host
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E que nao exista o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E verificar se nao ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento

    Exemplos: 
      | product | prd_source | code  | idtInscription | hash                                   | infos          | dadosComplementares                                                                                                                              |
      | <Host>  | <10>       | <204> | <45857891>     | <0000013f-a528-1088-0000-013fa6593d81> | <10;1838;7951> | <{"codOffer":"2717","username":"cFxR5yyi2QYAMO","idtPerson":"129618597","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"45857891"}> |

  Esquema do Cenario: Mapeamento UOL Host X Produtos UOL
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E que nao exista o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E verificar se nao ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento

    Exemplos: 
      | product         | prd_source | code  | idtInscription | hash                                   | infos          | dadosComplementares                                                                                                                                                                |
      | <Assinaturas>   | <10>       | <204> | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <10;2038;7951> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}>                                           |
      | <Namoro>        | <10>       | <204> | <40353542>     | <0000013f-a528-1088-0000-013fa6593d82> | <10;4250;7951> | <{"codOffer":"2165","username":"ohXJTDJe0k05pg6erX4syxVssSi4CB8xqxtSiQkt7DJ8Wy6M","idtPerson":"124825816","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"40353542"}> |
      | <Emprego Certo> | <10>       | <204> | <48295125>     | <0000013f-a528-1088-0000-013fa6593d83> | <10;6955;7951> | <{"codOffer":"7089","username":"hugo@normandia.com.br","idtPerson":"131695311","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48295125"}>                            |

  Esquema do Cenario: Mapeamento UOL Namoro X UOL Namoro
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E verificar se nao ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento

    Exemplos: 
      | product  | prd_source | code  | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                                                                |
      | <Namoro> | <6>        | <204> | <40353542>     | <0000013f-a528-1088-0000-013fa6593d81> | <6;4250;7951> | <{"codOffer":"2165","username":"ohXJTDJe0k05pg6erX4syxVssSi4CB8xqxtSiQkt7DJ8Wy6M","idtPerson":"124825816","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"40353542"}> |

Esquema do Cenario: Mapeamento UOL Namoro X UOL Namoro
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E verificar se nao ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento

    Exemplos: 
      | product  | prd_source | code  | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                                                                |
      | <Namoro> | <6>        | <204> | <40353542>     | <0000013f-a528-1088-0000-013fa6593d81> | <6;4250;7951> | <{"codOffer":"2165","username":"ohXJTDJe0k05pg6erX4syxVssSi4CB8xqxtSiQkt7DJ8Wy6M","idtPerson":"124825816","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"40353542"}> |

  Esquema do Cenario: Mapeamento UOL Namoro X Produtos UOL
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao com as informacoes <infos> e hash <hash> e dados complementares <dadosComplementares> para a inscricao <idtInscription> nao estejam persistidas na base de dados

    Exemplos: 
      | product         | prd_source | code  | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                                     |
      | <Assinaturas>   | <6>        | <204> | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <6;2038;7951> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}>                |
      | <Host>          | <6>        | <204> | <45857891>     | <0000013f-a528-1088-0000-013fa6593d82> | <6;1838;7951> | <{"codOffer":"2717","username":"cFxR5yyi2QYAMO","idtPerson":"129618597","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"45857891"}>        |
      | <Emprego Certo> | <6>        | <204> | <48295125>     | <0000013f-a528-1088-0000-013fa6593d83> | <6;6955;7951> | <{"codOffer":"7089","username":"hugo@normandia.com.br","idtPerson":"131695311","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48295125"}> |

  Esquema do Cenario: Mapeamento UOL Emprego Certo X UOL Emprego Certo
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E validar que a inscricao com as informacoes <infos> e hash <hash> e dados complementares <dadosComplementares> para a inscricao <idtInscription> estejam persistidas na base de dados

    Exemplos: 
      | product         | prd_source | code  | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                                     |
      | <Emprego Certo> | <9>        | <204> | <48295125>     | <0000013f-a528-1088-0000-013fa6593d81> | <9;6955;7951> | <{"codOffer":"7089","username":"hugo@normandia.com.br","idtPerson":"131695311","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48295125"}> |

  Esquema do Cenario: Mapeamento Emprego Certo X Produtos UOL
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E validar que a inscricao com as informacoes <infos> e hash <hash> e dados complementares <dadosComplementares> para a inscricao <idtInscription> nao estejam persistidas na base de dados

    Exemplos: 
      | product       | prd_source | code  | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                                                                |
      | <Assinaturas> | <9>        | <204> | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <9;2038;7951> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}>                                           |
      | <Host>        | <9>        | <204> | <45857891>     | <0000013f-a528-1088-0000-013fa6593d82> | <9;1838;7951> | <{"codOffer":"2717","username":"cFxR5yyi2QYAMO","idtPerson":"129618597","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"45857891"}>                                   |
      | <Namoro>      | <9>        | <204> | <40353542>     | <0000013f-a528-1088-0000-013fa6593d83> | <9;6955;7951> | <{"codOffer":"2165","username":"ohXJTDJe0k05pg6erX4syxVssSi4CB8xqxtSiQkt7DJ8Wy6M","idtPerson":"124825816","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"40353542"}> |

  Esquema do Cenario: Mapeamento UOL Cursos Online X UOL Cursos Online
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E verificar se ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento
    E validar que a inscricao com as informacoes <infos> e dados complementares <dadosComplementares> para a inscricao <idtInscription> estejam persistidas na base de dados

    Exemplos: 
      | product            | prd_source | code  | idtInscription | hash                                   | infos           | dadosComplementares                                                                                                                                     |
      | <Cursos Concursos> | <13>       | <204> | <48342239>     | <0000013f-a528-1088-0000-013fa6593d81> | <13;848;7951>   | <{"codOffer":"5520","username":"aff.cp01@aff.com.br","idtPerson":"131719081","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48342239"}>   |
      | <Cursos PRO>       | <13>       | <204> | <48310795>     | <0000013f-a528-1088-0000-013fa6593d82> | <13;15322;7951> | <{"codOffer":"5157","username":"base99aa@gmail.com","idtPerson":"131703506","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48310795"}>    |
      | <Cursos FIT>       | <13>       | <204> | <48327214>     | <0000013f-a528-1088-0000-013fa6593d83> | <13;10321;7951> | <{"codOffer":"5208","username":"academiaplataforma311","idtPerson":"131711827","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48327214"}> |
      | <Cursos Ingles>    | <13>       | <204> | <48336476>     | <0000013f-a528-1088-0000-013fa6593d84> | <13;10310;7951> | <{"codOffer":"4889","username":"cluciatufao","idtPerson":"131705217","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48336476"}>           |

  Esquema do Cenario: Mapeamento UOL Cursos Online X Produtos UOL
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E validar que a inscricao com as informacoes <infos> e hash <hash> e dados complementares <dadosComplementares> para a inscricao <idtInscription> nao estejam persistidas na base de dados

    Exemplos: 
      | product       | prd_source | code  | idtInscription | hash                                   | infos          | dadosComplementares                                                                                                                                                                |
      | <Assinaturas> | <13>       | <204> | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <13;2038;7951> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}>                                           |
      | <Host>        | <13>       | <204> | <45857891>     | <0000013f-a528-1088-0000-013fa6593d82> | <13;1838;7951> | <{"codOffer":"2717","username":"cFxR5yyi2QYAMO","idtPerson":"129618597","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"45857891"}>                                   |
      | <Namoro>      | <13>       | <204> | <40353542>     | <0000013f-a528-1088-0000-013fa6593d83> | <13;6955;7951> | <{"codOffer":"2165","username":"ohXJTDJe0k05pg6erX4syxVssSi4CB8xqxtSiQkt7DJ8Wy6M","idtPerson":"124825816","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"40353542"}> |

  Esquema do Cenario: Mapeamento Uol Antivirus X Produtos UOL
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E validar que a inscricao com as informacoes <infos> e hash <hash> e dados complementares <dadosComplementares> para a inscricao <idtInscription> nao estejam persistidas na base de dados

    Exemplos: 
      | product       | prd_source | code  | idtInscription | hash                                   | infos          | dadosComplementares                                                                                                                                                                |
      | <Assinaturas> | <14>       | <204> | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <14;2038;7951> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}>                                           |
      | <Host>        | <14>       | <204> | <45857891>     | <0000013f-a528-1088-0000-013fa6593d82> | <14;1838;7951> | <{"codOffer":"2717","username":"cFxR5yyi2QYAMO","idtPerson":"129618597","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"45857891"}>                                   |
      | <Namoro>      | <14>       | <204> | <40353542>     | <0000013f-a528-1088-0000-013fa6593d83> | <14;6955;7951> | <{"codOffer":"2165","username":"ohXJTDJe0k05pg6erX4syxVssSi4CB8xqxtSiQkt7DJ8Wy6M","idtPerson":"124825816","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"40353542"}> |
      
  Esquema do Cenario: Mapeamento Uol Antivirus X Uol Antivirus
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E verificar se ha o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento
    E validar que a inscricao com as informacoes <infos> e hash <hash> e dados complementares <dadosComplementares> para a inscricao <idtInscription> estejam persistidas na base de dados

    Exemplos: 
      | product     | prd_source | code  | idtInscription | hash                                   | infos           | dadosComplementares                                                                                                                                |
      | <Antivirus> | <14>       | <204> | <48336077>     | <0000013f-a528-1088-0000-013fa6593d81> | <14;34867;7951> | <{"codOffer":"5545","username":"barsil@teste.com","idtPerson":"131715930","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"48336077"}> |

