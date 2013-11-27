package uol.affiliated.commons.test.DAO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import uol.affiliated.commons.test.DBUtil;

	



public class UserSupplierImpl implements IUserSupplierDAO {
		

		/**
		 * @author tsantos
		 * 
		 */
		
		private Connection conn = null;
		private PreparedStatement statement = null;
		private ResultSet rs = null;
		private DBUtil dbutil;
		
		public UserSupplierImpl(DBUtil dBUtil){
		    this.dbutil = dBUtil;
		    this.conn = dBUtil.getConnectionUol3();
		}
		
		public String selectCafByNamLogin(String namLogin){
			String codDisplaySupplier=null;
			
			try {
				statement = conn.prepareStatement("SELECT COD_DISPLAY_SUPPLIER FROM USER_SUPPLIER WHERE IDT_PERSON = " +
	                                              "(SELECT IDT_PERSON FROM USER_ALL WHERE NAM_LOGIN = ?)");
				statement.setString(1, namLogin);
				
				rs = statement.executeQuery();
				
				if(rs.next()){
					codDisplaySupplier = rs.getString("COD_DISPLAY_SUPPLIER");
				}else{
					System.out.println(String.format("Codigo do afiliado não encontrado na tabela USER_SUPPLIER"));
				}
	
			} catch (SQLException e) {
				System.err.println(e);
				Assert.fail(String.format("\n Erro ao tentar deletar evento na AE_EVENT_LOG devido a: %s",e.getLocalizedMessage()));
			} finally {
				dbutil.closeResources(statement, rs);
			}
			
			return codDisplaySupplier;
			
		}
		

}
