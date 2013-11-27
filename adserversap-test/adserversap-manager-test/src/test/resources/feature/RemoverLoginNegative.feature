#language: pt
Funcionalidade: Remover Login Cenarios Negativos
	
  Cenario: Negativo: Login Perfil <root>: Solicitar Remoção de  login perfil root e Cancelar
    Dado login não existente com perfil <root>
    E login de teste existente com perfil <root>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão remover login com perfil <root> e cancelar
	Entao o login <não é> removido do banco
	
  Cenario: Negativo: Login Perfil <root>: Solicitar Remoção de  login perfil <admin> e Cancelar
    Dado login não existente com perfil <root>
    E login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão remover login com perfil <admin> e cancelar
	Entao o login <não é> removido do banco
	
  Cenario: Negativo: Login Perfil <root>: Solicitar Remoção de  login perfil <user> e Cancelar
    Dado login não existente com perfil <root>
    E login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão remover login com perfil <user> e cancelar
	Entao o login <não é> removido do banco
  
  Cenario: Negativo: Login Perfil <admin>: Solicitar Remoção de  login perfil <user> e Cancelar
    Dado login não existente com perfil <admin>
    E login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão remover login com perfil <user> e cancelar
	Entao o login <não é> removido do banco

Cenario: Negativo: Login Perfil <admin>: Solicitar Remoção de login perfil <admin> e Cancelar
    Dado login não existente com perfil <admin>
    E login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão remover login com perfil <admin> e cancelar
	Entao o login <não é> removido do banco
	
  Cenario: Negativo: Login Perfil <admin>: NÃO é possível remover login perfil <root> 
    Dado login não existente com perfil <admin>
    E login de teste existente com perfil <root>
    Quando efetuar login com sucesso com perfil <admin>
    Entao verificar que não é possível remover o usuário
    
  Cenario: Negativo: Login Perfil <admin>: NÃO é possível remover proprio login 
    Quando efetuar login com sucesso com perfil <admin>
    Entao verificar que não é possível remover o próprio usuário

  Cenario: Negativo: Login Perfil <root>: NÃO é possível remover proprio login 
    Quando efetuar login com sucesso com perfil <root>
    Entao verificar que não é possível remover o próprio usuário


	