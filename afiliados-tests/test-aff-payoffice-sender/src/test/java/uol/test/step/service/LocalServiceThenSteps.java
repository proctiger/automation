package uol.test.step.service;

import org.junit.Assert;

import uol.test.util.SshUtil;
import cucumber.api.java.After;
import cucumber.api.java.pt.Entao;

public class LocalServiceThenSteps extends AbstractLocalServiceSteps {

    LocalServiceStepsImpl localServiceStepsImpl = new LocalServiceStepsImpl();
        
	@Entao("^o evento nao voltara para a fila$")
	public void o_registro_nao_estara_na_fila_com_os_dados_(){
		System.out.println("-> Entao o registro nao voltara para a fila");
		Assert.assertFalse(localServiceStepsImpl.checkQueueByPayOfficeEvent(payofficeEvent));
		System.out.println("<- Entao o registro nao voltara para a fila");
	}

	@Entao("^verificar o se foi logado o pagamento$")
	public void verificar_o_se_no_logado_o_pagamento_com_os_dados_() {
		System.out.println("-> Entao verificar o se foi logado o pagamento");
		Assert.assertTrue(containsInLog(payofficeEvent.toString()));
		System.out.println("<- Entao verificar o se foi logado o pagamento");
	}

    private boolean containsInLog(String event) {
        System.out.println("Procurando no log "+ event);
        try {
        	String sizeStr = SshUtil.execCommand("a1-jesse-s-prt1", "a", "wc -c /export/logs/aff-payoffice-sender/aff-payoffice-sender-success.log");
        	System.out.println("============= STRING COM TAMANHO OBTIDO APOS PROCESSAMENTO: " + sizeStr);
        	if(sizeStr != null && !sizeStr.isEmpty() && Long.parseLong(sizeStr.split(" ")[0]) > priorSize) {
        		String log = SshUtil.execCommand("a1-jesse-s-prt1", "a", String.format("cat /export/logs/aff-payoffice-sender/aff-payoffice-sender-success.log | grep %s", payofficeEvent.getIdtEventLog()));
        		System.out.println("============= LIDO: " + log);
        		System.out.println("============= Teste: " + event);
        		if(log.contains(event)) {
                	System.out.println("=================== Evento foi consumido na fila ====================== ");        
                	return true;
                }
        	}
        } catch (Exception e) {
            System.err.println(e);
            Assert.fail("Erro ao colher log!");
        }
		System.out.println("=================== Evento não foi consumido na fila ====================== ");        
		return false;
    }

	/*
	 * E se os registros com o codigo da transacao <cod_trans> foi processado E
	 * verificar se ha o idtperson do filho <idtperson_son>, e codigo de produto
	 * <prd_source> no registro de indicacao na tabela de eventos E verificar se
	 * no registro de indicacao do relatorio consta o valor de comissao
	 * <commission_value> para o afiliado <idtperson> E verificar se no registro
	 * de indicacao do extrato consta o valor de comissao <commission_value>
	 * para o afiliado <idtperson> E verificar se no registro da tabela de
	 * comissao consta o valor de comissao <commission_value> para o afiliado
	 * <idtperson_parent> E verificar se o saldo atual do afiliado <idtperson>
	 * foi atualizado com o valor de comissao <commission_value> sobre o valor
	 * <value>
	 */

	@After
	public void fimTeste() throws Exception {
		System.out.println("\n\n --------------------------------- Fim - Cenario --------------------------------- \n\n");
	}
}