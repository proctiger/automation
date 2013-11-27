package uol.admanager.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uol.admanager.entity.ViewLog;

/**
 * 
 * @author cin_wrodrigues
 */
public class ViewLogDao {

	public static List<ViewLog> getViewLogList(List<String> createdViewsLegacyFileNameList) throws Exception {

		ArrayList<ViewLog> viewLogList = new ArrayList<ViewLog>();
		for (String gzFileName : createdViewsLegacyFileNameList) {
			final String query =  " SELECT idt_ad_view_log, des_url, dat_start, dat_end, num_lines 		 "
								+ " FROM   ADMGR_CONSOLIDATOR_ADM.AD_VIEW_LOG                            "
								+ " WHERE  des_url like 	'%" + gzFileName + "%'						 ";
			try (final Resource res = BaseDaoSingleton.getConsolidatorInstance()
					.executeStatement(query)) {
					
				if (res.getResultSet().next()) {
					ViewLog viewLog = new ViewLog();
					viewLog.setIdtAdViewLog(res.getResultSet().getString("idt_ad_view_log"));
					viewLog.setDesUrl(res.getResultSet().getString("des_url"));
					viewLog.setNumLines(res.getResultSet().getString("num_lines"));
					viewLog.setDateStart(res.getResultSet().getString("dat_start"));
					viewLog.setDateEnd(res.getResultSet().getString("dat_end"));
					
					viewLogList.add(viewLog);
				}
			}
		}
		return viewLogList;
	}

	public static boolean deleteViewLog(List<String> viewFileNameList) throws Exception {
		boolean resultado = false;
		for (String gzFileName : viewFileNameList) {
			final String query =  " DELETE FROM   ADMGR_CONSOLIDATOR_ADM.AD_VIEW_LOG  			  "
								+ " WHERE  des_url like											  " 
								+ " %" + gzFileName	+ "%";
	
			try (final Resource res = BaseDaoSingleton.getConsolidatorInstance()
					.updateStatement(query)) {
				if ( res.getResult() > 0){
					resultado = true;
				}else{
					resultado = false;
					break;
				}
				
			}
		}
		return resultado;
	}
	
    public static void insertLogView(String urlLog) throws Exception {
        String now = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String query = String.format( 
                " insert                                                       " +
                " into                                                         " +
                "    ADMGR_CONSOLIDATOR_ADM.AD_VIEW_LOG                        " +
                "    (IDT_AD_VIEW_LOG, DES_URL, DAT_START, DAT_END, NUM_LINES) " + 
                " values                                                       " +
                "    (ADMGR_CONSOLIDATOR_ADM.SQ_ADVIEWLOG_IDT.NEXTVAL, '%s',   " +
                "    TO_DATE('%s', 'DD-MM-YYYY'),                              " +
                "    TO_DATE('%s', 'DD-MM-YYYY'), 1)                          ", urlLog, now, now);
        BaseDaoSingleton.getConsolidatorInstance().insertStatement(query);
    }
    
    public static int processed(String urlLog) throws Exception {
        String query = String.format(
                " select                                " +
                "    COUNT(*) count                     " +
                " from                                  " +
                "    ADMGR_CONSOLIDATOR_ADM.AD_VIEW_LOG " + 
                " where                                 " +
                "    DES_URL = '%s'                     ", urlLog);
        return (BaseDaoSingleton.getConsolidatorInstance().executeStatement(query).getResult());
    }
}
