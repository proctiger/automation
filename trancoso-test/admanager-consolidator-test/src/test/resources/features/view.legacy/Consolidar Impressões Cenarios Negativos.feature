#language:pt
#author:cin_wrodrigues
Funcionalidade: Consolidar Impressões Legacy com Problema

### 
# Formato URL: /ads.imguol.com/x.gif?ch=1&s=P03&c=59&ad=47544&r=767791292573182&sa=18202&ty=2
### 
 
### testes parametro slot
 
 Cenario: Registro de impressao <Legacy> com problema no <slot> com valor <vazio> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado com problema no <slot> com valor <vazio> 
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo
    
  Cenario: Registro de impressao <Legacy> com problema no <slot> com valor <invalido> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado com problema no <slot> com valor <invalido> 
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo
    
  Cenario: Registro de impressao <Legacy> sem parametro <slot> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado que nao possui o parametro <slot> 
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo
 
### testes parametro anuncio
 Cenario: Registro de impressao <Legacy> com problema no <id do anuncio> com valor <vazio> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado com problema no <id do anuncio> com valor <vazio> 
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo
    
  Cenario: Registro de impressao <Legacy> com problema no <id do anuncio> com valor <invalido> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado com problema no <id do anuncio> com valor <invalido> 
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo
    
  Cenario: Registro de impressao <Legacy> sem parametro <id do anuncio> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado que nao possui o parametro <id do anuncio> 
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo
    
### testes parametro sales package
  Cenario: Registro de impressao <Legacy> com problema no <id do sales package> com valor <vazio> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado com problema no <id do sales package> com valor <vazio> 
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo
    
  Cenario: Registro de impressao <Legacy> com problema no <id do sales package> com valor <invalido> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado com problema no <id do sales package> com valor <invalido> 
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo

  Cenario: Registro de impressao <Legacy> sem parametro <id do sales package> 
    Dado que existe algum arquivo de impressões <Legacy> a ser processado que nao possui o parametro <id do sales package>
    Quando for iniciado o processamento de impressões
    Então o sistema exibe o erro no arquivo de log
    E o sistema <nao> contabiliza as impressões do arquivo