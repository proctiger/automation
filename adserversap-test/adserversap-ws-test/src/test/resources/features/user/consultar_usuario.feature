# language: pt
Funcionalidade: Consultar usuário
  * Testes relacionados ao serviço de consulta de usuário - GET /user/{login}
  * Descrição dos valores de status:
  - Status 1 = ATIVO
  - Status 0 = INATIVO

  Cenario: Consultar usuário ATIVO com perfil ROOT
    Dado um usuário com status <1> e perfil <root>
    Quando solicitar serviço de consulta de usuário
    Entao retorna status HTTP igual a <200>
    E retorna os dados do usuário

  Cenario: Consultar usuário ATIVO com perfil ADMIN
    Dado um usuário com status <1> e perfil <admin>
    Quando solicitar serviço de consulta de usuário
    Entao retorna status HTTP igual a <200>
    E retorna os dados do usuário

  Cenario: Consultar usuário ATIVO com perfil USER
    Dado um usuário com status <1> e perfil <user>
    Quando solicitar serviço de consulta de usuário
    Entao retorna status HTTP igual a <200>
    E retorna os dados do usuário

  Cenario: Consultar usuário INATIVO com perfil ROOT
    Dado um usuário com status <0> e perfil <root>
    Quando solicitar serviço de consulta de usuário
    Entao retorna status HTTP igual a <404>
    E retorna erro com o código <user.not.found> e a mensagem <Usuário não encontrado>

  Cenario: Consultar usuário INATIVO com perfil ADMIN
    Dado um usuário com status <0> e perfil <admin>
    Quando solicitar serviço de consulta de usuário
    Entao retorna status HTTP igual a <404>
    E retorna erro com o código <user.not.found> e a mensagem <Usuário não encontrado>

  Cenario: Consultar usuário INATIVO com perfil USER
    Dado um usuário com status <0> e perfil <user>
    Quando solicitar serviço de consulta de usuário
    Entao retorna status HTTP igual a <404>
    E retorna erro com o código <user.not.found> e a mensagem <Usuário não encontrado>

  Cenario: Consultar usuário inexistente
    Dado um usuário inexistente
    Quando solicitar serviço de consulta de usuário
    Entao retorna status HTTP igual a <404>
    E retorna erro com o código <user.not.found> e a mensagem <Usuário não encontrado>
