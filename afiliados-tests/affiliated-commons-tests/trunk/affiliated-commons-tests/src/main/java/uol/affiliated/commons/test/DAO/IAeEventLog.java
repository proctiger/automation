package uol.affiliated.commons.test.DAO;

import java.util.List;

import uol.affiliated.commons.test.bean.AeEventLog;

/**
 * @author tsantos
 * 
 */

public interface IAeEventLog {

	
	public void deleteEventByNamLoginAndDesName(String namLogin, String desName);
	public List <AeEventLog> selectEventByNamLoginDesNameAndIdtProductSource(String namLogin, long idtProductSource,String desName);
	
}
