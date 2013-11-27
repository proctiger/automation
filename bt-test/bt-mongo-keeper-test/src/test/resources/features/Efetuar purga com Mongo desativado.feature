#language:pt
#author:wrodrigues
Funcionalidade: Efetuar purga com Mongo desativado

  Cenario: Solicitar purga de registros na collection <cookies>: mongo desativado no Remote Config
    Dado criar registros na collection <cookies> para purga: 1 ano passado
    Dado mongo desativado no Remote Config
    Quando solicitar purga
    Entao verificar purga não realizada na collection <cookies>
    
  Cenario: Solicitar purga de registros na collection <profile>: 1 ano passado
    Dado criar registros na collection <profile> para purga: 1 ano passado
    Dado mongo desativado no Remote Config
    Quando solicitar purga
    Entao verificar purga não realizada na collection <profile>

  Cenario: Solicitar purga de collections <views>
    Dado criar collections <views> para purga
    Dado mongo desativado no Remote Config
    Quando solicitar purga
    Entao verificar purga não realizada

  Cenario: Solicitar purga de collections <segments>
    Dado criar collections <segments> para purga
    Dado mongo desativado no Remote Config
    Quando solicitar purga
    Entao verificar purga não realizada
