#language:pt
#author:dvrocha
Funcionalidade: Eleger arquivos a processar pela quantidade

  Cenário: Nenhum arquivo a processar
    Dado que não há nenhum arquivo a ser processado
    Quando o sistema for iniciado
    Então o sistema não processa nenhum arquivo
    E o sistema registra em log que não há arquivos a serem processados

  Cenário: Quantidade de arquivos a processar é menor que a quantidade máxima permitida
    Dado que a quantidade de arquivos a processar é menor que a quantidade máxima permitida
    Quando o sistema for iniciado
    Então o sistema processa todos os arquivos

  Cenário: Quantidade de arquivos a processar é maior que a quantidade máxima permitida
    Dado que a quantidade de arquivos a processar é maior que a quantidade máxima permitida
    Quando o sistema for iniciado
    Então o sistema processa a quantidade máxima de arquivos permitida

  Cenário: Quantidade de arquivos a processar é igual a quantidade máxima permitida
    Dado que a quantidade de arquivos a processar é igual a quantidade máxima permitida
    Quando o sistema for iniciado
    Então o sistema processa todos os arquivos
