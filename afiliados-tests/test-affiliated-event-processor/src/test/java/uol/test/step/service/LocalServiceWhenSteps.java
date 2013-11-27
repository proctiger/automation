package uol.test.step.service;

import java.util.Date;

import cucumber.api.Format;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;

public class LocalServiceWhenSteps extends AbstractLocalServiceSteps {
		
    LocalServiceStepsImpl localServiceStepsImpl = new LocalServiceStepsImpl();

    @Quando("^for simulado um pagamento de <(.+)> no dia <(.+)> com <(.+)> ,valor <(.+)>, afiliado <(.+)>, agrupadores <(.+)> , ordem de pagamento <(.+)> e codigo de transacao <(.+)>$")
    public void setPaymentData(String desName, @Format("dd-MM-yyyy") Date date,	String prdSource, float values, String idtPerson,String desGrouping, String payOrder, String codTrans) {
        System.out.println("\n ->Quando for simulado um  pagamento de "+desName+" no dia "+date+" com "+prdSource+" ,valores "+values+", afiliado "+idtPerson+", agrupadores "+desGrouping+" , ordem de pagamento "+payOrder.toString()+" e codigo de transacao "+codTrans);
        localServiceStepsImpl.simulatePaymentData(desName, date,values,idtPerson,desGrouping,payOrder,codTrans,prdSource);
        System.out.println("\n <-Quando for simulado um  pagamento de "+desName+" no dia "+date+" com "+prdSource+" ,valores "+values+", afiliado "+idtPerson+", agrupadores "+desGrouping+" , ordem de pagamento "+payOrder.toString()+" e codigo de transacao "+codTrans);
    }
    	
    @Quando("^o processador de eventos for executado$")
    public void startProcessor() {
    	System.out.println("\n -> Quando o processador de eventos for executado\n");
    	System.out.println("Executando o Processor");
    	localServiceStepsImpl.startJobNow();
    	System.out.println("\n <- Quando o processador de eventos for executado\n");
    }
    	
    @Quando("^a fila estiver inoperavel$")
    public void SimulateQueueInoperability(){
		System.out.println("\n -> Quando a fila estiver inoperavel\n");
		System.out.println("Parando a fila");
		localServiceStepsImpl.stopComponentNow("affiliated-jms-queue", "jboss", "a1-jane-s-prt1","a");
		System.out.println("\n <- Quando a fila estiver inoperavel\n");
    }
    
    @Quando("^o processador de eventos for executado de forma paralela$")
    public void startProcessorParallelly() {
        eventProcessorRunnable = new EventProcessorRunnable();
        new Thread(eventProcessorRunnable).start();
    }
    
    @Quando("^a fila estiver inoperavel depois de <(.+)> milisegundos$")
    public void SimulateQueueInoperability(long mili) {
        System.out.println("\n -> Quando a fila estiver inoperavel\n");
        while(!eventProcessorRunnable.isStarted()){
            try {
                Thread.sleep(mili);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int numberOfProcessedEvents = localServiceStepsImpl.getNumberOfProcessedEvents(eventTypeForTestCase);
            if(numberOfProcessedEvents > 0 ){
                break;
            }else{
            eventProcessorRunnable.setStarted(false);
            }
        }
        System.out.println("Parando a fila");
        localServiceStepsImpl.stopComponentNow("affiliated-jms-queue", "jboss", "a1-jane-s-prt1", "a");
        System.out.println("\n <- Quando a fila estiver inoperavel\n");
    }
    
    
}