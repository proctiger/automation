package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;

import uol.test.util.PayofficeEvent;

public class LocalServiceStepsImpl extends AbstractLocalServiceSteps {
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;

	public LocalServiceStepsImpl() {
		conn = getConnectionUol3();
	}


	public boolean checkQueueByPayOfficeEvent(PayofficeEvent payofficeEvent){
            boolean isOnQueue= false;
            try {
                    statement = conn.prepareStatement("select * from AFF_AC_JBOSS_ADM.JMS_MESSAGES where  DESTINATION= 'QUEUE.affiliatedPayofficeEventJms' and "+
                                          "utl_raw.cast_to_varchar2(dbms_lob.substr(MESSAGEBLOB)) like ?");
                      
                    statement.setLong(1,payofficeEvent.getIdtEventLog());
                    rs=statement.executeQuery();
                    if(rs.next())
                    {
                            isOnQueue= true;
                            System.out.println("Registro "+payofficeEvent.toString() + " encontrado na fila");
                    }else{
                            System.out.println("Registro "+payofficeEvent.toString()+ " não encontrado na fila");
                    }
            } catch (SQLException e) {
                    Assert.fail(String.format("\n Erro ao tentar executar a query: %s",e.getLocalizedMessage()));
            } finally {
                    closeResources(statement, rs);
            }
            return isOnQueue;
    }
	
	public void deleteEventFromAeEventLog(String codTransaction) {//////////////////////
		try {
			statement = conn
					.prepareStatement("DELETE FROM AFFILIATED_ADM.AE_EVENT_LOG where COD_TRANSACTION  = ?");
			statement.setString(1, codTransaction);
			statement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			Assert.fail("\n Erro ao tentar apagar o evento da tabela de eventos devido a:"
					+ e.getLocalizedMessage());
		} finally {
			closeResources(statement, rs);
		}
	}

