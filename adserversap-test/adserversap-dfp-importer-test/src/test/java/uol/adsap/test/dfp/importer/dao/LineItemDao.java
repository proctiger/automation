package uol.adsap.test.dfp.importer.dao;

import java.sql.*;
import java.util.*;

public class LineItemDao {

    private static DaoSingleton instance = DaoSingleton.getPinInstance();

    public static void deleteFromLineItemConsolidate() throws Exception {
        DaoResource resource = null;
        try {
            resource = instance.updateStatement(
                " DELETE FROM ADSS_CONSOLIDATOR_ADM.line_item_consolidate"
            );
        } finally {
            instance.closeResources(resource);
        }
    }

    public static List<String> selectCsvFromLineItemConsolidate() throws Exception {
        DaoResource resource = null;
        try {
            resource = instance.selectStatement("select idt_line_item ||','||                       " +
            		                            "       idt_order ||','||                           " +
            		                            "       to_char(dat_start, 'YYYY-MM-DD') ||','||    " +
            		                            "       num_impression ||','||                      " +
            		                            "       num_click                                   " +
            		                            "from   ADSS_CONSOLIDATOR_ADM.line_item_consolidate " +
            		                            "order by dat_start desc                            ");
            ResultSet rs = resource.getResultSet();
            List<String> lines = new ArrayList<>();
            while (rs.next()) {
                lines.add(rs.getString(1));
            }
            return lines;
        } finally {
            instance.closeResources(resource);
        }
    }
}
