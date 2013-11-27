# language: pt
Funcionalidade: Consultar lista de usuários
  Testes relacionados ao serviço de consulta da lista de usuários - GET /user

  Cenario: Consultar lista de usuários
    Quando solicitar serviço de lista de usuários
    Entao retorna status HTTP igual a <200>
    E retorna a lista de usuários ordenada por login