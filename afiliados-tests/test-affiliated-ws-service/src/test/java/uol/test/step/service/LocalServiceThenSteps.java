package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.java.pt.Entao;

import uol.test.step.AbstractTestStep;

public class LocalServiceThenSteps extends AbstractTestStep {
	
	private final static int DELAY_TIME = 5000;
	
	@Entao("^validar que a reposta do servico e o codigo <(.+)>$")
	public void checkResponseStatus(String codigo) {
		System.out.println(String.format("\n -> Verificando o codigo retornado pelo servico"));
		if(LocalServiceWhenSteps.getCode().equalsIgnoreCase(codigo)) {
			Assert.assertTrue(true);
			System.out.println(String.format("OK, passou. Codigo retornado = %s",LocalServiceWhenSteps.getCode()));
		}
		else {
			Assert.assertTrue(false);
			System.out.println(String.format("Teste falhou. Codigo retornado = %s",LocalServiceWhenSteps.getCode()));
		}
	}
	
	@Entao("^validar que o usuario cujo idt_person seja igual a <(.+)> foi inserido nas tabelas user_supplier e usersupp_commevensourgrou$")
	public void checkUserInsert(long idtPerson) {
		System.out.println(String.format("\n -> Verificando se o idt_person <%d> foi inserido nas tabelas user_supplier e usersupp_commevensourgrou",idtPerson));
    	PreparedStatement statement = null;
        ResultSet rs = null;
    	Connection conn = null;
    	try {
    		
    		String selectUserSupplier = "SELECT * FROM USER_SUPPLIER WHERE IDT_PERSON = ?";
    		
    		conn = getConnectionUol3();
    		System.out.println("Conexao = " + conn);
    		
    		int maxAttempts = 0;
    		boolean found = false;
    		
    		statement = conn.prepareStatement(selectUserSupplier);
			statement.setLong(1, idtPerson);
    		
    		do {
    			rs = statement.executeQuery();
    			if(!rs.next()) {	
    				Thread.sleep(DELAY_TIME);
    			}
    			else {
    				found = true;
    			}
    			maxAttempts++;
    			System.out.println(String.format("Tentativa %d",maxAttempts));
    		} while(maxAttempts < 6 && !found);
    		
    		if(!found) {
    			System.out.println(String.format("Teste falhou. Nao Achei o idt_person %d na tabela user_supplier",idtPerson));
    			Assert.assertTrue(false);
    		}
    		else {
    			System.out.println(String.format("Achei o idt_person %d na tabela user_supplier",idtPerson));
    			System.out.println(String.format("Verificando se o idt_person %d consta na tabela usersupp_commevensourgrou",idtPerson));
    			
    			String selectUserSuppComm = "SELECT * FROM USERSUPP_COMMEVENSOURGROU WHERE IDT_PERSON = ?";
    			
    			statement = conn.prepareStatement(selectUserSuppComm);
        		statement.setLong(1, idtPerson);
        		rs = statement.executeQuery();
    			
    			if(!rs.next()){
    				Assert.assertTrue(false);
        			System.out.println(String.format("Teste falhou. Nao Achei o idt_person %d na tabela usersupp_commevensourgrou",idtPerson));
    			}
    			else{
    				Assert.assertTrue(true);
        			System.out.println(String.format("Teste OK. Achei o idt_person %d nas tabelas user_supplier e usersupp_commevensourgrou",idtPerson));
    			}   
    		}
    	}
    	catch(SQLException e) {
    		Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
    	}
    	catch(Exception e) {
    		System.out.println(String.format("Erro: %s", e.getLocalizedMessage()));
    	}
    	finally {
        	closeResources(statement,rs);
    	}
	}
	
}