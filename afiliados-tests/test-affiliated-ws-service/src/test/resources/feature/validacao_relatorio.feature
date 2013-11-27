# language: pt
Funcionalidade: Validar Servico de Relatorio

 Esquema do Cenario: Fluxo Principal - Validacao de Servico de Relatorio
    Dado que nao exista dados de relatorio para o afiliado <afiliado>
    E que exista as informacoes do usuario no relatorio:   #inserção nas tabelas ae_report_event_grouping  e ae_report_event_dimension
    |codPromotion|datSubscription|idtInscription|namLogin|offerDescription|
    |WEBCONCPUBLICO|15-11-2013|48416417|testeqacarlos2@bol.com.br|UOL CONCURSOS PÚBLICOS R$ 79,90 NA|
    |WEBCONCPUBLICO|15-11-2013|48416417|testeqacarlos2@bol.com.br|UOL CONCURSOS PÚBLICOS R$ 79,90 NA| 
    E que exista as informacoes no relatorio: #inserção na tabela ae_report_balance
    | datEvent  | idtProductSource |desName  |numAmount|numCommissionValue|afiliado  |
    | 25/10/2013| 10               | Cadastro|1       |10                  |testeauto1|
    | 26/10/2013| 10               | Cadastro|1       |15                  |testeauto1|
    Quando for feita uma chamada ao servico de afiliados (relatorio) com <report_request> para o afiliado <afiliado>  #http://affiliated-ws.sys.srv.intranet/service/report/generate/{idtPerson} POST
    E for feita uma chamada ao servico de afiliados (detalhe cadastro) para o afiliado <afiliado> com data <date> #http://affiliated-ws.sys.srv.intranet/service/report/indication/subscription/detail/131694745/12-10-2013 GET
    Entao o resultado da chamada (relatorio) sera correspondente aos dados de entrada
	E o resultado da chamada (detalhe cadastro) sera igual aos dados de entrada

    Exemplos: 
      | afiliado   | date|report_request|                                                                                                                                                                      |date|report_request|
      | testeauto1 | 25/10/2013|{"startDate":25/10/2013,"endDate":25/10/2013,"OrderBy":datevent,"OrderType":ASC,"Type":10}|

      

#Alternativa
  Esquema do Cenario: Fluxo Principal - Validacao de Servico de Relatorio
    Dado que nao exista dados de relatorio para o afiliado <afiliado>
    E que exista as informacoes do usuario <usuario_info> no relatorio:
    E que exista as informacoes do afiliado <afiliado_info> no relatorio
    Quando for feita uma chamada ao servico de afiliados (relatorio) com <report_request> para o afiliado <afiliado>  #http://affiliated-ws.sys.srv.intranet/service/report/generate/{idtPerson} POST
    E for feita uma chamada ao servico de afiliados (detalhe cadastro) para o afiliado <afiliado> com data <date> #http://affiliated-ws.sys.srv.intranet/service/report/indication/subscription/detail/131694745/12-10-2013 GET
    Entao o resultado da chamada (relatorio) sera igual ao <afiliado_info> e <usuario_info>
	E o resultado da chamada (detalhe cadastro) sera igual ao <usuario_info>

    Exemplos: 
      | afiliado   | afiliado_info                                                                                                           | usuario_info                                                                                                                                                                      |date|report_request|
      | testeauto1 | {"datEvent":25/10/2013,"idtProductSource":10,"desName":Cadastro,"numAmount":1,"numValue":0,"numCommissionValue":10.00} | {"codPromotion":WEBCONCPUBLICO,"datSubscription":15-11-2013,"idtInscription":48416417,"namLogin":testeqacarlos2@bol.com.br,"offerDescription":UOL CONCURSOS PÚBLICOS R$ 79,90 NA} |25/10/2013|{"startDate":25/10/2013,"endDate":25/10/2013,"OrderBy":datevent,"OrderType":ASC,"Type":10}|

      
      
##http://affiliated-ws.sys.srv.intranet/service/report/generate/131694745 POST      
      
#DATA
#<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
#<report-request>
#  <start-date>11/10/2013</start-date>
#  <end-date>16/10/2013</end-date>
#  <order-by>datevent</order-by>
#  <order-type>ASC</order-type>
#  <types>
#    <type>10</type>
#  </types>
#</report-request>

#RESPONSE
#<?xml version="1.0" encoding="UTF-8" standalone="yes" ?>
# <result>
#<service-status>SUCCESS</service-status>
#<total-impression>0</total-impression>
#<total-click>0</total-click>
#<total-subscription>28</total-subscription>
#<total-payment>28</total-payment>
#<average-ctr>-</average-ctr>
#<total-value>280.0</total-value>
# <lines>
#<line>
#<dat-event>2013-10-12T00:00:00-03:00</dat-event>
#<num-impression>0</num-impression>
#<num-click>0</num-click>
#<num-subscription>14</num-subscription>
#<num-payment>14</num-payment>
#<num-ctr>-</num-ctr>
#<win-value>140.0</win-value>
# </line>
# <line>
#<dat-event>2013-10-15T00:00:00-03:00</dat-event>
#<num-impression>0</num-impression>
#<num-click>0</num-click>
#<num-subscription>14</num-subscription>
#<num-payment>14</num-payment>
#<num-ctr>-</num-ctr>
#<win-value>140.0</win-value>
 #</line>
 #</lines>
 #</result>
 
 
 
 ##################################################################
 
 #http://affiliated-ws.sys.srv.intranet/service/report/indication/subscription/detail/131694745/12-10-2013 Get
 
 
#RESPONSE
# <result>
#<service-status>SUCCESS</service-status>
#<lines>
#<line>
#<nam-login>8uzl5ZUaWLb@uol.com.br</nam-login>
#<offer-title>UOL HOST DOMÍNIOS</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>daniel.tiefenbarher@uol.com.br</nam-login>
#<offer-title>UOL HOST HOSPEDAGEM TOP ANUAL</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>jks@uol.com.br</nam-login>
#<offer-title>UOL HOST BLOG</offer-title>
#payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>E-mail Marketing I</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>
#UOL HOST CLOUD ESSENCIAL 4 GB + SQL SERVER WEB 2012
#0/offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>UOL HOST CONECTA</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>UOL HOST E-MAIL EXCHANGE C/ 1 CX POSTAL</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>UOL HOST E-MAIL PROFISSIONAL ECONÔMICO</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>
#UOL HOST LOJA DE APLICATIVOS - CONTA AZUL - INITIAL
#</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>UOL HOST OFFICE 365 ONLINE - 5 USUÁRIOS</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>UOL HOST PAINEL DO ADVOGADO II</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>UOL HOST PLANO DE REVENDA II - TRIMESTRAL</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>redis10@uol.com.br</nam-login>
#<offer-title>UOL HOST PLANO SQL SERVER 2005 I - MENSAL</offer-title>
#<payment-value>0</payment-value>
#</line>
#<line>
#<nam-login>uolhost@uol.com.br</nam-login>
#<offer-title>UOL HOST HOSPEDAGEM FIT MENSAL</offer-title>
<#payment-value>0</payment-value>
#</line>
#</lines>
#</result>
 