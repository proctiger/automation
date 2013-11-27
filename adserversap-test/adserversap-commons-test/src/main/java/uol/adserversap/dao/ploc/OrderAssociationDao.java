package uol.adserversap.dao.ploc;

import java.sql.ResultSet;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;

public class OrderAssociationDao {

	public String getPoNumber(String enterprise) throws Exception{
	
       String query = String.format("SELECT * FROM ADSERVER_SAP_ADM.order_association WHERE NAM_ENTERPRISE = \'%s\' order by des_po_number desc", enterprise);
       String poNumber = "";
       
       Resource resource = null;
       try {
           resource = BaseDaoSingleton.getPlocInstance().
                   selectStatement(query);
           ResultSet rs = resource.getResultSet();
           if (rs == null) {
               throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
           }
           while (rs.next()) {
              poNumber = rs.getString("DES_PO_NUMBER");
              if (!poNumber.isEmpty()){
            	  break;
              }
           }
       } finally {
           BaseDaoSingleton.getEtlInstance().closeResources(resource);
       }
       return poNumber;
   }
	
}
