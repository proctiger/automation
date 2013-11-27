package uol.test.step.service;

import javax.xml.bind.JAXBException;

import uol.test.util.PayofficeEvent;
import uol.test.util.QueueService;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;
public class LocalServiceGivenSteps extends AbstractLocalServiceSteps
{

	@Before
	public void inicioTeste() throws Exception {
		 
		System.out.println("\n\n --------------------------------- Inicio - Cenario --------------------------------- \n\n");
		
		 
		startComponentNow("affiliated-jms-queue", "jboss", "a1-jane-s-prt1","a");
		
	}
	
	@Dado("^que seja simulado um pagamento com os dados <(.+)>$")
	public void setupPreCondition(String eventXml) throws JAXBException 
	{
	    System.out.println("-> Dado que seja simulado um pagamento com os dados" + eventXml);
	     
	    payofficeEvent = PayofficeEvent.from(eventXml);
	    
	    System.out.println("<- Dado que seja simulado um pagamento com os dados" + eventXml);
	}
	
	@Dado("^que a data do sistema seja levada em consideracao como idtEventLog$")
	public void setupPreCondition() throws JAXBException 
	{
	    System.out.println("-> E que a data do sistema seja levada em consideracao como idtEventLog");
	     
	    Long idtEventLog = Long.valueOf(Long.toString(System.currentTimeMillis()).substring(0, 9));
	    System.out.println(String.format("========================idtEventLog = [%d] =============================", idtEventLog));
	    payofficeEvent.setIdtEventLog(idtEventLog);
	    
	    System.out.println("<- E que a data do sistema seja levada em consideracao como idtEventLog");
	}
	
	@Dado("^a fila esteja em execucao$")
	public void setupPreConditionFila()
	{
		System.out.println("==================  Execução da Fila ==================");
		
		startComponentNow("affiliated-jms-queue", "jboss", "a1-jane-s-prt1","a");
		
		System.out.println("==================  Execução da Fila ==================");		
	}
	

	@Dado("^o consumidor da fila esteja em execucao$")
	public void setupPreConditionMDB()
	{
		System.out.println("==================  Execução do MDB ==================");
		
		startComponentNow("aff-payoffice-sender", "jboss", "a1-jesse-s-prt1","a");
		
		System.out.println("==================  Execução do MDB==================");		
	}
	
	@Dado("^que nao exista eventos na fila$")
	public void removeAllEventsToQueue(){
	    System.out.println("\n->Dado que não exista eventos na fila");
	    QueueService queue = new QueueService();
	    queue.removeAllMessages();
	    queue.resetMessageCounter();
	    System.out.println("\n<-Dado que não exista eventos na fila");
	}
}