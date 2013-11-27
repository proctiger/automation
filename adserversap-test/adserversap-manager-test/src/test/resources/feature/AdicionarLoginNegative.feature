#language: pt
Funcionalidade: Adicionar Login Cenarios Negativos
	
  Cenario: Negativo: Preencher campo login com tags html para perfil user
    Dado login efetuado com sucesso com perfil <root>
    Quando adicionar login com perfil <user> com tags html
	Entao mensagem de erro do campo login com tags html é exibida
	E o login <não é> criado com sucesso no banco
	
  Cenario: Negativo: Preencher campo login com caracteres inválidos para perfil user
    Dado login efetuado com sucesso com perfil <root>
    Quando adicionar login com perfil <user> com caracter inválido
	Entao mensagem de erro do campo login com caracteres inválidos é exibida
	E o login <não é> criado com sucesso no banco
	
	
  Cenario: Negativo: Usuário com perfil ADMIN NÃO é capaz de criar usuário ROOT
    Dado login não existente com perfil <root>
    E login efetuado com sucesso com perfil <admin>
    Quando acessar página para criação de login
	Entao perfil root não é exibido para seleção
	
  Cenario: Negativo: Preencher campo login com tags html para perfil root
    Dado login efetuado com sucesso com perfil <root>
    Quando adicionar login com perfil <root> com tags html
	Entao mensagem de erro do campo login com tags html é exibida
	E o login <não é> criado com sucesso no banco
	
  Cenario: Negativo: Preencher campo login com caracteres inválidos para perfil root
    Dado login efetuado com sucesso com perfil <root>
    Quando adicionar login com perfil <root> com caracter inválido
	Entao mensagem de erro do campo login com caracteres inválidos é exibida
	E o login <não é> criado com sucesso no banco
	
  Cenario: Negativo: Não preencher campo obrigatório Login para perfil root
    Dado login efetuado com sucesso com perfil <root>
    Quando adicionar login com perfil <root> sem preencher campo login
	Entao mensagem de erro do campo login não preenchido é exibida

  Cenario: Negativo: Preencher campo login com tags html para perfil admin
    Dado login efetuado com sucesso com perfil <admin>
    Quando adicionar login com perfil <admin> com tags html
	Entao mensagem de erro do campo login com tags html é exibida
	E o login <não é> criado com sucesso no banco
	
  Cenario: Negativo: Preencher campo login com caracteres inválidos para perfil admin
    Dado login efetuado com sucesso com perfil <admin>
    Quando adicionar login com perfil <admin> com caracter inválido
	Entao mensagem de erro do campo login com caracteres inválidos é exibida
	E o login <não é> criado com sucesso no banco
	
  Cenario: Negativo: Não preencher campo obrigatório Login para perfil admin
    Dado login efetuado com sucesso com perfil <admin>
    Quando adicionar login com perfil <admin> sem preencher campo login
	Entao mensagem de erro do campo login não preenchido é exibida

 
	
  Cenario: Negativo: Não preencher campo obrigatório Login para perfil user
    Dado login efetuado com sucesso com perfil <root>
    Quando adicionar login com perfil <user> sem preencher campo login
	Entao mensagem de erro do campo login não preenchido é exibida
	