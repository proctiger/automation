# language: pt
Funcionalidade: Efetuar registro (POST)

  Cenario: Efetuar registro no Notificador Cadastral
    Quando efetuar registro no Notificador Cadastral
    Entao verificar resultado do registro com o codigo <202>

  Cenario: Efetuar registro no Notificador Cadastral pela Segunda vez
    Quando efetuar registro no Notificador Cadastral com token <jrr_tokenXXX>
	E efetuar registro no Notificador Cadastral com token <jrr_tokenXXX>
    Entao verificar resultado do registro com o codigo <400>
    
  Cenario: Efetuar registro no Notificador Cadastral com topicname INVALIDO 
    Quando efetuar registro no Notificador Cadastral com topicname <topicNameInvalido>
    Entao verificar resultado do registro com o codigo <400>
    
  Cenario: Efetuar registro no Notificador Cadastral com callbackUrl INEXISTENTE
    Quando efetuar registro no Notificador Cadastral com callbackUrl <http://invalid.callback:9999/ebroker>
    Entao verificar resultado do registro com o codigo <202>
    
  Cenario: Efetuar registro no Notificador Cadastral com keepUndelivered TRUE
    Quando efetuar registro no Notificador Cadastral com keepUndelivered <true>
    Entao verificar resultado do registro com o codigo <202>
    
  Cenario: Efetuar registro no Notificador Cadastral com keepUndelivered FALSE
    Quando efetuar registro no Notificador Cadastral com keepUndelivered <false>
    Entao verificar resultado do registro com o codigo <202>
    
  Cenario: Efetuar callback de status no serviço
    Quando efetuar callback de status simulando o notificador cadastral
    Entao verificar resultado do callback com o codigo <204>