# language: pt
Funcionalidade: Validar chave do servidor de sessao




  Esquema do Cenario:Verificar a sobrescricao da informacao da chave na ocorrencia de um evento de gravacao de um mesmo produto e de um diferente afiliado
    Dado uma chave no servidor de sessao com informacoes do afiliado A <dadosA> e com codigo de produto <produtoA>                                                                                                
    Quando for feita uma chamada com informacoes do afiliado B <dadosB> e com codigo de produto <produtoA>                                                                                                                                                                                                   
    Entao verificar as informacoes do afiliado <dadosB> se encontram na chave #OK
    
	Exemplos:
	| dadosA                                                                |produtoA  |dadosB                                  |
	|    83266,26159                                                        |  13      |55260,22555                             |
	
  Esquema do Cenario:Verificar a expiracao da chave por produto
    Dado uma chave no servidor de sessao com informacoes do afiliado <dados> e com tempo de expiracao de <tempo> (em segundos)                                         
    Quando o tempo de expiracao <tempo> se esgotar (em segundos)                                                       
    Entao verificar se a chave no servidor de sessao com informacoes do afiliado <dados> foi apagada do cookie #OK
    
    Exemplos:
    |dados                |tempo                                                                                               |
    |  13,83266,26159     |10                                                                                                  |
    |  10,83266,26159     |10                                                                                                  |
    
  Esquema do Cenario:Verificar a renovacao da expiracao quando ocorrer uma sobrescricao  
    Dado uma chave no servidor de sessao com informacoes do afiliado A <dadosA> com codigo de produto <produtoA> e com tempo de expiracao de <tempo> (em segundos)                                                                                                                    
    E ao passar <tempoesp> de tempo (em segundos)                                                                                                                                                                                                                               
    Quando for feita uma chamada com produto com informacoes do afiliado B <dadosB> e com codigo de produto <produtoA>                                                          
    Entao verificar se o tempo de expiracao <tempo> (em segundos) foi renovado   #OK
    
    Exemplos:
    |dadosA               |produtoA              |tempo       | tempoesp|dadosB                                                 |
    |83266,26159          |13                    |10          |   5     |55260,22555                                            |
    
  Esquema do Cenario:Verificar se as informacoes da chave correspondem com a do link da chamada  
    Dado uma chamada  do tipo <type> com dados do afiliado<dados>        
    Quando a chamada do tipo <type> com dados do afiliado<dados> for feita                                                                                                                                    
    Entao verificar se a informacao gravada na chave possui os mesmos dados do afiliado <dados>
    
    Exemplos:
    |type                           |dados                                                                                        | 
    |link                           |13,83266,26159                                                                               |   
    |xml                            |13,83266,26159                                                                               |
    
  Esquema de Cenario:Verificar o valor do tempo de expiração por produto
    Dado uma chamada com dados do afiliado<dados> e produto <produto> com tempo de expiracao <tempo> #set no remote config
    Quando a chamada for feita 
    Entao verificar se o produto <produto> tem o tempo de expiracao <tempo> (em dias) #OK
    
    Exemplos:
    |dados                      |produto        |tempo                                                                                 |
    |83266,26159                |13             |30                                                                                    |
    
   Esquema de Cenario: Verificar insercao de chave no servidor de sessao 2
    Dado uma chamada com dados do afiliado<dados> e produto <produto>
    E que o servidor de sessao 1 esteja inoperante
    Quando a chamada for feita 
    Entao verificar se a informacao gravada na chave possui os dados do afiliado <dados> no servidor de sessao 2
    
     Exemplos:
    |dados                      |produto                                                                                    |
    |83266,26159                |13                                                                                     |
    
    
   
  
    