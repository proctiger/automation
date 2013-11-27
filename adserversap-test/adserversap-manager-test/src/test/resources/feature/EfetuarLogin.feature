#language: pt
Funcionalidade: Efetuar Login

  Cenario: Efetuar login com usuário root
    Quando efetuar login com um usuário de perfil <root>
    Entao o login é efetuado com sucesso
    E a opção de gerenciamento de usuários <é> exibida no menu
    E a opção de debug de serviços <é> exibida no menu

  Cenario: Efetuar login com usuário moderador
    Quando efetuar login com um usuário de perfil <admin>
    Entao o login é efetuado com sucesso
    E a opção de gerenciamento de usuários <é> exibida no menu
    E a opção de debug de serviços <não é> exibida no menu

  Cenario: Efetuar login com usuário comum
    Quando efetuar login com um usuário de perfil <user>
    Entao o login é efetuado com sucesso
    E a opção de gerenciamento de usuários <não é> exibida no menu
    E a opção de debug de serviços <não é> exibida no menu

  Cenario: Não informar login
    Quando efetuar login sem informar login
    Entao o login não é efetuado
    E é exibido o seguinte erro na área de login: <Preencha usuário e senha>

  Cenario: Não informar senha
    Quando efetuar login sem informar senha
    Entao o login não é efetuado
    E é exibido o seguinte erro na área de login: <Preencha usuário e senha>

  Cenario: Não informar login nem senha
    Quando efetuar login sem informar login nem senha
    Entao o login não é efetuado
    E é exibido o seguinte erro na área de login: <Preencha usuário e senha>

  Cenario: Informar senha inválida
    Quando efetuar login informando senha inválida
    Entao o login não é efetuado
    E é exibido o seguinte erro na área de login: <Ocorreu um erro ao efetuar o login>

  Cenario: Efetuar login com usuário inativo
    Quando efetuar login com um usuário inativo
    Entao o login não é efetuado
    E é exibido o seguinte erro na área de login: <Ocorreu um erro ao efetuar o login>

  Cenario: Efetuar login com usuário inexistente no sistema
    Quando efetuar login com um usuário inexistente no sistema
    Entao o login não é efetuado
    E é exibido o seguinte erro na área de login: <Ocorreu um erro ao efetuar o login>

  Cenario: Efetuar login com usuário inexistente no radius
    Quando efetuar login com um usuário inexistente no radius
    Entao o login não é efetuado
    E é exibido o seguinte erro na área de login: <Ocorreu um erro ao efetuar o login>
