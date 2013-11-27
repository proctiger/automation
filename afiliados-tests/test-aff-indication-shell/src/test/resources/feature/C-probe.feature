# language: pt
Funcionalidade: Verificacao da monitoracao do probe

  Esquema do Cenario: Fluxo Principal: Validar servico disponivel
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que os servicos <componentes> estejam disponiveis nos pools <pools>
    E que sejam esperados <ms> milissegundos
    Quando o status do servico <servico> for solicitado
    Entao verificar se o servico esta retornando status <httpCode>

    Exemplos: 
      | dominio                        | protocolo | servico         | httpCode | componentes                                    | pools                                | ms      |
      | <indique.afiliados.uol.com.br> | <http>    | <monitor-probe> | <200>    | <affiliated-event-click;affiliated-ws-service> | <a1-helique-s-prt1;a1-iziafl-s-prt1> | <20000> |
      | <indique.afiliados.uol.com.br> | <http>    | <probe>         | <200>    | <affiliated-ws-service;affiliated-event-click> | <a1-iziafl-s-prt1;a1-helique-s-prt1> | <20000> |

  Esquema do Cenario: Fluxo Principal: Validar servico indisponivel
    Dado o dominio <dominio> utilizando o protocolo <protocolo>
    E que o servico <componente1> esteja indisponivel no pool <pool1>
    E que o servico <componente2> esteja disponivel no pool <pool2>
    E que sejam esperados <ms> milissegundos
    Quando o status do servico <servico> for solicitado
    Entao verificar se o servico esta retornando status <httpCode>

    Exemplos: 
      | dominio                        | protocolo | httpCode | servico         | componente1              | componente2              | pool1               | pool2               | ms      |
      | <indique.afiliados.uol.com.br> | <http>    | <500>    | <monitor-probe> | <affiliated-event-click> | <affiliated-ws-service>  | <a1-helique-s-prt1> | <a1-iziafl-s-prt1>  | <15000> |
      | <indique.afiliados.uol.com.br> | <http>    | <500>    | <probe>         | <affiliated-ws-service>  | <affiliated-event-click> | <a1-iziafl-s-prt1>  | <a1-helique-s-prt1> | <15000> |
