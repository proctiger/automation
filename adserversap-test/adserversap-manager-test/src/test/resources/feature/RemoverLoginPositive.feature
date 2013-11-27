#language: pt
Funcionalidade: Remover Login Cenarios Positivos

  Cenario: Positivo: Login Perfil <root>: Solicitar Remoção de  login perfil <root> e Confirmar
    Dado login não existente com perfil <root>
    E login de teste existente com perfil <root>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão remover login com perfil <root> e confirmar
	Entao mensagem de usuário removido com sucesso é exibida
	E o login <é> removido do banco
	
 Cenario: Positivo: Login Perfil <root>: Solicitar Remoção de  login perfil <admin> e Confirmar
    Dado login não existente com perfil <root>
    E login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão remover login com perfil <admin> e confirmar
	Entao mensagem de usuário removido com sucesso é exibida
	E o login <é> removido do banco

 Cenario: Positivo: Login Perfil <root>: Solicitar Remoção de  login perfil <user> e Confirmar
    Dado login não existente com perfil <root>
    E login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <root>
    Quando acionar botão remover login com perfil <user> e confirmar
	Entao mensagem de usuário removido com sucesso é exibida
	E o login <é> removido do banco
	
 Cenario: Positivo: Login Perfil <admin>: Solicitar Remoção de  login perfil <admin> e Confirmar
    Dado login não existente com perfil <admin>
    E login de teste existente com perfil <admin>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão remover login com perfil <admin> e confirmar
	Entao mensagem de usuário removido com sucesso é exibida
	E o login <é> removido do banco

 Cenario: Positivo: Login Perfil <admin>: Solicitar Remoção de  login perfil <user> e Confirmar
    Dado login não existente com perfil <admin>
    E login de teste existente com perfil <user>
    E login efetuado com sucesso com perfil <admin>
    Quando acionar botão remover login com perfil <user> e confirmar
	Entao mensagem de usuário removido com sucesso é exibida
	E o login <é> removido do banco


	