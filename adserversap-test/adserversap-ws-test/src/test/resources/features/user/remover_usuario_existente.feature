# language: pt
Funcionalidade: Remover usuário
  * Testes relacionados ao serviço de remoção de usuários - DELETE /user/{login}
  * Descrição dos valores de status:
  - Status 1 = ATIVO
  - Status 0 = INATIVO

  Cenario: Remover usuário ATIVO
    Dado um usuário com status <1>
    Quando solicitar serviço de remoção de usuário
    Entao retorna status HTTP igual a <204>
    E atualiza o status do usuário para <0>

  Cenario: Remover usuário INATIVO
    Dado um usuário com status <0>
    Quando solicitar serviço de remoção de usuário
    Entao retorna status HTTP igual a <404>
    E retorna erro com o código <user.not.found> e a mensagem <Usuário não encontrado>

  Cenario: Remover usuário inexistente
    Dado um usuário inexistente
    Quando solicitar serviço de remoção de usuário
    Entao retorna status HTTP igual a <404>
    E retorna erro com o código <user.not.found> e a mensagem <Usuário não encontrado>
