# language: pt
Funcionalidade: Valida para Produto Uol Host

  Esquema do Cenario: Fluxo Feliz: Valida para o Produto Uol Host
  Dado que o usuario acessa o servico apartir do http://indique.afiliados.uol.com.br/produto/valida/usuario
  Quando o produto <produto> for passado atraves do url  
  Então Verificar se o sistema esta retornando uma mensagem de sucesso no padrao json
  
  Exemplos: 
      |   produto  |                                                                                                                                             | value   | pay_order | cod_trans            | commission_value | status |
      |    HOST    |      
 

 Esquema do Cenario:  Fluxo de excecao:  O Valida para Produto diferente do Portfolio dos Afiliados
  Dado que o usuario acesse o servico apartir do http://indique.afiliados.uol.com.br/produto/valida/usuario
  Quando o produto <produto> for passado atraves do url  
  Então Verificar se o servico esta retornado mensagem de alerta no padrao json	
 
   Exemplos: 
   |   produto  |                                                                                                                                             | value   | pay_order | cod_trans            | commission_value | status |
   | UOL VIDEO  |      
 