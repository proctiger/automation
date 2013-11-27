#language:pt
#author:dvrocha
Funcionalidade: Controlar Processamento de Cliques

  Cenario: Apenas um arquivo e o mesmo ainda não foi processado
    Dado que existe um arquivo de cliques com <APENAS UM> clique a ser processado
    E que o arquivo de cliques ainda não foi processado
    Quando for iniciado o processamento de cliques
    Então o sistema registra o processamento dos arquivos de cliques
    
  Cenario: Apenas um arquivo com mais de um clique e o mesmo ainda não foi processado
    Dado que existe um arquivo de cliques com <MAIS DE UM> clique a ser processado
    E que o arquivo de cliques ainda não foi processado
    Quando for iniciado o processamento de cliques
    Então o sistema registra o processamento dos arquivos de cliques
   
  Cenario: Mais de um arquivo e nenhum arquivo foi processado anteriormente
    Dado que existe mais de um arquivo de cliques a ser processado
    E que o arquivo de cliques ainda não foi processado
    Quando for iniciado o processamento de cliques
    Então o sistema registra o processamento dos arquivos de cliques