	public void deleteEventFromAeBalanceEvent(String idtPerson) {//////////////////////
		try {
			statement = conn
					.prepareStatement("DELETE FROM AFFILIATED_ADM.AE_BALANCE_EVENT WHERE IDT_PERSON = ? AND DES_NAME in ('Pagamento','Compra','2ndTier')");
			statement.setString(1, idtPerson);
			if (statement.executeUpdate() == 1) {
				conn.commit();
				System.out
						.println("Evento  da tabela de extrato apagado com sucesso");
			} else {
				System.out
						.println("Não há registro na tabela de extrato para ser removido");
			}
		} catch (SQLException e) {
			Assert.fail(String
					.format("\n Erro ao tentar apagar o evento da tabela de extrato devido a: %s",
							e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
	}

	public void deleteEventFromCommissionEvent(String idtPerson) {//////////////////////
		try {
			statement = conn
					.prepareStatement("DELETE FROM AFFILIATED_ADM.COMMISSION_EVENT CM "
							+ "WHERE CM.IDT_USER_SUPPLIER_URL = (SELECT DISTINCT IDT_USER_SUPPLIER_URL FROM AFFILIATED_ADM.USER_SUPPLIER_URL USU "
							+ "WHERE DES_URL = 'afiliados.uol.com.br' AND IDT_PERSON = ?)");
			statement.setString(1, idtPerson);

			if (statement.executeUpdate() == 1) {
				conn.commit();
				System.out
						.println("Evento  da tabela de comissao apagado com sucesso");
			} else {
				System.out
						.println("Não há registro na tabela de comissao para ser removido");
			}
		} catch (SQLException e) {
			Assert.fail(String
					.format("\n Erro ao tentar apagar o evento da tabela de comissões devido a: %s",
							e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
	}

	public void getActualBalanceFromUserSupplier(String idtPerson) {//////////////////////
		try {
			statement = conn
					.prepareStatement("SELECT NUM_BALANCE_VALUE FROM AFFILIATED_ADM.USER_SUPPLIER WHERE IDT_PERSON = ?");
			statement.setString(1, idtPerson);
			rs = statement.executeQuery();
			if (rs.next()) {
				setActualNumBalance(Float.parseFloat(rs
						.getString("NUM_BALANCE_VALUE")));
				System.out.println("Saldo atual de "
						+ rs.getString("NUM_BALANCE_VALUE")
						+ " guardado com sucesso");
			} else {
				Assert.fail(String
						.format("\n Não foi possível encontrar o afiliado %s na tabela de saldo",
								idtPerson));
			}
		} catch (SQLException e) {
			Assert.fail(String
					.format("\n Erro ao retornar o saldo atual do afiliado devido a: %s",
							e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
	}

	public void simulatePaymentData(String desName, Date date, float value,//////////////////////
			String idtPerson, String desGroupings, String payOrder,
			String codTrans, String prdSource) {
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
				System.out.println("Evento de " + desName+ " da tabela de eventos inserido com sucesso");
			} else {
				Assert.fail(String.format("Não foi possível inserir o registro na tabela de eventos para o afiliado pai %s",
								idtPerson));
			}
		} catch (SQLException e) {
			System.err.println(e);
			Assert.fail(String.format("\n Erro ao tentar inserir evento de "+ desName + " na tabela de eventos devido a: %s",
					e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
	}

	public boolean checkQueueByDesGrouping(String desGroupings) {//////////////////////
		boolean isOnQueue = false;
		String idtEventLog = getIdtEventLogByDesGrouping(desGroupings);
		try {

			statement = conn
					.prepareStatement("select * from AFF_AC_JBOSS_ADM.JMS_MESSAGES where  DESTINATION= 'QUEUE.affiliatedPayofficeEventJms' and "
							+ "utl_raw.cast_to_varchar2(dbms_lob.substr(MESSAGEBLOB)) like ?");
			statement.setString(1, "%idtEventLog=" + idtEventLog + "%");
			rs = statement.executeQuery();

			if (rs.next()) {
				isOnQueue = true;
				System.out.println("Registro " + idtEventLog
						+ " encontrado na fila");
			} else {
				System.out.println("Registro " + idtEventLog
						+ " não encontrado na fila");
			}
		} catch (SQLException e) {
			Assert.fail(String.format("\n Erro ao tentar executar a query: %s",
					e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return isOnQueue;
	}

	public void deleteEventFromAeReportEvent(String idtPerson, String prdSource) {//////////////////////
		try {
			statement = conn
					.prepareStatement("DELETE FROM AFFILIATED_ADM.AE_REPORT_EVENT WHERE IDT_PERSON = ? AND DES_NAME in ('Pagamento','Compra','2ndTier') and IDT_PRODUCT_SOURCE = ?");
			statement.setString(1, idtPerson);
			statement.setString(2, prdSource);
			if (statement.executeUpdate() == 1) {
				conn.commit();
				System.out
						.println("Evento da tabela de relatório apagado com sucesso");
			} else {
				System.out
						.println("Não há registro na tabela de relatório para ser removido");
			}
		} catch (SQLException e) {
			Assert.fail(String
					.format("\n Erro ao tentar apagar o evento da tabela de relatório devido a: %s",
							e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
	}

	public String getIdtEventLogByDesGrouping(String desGrouping) {
		String idtEventLog = null;
		try {
			statement = conn.prepareStatement("SELECT IDT_EVENT_LOG FROM AFFILIATED_ADM.AE_EVENT_LOG WHERE DES_GROUPING  = ?");
			statement.setString(1, desGrouping);
			rs = statement.executeQuery();
			if (rs.next()) {
				idtEventLog = rs.getString("IDT_EVENT_LOG");
			} else {
				Assert.fail("Não há registro idt_event_log na tabela ae_event_log para des_grouping "
						+ desGrouping);
			}
		} catch (SQLException e) {
			System.err.println(e);
			Assert.fail(String
					.format("\n Erro ao tentar executar a query na base de dados, devido a: %s",
							e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return idtEventLog;

	}

}
