#language:pt
#author:dvrocha
Funcionalidade: Eleger arquivos a processar pela whitelist

  Cenário: Apenas um arquivo a processar e o mesmo está na whitelist
    Dado que existe algum arquivo a ser processado que está na whitelist
    Quando o sistema for iniciado
    Então o sistema processa os arquivos existentes na whitelist

  Cenário: Apenas um arquivo a processar e o mesmo não está na whitelist
    Dado que existe algum arquivo a ser processado que não está na whitelist
    Quando o sistema for iniciado
    Então o sistema não processa nenhum arquivo
    E o sistema registra em log que algum arquivo a ser processado não está na whitelist
    E o sistema registra em log que não há arquivos a serem processados

  Cenário: Mais de um arquivo a processar e algum não está na whitelist
    Dado que existe algum arquivo a ser processado que está na whitelist
    E que existe algum arquivo a ser processado que não está na whitelist
    Quando o sistema for iniciado
    Então o sistema processa os arquivos existentes na whitelist
    E o sistema registra em log que algum arquivo a ser processado não está na whitelist
