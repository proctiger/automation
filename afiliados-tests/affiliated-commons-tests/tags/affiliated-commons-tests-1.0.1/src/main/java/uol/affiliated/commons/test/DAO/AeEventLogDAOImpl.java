package uol.affiliated.commons.test.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import uol.affiliated.commons.test.DBUtil;
import uol.affiliated.commons.test.bean.AeEventLog;

/**
 * @author tsantos
 * 
 */

public class AeEventLogDAOImpl implements IAeEventLog {
	
	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet rs = null;
	private DBUtil dbutil;
	
	public AeEventLogDAOImpl(DBUtil dBUtil){
	    this.dbutil = dBUtil;
	    this.conn = dBUtil.getConnectionUol3();
	}
	
	public void deleteEventByNamLoginAndDesName(String namLogin, String desName){
		
		try {
			statement = conn.prepareStatement("DELETE FROM AE_EVENT_LOG WHERE DES_NAME = ? AND COD_AFFILIATED = (SELECT COD_DISPLAY_SUPPLIER FROM USER_SUPPLIER " +
                                              "WHERE IDT_PERSON = (SELECT IDT_PERSON FROM USER_ALL WHERE NAM_LOGIN = ?))");
			statement.setString(1, desName);
			statement.setString(2, namLogin);
			
			if (statement.executeUpdate() == 1) {
				System.out.println("Evento deletado com sucesso");
				conn.commit();
			} else {
				System.out.println(String.format("Não foi possível deletar o registro da AE_EVENT_LOG"));
			}
		} catch (SQLException e) {
			System.err.println(e);
			Assert.fail(String.format("\n Erro ao tentar deletar evento na AE_EVENT_LOG devido a: %s",e.getLocalizedMessage()));
		} finally {
			dbutil.closeResources(statement, rs);
		}
		
	}
	
	public List <AeEventLog> selectEventByNamLoginDesNameAndIdtProductSource(String namLogin, long idtProductSource,String desName){
         List<AeEventLog> aeEventLogList = new ArrayList<AeEventLog>();
		
		try {
			statement = conn.prepareStatement("SELECT * FROM AE_EVENT_LOG WHERE DES_NAME = ? AND IDT_PRODUCT_SOURCE = ? AND COD_AFFILIATED = (SELECT COD_DISPLAY_SUPPLIER FROM USER_SUPPLIER " +
                                              "WHERE IDT_PERSON = (SELECT IDT_PERSON FROM USER_ALL WHERE NAM_LOGIN = ?))");
			statement.setString(1, desName);
			statement.setLong(2, idtProductSource);
			statement.setString(3, namLogin);
			
	        rs = statement.executeQuery();
			
			while(rs.next()){
				AeEventLog aeEventLog = new AeEventLog();
				aeEventLog.setIdtEventLog(rs.getLong("IDT_EVENT_LOG"));
				aeEventLog.setCodTransaction(rs.getString("COD_TRANSACTION"));
				aeEventLog.setDesName(rs.getString("DES_NAME"));
				aeEventLog.setIdtProductSource(rs.getLong("IDT_PRODUCT_SOURCE"));
				aeEventLog.setDatEvent(rs.getDate("DAT_EVENT"));
				aeEventLog.setDatInsert(rs.getDate("DAT_INSERT"));
				aeEventLog.setNumEventAmount(rs.getLong("NUM_EVENT_AMOUNT"));
				aeEventLog.setNumValue(rs.getBigDecimal("NUM_VALUE"));
				aeEventLog.setGroupings(rs.getString("DES_GROUPING"));
				aeEventLog.setEventStatus(rs.getLong("IDT_EVENT_STATUS"));
				aeEventLogList.add(aeEventLog);
			}
	
		} catch (SQLException e) {
			System.err.println(e);
			System.out.println(String.format("\n Erro ao tentar retonar eventos da tabela AE_EVENT_LOG devido a: %s",e.getLocalizedMessage()));
		} finally {
			dbutil.closeResources(statement, rs);
		}
		
		return aeEventLogList;
		
	}

}
