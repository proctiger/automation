package uol.test.util;

import javax.annotation.Resource;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@MessageDriven(name = "affiliatedInscriptionConsumer")
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class TopicListener implements MessageListener {

    private static final Logger logger = LoggerFactory.getLogger(TopicListener.class);

    @Resource
    private MessageDrivenContext context;
    
    @Override
    public void onMessage(Message message) {
        try {
            TextMessage textMessage = ((TextMessage) message);
            String msg = textMessage.getText();
            System.out.println(msg);
        } catch (Exception e) {
            logger.error("Erro ao processar mensagem. Devolvendo para a fila [{}]. Erro: ", message, e);
            context.setRollbackOnly();
        }
    }

    public void setContext(MessageDrivenContext context) {
        this.context = context;
    }
}