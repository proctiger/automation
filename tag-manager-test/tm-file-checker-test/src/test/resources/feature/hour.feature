#language: pt
Funcionalidade: Verificar se está na hora da execução

  @MANUAL
  Cenário: Hora corrente é par e a execução está configurada apenas para horas pares
    Dado a hora corrente é par
    E a execução está configurada apenas para horas pares
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados

  @MANUAL
  Cenário: Hora corrente é ímpar e a execução está configurada apenas para horas pares
    Dado a hora corrente é ímpar
    E a execução está configurada apenas para horas pares
    Quando for iniciada a checagem de arquivos
    Então a execução não é iniciada
    E uma mensagem é registrada em log

  @MANUAL
  Cenário: Hora corrente é par e a execução está configurada apenas para horas ímpares
    Dado a hora corrente é par
    E a execução está configurada apenas para horas ímpares
    Quando for iniciada a checagem de arquivos
    Então a execução não é iniciada
    E uma mensagem é registrada em log

  @MANUAL
  Cenário: Hora corrente é ímpar e a execução está configurada apenas para horas ímpares
    Dado a hora corrente é ímpar
    E a execução está configurada apenas para horas ímpares
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados

  @MANUAL
  Cenário: Hora corrente é par e a execução está configurada para qualquer hora
    Dado a hora corrente é par
    E a execução está configurada para qualquer hora
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados

  @MANUAL
  Cenário: Hora corrente é ímpar e a execução está configurada para qualquer hora
    Dado a hora corrente é ímpar
    E a execução está configurada para qualquer hora
    Quando for iniciada a checagem de arquivos
    Então os arquivos configurados são verificados
