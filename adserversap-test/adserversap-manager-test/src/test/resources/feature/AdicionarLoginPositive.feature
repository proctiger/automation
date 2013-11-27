#language: pt
Funcionalidade: Adicionar Login Cenarios Positivos

  Cenario: Positivo: Login Perfil root: Adicionar login com perfil root
    Dado login não existente com perfil <root>
    E login efetuado com sucesso com perfil <root>
    Quando adicionar login com perfil <root>
	Entao mensagem de usuário criado com sucesso é exibida
	E o login <é> criado com sucesso no banco
	E o login é criado com sucesso no banco com perfil <root>

  Cenario: Positivo: Login Perfil root: Adicionar login com perfil admin
    Dado login não existente com perfil <root>
    E login efetuado com sucesso com perfil <admin>
    Quando adicionar login com perfil <admin>
	Entao mensagem de usuário criado com sucesso é exibida
	E o login <é> criado com sucesso no banco
	E o login é criado com sucesso no banco com perfil <admin>
	
  Cenario: Positivo: Login Perfil root: Adicionar login com perfil user
    Dado login não existente com perfil <user>
    E login efetuado com sucesso com perfil <root>
    Quando adicionar login com perfil <user>
	Entao mensagem de usuário criado com sucesso é exibida
	E o login <é> criado com sucesso no banco
	E o login é criado com sucesso no banco com perfil <user>

  Cenario: Positivo: Login Perfil admin: Adicionar login com perfil admin
    Dado login não existente com perfil <root>
    E login efetuado com sucesso com perfil <admin>
    Quando adicionar login com perfil <admin>
	Entao mensagem de usuário criado com sucesso é exibida
	E o login <é> criado com sucesso no banco
	E o login é criado com sucesso no banco com perfil <admin>
	
  Cenario: Positivo: Login Perfil admin: Adicionar login com perfil user
    Dado login não existente com perfil <user>
    E login efetuado com sucesso com perfil <admin>
    Quando adicionar login com perfil <user>
	Entao mensagem de usuário criado com sucesso é exibida
	E o login <é> criado com sucesso no banco
	E o login é criado com sucesso no banco com perfil <user>
	
  