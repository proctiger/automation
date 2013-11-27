#language:pt
#author:dvrocha
Funcionalidade: Consolidar Impressões

  Cenario: Não existem nenhum registro para o anúncio a ser processado
    Dado que existe algum arquivo de impressões a ser processado
    E que não existe nenhum registro no banco para o anúncio a ser processado
    Quando for iniciado o processamento de impressões
    Então o sistema contabiliza as impressões do arquivo

  Cenario: Já existe um registro para anúncio apenas com cliques
    Dado que existe algum arquivo de impressões a ser processado
    E que já existe um registro no banco para o anúncio a ser processado apenas com cliques
    Quando for iniciado o processamento de impressões
    Então o sistema contabiliza as impressões do arquivo

  Cenario: Já existe um registro para anúncio apenas com impressões
    Dado que existe algum arquivo de impressões a ser processado
    E que já existe um registro no banco para o anúncio a ser processado apenas com impressões
    Quando for iniciado o processamento de impressões
    Então o sistema contabiliza as impressões do arquivo

  Cenario: Já existe um registro para anúncio com cliques e impressões
    Dado que existe algum arquivo de impressões a ser processado
    E que já existe um registro no banco para o anúncio a ser processado com cliques e impressões
    Quando for iniciado o processamento de impressões
    Então o sistema contabiliza as impressões do arquivo

  Cenario: Já existe um registro para anúncio para um dia diferente
    Dado que existe algum arquivo de impressões a ser processado
    E que já existe um registro no banco para o anúncio a ser processado com impressões para um dia diferente
    Quando for iniciado o processamento de impressões
    Então o sistema contabiliza as impressões do arquivo

  Cenario: Já existe um registro para o anúncio para um slot diferente
    Dado que existe algum arquivo de impressões a ser processado
    E que já existe um registro no banco para o anúncio a ser processado com impressões para um slot diferente
    Quando for iniciado o processamento de impressões
    Então o sistema contabiliza as impressões do arquivo

  Cenario: Já existe um registro para o anúncio com venda diferente
    Dado que existe algum arquivo de impressões a ser processado
    E que já existe um registro no banco para o anúncio a ser processado com impressões para uma venda diferente
    Quando for iniciado o processamento de impressões
    Então o sistema contabiliza as impressões do arquivo

  Cenario: Já existe um registro para o anúncio com hora diferente
    Dado que existe algum arquivo de impressões a ser processado
    E que já existe um registro no banco para o anúncio a ser processado com impressões para uma hora diferente
    Quando for iniciado o processamento de impressões
    Então o sistema contabiliza as impressões do arquivo

 #Cenario: Repetição de anúncios no mesmo arquivo
 #  Dado que existe algum arquivo de impressões a ser processado
 #  E que não existe nenhum registro no banco para o anúncio a ser processado
 #  E que arquivo possui mais uma linha para o mesmo anúncio
 #  Quando for iniciado o processamento de impressões
 #  Então o sistema contabiliza as impressões do arquivo
 #
 #Cenario: Repetição de anúncios em arquivos diferentes
 #  Dado que existe mais de um arquivo de impressões a ser processado
 #  E que não existe nenhum registro no banco para o anúncio a ser processado
 #  E que arquivos possuem mesmo anúncio para ser processado
 #  Quando for iniciado o processamento de impressões
 #  Então o sistema contabiliza as impressões do arquivo