package uol.test.step.validation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.json.JSONObject;

import uol.affiliated.commons.rest.client.exception.BadRequestServiceException;
import uol.affiliated.commons.test.CucumberTestUtil;
import uol.affiliated.commons.test.TestStep;
import uol.affiliated.commons.test.exception.StepException;
import cucumber.api.java.pt.Quando;

public class IndicationWhenSteps extends AbstractIndicationSteps {

    @Quando("^o servico <(.+)> for solicitado atraves da url com produto <(.+)> e afiliado <(.+)>$")
	public void callService(final String service, final String product, final String user) {
		CucumberTestUtil.when(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				jsonp = false;
				call(service, product, user);
			}
		});
	}
	
	@Quando("^o status do servico <(.+)> for solicitado atraves da url com produto <(.+)> e afiliado <(.+)>$")
	public void callServiceStatus(final String service, final String product, final String user) {
		CucumberTestUtil.when(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				jsonp = false;
				callStatus(service, product, user);
			}
		});
	}
	
	@Quando("^o status do servico <(.+)> for solicitado atraves da url com produto <(.+)>, afiliado <(.+)> e callback <(.+)>$")
	public void callServiceStatusWithCallback(final String service, final String product, final String user, final String callback) {
		CucumberTestUtil.when(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				IndicationWhenSteps.request.setQueryParameters(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("callback", callback);
					}
				});
				jsonp = true;
				IndicationWhenSteps.callback = callback;
				callStatus(service, product, user);
			}
		});
	}
	
	@Quando("^o servico <(.+)> for solicitado atraves da url com produto <(.+)>, afiliado <(.+)> e callback <(.+)>$")
	public void callServiceWithCallback(final String service, final String product, final String user, final String callback) {
		CucumberTestUtil.when(new TestStep() {
			public void execute(PrintWriter printer) throws StepException {
				IndicationWhenSteps.request.setQueryParameters(new HashMap<String, String>() {
					private static final long serialVersionUID = 1L;
					{
						put("callback", callback);
					}
				});
				jsonp = true;
				IndicationWhenSteps.callback = callback;
				call(service, product, user);
			}
		});
	}
	
	@Quando("^o status do servico <(.+)> for solicitado$")
	public void callStatus(final String service) throws StepException {
		try {
			IndicationWhenSteps.request.setPathParameters(new HashMap<String, String>() {
				private static final long serialVersionUID = 1L;
				{
					put("service", service);
				}
			});
			IndicationWhenSteps.status = IndicationWhenSteps.request.doGet(String.format("%s/{service}", requestURI)).getStatusCode();
		} catch(BadRequestServiceException e2) {
			IndicationWhenSteps.status = 400;
		} catch (Exception e1) {
			throw new StepException(e1);
		} finally {
			try {
				request.close();
			} catch (IOException e) {
				throw new StepException(e);
			}
		}
	}

	private void call(final String service, final String product,
			final String user) throws StepException {
		IndicationWhenSteps.request.setPathParameters(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("product", product);
				put("service", service);
				put("user",    user);
			}
		});
		String result = IndicationWhenSteps.request.doGet(String.format("%s/{product}/{service}/{user}", requestURI), String.class, MediaType.APPLICATION_JSON_TYPE);
		IndicationWhenSteps.redir = IndicationWhenSteps.request.getHeaderValue("Location");
		try {
			request.close();
		} catch (IOException e) {
			throw new StepException(e);
		} finally {
			if(jsonp) {
				Matcher matcher = Pattern.compile(String.format("(%s\\()(\\{.+\\})(\\))", IndicationWhenSteps.callback)).matcher(result);
				if(matcher.find()) {
					result = matcher.group(2);
				}
			}
			try {
			    if(result == null || result.isEmpty()){
			        IndicationWhenSteps.json = null;  
			    }else{
			    IndicationWhenSteps.json = new JSONObject(result);
			    }
			} catch (JSONException e) {
				IndicationWhenSteps.json = null;
			}
		}
	}
	
	private void callStatus(final String service, final String product, final String user) throws StepException {
		IndicationWhenSteps.request.setPathParameters(new HashMap<String, String>() {
			private static final long serialVersionUID = 1L;
			{
				put("product", product);
				put("service", service);
				put("user",    user);
			}
		});
		try {
			IndicationWhenSteps.status = IndicationWhenSteps.request.doGet(String.format("%s/{product}/{service}/{user}", requestURI)).getStatusCode();
			IndicationWhenSteps.redir = IndicationWhenSteps.request.getHeaderValue("Location");
		} catch(BadRequestServiceException e2) {
			IndicationWhenSteps.status = 400;
		} catch (Exception e1) {
			throw new StepException(e1);
		} finally {
			try {
				request.close();
			} catch (IOException e) {
				throw new StepException(e);
			}
		}
	}
}
