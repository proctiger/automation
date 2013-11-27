package uol.test.step.service;


import org.junit.Assert;

import com.google.gson.Gson;

import uol.test.util.Event;
import uol.test.util.QueueService;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;

public class LocalServiceGivenSteps extends AbstractLocalServiceSteps {
    
    private static final String PAGAMENTO = "Pagamento";
    private static final String SECOND_TIER = "2ndTier";
   
    LocalServiceStepsImpl localServiceStepsImpl = new LocalServiceStepsImpl();

	 @Before
	 public void inicioTeste() throws Exception {
		 System.out.println("\n\n --------------------------------- Inicio - Cenario --------------------------------- \n\n");
		 startQueue();
	 }

	 @Dado("^que o componente payoffice esteja fora$")
         public void downPayOffice(){
             System.out.println("\n->Dado que o componente payoffice esteja fora");
             localServiceStepsImpl.stopComponentNow("aff-payoffice-sender", "jboss", "a1-jesse-s-prt1","a");                
             System.out.println("\n<-Dado que o componente payoffice esteja fora");
         }
	 
	@Dado("^que não exista eventos na fila$")
	public void removeAllEventsToQueue(){
	    System.out.println("\n->Dado que não exista eventos na fila");
	    QueueService queue = new QueueService();
	    queue.removeAllMessages();
	    queue.resetMessageCounter();
	    System.out.println("\n<-Dado que não exista eventos na fila");
	}
	 
	 @Dado("^que não exista eventos de <(.+)>$")
         public void removeEvents(String eventsType){
	     eventTypeForTestCase = eventsType;
             System.out.println("\n->Dado que não exista eventos de" + eventsType);
             localServiceStepsImpl.removeEvents(eventsType);
             if(eventsType.equals(PAGAMENTO)){
             localServiceStepsImpl.removeEvents(SECOND_TIER);
             }
             System.out.println("\n<-Dado que não exista eventos de" + eventsType);
         }
         
         @Dado("^que <(.+)> eventos sejam gerados , modelo <(.+)>$")
         public void generateEvents(int numberOfEvents, String eventJson){
             System.out.println("\n->Dado que "+ numberOfEvents +" eventos sejam gerados");
             Gson gson = new Gson();
             Event event = gson.fromJson(eventJson, Event.class);
             localServiceStepsImpl.generateEvents(numberOfEvents, event);
             System.out.println("\n<-Dado que "+ numberOfEvents +" eventos sejam gerados");
         }
	 
	 @Dado("^que exista uma relacao de indicacao entre afiliado pai <(.+)> e afiliado filho <(.+)>$")
	 	public void checkIndicationRelationship(String idtPersonParent,String idtPersonSon){
		    System.out.println("\n->Dado que exista uma relacao de indicacao entre afiliado pai e afiliado filho");
		 	if (localServiceStepsImpl.checkForParent(idtPersonParent,idtPersonSon)){
			System.out.println(String.format("O afiliado filho %s foi indicado pelo afiliado pai %s", idtPersonSon,idtPersonParent));
			}else{
					Assert.fail(String.format("O afiliado filho %s não foi indicado pelo afiliado pai %s", idtPersonSon,idtPersonParent));
			}
		 	System.out.println("\n<-Dado que exista uma relacao de indicacao entre afiliado pai e afiliado filho");
	 }
	 
	 @Dado("^que exista um evento de comissionamento com os dados <(.*)>, <(.*)>, <(.*)> e <(.*)>$")
	 public void setupPreCondition(String idtPerson, String desName,String codTransaction ,String prdSource){
		    desNameEvent = desName;
		    idtProductSourceEvent = Integer.parseInt(prdSource);
	            System.out.println("\n->Dado que a fila esteja em execução\n");
		    localServiceStepsImpl.startComponentNow("affiliated-jms-queue", "jboss", "a1-jane-s-prt1","a");
		    System.out.println("\n->Dado que o aff-payoffice-sender não esteja em execução\n"); 
		    localServiceStepsImpl.stopComponentNow("aff-payoffice-sender", "jboss", "a1-jesse-s-prt1","a");		    
		    System.out.println("\n Deletando registro de evento pelo codigo de transacao " + codTransaction);
			localServiceStepsImpl.deleteEventFromAeEventLog(codTransaction);
			System.out.println("\n Deletando registro do Relatório do afiliado "+idtPerson+" e código do produto "+prdSource);
			localServiceStepsImpl.deleteEventFromAeReportEvent(idtPerson,prdSource);
			System.out.println("\n Deletando registro do Extrato do afiliado  "+idtPerson);
			localServiceStepsImpl.deleteEventFromAeBalanceEvent(idtPerson);
			//System.out.println("\n Deletando registro da Comissão do afiliado "+idtPerson);
			//localServiceStepsImpl.deleteEventFromCommissionEvent(idtPerson);
			System.out.println("\n Guardando o saldo atual do afiliado  "+idtPerson);
			localServiceStepsImpl.getActualBalanceFromUserSupplier(idtPerson);
			System.out.println("\n<- Dado que exista um evento de comissionamento\n");
		
	 
	 }
	 
	 @Dado("^que a fila esteja em execução$")
         public void startQueue(){
                    System.out.println("\n->Dado que a fila esteja em execução\n");
                    localServiceStepsImpl.startComponentNow("affiliated-jms-queue", "jboss", "a1-jane-s-prt1","a");
         }
         
	 
	
	 
}