#language:pt
#author:wrodrigues
Funcionalidade: Efetuar purga da collection de cookies

  Cenario: Solicitar purga de registros na collection <cookies>: 1 dia passado
    Dado criar registros na collection <cookies> para purga: 1 dia passado
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga de registros na collection <cookies>

  Cenario: Solicitar purga de registros na collection <cookies>: 1 mês passado
    Dado criar registros na collection <cookies> para purga: 1 mês passado
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga de registros na collection <cookies>

  Cenario: Solicitar purga de registros na collection <cookies>: 1 ano passado
    Dado criar registros na collection <cookies> para purga: 1 ano passado
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga de registros na collection <cookies>

  Cenario: Solicitar purga de <5> registros na collection <cookies>
    Dado criar <5> registros na collection <cookies> para purga
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga de registros na collection <cookies>

  Cenario: Solicitar purga inválida de registros collection <cookies> mês atual
    Dado criar registro na collection <cookies> mês atual
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga não realizada na collection <cookies>
