#language:pt
#author:wrodrigues
Funcionalidade: Executar cruncher com Mongo em horario de manutencao no Remote Config
  
   Cenário: Mongo em manutencao: Collection Cookies eventos NÃO serão inseridos
  	Dado que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
  	E que não existe nenhum registro de impressão para um usuário expecífico
  	E que não existe nenhum registro de segmento para um usuário expecífico
  	E que não existe nenhum registro de perfil para um usuário expecífico
  	E que não existe nenhum registro de cookie para um usuário expecífico
    E mongo ativado no Remote Config
    E horario atual dentro da tabela de manutencao do mongo
  	Quando o sistema for iniciado
  	Então os eventos processados não serão inseridos na collection de cookies
  
   Cenário: Mongo em manutencao: Collection Perfil eventos novos NÃO serão inseridos
    Dado que exite um arquivo com eventos de perfil para um usuário expecífico
    E que não existe nenhum registro de perfil para um usuário expecífico
    E mongo ativado no Remote Config
    E horario atual dentro da tabela de manutencao do mongo
    Quando o sistema for iniciado
    Então os eventos processados não serão inseridos na collection perfil
  
   Cenário: Mongo em manutencao: Collection de Impressões NÃO será criada
    Dado que não existe a collection de impressões para o mês atual
    E que exite um arquivo com eventos de impressão para um usuário expecífico
    E mongo ativado no Remote Config
    E horario atual dentro da tabela de manutencao do mongo
    Quando o sistema for iniciado
    Então a collection de impressões do mês atual NÃO será criada
 
   Cenário: Mongo em manutencao: Collection de Impressões eventos NÃO serão inseridos
    Dado que já existe a collection de impressões para o mês atual
    E que não existe nenhum registro de impressão para um usuário expecífico
    E que exite um arquivo com eventos de impressão para um usuário expecífico
    E mongo ativado no Remote Config
    E horario atual dentro da tabela de manutencao do mongo
    Quando o sistema for iniciado
    Então os eventos processados NÃO serão inseridos na collection de impressões do mês atual   
   
   Cenário: Mongo em manutencao: Collection Segmentos NÃO será cria
    Dado que não existe a collection de segmentos para o mês atual
    E que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
    E mongo ativado no Remote Config
    E horario atual dentro da tabela de manutencao do mongo
    Quando o sistema for iniciado
    Então a collection de segmentos do mês atual NÃO será criada
    
   Cenário: Mongo em manutencao: Collection Segmentos eventos NÃO serão inseridos
    Dado que já existe a collection de segmentos para o mês atual
    E que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
    E que não existe nenhum registro de segmento para um usuário expecífico
    E mongo ativado no Remote Config
    E horario atual dentro da tabela de manutencao do mongo
    Quando o sistema for iniciado
    Então os eventos processados NÃO serão inseridos na collection de segmentos do mês atual
	
	
	