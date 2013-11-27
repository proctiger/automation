#language:pt
#author:cin_wrodrigues
Funcionalidade: Controlar Processamento de Impressões com Problema

 Cenario: Apenas um arquivo e o mesmo já foi processado anteriormente
  Dado que existe apenas um arquivo de impressões <Novo> a ser processado
  E que nao existem anuncios processados para os arquivos de impressao
  E que o arquivo de impressões <Novo> já foi processado anteriormente
  Quando for iniciado o processamento de impressões
  Então o sistema não registra o processamento dos arquivos de impressões processados
  E o sistema <nao> contabiliza view e dimension do arquivo
  
 Cenario: Mais de um arquivo e algum arquivo foi processado anteriormente
  Dado que existe mais de um arquivo de impressões <Novo> a ser processado
  E que nao existem anuncios processados para os arquivos de impressao
  E que algum arquivo de impressões <Novo> já foi processado anteriormente
  Quando for iniciado o processamento de impressões
  Então o sistema registra o processamento dos arquivos de impressões
  E o sistema contabiliza as impressões do arquivo
 
 Cenario: Mais de um arquivo e todos os arquivos foram processados anteriormente
  Dado que existe mais de um arquivo de impressões <Novo> a ser processado
  E que todos os arquivos de impressões <Novo> já foram processados anteriormente
  E que nao existem anuncios processados para os arquivos de impressao
  Quando for iniciado o processamento de impressões
  Então o sistema não registra o processamento dos arquivos de impressões processados
  E o sistema <nao> contabiliza view e dimension do arquivo
 
 Cenario: Apenas um arquivo <Novo> e o mesmo está vazio
  Dado que existe apenas um arquivo de impressões <Novo> a ser processado vazio
  Quando for iniciado o processamento de impressões
  Então o sistema registra o processamento dos arquivos de impressões
  E o sistema exibe o erro no arquivo de log
 
  Cenario: Apenas um arquivo <Novo> e o mesmo está sem permissão de leitura
   Dado que existe apenas um arquivo de impressões <Novo> a ser processado sem permissão de leitura
   Quando for iniciado o processamento de impressões
   Então o sistema registra o processamento dos arquivos de impressões
   E o sistema exibe o erro <erro ao tentar processar o arquivo> no arquivo de log
    
 Cenario: Nenhum arquivo disponivel para ser processado
  Dado   que nao existe arquivo de impressões <Novo> a ser processado
  Quando for iniciado o processamento de impressões
  Então o sistema <nao> exibe o erro no arquivo de log