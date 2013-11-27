#language:pt
#author:cin_wrodrigues
Funcionalidade: Controlar Processamento de Cliques com Problema

 Cenario: Apenas um arquivo e o mesmo já foi processado anteriormente
  Dado que existe apenas um arquivo de cliques a ser processado
  E que nao existem anuncios processados para os arquivos de cliques
  E que o arquivo de cliques já foi processado anteriormente
  Quando for iniciado o processamento de cliques
  Entao o sistema não registra o processamento dos arquivos de cliques processados
  E o sistema <nao> contabiliza cliques e dimension do arquivo

 Cenario: Mais de um arquivo e algum arquivo foi processado anteriormente
  Dado que existe mais de um arquivo de cliques a ser processado
  E que nao existem anuncios processados para os arquivos de cliques
  E que algum arquivo de cliques já foi processado anteriormente
  Quando for iniciado o processamento de cliques
  Então o sistema registra o processamento dos arquivos de cliques
  E o sistema contabiliza os cliques do arquivo

 Cenario: Mais de um arquivo e todos os arquivos foram processados anteriormente
  Dado que existe mais de um arquivo de cliques a ser processado
  E que todos os arquivos de cliques já foram processados anteriormente
  E que nao existem anuncios processados para os arquivos de cliques
  Quando for iniciado o processamento de cliques
  Então o sistema não registra o processamento dos arquivos de cliques processados
  E o sistema <nao> contabiliza cliques e dimension do arquivo
 
 Cenario: Apenas um arquivo de cliques e o mesmo está vazio
  Dado que existe apenas um arquivo de cliques a ser processado vazio
  Quando for iniciado o processamento de cliques
  Então o sistema registra o processamento dos arquivos de cliques
  E o sistema exibe o erro no arquivo de log
  
 Cenario: Apenas um arquivo de cliques e o mesmo está sem permissão de leitura
  Dado que existe apenas um arquivo de cliques a ser processado sem permissão de leitura
  Quando for iniciado o processamento de cliques
  Então o sistema registra o processamento dos arquivos de cliques
  E o sistema exibe o erro <erro ao tentar processar o arquivo> no arquivo de log
   
 Cenario: Nenhum arquivo disponivel para ser processado
  Dado   que nao existe arquivo de cliques a ser processado
  Quando for iniciado o processamento de cliques
  Então o sistema <nao> exibe o erro no arquivo de log