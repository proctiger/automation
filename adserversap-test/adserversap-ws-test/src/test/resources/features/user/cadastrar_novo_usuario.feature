# language: pt
Funcionalidade: Inserir usuário
  * Testes relacionados ao serviço de inserção de usuários - PUT /user (Para logins novos)

  Cenario: Cadastrar usuário com login INVÁLIDO
    Dado um usuário novo com login inválido
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <400>
    E retorna erro com o código <user.login.format.is.invalid> e a mensagem <Parâmetro login com formato invalido>
    E não persiste os dados do usuário

  Cenario: Cadastrar usuário com perfil ROOT
    Dado um usuário novo com perfil <root>
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Cadastrar usuário com perfil ADMIN
    Dado um usuário novo com perfil <admin>
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Cadastrar usuário com perfil USER
    Dado um usuário novo com perfil <user>
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Cadastrar usuário com o mínimo de caracteres
    Dado um usuário novo com login <a>
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Cadastrar usuário com o máximo de caracteres
    Dado um usuário novo com login <maximo-de-64-caracteres-login-com-maximo-de-64-caracteres-com-64>
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Cadastrar usuário informando letras maiúsculas no login
    Dado um usuário novo com login <MaiusCUlo>
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Cadastrar usuário informando letras maiúsculas no perfil
    Dado um usuário novo com perfil <ADmiN>
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <204>
    E persiste os dados do usuário

  Cenario: Cadastrar usuário com login NULO
    Dado um usuário novo com login nulo
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <400>
    E retorna erro com o código <user.login.is.missing> e a mensagem <Parâmetro login não informado ou possui valor em branco>
    E não persiste os dados do usuário

  Cenario: Cadastrar usuário com o perfil NULO
    Dado um usuário novo com perfil nulo
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <400>
    E retorna erro com o código <user.profile.is.missing> e a mensagem <Parâmetro profile não informado ou possui valor em branco>
    E não persiste os dados do usuário

  Cenario: Cadastrar usuário com login EM BRANCO
    Dado um usuário novo com login em branco
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <400>
    E retorna erro com o código <user.login.is.missing> e a mensagem <Parâmetro login não informado ou possui valor em branco>
    E não persiste os dados do usuário

  Cenario: Cadastrar usuário com o perfil EM BRANCO
    Dado um usuário novo com perfil em branco
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <400>
    E retorna erro com o código <user.profile.is.missing> e a mensagem <Parâmetro profile não informado ou possui valor em branco>
    E não persiste os dados do usuário

  Cenario: Cadastrar usuário com o perfil INVÁLIDO
    Dado um usuário novo com perfil inválido
    Quando solicitar serviço de inserção de usuários
    Entao retorna status HTTP igual a <400>
    E retorna erro com o código <user.profile.format.is.invalid> e a mensagem <Parâmetro profile com formato inválido>
    E não persiste os dados do usuário

  Cenario: Cadastrar usuário com mais caracteres que o máximo permitido
    Dado um usuário novo com login <mais-de-64-caracteres-mais-de-64-caracteres-mais-de-64-caracteres>
    Quando solicitar serviço de inserção de usuários
    E retorna erro com o código <user.login.exceeds.max.length> e a mensagem <Parâmetro login possui número excedente de caracteres>
    E não persiste os dados do usuário
