#language:pt
#author:cin_wrodrigues
Funcionalidade: Consolidar Impressões com Problema

### 
# Obs.: Cada argumento da URL foi chamado de trinca ( parametros ad,sa,s )
# Exemplo URL com 4 trincas:
# http://imp.ads.imguol.com/view
# ?ads=ad:33678;sa:467;s:43;
# &ads=ad:33678;sa:467;s:43;
# &ads=ad:33678;sa:467;s:43;
# &ads=ad:33678;sa:467;s:43;
### 
   
  Cenario: Registro de impressao com problema no id do anuncio <vazio> da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do anuncio> com valor <vazio> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
  
  Cenario: Registro de impressao com problema no id do anuncio <invalido> da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do anuncio> com valor <invalido> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao sem parametro id do anuncio da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <id do anuncio> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do sales package <vazio> da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do sales package> com valor <vazio> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do sales package <invalido> da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do sales package> com valor <invalido> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL

  Cenario: Registro de impressao sem parametro id do sales package da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <id do sales package> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
  
  Cenario: Registro de impressao com problema no id do slot <vazio> da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <slot> com valor <vazio> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do slot <invalido> da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <slot> com valor <invalido> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao sem parametro id do slot da trinca <1> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <slot> da trinca <1> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL

### Cenarios replicados considerando problema no parametro da segunda trinca da URL    
    
  Cenario: Registro de impressao com problema no id do anuncio <vazio> da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do anuncio> com valor <vazio> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do anuncio <invalido> da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do anuncio> com valor <invalido> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao sem parametro id do anuncio da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <id do anuncio> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do sales package <vazio> da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do sales package> com valor <vazio> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do sales package <invalido> da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do sales package> com valor <invalido> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL

  Cenario: Registro de impressao sem parametro id do sales package da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <id do sales package> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
  
  Cenario: Registro de impressao com problema no id do slot <vazio> da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <slot> com valor <vazio> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL

  Cenario: Registro de impressao com problema no id do slot <invalido> da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <slot> com valor <invalido> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao sem parametro id do slot da trinca <2> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <slot> da trinca <2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL

### Cenarios replicados considerando problema no parametro da primeira e segunda trinca da URL    
    
  Cenario: Registro de impressao com problema no id do anuncio <vazio> da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do anuncio> com valor <vazio> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do anuncio <invalido> da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do anuncio> com valor <invalido> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao sem parametro id do anuncio da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <id do anuncio> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do sales package <vazio> da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do sales package> com valor <vazio> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do sales package <invalido> da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <id do sales package> com valor <invalido> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL

  Cenario: Registro de impressao sem parametro id do sales package da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <id do sales package> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
  
  Cenario: Registro de impressao com problema no id do slot <vazio> da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <slot> com valor <vazio> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao com problema no id do slot <invalido> da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado com problema no <slot> com valor <invalido> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL
    
  Cenario: Registro de impressao sem parametro id do slot da trinca <1 e 2> da URL
    Dado que existe algum arquivo de impressões a ser processado sem o parametro <slot> da trinca <1 e 2> da URL
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema contabiliza as impressões do arquivo das demais trincas da URL