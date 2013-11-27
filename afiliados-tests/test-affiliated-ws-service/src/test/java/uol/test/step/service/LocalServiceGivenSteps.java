package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import uol.test.step.AbstractTestStep;

import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;


public class LocalServiceGivenSteps extends AbstractTestStep {
    
    @Before
	public void inicioTeste() throws Exception {
    	startAndWaitApplication("affiliated-ws-service", "jetty","a1-hockafl-s-prt2");
		System.out.println("\n\n --------------------------------- Inicio - Cenario --------------------------------- \n\n");
    } 
    
    @Dado("^que exista na tabela user_all um usuario cujo idt_person seja igual a <(.+)>$")
    public void checkUserInUserAll(long idtPerson) {
    	System.out.println(String.format("\n -> Dado que exista o idt_person <%d> na tabela user_all",idtPerson));
    	PreparedStatement statement = null;
        ResultSet rs = null;
    	Connection conn = null;
    	try {
    		
    		String select = "SELECT * FROM USER_ALL WHERE IDT_PERSON = ?";
    		
    		conn = getConnectionUol3();
    		statement = conn.prepareStatement(select);
    		statement.setLong(1, idtPerson);
    		rs = statement.executeQuery();
    		
    		if(!rs.next()) {	
    			Assert.fail(String.format("Nao achei o idt_person %d",idtPerson));
    		}
    		else {
    			System.out.println(String.format("OK, passou. Achei o idt_person %d",idtPerson));
    		}
    	}
    	catch(SQLException e) {
    		Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
    	}
    	finally {
    		closeResources(statement,rs);
    	}
    }
    
    @Dado("^que nao exista nas tabelas user_supplier, usersupp_commevensourgrou e user_supplier_parent um usuario cujo idt_person seja igual a <(.+)>$")
    public void checkUserInAffiliated(long idtPerson) {
    	System.out.println(String.format("\n -> Dado que nao exista o idt_person <%d> nas tabelas user_supplier e usersupp_commevensourgrou",idtPerson));
    	PreparedStatement statement = null;
        ResultSet rs = null;
    	Connection conn = null;
    	try {
    		
    		String selectUserSupplier = "SELECT * FROM USER_SUPPLIER WHERE IDT_PERSON = ?";
    		
    		conn = getConnectionUol3();
    		statement = conn.prepareStatement(selectUserSupplier);
    		statement.setLong(1, idtPerson);
    		rs = statement.executeQuery();
    		
    		if(!rs.next()) {	
    			System.out.println(String.format("OK, passou. Nao Achei o idt_person %d na tabela user_supplier",idtPerson));
    		}
    		else {
    			System.out.println(String.format("Achei o idt_person %d na tabela user_supplier",idtPerson));
    			System.out.println(String.format("Verificando se o idt_person %d consta na tabela usersupp_commevensourgrou",idtPerson));
    			
    			String selectUserSuppComm = "SELECT * FROM USERSUPP_COMMEVENSOURGROU WHERE IDT_PERSON = ?";
    			
    			statement = conn.prepareStatement(selectUserSuppComm);
        		statement.setLong(1, idtPerson);
        		rs = statement.executeQuery();
    			
    			if(rs.next()){
                    System.out.println(String.format("Achei o idt_person %d na tabela usersupp_commevensourgrou",idtPerson));
                    System.out.println(String.format("Removendo o registro na tabela usersupp_commevensourgrou existente para o idt_person %d",idtPerson));
                    String deleteUserSuppComm = "DELETE FROM USERSUPP_COMMEVENSOURGROU where IDT_PERSON = ?";
                    statement = conn.prepareStatement(deleteUserSuppComm);
                    statement.setLong(1, idtPerson);
                    statement.executeUpdate();
                    System.out.println(String.format("Registro removido com sucesso na tabela usersupp_commevensourgrou para o idt_person %d", idtPerson));
                } else {
                    System.out.println(String.format("Registro nao encontrado na tabela usersupp_commevensourgrou para o idt_person %d", idtPerson));
                }
    			
    			System.out.println(String.format("Verificando se o idt_person %d consta na tabela user_supplier_parent",idtPerson));
    			
    			String selectUserSuppParent = "SELECT * FROM USER_SUPPLIER_PARENT WHERE IDT_PERSON = ?";
    			
    			statement = conn.prepareStatement(selectUserSuppParent);
        		statement.setLong(1, idtPerson);
        		rs = statement.executeQuery();
        		
        		if(rs.next()){
                    System.out.println(String.format("Achei o idt_person %d na tabela user_supplier_parent",idtPerson));
                    System.out.println(String.format("Removendo o registro na tabela user_supplier_parent existente para o idt_person %d",idtPerson));
                    String deleteUserSuppParent = "DELETE FROM USER_SUPPLIER_PARENT where IDT_PERSON = ?";
                    statement = conn.prepareStatement(deleteUserSuppParent);
                    statement.setLong(1, idtPerson);
                    statement.executeUpdate();
                    System.out.println(String.format("Registro removido com sucesso na tabela user_supplier_parent para o idt_person %d", idtPerson));
                } else {
                    System.out.println(String.format("Registro nao encontrado na tabela user_supplier_parent para o idt_person %d", idtPerson));
                }
    			
    			System.out.println(String.format("Removendo o registro na tabela user_supplier existente para o idt_person %d",idtPerson));
                String deleteUserSupplier = "DELETE FROM USER_SUPPLIER where IDT_PERSON = ?";
                statement = conn.prepareStatement(deleteUserSupplier);
                statement.setLong(1, idtPerson);
                statement.executeUpdate();
                System.out.println(String.format("Registro removido com sucesso na tabela user_supplier para o idt_person %d", idtPerson));
    		}
    	}
    	catch(SQLException e) {
    		Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
    	}
    	finally {
    		try {
    			conn.commit();
    		} catch (SQLException e) {
    			Assert.fail(String.format("Nao foi possivel realizar commit no banco, devido a: %s",e.getLocalizedMessage()));
    		}
        	closeResources(statement,rs);
    	}
    }
    
}
