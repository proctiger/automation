#language:pt
#author:dvrocha
Funcionalidade: Atualizar cookie de segmentos

	Cenário: Não há nenhum registro para o usuário e existem arquivos de impressão
	Dado que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Não há nenhum registro para o usuário e existem arquivos de perfil
	Dado que exite um arquivo com eventos de perfil para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Não há nenhum registro para o usuário e existem arquivos de impressão e perfil
	Dado que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
	E que exite um arquivo com eventos de perfil para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Não há nenhum registro para o usuário e existem arquivos de impressão sem URLs cadastradas
	Dado que exite um arquivo com eventos de impressão para um usuário expecífico com URLs não classificadas
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então o campo updated será atualizado na collection de cookies
	
	Cenário: Só existe registro de cookie para o usuário e existem arquivos de impressao
	Dado que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que já existe algum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Só existe registro de cookie para o usuário e existem arquivos de perfil
	Dado que exite um arquivo com eventos de perfil para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que já existe algum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Só existe registro de cookie para o usuário e existem arquivos de impressão e perfil
	Dado que exite um arquivo com eventos de impressão que configuram em segmentos para um usuário expecífico
	E que exite um arquivo com eventos de perfil para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que já existe algum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Só existe registro de cookie para o usuário e existem arquivos de impressão sem URLs cadastradas
	Dado que exite um arquivo com eventos de impressão para um usuário expecífico com URLs não classificadas
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que já existe algum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então o campo updated será atualizado na collection de cookies
	
	Cenário: Não há nenhum registro para o usuário e existem arquivos de impressão que configuram em segmentos grudentos
	Dado que exite um arquivo com eventos de impressão que configuram em segmentos grudentos para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Não há nenhum registro para o usuário e existem arquivos de perfil e impressão que configuram em segmentos grudentos
	Dado que exite um arquivo com eventos de impressão que configuram em segmentos grudentos para um usuário expecífico
	E que exite um arquivo com eventos de perfil para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de segmento para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Só existe registro de segmentos grudentos para o usuário e existem arquivos de impressão que configuram em segmentos grudentos
	Dado que exite um arquivo com eventos de impressão que configuram em segmentos grudentos para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	E que já existe algum registro de segmento grudento para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Só existe registro de segmentos grudentos para o usuário e existem arquivos de perfil 
	Dado que exite um arquivo com eventos de perfil para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	E que já existe algum registro de segmento grudento para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Só existe registro de segmentos grudentos para o usuário e existem arquivos de perfil e impressão que configuram em segmentos grudentos
	Dado que exite um arquivo com eventos de impressão que configuram em segmentos grudentos para um usuário expecífico
	E que exite um arquivo com eventos de perfil para um usuário expecífico
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	E que já existe algum registro de segmento grudento para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies
	
	Cenário: Só existe registro de segmentos grudentos para o usuário e existem arquivos de de impressão sem URLs cadastradas
	Dado que exite um arquivo com eventos de impressão para um usuário expecífico com URLs não classificadas
	E que não existe nenhum registro de impressão para um usuário expecífico
	E que não existe nenhum registro de perfil para um usuário expecífico
	E que não existe nenhum registro de cookie para um usuário expecífico
	E que já existe algum registro de segmento grudento para um usuário expecífico
	Quando o sistema for iniciado
	Então os eventos processados serão inseridos na collection de cookies