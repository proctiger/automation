package uol.test.step.service;

import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import uol.test.util.QueueService;
import cucumber.api.java.After;
import cucumber.api.java.pt.Entao;

public class LocalServiceThenSteps extends AbstractLocalServiceSteps  {

	private static final String SECOND_TIER = "2ndTier";
	private static final int PROCESSED = 1;
	private static final int NOT_PROCESSED = 0;
	private static final int URLNOTFOUND = 9;
        private static final String PAGAMENTO = "Pagamento";
	LocalServiceStepsImpl localServiceStepsImpl = new LocalServiceStepsImpl();
	
	
    @Entao("^verificar se existem <(.+)> de <(.+)> na fila$")
    public void verifyEventsToQueue(int numberOfEvents, String eventType) throws Throwable {
        System.out.println("-> Entao verificar se existem " + numberOfEvents + " de " + eventType + " na fila\n");
        QueueService queue = new QueueService();
        int number = 0;
        if (eventType.equals(PAGAMENTO)) {
            number = numberOfEvents * 2;
        }else{
            number = numberOfEvents;
        }
        Assert.assertEquals(number, queue.getListMessage());
        System.out.println("<- Entao verificar se existem " + numberOfEvents + " de " + eventType + " na fila");
    }
	
        @Entao("^verificar se os <(.+)> eventos de <(.+)> foram processados$")
        public void verificar_se_os_todos_registros_foram_processados(int numberOfEvents, String eventType) throws Throwable {
            System.out.println("-> Entao verificar se os "+numberOfEvents+" eventos de "+eventType+" foram processados \n");
            Assert.assertTrue(localServiceStepsImpl.checkProcessAllEvents(numberOfEvents , eventType));
            System.out.println("<- Entao verificar se os "+numberOfEvents+" eventos de "+eventType+" foram processados \n");
        }
	
        
        @Entao("^verificar se a quantidade de eventos de <(.+)> processados e menor que <(.+)>$")
        public void verifyProcessedEvents(String eventType, int numberOfEvents) throws Throwable {
            System.out.println("-> Entao verificar se a quantidade de eventos processados e menor que " + numberOfEvents +"\n");
            System.out.println("Esperando Processamento ser finalizado ... ");
            while(!eventProcessorRunnable.isFinished());
            System.out.println("Processamento finalizado , continuando verificação.");
            eventProcessorRunnable = null;
            int numberOfProcessedEvents = localServiceStepsImpl.getNumberOfProcessedEvents(eventType);
            System.out.println("Numero de eventos encontrados -> " + numberOfProcessedEvents);
            Assert.assertTrue((numberOfProcessedEvents < numberOfEvents) && (numberOfProcessedEvents > 0));
            System.out.println("<- Entao verificar se a quantidade de eventos processados e menor que " + numberOfEvents +"\n");
        }
	@Entao("^verificar se no registro de indicacao do extrato consta o valor de comissao <(.+)> para o afiliado pai <(.+)>$")
	public void verifyIndicationExtract(float commissionValue, int idtPersonParent) throws Throwable {
		System.out.println("\n-> Entao verificar se no registro de indicacao do extrato consta o valor de comissao " + commissionValue + " para o afiliado pai " + idtPersonParent);
		Assert.assertTrue(localServiceStepsImpl.checkBalance(idtPersonParent, commissionValue));
	    System.out.println("\n<- Entao verificar se no registro de indicacao do extrato consta o valor de comissao " + commissionValue + " para o afiliado pai " + idtPersonParent);
	}
	
	@Entao("^verificar se no registro de indicacao do relatorio consta o valor de comissao <(.+)> para o afiliado pai <(.+)>$")
	public void verifyIndicationReport(double commissionValue, long idtPersonParent) throws Throwable {
	System.out.println("\n-> Entao verificar se no registro de indicacao do relatorio consta o valor de comissao "+commissionValue+" para o afiliado pai" + idtPersonParent );
	Assert.assertTrue(localServiceStepsImpl.checkReport(commissionValue, idtPersonParent));
	System.out.println("\n<- Entao verificar se no registro de indicacao do relatorio consta o valor de comissao"+commissionValue+" para o afiliado pai" + idtPersonParent );	
	}

