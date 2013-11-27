#language:pt
#author:wrodrigues
Funcionalidade: Efetuar purga da collection de impressões

  Cenario: Solicitar purga de collections <views>
    Dado criar collections <views> para purga
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga

  Cenario: Solicitar purga de 20 collections <views>
    Dado criar <20> collections <views> para purga
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga

  Cenario: Solicitar purga de collection <views> mês atual
    Dado criar collections <views> mês atual
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga não realizada

  Cenario: Solicitar purga de collection <views> mês anterior
    Dado criar collections <views> mês anterior
    Dado mongo ativado no Remote Config
    Dado horario atual dentro da tabela de manutencao do mongo    
    Quando solicitar purga
    Entao verificar purga não realizada
