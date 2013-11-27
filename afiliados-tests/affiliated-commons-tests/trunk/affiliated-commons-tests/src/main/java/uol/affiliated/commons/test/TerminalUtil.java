package uol.affiliated.commons.test;

import java.io.IOException;
import java.io.PrintWriter;

import org.junit.Assert;

/**
 * @author cin_rrsilva
 * 
 */

public class TerminalUtil {

	private PrintWriter writer;

	private TerminalUtil(PrintWriter writer) {
		this.writer = writer;
	}
	
	public static TerminalUtil getInstance(PrintWriter writer) {
		return new TerminalUtil(writer);
	}
	
	public void appStop(String applicationName, String container, String host,String pwd){
		writer.println("Stoping " + applicationName + " ....");
		try {
			SshUtil.exec(host, applicationName, container, "stop",pwd);
		} catch (IOException e) {
			Assert.fail(String.format("\n Erro ao tentar parar o " +applicationName, e.getLocalizedMessage()));
		} catch (InterruptedException e) {
			Assert.fail(String.format("\n Erro ao tentar parar o " +applicationName, e.getLocalizedMessage()));		
		}
		writer.println("Stopped " + applicationName + ".");
	}
	
	public void appStart(String applicationName, String container, String host,String pwd){
		writer.println("Stoping " + applicationName + " ....");
		try {
			SshUtil.exec(host, applicationName, container, "start",pwd);
		} catch (IOException e) {
			Assert.fail(String.format("\n Erro ao tentar iniciar o " +applicationName, e.getLocalizedMessage()));
		} catch (InterruptedException e) {
			Assert.fail(String.format("\n Erro ao tentar iniciar o " +applicationName, e.getLocalizedMessage()));		
		}
		writer.println("Stopped " + applicationName + ".");
	}

	public void appStartAndWait(String applicationName, String container, String host,String pwd){
		writer.println("Starting " + applicationName + " ....");
		try {
			SshUtil.exec(host, applicationName, container, "start",pwd);
		} catch (IOException e) {
			Assert.fail(String.format("\n Erro ao tentar parar o " +applicationName, e.getLocalizedMessage()));
		} catch (InterruptedException e) {
			Assert.fail(String.format("\n Erro ao tentar parar o " +applicationName, e.getLocalizedMessage()));		
		}
		writer.println("Started " + applicationName + ".");
	}

	public void jobStop(String jobFileName, String action, String application, String host)  throws Exception {
		writer.println("Stopping " + application + " ....");
		SshUtil.execJob(host, application, action, jobFileName);
		writer.println("Stopped " + application + ".");
	}

	public void jobStart(String jobFileName, String action, String application, String host){
		writer.println("Starting " + application + " ....");
		try {
			SshUtil.execJob(host, application, action, jobFileName);
		} catch (IOException e) {
			Assert.fail(String.format("\n Erro ao tentar executar o " +application, e.getLocalizedMessage()));
		} catch (InterruptedException e) {
			Assert.fail(String.format("\n Erro ao tentar executar o " +application, e.getLocalizedMessage()));
		}
		writer.println("Started " + application + ".");
	}
}