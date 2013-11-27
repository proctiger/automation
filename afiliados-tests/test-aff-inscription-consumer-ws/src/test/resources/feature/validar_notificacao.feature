# language: pt
Funcionalidade: Validar Notificacoes

  #casos de testes de validacao das mensagens recebidas pela fila
 
  Esquema do Cenario: Notificacao : info DAS descriptografadas
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos                                    | dadosComplementares                                                                                                                      |
      | <204> | <13>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;2038;723> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Notificacao : caminho perfeito idtURL
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao com as informacoes <infos> e chave <hash> e dados complementares <dadosComplementares> para a inscricao <idtInscription> estejam persistidas na base de dados

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                      |
      | <204> | <13>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;2038;723> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Notificacao :  idtURL nao existe em afiliados
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                      |
      | <204> | <13>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;82012;723> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Notificacao: idtPerson invalido
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos                                    | dadosComplementares                                                                                                                     |
      | <204> | <13>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;2038;723> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"265283","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Notificacao: source nao existente em afiliados
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos                                     | dadosComplementares                                                                                                                      |
      | <204> | <0>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <97;2038;723> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Notificacao: Source ""
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos                                       | dadosComplementares                                                                                                                      |
      | <204> | <0>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <;2038;723> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Notificacao: caf/idtUrl ""
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos    | dadosComplementares                                                                                                                      |
      | <204> | <0>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;;723> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Notificacao: idtLabel ""
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos                                 | dadosComplementares                                                                                                                      |
      | <204> | <0>       | <988044>       | <0000013f-a528-1088-0000-013fa6593d81> | <4;2038;> | <{"codOffer":"1926","username":"IaykjDq3Tr","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> |

  Esquema do Cenario: Notificacao: codAfiliado invalido
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados
    E validar que a resposta do servico e o codigo HTTP <code>

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos              | dadosComplementares                                                                                                                        |
      | <204> | <13>       | <2549910>      | <0000013f-a528-1088-0000-013fa6593d81> | <4;ABCDE12345;723> | <{"codOffer":"1926","username":"cNAlH9RbfX6","idtPerson":"3853627","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"2549910"}> |

  Esquema do Cenario: Notificacao: idtUrl invalido
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    E que nao exista o idtInscription <idtInscription> e codigo de produto <prd_source> no registro correspondente na tabela de evento
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados
    E validar que a resposta do servico e o codigo HTTP <code>

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos         | dadosComplementares                                                                                                                        |
      | <204> | <13>       | <2549910>      | <0000013f-a528-1088-0000-013fa6593d81> | <4;00000;723> | <{"codOffer":"1926","username":"cNAlH9RbfX6","idtPerson":"3853627","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"2549910"}> |


  Esquema do Cenario: Notificacao: inscription inexistente na inscription_sales em afiliados
    Dado que exista no DAS3 a hash <hash> de inscricao <idtInscription>
    E que no servidor de sessao nao exista as informacoes de afiliados <infos> de hash <hash> e codigo de produto <prd_source>
    E que nao exista a idtInscription <idtInscription> cadastrado em afiliados
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>
    E validar que a inscricao <idtInscription> nao esteja persistida na base de afiliados

    Exemplos: 
      | code  | prd_source | idtInscription | hash                                   | infos             | dadosComplementares                                                                                                                                     |
      | <204> | <13>       | <38193255>     | <0000013f-a528-1088-0000-013fa6593d81> | <13;62026;49022> | <{"codOffer":"5001","username":"af-fitnesse@gmail.com","idtPerson":"131695315","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"38193255"}> |

  Esquema do Cenario: Notificacao: IdtInscription nao enviada.
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>

    Exemplos: 
      | code  | idtInscription | infos                                    | dadosComplementares                                                                                          |
      | <204> | <2506247>      | <4;72579b9ae7f844fc9b9ded4b62d3e085;723> | <{"codOffer":"1926","username":"QUALIDADE","idtPerson":"3816902","uuid":"B1636B3C58FF2DAFE040850A23806C05"}> |


  Esquema do Cenario: Notificacao: DAS SEM DADOS PARA A INSCRIPTION INVALIDA.
    Dado que nao exista no DAS3 a hash <hash> de inscricao <idtInscription>
    Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados complementares <dadosComplementares>
    Entao validar que a resposta do servico e o codigo HTTP <code>

    Exemplos: 
      | code  | idtInscription | dadosComplementares                                                                                                                        |
      | <204> | <2719716007>   | <{"codOffer":"1926","username":"QUALIDADE","idtPerson":"3816902","uuid":"B1636B3C58FF2DAFE040850A23806C05","idtInscription":"2719716007"}> |
