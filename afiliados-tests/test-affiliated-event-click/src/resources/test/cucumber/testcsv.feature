#language:pt
Funcionalidade: Geracao do conteudo CSV atribuido ao redis vinculado ao cookie plantando na maquina do usuario.

Esquema do Cenário: Gerando conteudo de indicacao no redis apos evento de Indicacao
Dado que temos eventos persistidos na tabela AE_EVENT_LOG
Quando efetuar requisicao para evento de indicacao com caf "<caf>" label "<idtLabel>" e source "<source>"
Entao o cookie deve ser plantado
E o valor gerado no redis deve ser "<redisContent>"
E a quantidade de eventos persistidos na tabela AE_EVENT_LOG apos a requisicao deve aumentar em uma unidade

Exemplos:
|    caf                           | 	idtLabel    |  source    | redisContent       |
| 81d6fbaa59854a63973dd143966147f1 |                |   13       | 13;81d6fbaa59854a63973dd143966147f1;I |
| 7c1260252ced4657a1fe9e89443cbbf2 |      401       |   14       | 14;7c1260252ced4657a1fe9e89443cbbf2;401;I |

Esquema do Cenário: Gerando conteudo de clique no redis apos evento de Clique
Dado que temos eventos persistidos na tabela AE_EVENT_LOG
Quando efetuar requisicao para evento de clique com idturl "<idtUrl>", label "<idtLabel>" e source "<source>"
Entao o cookie deve ser plantado
E o valor gerado no redis deve ser "<redisContent>"
E a quantidade de eventos persistidos na tabela AE_EVENT_LOG apos a requisicao deve aumentar em uma unidade

Exemplos:
| idtUrl    | idtLabel   |  source    | redisContent    |
|    1      |    7961    |    13      |     13;1;7961   |
|    2      |            |    14      |     14;2        |