	@Entao("^verificar se ha o idtperson do filho <(.+)>, e codigo de produto <(.+)> no registro de indicacao na tabela de eventos$")
	public void verifyIndication(String idtPerson, int prdSource) throws Throwable {
		System.out.println("\n -> Entao verificar se ha o idtperson do filho " +idtPerson+ " e codigo de produto "+prdSource+" registro de indicacao na tabela de eventos");
		Assert.assertTrue(localServiceStepsImpl.checkEvent(idtPerson, prdSource));
		System.out.println("\n <- Entao verificar se ha o idtperson do filho " +idtPerson+ " e codigo de produto "+prdSource+" no registro de indicacao na tabela de eventos");
	}

	@Entao("^verificar se o saldo atual do afiliado pai <(.+)> foi atualizado com o valor de comissao <(.+)> sobre o valor <(.+)>$")
	public void verifyIndicationBalance(int idtPersonParent, float commissionValue, float value) throws Throwable {
		System.out.println("\n-> Entao verificar se o saldo atual do afiliado pai "+idtPersonParent+" foi atualizado com o valor de comissao "+commissionValue);
		float commission = localServiceStepsImpl.getIndicationComission(idtPersonParent,idtProductSourceEvent);
		float balance = actualNumBalance + commission;
		System.out.println("Valor anterior " + actualNumBalance +" mais "+ "Comissão " + commission + " = " + balance);
		Assert.assertEquals(balance , localServiceStepsImpl.getBalance(idtPersonParent), 0.02);
		System.out.println("\n<- Entao verificar se o saldo atual do afiliado pai "+idtPersonParent+" foi atualizado com o valor de comissao "+commissionValue);
	}
	
	@Entao("^verificar se no registro da tabela de comissao consta o valor de comissao <(.+)> para o <(.+)>$")
	public void verifyIndicationComission(float commissionValue, int idtPersonParent) throws Throwable {
		System.out.println("\n-> Entao verificar se no registro da tabela de comissao consta o valor de comissao "+commissionValue+" para o "+idtPersonParent);
		Assert.assertEquals(commissionValue , localServiceStepsImpl.getIndicationComission(idtPersonParent,idtProductSourceEvent), 0.02);
		System.out.println("\n<- Entao verificar se no registro da tabela de comissao consta o valor de comissao "+commissionValue+" para o "+idtPersonParent);
	}

	@Entao("^verificar se os registros com  o codigo da transacao <(.+)> foi processado com status <(.+)>$")
	public void verifyProcess(String codTransaction, int status) throws Throwable {
		System.out.println("\n-> Entao verificar se os registros com o codigo da transacao "+codTransaction+" foi processado com status="+status);
		//Map<String, Integer> statusMap  = localServiceStepsImpl.getStatusEvent(codTransaction);
		//for (Entry<String, Integer> statusNode : statusMap.entrySet()) {
		//	Assert.assertEquals(status , statusNode.getValue().intValue());
		//}
		Map<String, Integer> statusMap  = localServiceStepsImpl.getStatusEvent(codTransaction);
		int retry=0;
			if(statusMap.values().iterator().next()==0){
				do{
					retry++;
					System.out.println("Executando o Processor Novamente, tentativa = "+ retry);
			    	localServiceStepsImpl.startJobNow();
			    	statusMap=localServiceStepsImpl.getStatusEvent(codTransaction);	
				}while(retry<12 && statusMap.values().iterator().next()==0);
			}
			Assert.assertSame(status, statusMap.values().iterator().next());
		
		System.out.println("\n-> Entao verificar se os registros com o codigo da transacao "+codTransaction+" foi processado com status="+status);
	}
    
