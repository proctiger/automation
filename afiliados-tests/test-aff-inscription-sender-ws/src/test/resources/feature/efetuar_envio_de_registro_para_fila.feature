# language: pt
Funcionalidade: Efetuar envio de registro para fila

  Esquema do Cenario: Efetuar registro no Notificador Cadastral e verificar se chega na fila JMS
  	Quando efetuar uma chamada ao servico simulando a notificacao de um evento de cadastro com os dados <dados>
    Entao verificar se retorna o HTTP <code> para o Notificador Cadastral apos o envio para a fila
    
    Exemplos:
    | code |dados                                                                                                                       |
    | <204>| <{"codOffer":"5155","username":"usuario1","idtPerson":"2652843","uuid":"B1636B3C58FF2DAFE040850A23806C05", "idtInscription":"988044"}> 	 |