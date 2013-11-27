package uol.adserversap.dao.dart;

import java.sql.ResultSet;
import java.util.ArrayList;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;
import uol.adserversap.entity.DartAgency;



/**
 *
 * @author cin_wrodrigues
 */
public class DartAgencyDao {


    public static ArrayList<DartAgency> selectAllAgenciesDartOrderByName() throws Exception {
        ArrayList<DartAgency> result = new ArrayList<>();
        String query = "" +
                " SELECT   id, name, notes       " +
                " FROM     ADMANAGER.NG_AGENCIES " +
                " ORDER BY name                  ";

        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getDartInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result.add( new DartAgency(Long.parseLong(rs.getString("id")), rs.getString("name"), rs.getString("notes")) );

            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static DartAgency getAgencyOnDart() throws Exception {
        DartAgency agencyDART = new DartAgency();
        String query = "" +
                " SELECT id, name, notes       " +
                " FROM   ADMANAGER.NG_AGENCIES " +
                " WHERE  ROWNUM = 1            ";

        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getDartInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                agencyDART =  new DartAgency(Long.parseLong(rs.getString("id")), rs.getString("name"), rs.getString("notes"));
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return agencyDART;
    }

    public static DartAgency selectDartAgencyById(long agId) throws Exception {
        final String query = String.format("" +
                " SELECT ID, FOREIGNIDENTIFIER, NAME, NOTES " +
                " FROM   ADMANAGER.NG_AGENCIES              " +
                " WHERE  ID = '%s'                          ",
                agId);

        try(final Resource res = BaseDaoSingleton.getDartInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            if (rs != null && rs.next()) {
                final DartAgency agency = new DartAgency();
                agency.setId(rs.getLong("ID"));
                agency.setForeignId(rs.getString("FOREIGNIDENTIFIER"));
                agency.setName(rs.getString("NAME"));
                agency.setNotes("NOTES");

                return agency;
            }
        }

        return null;
    }
}

