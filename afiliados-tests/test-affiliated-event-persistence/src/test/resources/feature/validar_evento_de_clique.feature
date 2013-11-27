# language: pt
#http://event-persistence.ws.afiliados.intranet/event/10/ea17f99389ad4b289b6f7d5b906b15fb/cae30488cfff4eeca520f7c4ac27474a/
#Clique/19-11-2013/1/10.0?g=idtLabel:23655;idtUrl:71045;type:link;eventType:Clique
#click.afiliados.uol.com.br/Clique?source=10&type=link&idtUrl=71045&idtLabel=23655
Funcionalidade: Validacao de evento de Clique

  Esquema do Cenario: Fluxo principal: evento de Clique com todos os valores corretos
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado     | http_code |
      | <idtLabel:23655;idtUrl:71045;type:link> | <Clique> | <10>               | <testeauto1> | <200>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique sem des_name
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado     | http_code |
      | <idtLabel:23655;idtUrl:71045;type:link> | <>       | <10>               | <testeauto1> | <404>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique sem idtUrl
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                    | des_name | idt_product_source | afiliado     | http_code |
      | <idtLabel:23655type:link> | <Clique> | <10>               | <testeauto1> | <400>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique sem idtLabel
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                   | des_name | idt_product_source | afiliado     | http_code |
      | <idtUrl:71045;type:link> | <Clique> | <10>               | <testeauto1> | <200>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique sem type
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta sera <response> com codigo <http_code>

    Exemplos: 
      | values                        | des_name | idt_product_source | afiliado     | http_code | response             |
      | <idtLabel:23655;idtUrl:71045> | <Clique> | <10>               | <testeauto1> | <400>     | <NOT_FOUND_GROUPING> |

  Esquema do Cenario: Fluxo de excecao: evento de Clique sem source
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado     | http_code |
      | <idtLabel:23655;idtUrl:71045;type:link> | <Clique> | <>                 | <testeauto1> | <404>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique sem idtUrl,idtLabel e type
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta sera <response> com codigo <http_code>

    Exemplos: 
      | values | des_name | idt_product_source | afiliado     | http_code | response             |
      | <>     | <Clique> | <10>               | <testeauto1> | <400>     | <NOT_FOUND_GROUPING> |

  Esquema do Cenario: Fluxo de excecao: evento de Clique sem idtUrl,idtLabel,type e source
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values | des_name | idt_product_source | afiliado     | http_code |
      | <>     | <Clique> | <>                 | <testeauto1> | <404>     |

  Esquema do Cenario: Fluxo excecao: evento de Clique com idtUrl invalido
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta sera <response> com codigo <http_code>

    Exemplos: 
      | values                                         | des_name | idt_product_source | afiliado     | http_code | response            |
      | <idtLabel:23655;idtUrl:123111111111;type:link> | <Clique> | <10>               | <testeauto1> | <400>     | <NOT_FOUND_IDT_URL> |

  Esquema do Cenario: Fluxo excecao: evento de Clique com idtLabel invalido
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                | des_name | idt_product_source | afiliado     | http_code |
      | <idtLabel:123;idtUrl:71045;type:link> | <Clique> | <10>               | <testeauto1> | <200>     |

  Esquema do Cenario: Fluxo excecao: evento de Clique com nome invalido
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta sera <response> com codigo <http_code>

    Exemplos: 
      | values                                  | des_name  | idt_product_source | afiliado     | http_code | response                  |
      | <idtLabel:23655;idtUrl:71045;type:link> | <pikachu> | <10>               | <testeauto1> | <400>     | <NOT_FOUND_SOURCE_CONFIG> |

  Esquema do Cenario: Fluxo excecao: evento de Clique com codigo do produto invalido
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta sera <response> com codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado     | http_code | response                  |
      | <idtLabel:23655;idtUrl:71045;type:link> | <Clique> | <99>               | <testeauto1> | <400>     | <NOT_FOUND_SOURCE_CONFIG> |

  Esquema do Cenario: Fluxo excecao: evento de Clique com afiliado inexistente
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado | http_code |
      | <idtLabel:23655;idtUrl:71045;type:link> | <Clique> | <10>               | <ytutt>  | <404>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique com afiliado com status inativo
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado     | http_code |
      | <idtLabel:23662;idtUrl:71050;type:link> | <Clique> | <10>               | <testeauto5> | <200>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique com afiliado com status pendente
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado     | http_code |
      | <idtLabel:23663;idtUrl:71049;type:link> | <Clique> | <10>               | <testeauto3> | <200>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique com afiliado com status reprovado
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta sera <response> com codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado     | http_code | response            |
      | <idtLabel:23655;idtUrl:71045;type:link> | <Clique> | <10>               | <testeauto6> | <400>     | <NOT_FOUND_IDT_URL> |

  Esquema do Cenario: Fluxo de excecao: evento de Clique com afiliado com status desativado
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao nao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta sera <response> com codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado     | http_code | response            |
      | <idtLabel:23655;idtUrl:71045;type:link> | <Clique> | <10>               | <testeauto7> | <400>     | <NOT_FOUND_IDT_URL> |

  Esquema do Cenario: Fluxo de excecao: evento de Clique com idtUrl inativo
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado                | http_code |
      | <idtLabel:23664;idtUrl:71055;type:link> | <Clique> | <10>               | <testeauto1@bol.com.br> | <200>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique com idtLabel inativo
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado                | http_code |
      | <idtLabel:23664;idtUrl:71052;type:link> | <Clique> | <10>               | <testeauto1@bol.com.br> | <200>     |

  Esquema do Cenario: Fluxo de excecao: evento de Clique com idtUrl aguardando analise
    Dado que nao exista um evento de <des_name> para o afiliado <afiliado> na tabela de eventos
    Quando for feita um chamada do evento de <des_name> para o produto <idt_product_source> e afiliado <afiliado> com valores <values> para o servico de persistencia
    Entao havera registro de <des_name> na tabela de eventos de <idt_product_source> e afiliado <afiliado> com agrupadores <values>
    E a resposta tera o codigo <http_code>

    Exemplos: 
      | values                                  | des_name | idt_product_source | afiliado                | http_code |
      | <idtLabel:23664;idtUrl:71056;type:link> | <Clique> | <10>               | <testeauto1@bol.com.br> | <200>     |
