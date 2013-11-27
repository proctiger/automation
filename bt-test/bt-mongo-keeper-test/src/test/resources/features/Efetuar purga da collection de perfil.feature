#language:pt
#author:wrodrigues
Funcionalidade: Efetuar purga da collection de perfil

  Cenario: Solicitar purga de registros na collection <profile>: 1 dia passado
    Dado criar registros na collection <profile> para purga: 1 dia passado
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga de registros na collection <profile>

  Cenario: Solicitar purga de registros na collection <profile>: 1 mês passado
    Dado criar registros na collection <profile> para purga: 1 mês passado
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga de registros na collection <profile>

  Cenario: Solicitar purga de registros na collection <profile>: 1 ano passado
    Dado criar registros na collection <profile> para purga: 1 ano passado
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga de registros na collection <profile>

  Cenario: Solicitar purga de <5> registros na collection <profile>
    Dado criar <5> registros na collection <profile> para purga
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga de registros na collection <profile>

  Cenario: Solicitar purga inválida de registros collection <profile> mês atual
    Dado criar registro na collection <profile> mês atual
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga não realizada na collection <profile>
