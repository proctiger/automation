# language: pt
Funcionalidade: Validacao da chamada de indicacao

  Esquema do Cenario: Fluxo principal: Indicacao de afiliado valido
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E o conteudo da chave no servidor de sessao com codigo do produto <idt_product_source> contera o valor <value>
    E na tabela de eventos existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | idt_product_source | value                                   |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto1> | <13>               | <13;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <CURSOS> | <testeauto1> | <13>               | <13;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto1> | <10>               | <10;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <HOST>   | <testeauto1> | <10>               | <10;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto1> | <14>               | <14;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <AV>     | <testeauto1> | <14>               | <14;f8ceab214b314dbf9df19dda72950c39;I> |

  Esquema do Cenario: Fluxo principal: Indicacao de afiliado com e-mail bol
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E o conteudo da chave no servidor de sessao com codigo do produto <idt_product_source> contera o valor <value>
    E na tabela de eventos existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado                | idt_product_source | value                                   |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <CURSOS> | <testeauto1@bol.com.br> | <13>               | <13;b6d423584df34f159461d74441a73576;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto1@bol.com.br> | <13>               | <13;b6d423584df34f159461d74441a73576;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto1@bol.com.br> | <10>               | <10;b6d423584df34f159461d74441a73576;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <HOST>   | <testeauto1@bol.com.br> | <10>               | <10;b6d423584df34f159461d74441a73576;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto1@bol.com.br> | <14>               | <14;b6d423584df34f159461d74441a73576;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <AV>     | <testeauto1@bol.com.br> | <14>               | <14;b6d423584df34f159461d74441a73576;I> |

  Esquema do Cenario: Fluxo principal: Indicacao de afiliado valido com e-mail visitante
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E o conteudo da chave no servidor de sessao com codigo do produto <idt_product_source> contera o valor <value>
    E na tabela de eventos existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado               | idt_product_source | value                                   |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto1@af.com.br> | <13>               | <13;c6a4a9a9a3a345e3a4392eac6135fd24;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <CURSOS> | <testeauto1@af.com.br> | <13>               | <13;c6a4a9a9a3a345e3a4392eac6135fd24;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto1@af.com.br> | <10>               | <10;c6a4a9a9a3a345e3a4392eac6135fd24;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <HOST>   | <testeauto1@af.com.br> | <10>               | <10;c6a4a9a9a3a345e3a4392eac6135fd24;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto1@af.com.br> | <14>               | <14;c6a4a9a9a3a345e3a4392eac6135fd24;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <AV>     | <testeauto1@af.com.br> | <14>               | <14;c6a4a9a9a3a345e3a4392eac6135fd24;I> |

  Esquema do Cenario: Fluxo principal: Indicacao de afiliado valido com produto utilizando letra maiuscula e minuscula
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E o conteudo da chave no servidor de sessao com codigo do produto <idt_product_source> contera o valor <value>
    E na tabela de eventos existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | idt_product_source | value                                   |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <cUrSos> | <testeauto1> | <13>               | <13;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <Cursos> | <testeauto1> | <13>               | <13;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <cUrSos> | <testeauto1> | <13>               | <13;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <Cursos> | <testeauto1> | <13>               | <13;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <host>   | <testeauto1> | <10>               | <10;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HoSt>   | <testeauto1> | <10>               | <10;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <host>   | <testeauto1> | <10>               | <10;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <HoSt>   | <testeauto1> | <10>               | <10;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <Av>     | <testeauto1> | <14>               | <14;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <av>     | <testeauto1> | <14>               | <14;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <Av>     | <testeauto1> | <14>               | <14;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <av>     | <testeauto1> | <14>               | <14;f8ceab214b314dbf9df19dda72950c39;I> |

  Esquema do Cenario: Fluxo principal: Indicacao de afiliado valido com sobrescricao da informacao no servidor de sessao
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto2> e afiliado <afiliado2>
    E o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E o conteudo da chave no servidor de sessao com codigo do produto <idt_product_source> contera o valor <value>
    E na tabela de eventos existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | produto2 | afiliado     | afiliado2    | idt_product_source | value                                   |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <CURSOS> | <testeauto1> | <testeauto2> | <13>               | <13;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <CURSOS> | <CURSOS> | <testeauto1> | <testeauto2> | <13>               | <13;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <HOST>   | <testeauto1> | <testeauto2> | <10>               | <10;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <HOST>   | <HOST>   | <testeauto1> | <testeauto2> | <10>               | <10;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <AV>     | <testeauto1> | <testeauto2> | <14>               | <14;f8ceab214b314dbf9df19dda72950c39;I> |
      | <indique.afiliados.uol.com.br> | <indica> | <https>   | <AV>     | <AV>     | <testeauto1> | <testeauto2> | <14>               | <14;f8ceab214b314dbf9df19dda72950c39;I> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado inexistente
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao nao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E na tabela de eventos nao existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>
    E nao havera chave do produto  <idt_product_source> no servidor de sessao
    E verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado  | idt_product_source | json                      |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <pikachu> | <13>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <pikachu> | <10>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <pikachu> | <14>               | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado com status Mais(M-Mais)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao nao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E na tabela de eventos nao existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>
    E nao havera chave do produto  <idt_product_source> no servidor de sessao
    E verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | idt_product_source | json                      |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto8> | <13>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto8> | <10>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto8> | <14>               | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado com status UOL Host
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao nao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E na tabela de eventos nao existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>
    E nao havera chave do produto  <idt_product_source> no servidor de sessao
    E verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado                | idt_product_source | json                      |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto2@bol.com.br> | <13>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto2@bol.com.br> | <10>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto2@bol.com.br> | <14>               | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado com status inativo
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao nao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E na tabela de eventos nao existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>
    E verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | idt_product_source | json                      |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto5> | <13>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto5> | <10>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto5> | <14>               | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado com status pendente
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao nao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E na tabela de eventos nao existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>
    E nao havera chave do produto  <idt_product_source> no servidor de sessao
    E verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | idt_product_source | json                      |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto3> | <13>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto3> | <10>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto3> | <14>               | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado com status reprovado
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao nao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E na tabela de eventos nao existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>
    E nao havera chave do produto  <idt_product_source> no servidor de sessao
    E verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | idt_product_source | json                      |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto6> | <13>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto6> | <10>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto6> | <14>               | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado com status desativado
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao nao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E na tabela de eventos nao existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>
    E nao havera chave do produto  <idt_product_source> no servidor de sessao
    E verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | idt_product_source | json                      |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <CURSOS> | <testeauto7> | <13>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <HOST>   | <testeauto7> | <10>               | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <AV>     | <testeauto7> | <14>               | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de produto nao existente no portfolio de afiliado
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que nao exista registros de indicacao do afiliado <afiliado> na tabela de eventos
    E que nao exista no servidor de sessao as chaves do produto <idt_product_source>
    Quando o status do servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao nao ocorrera o redirecionamento para o registrador de clicks de afiliados
    E na tabela de eventos nao existira um evento de indicacao com codigo do produto <idt_product_source> para o <afiliado>
    E nao havera chave do produto  <idt_product_source> no servidor de sessao
    Entao verificar se o servico esta retornando status <status>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto | afiliado     | idt_product_source | status |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <blah>  | <testeauto1> | <13>               | <400>  |
      | <indique.afiliados.uol.com.br> | <indica> | <http>    | <bluh>  | <testeauto1> | <14>               | <400>  |
