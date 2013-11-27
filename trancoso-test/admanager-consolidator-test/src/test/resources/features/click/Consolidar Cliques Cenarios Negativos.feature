#language:pt
#author:cin_wrodrigues
Funcionalidade: Consolidar Cliques com Problema

# Formato do json
# {"ty":2,"t":1379355715384,"s":"P01","c":22,"sa":18170,"ad":47396,"ch":1}
 
 Cenario: Registro com problema no parametro <timestamp> com valor <vazio>
   Dado que existe algum arquivo de cliques a ser processado com problema no parametro <timestamp> com valor <vazio>
   Quando for iniciado o processamento de cliques
   Então o sistema <nao> contabiliza os cliques do arquivo
   E o sistema exibe o erro no arquivo de log

 Cenario: Registro com problema no parametro <timestamp> com valor <invalido>
   Dado que existe algum arquivo de cliques a ser processado com problema no parametro <timestamp> com valor <invalido>
   Quando for iniciado o processamento de cliques
   Então o sistema <nao> contabiliza os cliques do arquivo
   E o sistema exibe o erro no arquivo de log
      
 Cenario: Registro sem o parametro <timestamp>
   Dado que existe algum arquivo de cliques a ser processado que nao possui o parametro <timestamp>
   Quando for iniciado o processamento de cliques
   Então o sistema <nao> contabiliza os cliques do arquivo
   E o sistema exibe o erro no arquivo de log    

 Cenario: Registro com problema no parametro <slot> com valor <vazio>
   Dado que existe algum arquivo de cliques a ser processado com problema no parametro <slot> com valor <vazio>
   Quando for iniciado o processamento de cliques
   Então o sistema <nao> contabiliza os cliques do arquivo
   E o sistema exibe o erro no arquivo de log
 
  Cenario: Registro com problema no parametro <slot> com valor <invalido>
  	Dado que existe algum arquivo de cliques a ser processado com problema no parametro <slot> com valor <invalido>
    Quando for iniciado o processamento de cliques
    Então o sistema <nao> contabiliza os cliques do arquivo
    E o sistema exibe o erro no arquivo de log
       
  Cenario: Registro sem o parametro <slot>
  	Dado que existe algum arquivo de cliques a ser processado que nao possui o parametro <slot>
    Quando for iniciado o processamento de cliques
    Então o sistema <nao> contabiliza os cliques do arquivo
    E o sistema exibe o erro no arquivo de log       
  
   Cenario: Registro com problema no parametro <id do sales package> com valor <vazio>
  	Dado que existe algum arquivo de cliques a ser processado com problema no parametro <id do sales package> com valor <vazio>
    Quando for iniciado o processamento de cliques
    Então o sistema <nao> contabiliza os cliques do arquivo
    E o sistema exibe o erro no arquivo de log  
 
  Cenario: Registro com problema no parametro <id do sales package>  com valor <invalido>
  	Dado que existe algum arquivo de cliques a ser processado com problema no parametro <id do sales package> com valor <invalido>
    Quando for iniciado o processamento de cliques
    Então o sistema <nao> contabiliza os cliques do arquivo
    E o sistema exibe o erro no arquivo de log
       
  Cenario: Registro sem o parametro <id do sales package>
  	Dado que existe algum arquivo de cliques a ser processado que nao possui o parametro <id do sales package>
    Quando for iniciado o processamento de cliques
    Então o sistema <nao> contabiliza os cliques do arquivo
    E o sistema exibe o erro no arquivo de log      
    
  Cenario: Registro com problema no parametro <id do anuncio> com valor <vazio>
  	Dado que existe algum arquivo de cliques a ser processado com problema no parametro <id do anuncio> com valor <vazio>
    Quando for iniciado o processamento de cliques
    Então o sistema <nao> contabiliza os cliques do arquivo
    E o sistema exibe o erro no arquivo de log
 
  Cenario: Registro com problema no parametro <id do anuncio> com valor <invalido>
  	Dado que existe algum arquivo de cliques a ser processado com problema no parametro <id do anuncio> com valor <invalido>
    Quando for iniciado o processamento de cliques
    Então o sistema <nao> contabiliza os cliques do arquivo
    E o sistema exibe o erro no arquivo de log
       
  Cenario: Registro sem o parametro <id do anuncio>
  	Dado que existe algum arquivo de cliques a ser processado que nao possui o parametro <id do anuncio>
    Quando for iniciado o processamento de cliques
    Então o sistema <nao> contabiliza os cliques do arquivo
    E o sistema exibe o erro no arquivo de log      