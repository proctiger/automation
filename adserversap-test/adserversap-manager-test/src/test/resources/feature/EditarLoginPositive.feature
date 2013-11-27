#language: pt
Funcionalidade: Editar Login Cenarios Positivos

   Cenario: Positivo: Login Perfil <admin>: Alterar login de perfil <admin> para <user>
    Dado login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão editar de login com perfil <admin>
    E alterar login para perfil <user> e salvar
	Entao mensagem de usuário alterado com sucesso é exibida
	E o login é alterado com sucesso no banco com perfil <user>

   Cenario: Positivo: Login Perfil <admin>: Alterar login de perfil <user> para <admin>
    Dado login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão editar de login com perfil <user>
    E alterar login para perfil <admin> e salvar
	Entao mensagem de usuário alterado com sucesso é exibida
	E o login é alterado com sucesso no banco com perfil <admin>
	
   Cenario: Positivo: Login Perfil <root>: Alterar login de perfil <root> para <user>
    Dado login de teste existente com perfil <root>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <root>
    E alterar login para perfil <user> e salvar
	Entao mensagem de usuário alterado com sucesso é exibida
	E o login é alterado com sucesso no banco com perfil <user>
  
   Cenario: Positivo: Login Perfil <root>: Alterar login de perfil <root> para <admin>
    Dado login de teste existente com perfil <root>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <root>
    E alterar login para perfil <admin> e salvar
	Entao mensagem de usuário alterado com sucesso é exibida
	E o login é alterado com sucesso no banco com perfil <admin>
	
   Cenario: Positivo: Login Perfil <root>: Alterar login de perfil <admin> para <user>
    Dado login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <admin>
    E alterar login para perfil <user> e salvar
	Entao mensagem de usuário alterado com sucesso é exibida
	E o login é alterado com sucesso no banco com perfil <user>

   Cenario: Positivo: Login Perfil <root>: Alterar login de perfil <admin> para <root>
    Dado login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <admin>
    E alterar login para perfil <root> e salvar
	Entao mensagem de usuário alterado com sucesso é exibida
	E o login é alterado com sucesso no banco com perfil <root>
	
   Cenario: Positivo: Login Perfil <root>: Alterar login de perfil <user> para <admin>
    Dado login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <user>
    E alterar login para perfil <admin> e salvar
	Entao mensagem de usuário alterado com sucesso é exibida
	E o login é alterado com sucesso no banco com perfil <admin>
	
   Cenario: Positivo: Login Perfil <root>: Alterar login de perfil <user> para <root>
    Dado login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <user>
    E alterar login para perfil <root> e salvar
	Entao mensagem de usuário alterado com sucesso é exibida
	E o login é alterado com sucesso no banco com perfil <root>
	
	