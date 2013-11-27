#language:pt
#author:wrodrigues
Funcionalidade: Efetuar purga da collection de segmentos

  Cenario: Solicitar purga de collections <segments>
    Dado criar collections <segments> para purga
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga

  Cenario: Solicitar purga de 20 collections <segments>
    Dado criar <20> collections <segments> para purga
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga

  Cenario: Solicitar purga de collection <segments> mês atual
    Dado criar collections <segments> mês atual
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga não realizada

  Cenario: Solicitar purga de collection <segments> mês anterior
    Dado criar collections <segments> mês anterior
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga não realizada
