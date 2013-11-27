# language: pt
Funcionalidade: Validar Probe

  Esquema do Cenario: Efetuar validacao no probe
    Quando efetuar a validacao no <tipoProbe> probe
    Entao verifique que o resultado obtido do slb probe e igual ao codigo <codigo>
    
    Exemplos:
    | tipoProbe   | codigo |
    | < >         | 200    |
    | <monitor->  | 200    |
    | <romero->   | 404    |