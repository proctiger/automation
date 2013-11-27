package uol.test.step.service;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import uol.affiliated.commons.jms.client.JMSEventSender;
import uol.test.util.SshUtil;
import cucumber.api.java.pt.Quando;

public class LocalServiceWhenSteps extends AbstractLocalServiceSteps {

    @Autowired
    private JMSEventSender jmsEventSender;

    @Quando("^o evento for enviado para a pagadoria$")
	public void sendToQueue() {
        System.out.println("-> Quando o evento for enviado para a pagadoria");
        System.out.println("Obtendo tamanho inicial do log de sucesso ... ");
		try {
			String sizeStr = SshUtil.execCommand("a1-jesse-s-prt1", "a", "wc -c /export/logs/aff-payoffice-sender/aff-payoffice-sender-success.log");
			if(sizeStr == null || sizeStr.isEmpty()) {
				priorSize = 0;
			} else {
				priorSize = Long.parseLong(sizeStr.split(" ")[0]);
			}
		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}
    	System.out.println("============= TAMANHO DO ARQUIVO: " + priorSize);
        try {
	        System.out.println("Enviando evento ... " + payofficeEvent.toString());
	        System.out.println("Enviando evento (XML) ... " + payofficeEvent.toXml());
	        jmsEventSender.send(payofficeEvent.toXml());
        } catch (Exception e) {
            System.err.println(e);
            Assert.fail("Não foi possivel enviar o evento para fila");
        }
        System.out.println("<- Quando o evento for enviado para a pagadoria");
	}
}