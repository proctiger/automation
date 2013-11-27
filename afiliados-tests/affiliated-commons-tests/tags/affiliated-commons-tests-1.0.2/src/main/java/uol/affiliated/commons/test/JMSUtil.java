package uol.affiliated.commons.test;

import java.util.HashMap;

import javax.ws.rs.core.MediaType;

import uol.affiliated.commons.rest.client.RestServiceCaller;
import uol.affiliated.commons.rest.client.factory.RestServiceCallerFactory;


public class JMSUtil {

	protected static int code;

	public void removeAllMessages() {
		queueCommand("0");
	}

	public void resetMessageCounter() {
		queueCommand("1");
	}

	private void queueCommand(final String command) {
		RestServiceCaller request  = RestServiceCallerFactory.createRestServiceCaller("http://jms1.afiliados.intranet:8080", 1000);
		request.setQueryParameters(new HashMap<String, String>(1) {
			private static final long serialVersionUID = -940490002331971553L;
			{
				put("action","invokeOp");
				put("name","jboss.mq.destination:service=Queue,name=affiliatedPayofficeEventJms");
				put("methodIndex",command);
			}
		});
		try {
			code = request.doPost("http://jms1.afiliados.intranet:8080/jmx-console/HtmlAdaptor", MediaType.APPLICATION_FORM_URLENCODED_TYPE).getStatusCode();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	private String queueCommandwithReturn(final String command) {
		String body = new String();
		RestServiceCaller request  = RestServiceCallerFactory.createRestServiceCaller("http://jms1.afiliados.intranet:8080", 1000);
		request.setQueryParameters(new HashMap<String, String>(1) {
			private static final long serialVersionUID = -940490002331971553L;
			{
				put("action","invokeOp");
				put("name","jboss.mq.destination:service=Queue,name=affiliatedPayofficeEventJms");
				put("methodIndex",command);
			}
		});
		try {
			body = request.doPost("http://jms1.afiliados.intranet:8080/jmx-console/HtmlAdaptor", String.class, MediaType.APPLICATION_FORM_URLENCODED_TYPE);
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