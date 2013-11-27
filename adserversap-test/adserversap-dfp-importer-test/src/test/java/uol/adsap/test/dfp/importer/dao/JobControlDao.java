package uol.adsap.test.dfp.importer.dao;

import java.sql.*;
import java.util.*;

public class JobControlDao {

    private static DaoSingleton instance = DaoSingleton.getPinInstance();

    public static void deleteFromJobControl() throws Exception {
        DaoResource resource = null;
        try {
            resource = instance.updateStatement(
                            " DELETE FROM ADSS_CONSOLIDATOR_ADM.job_control"
                            );
        } finally {
            instance.closeResources(resource);
        }
    }

    public static void insertIntoJobControl(String datStart, String namJob, String namInstance, String codStatus) throws Exception {
        DaoResource resource = null;
        try {
            resource = instance.updateStatement(String.format(
                            " INSERT INTO ADSS_CONSOLIDATOR_ADM.job_control" +
                                            " (DAT_START, NAM_JOB, NAM_INSTANCE, COD_STATUS)" +
                                            " VALUES" +
                                            " (%s, '%s', '%s', '%s')"
                                            , datStart, namJob, namInstance, codStatus));
        } finally {
            instance.closeResources(resource);
        }
    }

    public static List<String> selectStatusFromJobControl() throws Exception {
        DaoResource resource = null;
        try {
            resource = instance.selectStatement(
                            "select cod_status                           " +
                            "from   ADSS_CONSOLIDATOR_ADM.job_control    " +
                            "where  nam_job = 'AdServerSAP-DFP-Importer' ");
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
