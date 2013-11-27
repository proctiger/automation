package uol.affiliated.adapter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import junit.framework.Assert;
import cucumber.annotation.pt.*;

import javax.swing.JOptionPane;

public class AdapterQueueSteps extends BaseSteps {
	
	//Verifica a quantidade de registros com FLG_AFF_QUEUE = 0 e armazena na variavel result
	@Quando("^existirem registros com FLG_AFF_QUEU = 0")
	public void test_one() throws SQLException{
		try{
			conn = getConnection();
	        stmt = conn.createStatement();   
	        
	        //TODO: Update flags -> 1 e Inserts
	        
	        /*try{
	        	System.out.println("Setando os valores de FLG_AFF_QUEUE para Zero");
		        rs = stmt.executeQuery(UPDATE_FLG_QUEUE_ZERO);
	        }catch(SQLException ex){
	        	
	        }*/
	        
	        rs = stmt.executeQuery(SELECT_COUNT_FLG_QUEUE);
	        rs.next();
	        result = rs.getInt("total");
	        System.out.println("Não foram enviados para fila: " + result);
	        
	        rs = stmt.executeQuery(SELECT_COUNT_JMS_MESSAGES);
			rs.next();
			at_queue = rs.getInt("total");
			System.out.println("Quantidade de registros que estão na fila: " + at_queue);
	        
		}catch(SQLException ex){
			Assert.fail("Falha ao tentar obter a conexao com o banco de dados.");
		}finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (stmt != null) {
                try {
                	stmt.close();
                } catch (SQLException e) { /* ignored */}
            }
            if (conn != null) {                try {
                    conn.close();
                } catch (SQLException e) { /* ignored */}
            }
        }
	}
	
	/* -- Atribui o valor de result para result2;
	 * -- Dentro de um laço, utiliza o mesmo select que a anotacao @Quando e verifica se o valor de result 2 chega a 0; 
	 * -- Durante os selects, se o valor de result2 for o mesmo durante 'n' tentativas, entao o laco e interrompido  */
	
	@Entao("^os registros devem ser enviados pra fila e o nro de registros com FLAG_AFF_QUEU=0 deve ser (\\d+)")
	public void test_two(int expected) throws InterruptedException, SQLException, IOException{
		
		//TODO: Startar o JBoss via codigo em ambientes diferentes (staging e QA)
		/**
	      * pause for 4 seconds = 4000
	      * pause for 1 minute  = 60000
	      * pause for 5 minutes = 300000
	      * 
	      */
		//Thread.sleep(1000);
		
		/*Scanner sc = new Scanner(System.in);
        System.out.println("Iniciando console");
        System.out.println("Digite o comando para iniciar o JBOSS: ");
        //String comando = sc.nextLine(); // ler string
        String comando = JOptionPane.showInputDialog("Digite o comando para iniciar o JBOSS:");
        System.out.println(comando);   
        Runtime.getRuntime().exec(comando);*/
		
		
		Process pJboss = Runtime.getRuntime().exec("cmd /c start C:/JBoss/jboss-4.2.3.GA/bin/run.bat");
		//System.out.print(pJboss);	
		  
		  result2 = result;
		  
		  try{
			  conn = getConnection();
		      stmt = conn.createStatement();
				  try{
					  do{
						  if(last_result != result2)
							  last_result = result2;
						  else if(last_result == result2){
							  cont++;
							  Thread.sleep(6000);
						  }
						  				 
						  if(cont == ATTEMPS && result2 == last_result){
							  System.out.println("Limite de tentativas atigindo");
							  break;
						  }
						  else{
							  Thread.sleep(6000);
							  rs = stmt.executeQuery(SELECT_COUNT_FLG_QUEUE);
							  rs.next();
							  result2 = rs.getInt("total");
							  if(result2 != last_result)
								  cont = 0;
						  }
						  rs = stmt.executeQuery(SELECT_COUNT_JMS_MESSAGES);
						  rs.next();
						  at_queue = rs.getInt("total");
						  System.out.println("- Falta enviar " + result2 + " item(s)" +
								  			" - Registros na fila: " + at_queue);
					  }while (result2 > expected);
				  Assert.assertEquals(expected, result2); 
				         
				  }catch (SQLException ex){
					  System.out.println("Os registros não foram enviados.");
					  throw ex;
				  }
			 
			  }catch(SQLException ex){
				  Assert.fail("Falha ao tentar obter a conexao com o banco de dados.");
			  }finally{
				  if (rs != null) {
		                try {
		                    rs.close();
		                } catch (SQLException e) { /* ignored */}
		            }
		            if (stmt != null) {
		                try {
		                	stmt.close();
		                } catch (SQLException e) { /* ignored */}
		            }
		            if (conn != null) {
		                try {
		                    conn.close();
		                } catch (SQLException e) { /* ignored */}
		            } 
		  }
	}
	
	/* -- Verifica se a quantidade de registros na tabela JMS_MESSAGES e menor ou igual o valor de result
	 * -- Se a quantidade de registros for maior, siginifica que algo a mais foi enviado para tabela da fila */
	
	@Entao("^o nro de registros na tabela da fila deve ser <= ao resultado da quantidade de items a serem enviados")
	public void test_three() throws SQLException{
		
		//TODO: Fazer uma validaçao melhor
		try{
			conn = getConnection();
			// Statements allow to issue SQL queries to the database
			stmt = conn.createStatement();
				try{
					rs = stmt.executeQuery(SELECT_COUNT_JMS_MESSAGES);
					rs.next();
					at_queue = rs.getInt("total");
					
					assert(at_queue <= result);
	
					System.out.println("Os registros obtidos anteriormente estão sendo enviados para fila e " +
							"e também consumidos");
					
				}catch (SQLException ex){
					  System.out.println("Os registros não foram enviados.");
					  throw ex;
				    }
		}catch(SQLException ex){
			  Assert.fail("Falha ao tentar obter a conexao com o banco de dados.");
		  }finally{
			  if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) { /* ignored */}
	            }
	            if (stmt != null) {
	                try {
	                	stmt.close();
	                } catch (SQLException e) { /* ignored */}
	            }
	            if (conn != null) {
	                try {
	                    conn.close();
	                } catch (SQLException e) { /* ignored */}
	            } 
	  }
		System.out.println("teste");
	}
	
}