    @Entao("^verificar se o evento de indicacao nao foi processado e o de pagamento foi com  o codigo da transacao <(.+)>$")
	public void verifyNotProcess(String codTransaction) throws Throwable {
		System.out.println("\n-> verificar se o evento de indicacao nao foi processado e o de pagamento foi com  o codigo da transacao " + codTransaction);
		Map<String, Integer> statusMap  = localServiceStepsImpl.getStatusEvent(codTransaction);
		for (Entry<String, Integer> statusNode : statusMap.entrySet()) {
			if(statusNode.getKey().equalsIgnoreCase(SECOND_TIER)){
				Assert.assertEquals(URLNOTFOUND , statusNode.getValue().intValue());
			}else{
			Assert.assertEquals(PROCESSED , statusNode.getValue().intValue());
		    }
		}
		System.out.println("\n-> verificar se o evento de indicacao nao foi processado e o de pagamento foi com  o codigo da transacao " + codTransaction);
	}
    
    
    @Entao("^verificar se no registro do relatorio consta o valor de comissao <(.+)> para o afiliado <(.+)>$")
    public void verifyReport(double commissionValue, long idtPerson) throws Throwable {
    	System.out.println("\n-> Entao verificar se no registro do relatorio consta o valor de comissao "+commissionValue+" para o afiliado " + idtPerson);
    	Assert.assertTrue(localServiceStepsImpl.checkReport(commissionValue, idtPerson));
    	System.out.println("\n<- Entao verificar se no registro do relatorio consta o valor de comissao"+commissionValue+" para o afiliado " + idtPerson);	
    	}
    
    @Entao("^verificar se no registro do extrato consta o valor de comissao <(.+)> para o afiliado <(.+)>$")
    public void verifyExtract(float commissionValue, int idtPerson) throws Throwable {
    	System.out.println("\n-> Entao verificar se no registro do extrato consta o valor de comissao " + commissionValue + " para o afiliado " + idtPerson);
		Assert.assertTrue(localServiceStepsImpl.checkBalance(idtPerson, commissionValue));
	    System.out.println("\n<- Entao verificar se no registro do extrato consta o valor de comissao " + commissionValue + " para o afiliado " + idtPerson);
    }
    
    @Entao("^verificar se no registro da tabela de comissao consta o valor de comissao <(.+)> para o afiliado <(.+)>$")
    public void verifyComission(float commissionValue, int idtPerson) throws Throwable {
		System.out.println("\n-> Entao verificar se no registro da tabela de comissao consta o valor de comissao "+commissionValue+" para o "+idtPerson);
		Assert.assertEquals(commissionValue , localServiceStepsImpl.getCommission(idtPerson , desNameEvent, idtProductSourceEvent), 0.02);
		System.out.println("\n<- Entao verificar se no registro da tabela de comissao consta o valor de comissao "+commissionValue+" para o "+idtPerson);
	}
    
    
    @Entao("^verificar se o saldo atual do afiliado  <(.+)> foi atualizado com o valor de comissao <(.+)> sobre o valor <(.+)>$")
    public void verifyBalance(int idtPerson, float commissionValue, float value) throws Throwable {
		System.out.println("\n-> Entao verificar se o saldo atual do afiliado "+idtPerson+" foi atualizado com o valor de comissao "+commissionValue);
		float commission = localServiceStepsImpl.getCommission(idtPerson , desNameEvent, idtProductSourceEvent);
		float balance = actualNumBalance + commission;
		System.out.println("Valor anterior " + actualNumBalance +" mais "+ "Comissão " + commission + " = " + balance);
		Assert.assertEquals(balance , localServiceStepsImpl.getBalance(idtPerson), 0.02);
		System.out.println("\n<- Entao verificar se o saldo atual do afiliado  "+idtPerson+" foi atualizado com o valor de comissao "+commissionValue);
	}
    
    @Entao("^verificar se os registros com  o codigo da transacao <(.+)> nao foi processado$")
    public void verifyForNoProcess(String codTransaction) throws Throwable {
    	System.out.println("\n-> Entao verificar se os registros com o codigo da transacao "+codTransaction+" nao foi processado$");
		Map<String, Integer> statusMap  = localServiceStepsImpl.getStatusEvent(codTransaction);
		for (Entry<String, Integer> statusNode : statusMap.entrySet()) {
			Assert.assertEquals(NOT_PROCESSED , statusNode.getValue().intValue());
		}
		System.out.println("\n-> Entao verificar se os registros com o codigo da transacao "+codTransaction+" nao foi processado$");
    }
    
