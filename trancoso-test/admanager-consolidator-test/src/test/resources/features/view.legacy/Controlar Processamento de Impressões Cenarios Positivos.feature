#language:pt
#author:cin_wrodrigues
Funcionalidade: Controlar Processamento de Impressões Legacy

  Cenario: Apenas um arquivo com apenas um anuncio e o mesmo ainda não foi processado
    Dado que existe um arquivo de impressões <Legacy> com <APENAS UM> um anuncio a ser processado
    E que o arquivo de impressões <Legacy> ainda não foi processado
    Quando for iniciado o processamento de impressões
    Então o sistema registra o processamento dos arquivos de impressões
 
  Cenario: Apenas um arquivo com mais de um anuncio e o mesmo ainda não foi processado
    Dado que existe um arquivo de impressões <Legacy> com <MAIS DE UM> um anuncio a ser processado
    E que o arquivo de impressões <Legacy> ainda não foi processado
    Quando for iniciado o processamento de impressões
    Então o sistema registra o processamento dos arquivos de impressões

  Cenario: Mais de um arquivo e nenhum arquivo foi processado anteriormente
    Dado que existe mais de um arquivo de impressões <Legacy> a ser processado
    E que o arquivo de impressões <Legacy> ainda não foi processado
    Quando for iniciado o processamento de impressões
    Então o sistema registra o processamento dos arquivos de impressões
