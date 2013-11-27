#language: pt
Funcionalidade: Editar Login Cenarios Negativos

 Cenario: Negativo: Login Perfil <admin>: Cancelar alteração de login de perfil <user> para <admin>
    Dado login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão editar de login com perfil <user>
    E alterar login para perfil <admin> e cancelar
	Entao o login é alterado com sucesso no banco com perfil <user>

 Cenario: Negativo: Login Perfil <admin>: Cancelar alteração de login de perfil <user> para <admin>
    Dado login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão editar de login com perfil <user>
    E alterar login para perfil <admin> e cancelar
	Entao o login é alterado com sucesso no banco com perfil <user>

  Cenario: Negativo: Login Perfil <admin>: Cancelar alteração de login de perfil <admin> para <user>
    Dado login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão editar de login com perfil <admin>
    E alterar login para perfil <user> e cancelar
	Entao o login é alterado com sucesso no banco com perfil <admin>

   Cenario: Negativo: Login Perfil <admin>: Alterar login de perfil <admin> para <root>
    Dado login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão editar de login com perfil <admin> 
	Entao perfil root não é exibido para seleção 
	
   Cenario: Negativo: Login Perfil <admin>: Alterar login de perfil <user> para <root>
    Dado login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão editar de login com perfil <user>
	Entao perfil root não é exibido para seleção 

   Cenario: Negativo: Login Perfil <root>: Cancelar alteração de login de perfil <admin> para <user>
    Dado login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <admin>
    E alterar login para perfil <user> e cancelar
	Entao o login é alterado com sucesso no banco com perfil <admin>

  Cenario: Negativo: Login Perfil <root>: Cancelar alteração de login de perfil <admin> para <root>
    Dado login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <admin>
    E alterar login para perfil <root> e cancelar
	Entao o login é alterado com sucesso no banco com perfil <admin>

  Cenario: Negativo: Login Perfil <root>: Cancelar alteração de login de perfil <user> para <admin>
    Dado login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <user>
    E alterar login para perfil <admin> e cancelar
	Entao o login é alterado com sucesso no banco com perfil <user>

  Cenario: Negativo: Login Perfil <root>: Cancelar alteração de login de perfil <user> para <root>
    Dado login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão editar de login com perfil <user>
    E alterar login para perfil <root> e cancelar
	Entao o login é alterado com sucesso no banco com perfil <user>
