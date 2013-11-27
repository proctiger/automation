# language: pt
Funcionalidade: Validar log e recuperacao do servidor de sessao

         
    
  Esquema do Cenario: Verificar se as informacoes do log de backup são repassadas para o servidor de sessão após sua recuperação
    Dado um servidor de sessao com inoperabilidade                                       
    E um log de backup com dados do afiliadoA <dadosA> e com produto <produtoA>                                                                                                                          
    E um log de backup com dados do afiliadoB <dadosB> com produto <produtoB>                                                                                                                                                                                                                                                              
    Quando o servidor de sessao for restartado                                                                                                                                                                                                           
    Entao verificar se esta gravado no servidor de sessao os dados do afiliadoA <dadosA> e com produto <produtoA>                                                       
    E os dados do afiliadoB <dadosB> com produto <produtoB> 
    
    Exemplos:
    | dadosA         |dadosB      |produtoA |produtoB|
    |83266,26159     |55260,22555 |13       |14      | 