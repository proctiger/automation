# language: pt
Funcionalidade: Servico de Validacao

  Esquema do Cenario: Fluxo Principal: Valida para o Produto
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado               | json                    |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Valida para o Produto nao pertecente ao portfolio dos afiliados
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o status do servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando status <status>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto     | afiliado                | status |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <uol video> | <testeauto1@bol.com.br> | <400>  |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <uol video> | <testeauto1@bol.com.br> | <400>  |

  Esquema do Cenario: Fluxo de excecao: Valida para o Produto nao pertecente ao portfolio dos afiliados (Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o status do servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando status <status>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto     | afiliado               | status | callback   |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <uol video> | <testeauto1@af.com.br> | <400>  | <function> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <uol video> | <testeauto1@af.com.br> | <400>  | <function> |

  Esquema do Cenario: Fluxo principal: Validacao de afiliado valido com produto utilizando letra maiuscula e minuscula
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado               | json                    |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <host>   | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HosT>   | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <host>   | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HosT>   | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <Av>     | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <av>     | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <Av>     | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <av>     | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <cursos> | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CuRsOs> | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <cursos> | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CuRsOs> | <testeauto1@af.com.br> | <{"status":"APPROVED"}> |

  Esquema do Cenario: Fluxo excecao: Valida para o afiliado com Statu Pendente
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | json                      |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto3> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto3> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto3> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto3> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto3> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto3> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado com status UOL Host
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado                | json                      |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto2@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto2@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto2@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto2@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto2@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto2@bol.com.br> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo excecao: Valida para o afiliado com Statu Reprovado
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado                    | json                      |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <teste-afiliado@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <teste-afiliado@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <teste-afiliado@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <teste-afiliado@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <teste-afiliado@bol.com.br> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <teste-afiliado@bol.com.br> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo excecao: Valida para o afiliado com Statu Desativado
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado    | json                      |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testauto7> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testauto7> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testauto7> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testauto7> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testauto7> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testauto7> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo excecao: Valida para o afiliado inexistente na Base
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado | json                      |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <teste>  | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <teste>  | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <teste>  | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <teste>  | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <teste>  | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <teste>  | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo excecao: Valida para o afiliado com status Mais(M-Mais)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto> e afiliado <afiliado>
    Entao verificar se o servico esta retornando uma mensagem <json>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | json                      |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto8> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto8> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto8> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto8> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto8> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto8> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo Principal: Valida para o Produto (Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado               | callback   | jsonp                   |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |

  Esquema do Cenario: Fluxo Principal: Valida para o Usuario com Statu Ativo (Convesao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado               | callback   | jsonp                   |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto1@af.com.br> | <function> | <{"status":"APPROVED"}> |

  Esquema do Cenario: Fluxo Principal: Valida para o Usuario (Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | callback   | jsonp                   |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto1> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto1> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto1> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto1> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto1> | <function> | <{"status":"APPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto1> | <function> | <{"status":"APPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Valida para o Usuario com Statu Pendente (Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | callback   | jsonp                     |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto3> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto3> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto3> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto3> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto3> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto3> | <function> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Valida para o Usuario com Statu Reprovado (Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado                    | callback   | jsonp                     |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <teste-afiliado@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <teste-afiliado@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <teste-afiliado@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <teste-afiliado@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <teste-afiliado@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <teste-afiliado@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Valida para o Usuario com Statu Desativado (Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado    | callback   | jsonp                     |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testauto7> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testauto7> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testauto7> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testauto7> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testauto7> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testauto7> | <function> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Valida para o Usuario inexistente na Base (Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado | callback   | jsonp                     |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <teste>  | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <teste>  | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <teste>  | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <teste>  | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <teste>  | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <teste>  | <function> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Valida para o Usuario com status Mais(M-Mais)(Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado     | callback   | jsonp                     |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto8> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto8> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto8> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto8> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto8> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto8> | <function> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Valida para o Usuario Inicial (Conversao para JsonP)
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado                        | callback   | jsonp                     |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <afiliado-visitante7@gmail.com> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <afiliado-visitante7@gmail.com> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <afiliado-visitante7@gmail.com> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <afiliado-visitante7@gmail.com> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <afiliado-visitante7@gmail.com> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <afiliado-visitante7@gmail.com> | <function> | <{"status":"UNAPPROVED"}> |

  Esquema do Cenario: Fluxo de excecao: Indicacao de afiliado com status UOL Host
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    Quando o servico <servico> for solicitado atraves da url com produto <produto>, afiliado <afiliado> e callback <callback>
    Entao verificar se o servico esta retornando uma mensagem <jsonp>

    Exemplos: 
      | dominio                        | servico  | protocolo | produto  | afiliado                | callback   | jsonp                     |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <HOST>   | <testeauto2@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <HOST>   | <testeauto2@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <AV>     | <testeauto2@bol.com.br>  | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <AV>     | <testeauto2@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <http>    | <CURSOS> | <testeauto2@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
      | <indique.afiliados.uol.com.br> | <valida> | <https>   | <CURSOS> | <testeauto2@bol.com.br> | <function> | <{"status":"UNAPPROVED"}> |
