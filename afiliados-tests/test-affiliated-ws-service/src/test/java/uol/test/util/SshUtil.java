package uol.test.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author cin_rrsilva
 * 
 */

public class SshUtil {
	
	public static void exec(String host, String application, String container, String action) throws IOException, InterruptedException{
		String[] comando = {"bash","-c","sshpass -p 'a' ssh afiliados@" + host + " 'sudo /etc/init.d/" + container + " " + action + " " + application + "'"};
		//String[] comando = {"bash","-c","/usr/local/bin/sshpass -p 'a' /usr/bin/ssh afiliados@" + host + ".host.intranet 'sudo /etc/init.d/" + container + " " + action + " " + application + "'"};
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
