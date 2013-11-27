package uol.test.util;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;
import org.junit.Assert;

import uol.test.step.service.AbstractLocalServiceSteps;
import uol.test.util.ServiceRequest;


public class QueueService {


    protected static int code;

        public void removeAllMessages() {
            queueCommand("0");
        }
        
        public void resetMessageCounter() {
            queueCommand("1");
        }
        

        
        private void queueCommand(final String command) {
            ServiceRequest request = new ServiceRequest();
            request.setBody(new HashMap<String, String>(1) {
                private static final long serialVersionUID = -940490002331971553L;
                {
                    put("action","invokeOp");
                    put("name","jboss.mq.destination:service=Queue,name=affiliatedPayofficeEventJms");
                    put("methodIndex",command);
                }
            });
            
            try {
                code = request.doPost("http://affiliated-jms-queue.host:8080/jmx-console/HtmlAdaptor",MediaType.APPLICATION_FORM_URLENCODED_TYPE).getStatusCode();
            } catch (Exception e) {
                System.out.println(e);
              
            }
        }

        private String queueCommandwithReturn(final String command) {
           String body = new String();
            ServiceRequest request = new ServiceRequest();
            request.setBody(new HashMap<String, String>(1) {
                private static final long serialVersionUID = -940490002331971553L;
                {
                    put("action","invokeOp");
                    put("name","jboss.mq.destination:service=Queue,name=affiliatedPayofficeEventJms");
                    put("methodIndex",command);
                }
            });
            
            try {
                body = request.doPostReturnBody("http://affiliated-jms-queue.host:8080/jmx-console/HtmlAdaptor",MediaType.APPLICATION_FORM_URLENCODED_TYPE);
            } catch (Exception e) {
                System.out.println(e);
                
            }
            return body;
        }
        
        
        public int getListMessage() {
            String body = queueCommandwithReturn("4");
            int message =  Integer.parseInt(body.split("<td>")[12].split("</td>")[0]);
            return message;
        }

    }
