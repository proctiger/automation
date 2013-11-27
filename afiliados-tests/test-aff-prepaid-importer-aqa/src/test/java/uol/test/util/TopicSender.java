package uol.test.util;

import java.util.Properties;

import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.InitialContext;

public class TopicSender {

  //  private AppConfiguration configuration;
    
    private static String connectionFactoryName;

    private static String queueName;

    private static Properties properties;

    public void send(String message) throws Exception {
    	TopicConnection conn = null;
        TopicSession session = null;
        TopicPublisher publisher = null;
        try {
            InitialContext ctx = new InitialContext();
            TopicConnectionFactory factory = (TopicConnectionFactory)  ctx.lookup("ConnectionFactory");
            conn = factory.createTopicConnection("guest", "guest");
            session = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            conn.start();
            Topic topic = (Topic) ctx.lookup("topic/TopicoGenerico");
            publisher = session.createPublisher(topic);
            TextMessage om = session.createTextMessage(message);
            publisher.publish(om);
        } finally {
            if (publisher != null) {
            	publisher.close();
            }
            if (session != null) {
                session.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }
    
    /*private long calculateQueueDeliveryTime() {
        return System.currentTimeMillis() + configuration.getQueueScheduledDeliveryMillis();
    }*/

    public void setConnectionFactoryName(String connectionFactoryName) {
        this.connectionFactoryName = connectionFactoryName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

   /* public void setConfiguration(AppConfiguration configuration) {
        this.configuration = configuration;
    }*/
}