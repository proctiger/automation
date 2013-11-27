package uol.affiliated.commons.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author cin_rrsilva
 * 
 */

class SshUtil {

	public static void exec(String host, String application, String container, String action,String pwd) throws IOException, InterruptedException{
		String[] comando = {"bash","-c","/usr/local/bin/sshpass -p '"+pwd+"' /usr/bin/ssh -t -t afiliados@" + host + ".host.intranet 'sudo /etc/init.d/" + container + " " + action + " " + application + "'"};
		System.out.println("Comando sendo executado : " + comando[2]);
		Process p = Runtime.getRuntime().exec(comando);
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line=null;
		while((line=input.readLine()) != null) {
			System.out.println(line);
		}
		int exitVal = p.waitFor();
		if(exitVal != 0){
			System.out.println("Erro executar comando,  codigo  " + exitVal);
		}
	}

	public static String execCommand(String host, String pass, String command) throws IOException, InterruptedException{
		String[] comando = {"bash","-c"," sshpass -p '"+pass+"' ssh -t -t afiliados@" + host + ".host.intranet '"+command+"'"};
		System.out.println("Comando sendo executado : " + comando[2]);
		Process p = Runtime.getRuntime().exec(comando);
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line=null;
		StringBuilder returnCommand =  new StringBuilder();
		while((line=input.readLine()) != null) {
			returnCommand.append(line);
		}
		int exitVal = p.waitFor();
		if(exitVal != 0){
			System.out.println("Erro ao executar o comando,  codigo  " + exitVal);
		}
		return returnCommand.toString();
	}

	public static void execJob(String host,String application, String action, String jobFileName) throws IOException, InterruptedException{
		String[] comando = {"bash","-c","/usr/local/bin/sshpass -p 'a' /usr/bin/ssh afiliados@" + host + ".host.intranet 'sudo /opt/" + application + "/scripts/" + jobFileName + " " + action +"'"};
		System.out.println("Comando sendo executado : " + comando[2]);
		Process p = Runtime.getRuntime().exec(comando);
		BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String line=null;
		while((line=input.readLine()) != null) {
			System.out.println(line);
		}
		int exitVal = p.waitFor();
		if(exitVal != 0){
			System.out.println("Erro executar comando,  codigo  " + exitVal);
		}
	}
}