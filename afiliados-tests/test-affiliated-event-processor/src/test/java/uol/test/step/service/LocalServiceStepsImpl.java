package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import uol.test.util.Event;


public class LocalServiceStepsImpl extends AbstractLocalServiceSteps{
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;
	
	public LocalServiceStepsImpl(){
		conn = getConnectionUol3();
	}
	
	public Map<String, Integer> getStatusEvent(String codTransaction){
		try {
			statement = conn.prepareStatement("SELECT idt_event_status, des_name FROM AE_EVENT_LOG where COD_TRANSACTION = ?");
			statement.setString(1,codTransaction);
			rs = statement.executeQuery();
			Map<String, Integer> statusMap = new HashMap<String, Integer>();
			while(rs.next()) {
				int status = rs.getInt("idt_event_status");
				String desName = rs.getString("des_name");
				statusMap.put(desName , status);
				System.out.println("Status do Evento de " + desName + " -> " + status);
			}
			return statusMap;
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return new HashMap<String, Integer>();
	}
	
	public boolean checkEvent(String idtPerson,int prd_source){
		try {
			statement = conn.prepareStatement("SELECT * FROM AE_EVENT_LOG WHERE DES_NAME = '2ndTier' AND IDT_PRODUCT_SOURCE = ? AND DES_GROUPING like ?");
			statement.setInt(1, prd_source);
			statement.setString(2, "%" + idtPerson + "%");
			rs = statement.executeQuery();
			if (!rs.next()) {
				System.out.println("Não encontrei o Evento");
				return false;
			} else {
				System.out.println("Encontrei o Evento");
				return true;
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return false;
	}
	
	public float getIndicationComission(int idtPerson,int idtProductSource) {
		float commission = 0;
		try {
			//statement = conn.prepareStatement(" select num_supplier_win_value from commission_event where idt_user_supplier_url = ( select idt_user_supplier_url from user_supplier_url where idt_person = ? and des_url = 'afiliados.uol.com.br')");
			statement = conn.prepareStatement("select num_commission_value from ae_report_event where idt_person = ? and des_name = '2ndTier' and idt_product_source = ?");
			statement.setInt(1, idtPerson);
			statement.setInt(2, idtProductSource);
			rs = statement.executeQuery();
			//while(rs.next()) {
			if(rs.next()){
				commission = rs.getFloat("num_commission_value");
				System.out.println("Valor da Comissão na tabela de evento -> " + commission);
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		
		return commission;
	}
	
	public float getCommission(int idtPerson , String desName, int idtProductSource)  {
		float commission = 0;
		try {
			statement = conn.prepareStatement("select num_commission_value from ae_report_event where idt_person = ? and des_name = ? and idt_product_source = ?");
			statement.setInt(1, idtPerson);
			statement.setString(2, desName);
			statement.setInt(3, idtProductSource);
			rs = statement.executeQuery();
			//while(rs.next()) {
			if(rs.next()){
				commission = rs.getFloat("num_commission_value");
				System.out.println("Valor da Comissão na tabela de evento -> " + commission);
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		
		return commission;
	}
	
	public float getBalance(int idtPerson){
		try {
			statement = conn.prepareStatement("select num_balance_value from user_supplier where idt_person = ? ");
			statement.setInt(1, idtPerson);
			rs = statement.executeQuery();
			while(rs.next()) {
				float balance =  rs.getFloat("num_balance_value");
				System.out.println("Valor do saldo do afiliado Pai -> " + balance);
				return balance;
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return 0;
	}
	
	public boolean checkBalance(int idtPerson, float commissionValue){
		try {
			statement = conn.prepareStatement("select * from ae_balance_event where idt_person = ? and num_commission_value = ?");
			statement.setInt(1, idtPerson);
			statement.setFloat(2, commissionValue);
			rs = statement.executeQuery();
			if (!rs.next()) {
				System.out.println("Não encontrei no Extrato");
				return false;
			} else {
				System.out.println("Encontrei no Extrato");
				return true;
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return false;
	}
	
	public boolean checkReport(double commissionValue, long idtPerson){
		try {
			statement = conn.prepareStatement("select * from ae_report_event where idt_person = ? and num_commission_value = ?");
			statement.setLong(1, idtPerson);
			statement.setDouble(2, commissionValue);
			rs = statement.executeQuery();
			if (!rs.next()) {
				System.out.println("Não encontrei no Relatorio");
				return false;
			} else {
				System.out.println("Encontrei no Relatorio");
				return true;
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return false;
	}
	
	
	
	
	public boolean checkForParent(String idtPerson, String idtPersonSon){
		boolean isParent= false;
    	 try {
         	statement = conn.prepareStatement("SELECT * FROM AFFILIATED_ADM.USER_SUPPLIER_PARENT WHERE IDT_PERSON_PARENT = ? AND IDT_PERSON = ?");
 			statement.setString(1,idtPerson);
 			statement.setString(2,idtPersonSon);
         	rs = statement.executeQuery();
         	if (rs.next()){
         	    isParent=true;
         		}
 			} catch (SQLException e) {
 				System.err.println(e);
 				Assert.fail(String.format("\n Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
			} finally {
				closeResources(statement, rs);
			}
			return isParent;
    	
	}
	

	public void deleteEventFromAeEventLog(String codTransaction) {
		try {
			statement = conn.prepareStatement("DELETE FROM AFFILIATED_ADM.AE_EVENT_LOG where COD_TRANSACTION  = ?");
			statement.setString(1, codTransaction);
			statement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			Assert.fail("\n Erro ao tentar apagar o evento da tabela de eventos devido a:" + e.getLocalizedMessage());
		} finally {
			closeResources(statement, rs);
		}
	}
	
	
	public void deleteEventFromAeBalanceEvent(String idtPerson){
   	 try {
        	statement = conn.prepareStatement("DELETE FROM AFFILIATED_ADM.AE_BALANCE_EVENT WHERE IDT_PERSON = ? AND DES_NAME in ('Pagamento','Compra','2ndTier')");
        	statement.setString(1,idtPerson);
	         	if(statement.executeUpdate()==1){
	         		conn.commit();
	         		System.out.println("Evento  da tabela de extrato apagado com sucesso");
	         	}else{
	         		System.out.println("Não há registro na tabela de extrato para ser removido");
	         	}
			} catch (SQLException e) {
				Assert.fail(String.format("\n Erro ao tentar apagar o evento da tabela de extrato devido a: %s", e.getLocalizedMessage()));
			} finally {
				closeResources(statement, rs);
			}
	}
	
	public void deleteEventFromCommissionEvent(String idtPerson){
    	 try {
         	statement = conn.prepareStatement("DELETE FROM AFFILIATED_ADM.COMMISSION_EVENT CM " +
         									  "WHERE CM.IDT_USER_SUPPLIER_URL = (SELECT DISTINCT IDT_USER_SUPPLIER_URL FROM AFFILIATED_ADM.USER_SUPPLIER_URL USU "+
         									  "WHERE DES_URL = 'afiliados.uol.com.br' AND IDT_PERSON = ?)");
         	statement.setString(1,idtPerson);
     
	          if(statement.executeUpdate()==1){
	        	  conn.commit();
	         		System.out.println("Evento  da tabela de comissao apagado com sucesso");
	         	}else{
	         		System.out.println("Não há registro na tabela de comissao para ser removido");
	         	}
 			} catch (SQLException e) {
 				Assert.fail(String.format("\n Erro ao tentar apagar o evento da tabela de comissões devido a: %s", e.getLocalizedMessage()));
 			} finally {
				closeResources(statement, rs);
			}
	}
	
	public void getActualBalanceFromUserSupplier(String idtPerson){
    	 try {
         	statement = conn.prepareStatement("SELECT NUM_BALANCE_VALUE FROM AFFILIATED_ADM.USER_SUPPLIER WHERE IDT_PERSON = ?");
         	statement.setString(1,idtPerson);
         	rs = statement.executeQuery();
	         	if(rs.next()){
	         		setActualNumBalance(Float.parseFloat(rs.getString("NUM_BALANCE_VALUE")));
	         		System.out.println("Saldo atual de " + rs.getString("NUM_BALANCE_VALUE")+" guardado com sucesso");
	         	}else{
	 				Assert.fail(String.format("\n Não foi possível encontrar o afiliado %s na tabela de saldo", idtPerson));
	         	}
 			} catch (SQLException e) {
 				Assert.fail(String.format("\n Erro ao retornar o saldo atual do afiliado devido a: %s", e.getLocalizedMessage()));
 			} finally {
				closeResources(statement, rs);
			}
	}
 		
	public void simulatePaymentData(String desName, Date date, float value,	String idtPerson, String desGroupings, String payOrder,String codTrans, String prdSource) {
		try {
			long idtEventLog = sqNextVal("SQ_AEEVENTLOG_IDT");
			statement = conn.prepareStatement("INSERT INTO AFFILIATED_ADM.ae_event_log VALUES (?,?,?,?,?,sysdate,?,?,?,sysdate,0,sysdate)");
			statement.setLong(1, idtEventLog);
			statement.setString(2, codTrans);
			statement.setString(3, prdSource);
			statement.setString(4, desName);
			statement.setString(5, selectCafByIdtPerson(idtPerson));
			statement.setString(6, payOrder);
			statement.setFloat(7, value);
			statement.setString(8, desGroupings);
			if (statement.executeUpdate() == 1) {
				conn.commit();
				System.out.println("Evento de "+desName+" da tabela de eventos inserido com sucesso");
			} else {
				Assert.fail(String.format("Não foi possível inserir o registro na tabela de eventos para o afiliado pai %s",idtPerson));
			}
		} catch (SQLException e) {
			System.err.println(e);
			Assert.fail(String.format("\n Erro ao tentar inserir evento de "+desName+" na tabela de eventos devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
	}
	
	public boolean checkQueueByDesGrouping(String desGroupings){
		boolean isOnQueue= false;
		String idtEventLog = getIdtEventLogByDesGrouping(desGroupings);
		try {
			
			statement = conn.prepareStatement("select * from AFF_AC_JBOSS_ADM.JMS_MESSAGES where  DESTINATION= 'QUEUE.affiliatedPayofficeEventJms' and "+
                                              "utl_raw.cast_to_varchar2(dbms_lob.substr(MESSAGEBLOB)) like ?");
			statement.setString(1,"%<idtEventLog>"+idtEventLog+"</idtEventLog>%");
			rs=statement.executeQuery();
			
			if(rs.next()){
			    isOnQueue= true;
				System.out.println("Registro "+idtEventLog+ " encontrado na fila");
			}else{
				System.out.println("Registro "+idtEventLog+ " não encontrado na fila");
			}
			/*while(rs.next()&&isOnQueue==false){
				queueEvent = rs.getString(1);
				idtEventLog = queueEvent.substring(queueEvent.indexOf("idtEventLog=")+12,queueEvent.indexOf(","));
				System.out.println("ID DO DESGROUPING ="+getIdtEventLogByDesGrouping(desGroupings));
				System.out.println("ID NA FILA ="+idtEventLog);	
				if(idtEventLog.equals(getIdtEventLogByDesGrouping(desGroupings))){
					isOnQueue = true;
					System.out.println("Registro "+ idtEventLog +"encontrado na fila");
				}	
			}
			if (isOnQueue==false){
				Assert.fail("Não há o idt_event_log "+getIdtEventLogByDesGrouping(desGroupings)+" na fila");
			}*/
		} catch (SQLException e) {
			Assert.fail(String.format("\n Erro ao tentar executar a query: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return isOnQueue;
	}
	
	public void deleteEventFromAeReportEvent(String idtPerson,String prdSource){
		try {
			statement = conn.prepareStatement("DELETE FROM AFFILIATED_ADM.AE_REPORT_EVENT WHERE IDT_PERSON = ? AND DES_NAME in ('Pagamento','Compra','2ndTier') and IDT_PRODUCT_SOURCE = ?");
			statement.setString(1, idtPerson);
			statement.setString(2, prdSource);
			if (statement.executeUpdate() == 1) {
				conn.commit();	
				System.out.println("Evento da tabela de relatório apagado com sucesso");
			} else {
				System.out.println("Não há registro na tabela de relatório para ser removido");
			}
		} catch (SQLException e) {
			Assert.fail(String.format("\n Erro ao tentar apagar o evento da tabela de relatório devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
	}
	
	public String getIdtEventLogByDesGrouping(String desGrouping){
		String idtEventLog =null;
		try {
	         	statement = conn.prepareStatement("SELECT IDT_EVENT_LOG FROM AFFILIATED_ADM.AE_EVENT_LOG WHERE DES_GROUPING  = ?");
	 			statement.setString(1,desGrouping);
	         	rs = statement.executeQuery();
	         	if (rs.next()){
	         		idtEventLog= rs.getString("IDT_EVENT_LOG");
	         		}else{
	         			Assert.fail("Não há registro idt_event_log na tabela ae_event_log para des_grouping "+ desGrouping);
	         		}
	 			} catch (SQLException e) {
	 				System.err.println(e);
	 				Assert.fail(String.format("\n Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
	 			} finally {
					closeResources(statement, rs);
				}
		 return idtEventLog;	
	    	
	}
	
	 public void generateEvents(int numberOfEvents, Event event) {
	        System.out.println("Gerando " + numberOfEvents + " eventos ....");
	        try{
	        Statement stmt = conn.createStatement();
	        for (int i = 1; i <= numberOfEvents; i++) {
	            String query = new String("INSERT INTO AFFILIATED_ADM.AE_EVENT_LOG VALUES (SQ_AEEVENTLOG_IDT.nextval,'" + i + "',"+ event.getProduct() +",'" +event.getDesName()+"','"+event.getCaf()+"',sysdate,"+event.getNumEventAmount()+","+event.getNumValue()+",'"+event.getDesGrouping()+"',sysdate,0,sysdate)");
	            stmt.addBatch(query);
	        }
	        stmt.executeBatch();
	        conn.commit();
	        stmt.close();
	        System.out.println("Eventos Gerados!");
	         } catch (SQLException e) {
	             System.err.println(e);
	            Assert.fail(String.format("Não foi possível gerar os eventos"));
	        }
	    }

	   public boolean checkProcessAllEvents(int numberOfEvents, String eventType) {
	        try {
	            statement = conn.prepareStatement("SELECT COUNT(*) FROM AE_EVENT_LOG WHERE des_name = ? and idt_event_status = 1");
	            statement.setString(1, eventType);
	            rs = statement.executeQuery();
	            int events = 0;
	            while (rs.next()) {
	                events = rs.getInt("COUNT(*)");
	            }
	            conn.commit();
	            if(events == numberOfEvents){
	                System.out.println("Eventos Processados! ");
	                return true;
	            }
	            return false;
	        } catch (SQLException e) {
	            Assert.fail(String.format("Não foi possível encontrar os eventos"));
	        }
	        return false;
	    }
	   

           public int getNumberOfProcessedEvents(String eventType) {
               int numberOfEvents = 0; 
               try {
                    statement = conn.prepareStatement("SELECT COUNT(*) FROM AE_EVENT_LOG WHERE des_name = ? and idt_event_status = 1");
                    statement.setString(1, eventType);
                    rs = statement.executeQuery();
                    while (rs.next()) {
                        numberOfEvents = rs.getInt("COUNT(*)");
                    }
                } catch (SQLException e) {
                    Assert.fail(String.format("Não foi possível encontrar os eventos"));
                }
                return numberOfEvents;
            }

	    public void removeEvents(String eventsType) {
	        try {
	            statement = conn.prepareStatement("delete FROM AE_EVENT_LOG WHERE des_name = ?");
	            statement.setString(1, eventsType);      
	            statement.executeQuery();
	            conn.commit();
	        } catch (SQLException e) {
	            Assert.fail(String.format("Não foi possível deletar os eventos"));
	        }
	        
	  
	    	
	    }
	
	

}
