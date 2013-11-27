# language: pt
Funcionalidade: Validar cookie geral

 
    Esquema do Cenario: Validar expiracao do cookie geral
  	  Dado um cookie com informacoes do afiliado <dados> e com tempo de expiracao de <tempo> (em segundos) 
  	  Quando o tempo de expiracao <tempo> se esgotar                                                         
  	  Entao verificar se o cookie com informações do afiliado <dados> é apagado #OK
  	  
  	  Exemplos:
  	  |dados                                                     |tempo                                                                                  |  
  	  |13,83266,26159									         |10														                         	 |                         
    
    Esquema do Cenario: Validar criacao do cookie com hash valida
      Dado uma chamada de anuncios prontos com informações do afiliado <dados>                                                                                                                    
      Quando for feita uma chamada do tipo <type> com informacoes do afiliado <dados>                                                                                                            
      Entao verificar se é gerado um cookie com o hash                                        
      E se a chave contida no servidor de sessao é composta por essa hash      #OK
      

      Exemplos:
  	  |dados                                                     |type                                                                                  |  
  	  |13,83266,26159                                            |xml                                                                                   |
  	  |13,83266,26159                                            |link                                                                                  |
  	  
  	 Esquema do Cenario: Validar sobrescricao de cookie do modelo antigo por meio de um mesmo produto correspondente
  	 	Dado um cookie com informacoes <dadoscrip> criptografados (do modelo antigo) 
  	 	Quando for feita uma chamada com informacoes do afiliado <dados>
  	 	Entao verificar se o cookie nao contem as informacoes <dadoscrip>
  	 	E se a chave contida no servidor de sessao nao é composta por informacoes <dadoscrip> # "dadoscrip" + ":13" OK
  	 	E se o conteudo dessa chave contem os <dadoscrip> descriptografado
  	 	
  	  Exemplos:
  	  |dados                                                     |dadoscrip                                                       |  #O valor criptografado é do produto 13
  	  |13,83266,26159                                            |b48gxQmR8n/xYCi8HR03Aw==                                        |
  	  |13,83266,26159                                            |b48gxQmR8n/xYCi8HR03Aw==          
  	  
  	  Esquema do Cenario: Validar sobrescricao de cookie do modelo antigo por meio de um diferente produto
  	 	Dado um cookie com informacoes <dadoscrip> criptografados (do modelo antigo) 
  	 	Quando for feita uma chamada com informacoes do afiliado <dados>
  	 	Entao verificar se o cookie nao contem as informacoes <dadoscrip>
  	 	E se a chave contida no servidor de sessao nao é composta por informacoes <dadoscrip> # "dadoscrip" + ":13" OK
  	 	E se o conteudo dessa chave contem os <dadoscrip> descriptografado
  	 	E se o conteudo da chave de produto <produto> contem os <dados> 
  	 	
  	  Exemplos:
  	  |dados                          |produto                           |dadoscrip                                                       |  
  	  |15,83266,26159                 |   15                             |b48gxQmR8n/xYCi8HR03Aw==                                        |
  	  |15,83266,26159                 |   15                             |b48gxQmR8n/xYCi8HR03Aw==                                           |
  	 	
  	 	
  	 	
  	 	
  	  
  	  
  	 