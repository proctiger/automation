package uol.admanager.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uol.admanager.entity.ClickLog;

/**
 * 
 * @author cin_wrodrigues
 */
public class ClickLogDao {

	public static List<ClickLog> getClickLogList(List<String> createdClickFileNameList) throws Exception {

		ArrayList<ClickLog> clickLogList = new ArrayList<ClickLog>();
		for (String gzFileName : createdClickFileNameList) {
			final String query =  " SELECT idt_ad_click_log, des_url, dat_start, dat_end, num_lines 		 "
								+ " FROM   ADMGR_CONSOLIDATOR_ADM.AD_CLICK_LOG                            "
								+ " WHERE  des_url like 	'%" + gzFileName + "%'						 ";
			try (final Resource res = BaseDaoSingleton.getConsolidatorInstance()
					.executeStatement(query)) {
					
				if (res.getResultSet().next()) {
					ClickLog clickLog = new ClickLog();
					clickLog.setIdtAdClickLog(res.getResultSet().getString("idt_ad_click_log"));
					clickLog.setDesUrl(res.getResultSet().getString("des_url"));
					clickLog.setNumLines(res.getResultSet().getString("num_lines"));
					clickLog.setDateStart(res.getResultSet().getString("dat_start"));
					clickLog.setDateEnd(res.getResultSet().getString("dat_end"));
					
					clickLogList.add(clickLog);
				}
			}
		}
		return clickLogList;
	}

	public static boolean deleteClickLog(List<String> clickFileNameList) throws Exception {
		boolean resultado = false;
		for (String gzFileName : clickFileNameList) {
			final String query =  " DELETE FROM ADMGR_CONSOLIDATOR_ADM.AD_CLICK_LOG   		      "
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
	
    public static void insertLogClick(String urlLog, int lines) throws Exception {
        String now = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        String query = String.format( 
                " insert                                                       " +
                " into                                                         " +
                "    ADMGR_CONSOLIDATOR_ADM.AD_CLICK_LOG                        " +
                "    (IDT_AD_CLICK_LOG, DES_URL, DAT_START, DAT_END, NUM_LINES) " + 
                " values                                                       " +
                "    (ADMGR_CONSOLIDATOR_ADM.SQ_ADCLICLOG_IDT.NEXTVAL, '%s',   " +
                "    TO_DATE('%s', 'DD-MM-YYYY'),                              " +
                "    TO_DATE('%s', 'DD-MM-YYYY'), %d)                          ", urlLog, now, now, lines);
        BaseDaoSingleton.getConsolidatorInstance().insertStatement(query);
    }
}
