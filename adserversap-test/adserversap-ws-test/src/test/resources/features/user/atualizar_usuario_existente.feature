# language: pt
Funcionalidade: Atualizar dados do usuário
  * Testes relacionados ao serviço de atualização de usuários - PUT /user (Para logins já existentes)

  Cenario: Atualizar perfil do usuário para ROOT
    Dado um usuário com status <1> e perfil <admin>
    E pretendendo atualizar o perfil para <root>
    Quando solicitar serviço de atualização de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Atualizar perfil do usuário para ADMIN
    Dado um usuário com status <1> e perfil <root>
    E pretendendo atualizar o perfil para <admin>
    Quando solicitar serviço de atualização de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Atualizar perfil do usuário para USER
    Dado um usuário com status <1> e perfil <admin>
    E pretendendo atualizar o perfil para <user>
    Quando solicitar serviço de atualização de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Atualizar usuário INATIVO
    Dado um usuário com status <0> e perfil <admin>
    E pretendendo atualizar o perfil para <root>
    Quando solicitar serviço de atualização de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário
