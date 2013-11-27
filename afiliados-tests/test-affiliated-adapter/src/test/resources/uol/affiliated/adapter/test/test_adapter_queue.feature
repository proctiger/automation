# language: pt
Funcionalidade: Envio/Consumo de registros para fila

  Cenario: Verificar se a quantidade de registros com FLG = 0 esta diminuindo
  	Quando existirem registros com FLG_AFF_QUEU = 0
    Entao os registros devem ser enviados pra fila e o nro de registros com FLAG_AFF_QUEU=0 deve ser 0
    E o nro de registros na tabela da fila deve ser <= ao resultado da quantidade de items a serem enviados
    
  Cenario: Verificar se o rollback está sendo feito