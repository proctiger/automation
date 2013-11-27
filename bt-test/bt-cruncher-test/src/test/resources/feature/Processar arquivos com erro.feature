#language:pt
#author:dvrocha
Funcionalidade: Processar arquivos com erro

  Cenário: Existe um sinal com erro no início do arquivo
    Dado que o arquivo a ser processado possui um erro na primeira linha
    Quando o sistema for iniciado
    Então o sistema processa todas as linhas que não possuem erro
    E o sistema registra em log os erros existentes no arquivo

  Cenário: Existe um sinal com erro no final do arquivo
    Dado que o arquivo a ser processado possui um erro na ultima linha
    Quando o sistema for iniciado
    Então o sistema processa todas as linhas que não possuem erro
    E o sistema registra em log os erros existentes no arquivo

  Cenário: Todos os sinais do arquivo possuem erro
    Dado que o arquivo a ser processado possui um erro em todas as linhas
    Quando o sistema for iniciado
    Então o sistema não processa nenhuma linha
    E o sistema registra em log os erros existentes no arquivo

  Cenário: Arquivo está vazio
    Dado que o arquivo a ser processado está vazio
    Quando o sistema for iniciado
    Então o sistema não processa o arquivo
    E o sistema registra em log os erros existentes no arquivo