    @Entao("^verificar se no registro do relatorio nao consta o valor de comissao <(.+)> para o afiliado <(.+)>$")
    public void verifyForNoReport(float commissionValue, int idtPerson) throws Throwable {    
    	System.out.println("\n-> Entao verificar se no registro do relatorio nao consta o valor de comissao "+commissionValue+" para o afiliado " + idtPerson);
    	Assert.assertFalse(localServiceStepsImpl.checkReport(commissionValue, idtPerson));
    	System.out.println("\n<- Entao verificar se no registro do relatorio nao consta o valor de comissao"+commissionValue+" para o afiliado " + idtPerson);	
    }
    
    @Entao("^verificar se no registro do extrato nao consta o valor de comissao <(.+)> para o afiliado <(.+)>$")
    public void verifyForNoExtract(float commissionValue, int idtPerson) throws Throwable {
    	System.out.println("\n-> Entao verificar se no registro do extrato nao consta o valor de comissao " + commissionValue + " para o afiliado " + idtPerson);
		Assert.assertFalse(localServiceStepsImpl.checkBalance(idtPerson, commissionValue));
	    System.out.println("\n<- Entao verificar se no registro do extrato nao consta o valor de comissao " + commissionValue + " para o afiliado " + idtPerson);
    }
    @Entao("^verificar se o saldo atual do afiliado  <(.+)> nao foi atualizado com o valor de comissao <(.+)> sobre o valor <(.+)>$")
    public void verifyForNoBalanceUpdate(int idtPerson, float commissionValue, float value) throws Throwable {
    	System.out.println("\n-> Entao verificar se o saldo atual do afiliado "+idtPerson+" nao foi atualizado com o valor de comissao "+commissionValue);
		float commission = localServiceStepsImpl.getCommission(idtPerson , desNameEvent, idtProductSourceEvent);
		float balance = actualNumBalance + commission;
		System.out.println("Valor anterior " + actualNumBalance +" mais "+ "Comissão " + commission + " = " + balance);
		Assert.assertEquals(actualNumBalance , localServiceStepsImpl.getBalance(idtPerson), 0.02);
		System.out.println("\n<- Entao verificar se o saldo atual do afiliado  "+idtPerson+" nao foi atualizado com o valor de comissao "+commissionValue);
    }
    
    @Entao("^verificar se o registro esta na fila com agrupadores <(.+)>$")
    public void verificar_se_o_registro_esta_na_fila_com_agrupadores(String desGroupings){
    	 System.out.println("\n-> Entao verificar se o registro esta na fila com agrupadores " + desGroupings);
    	 Assert.assertTrue(localServiceStepsImpl.checkQueueByDesGrouping(desGroupings));
    	 System.out.println("\n<- Entao verificar se o registro esta na fila com agrupadores " + desGroupings);
    }
    
    @Entao("^verificar se o registro nao esta na fila com agrupadores <(.+)>$")
    public void verificar_se_o_registro_nao_esta_na_fila_com_agrupadores(String desGroupings){
   	 System.out.println("\n-> Entao verificar se o registro nao esta na fila com agrupadores " + desGroupings);
   	 Assert.assertFalse(localServiceStepsImpl.checkQueueByDesGrouping(desGroupings));
   	 System.out.println("\n<- Entao verificar se o registro nao esta na fila com agrupadores " + desGroupings);
   }
/*    E se os registros com  o codigo da transacao <cod_trans> foi processado 
    E verificar se ha o idtperson do filho <idtperson_son>, e codigo de produto <prd_source> no registro de indicacao na tabela de eventos
    E verificar se no registro de indicacao do relatorio consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se no registro de indicacao do extrato consta o valor de comissao <commission_value> para o afiliado <idtperson>
    E verificar se no registro da tabela de comissao consta o valor de comissao <commission_value> para o afiliado <idtperson_parent>
    E verificar se o saldo atual do afiliado <idtperson> foi atualizado com o valor de comissao <commission_value> sobre o valor <value>    */
    
    
	@After
	public void fimTeste() throws Exception {
		System.out.println("\n->@AFTER  Startando o aff-payoffice-sender "); //Garante que ao final do teste, o componente aff-payoffice-sender não ficará parado
	    localServiceStepsImpl.startComponentNow("aff-payoffice-sender", "jboss", "a1-jesse-s-prt1","a");		    
		System.out.println("\n\n --------------------------------- Fim - Cenario --------------------------------- \n\n");
	}

}