package uol.adserversap.dao.dart;

import java.sql.ResultSet;
import java.util.ArrayList;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;
import uol.adserversap.entity.DartAdvertiser;

/**
 *
 * @author cin_wrodrigues
 */
public class DartAdvertiserDao {


    public static ArrayList<DartAdvertiser> selectAllAdvertisersDartOrderByName() throws Exception {
        ArrayList<DartAdvertiser> result = new ArrayList<DartAdvertiser>();
        String query = "" +
                " SELECT   id, name, notes          " +
                " FROM     ADMANAGER.NG_ADVERTISERS " +
                " ORDER BY name                     ";

        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getDartInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result.add( new DartAdvertiser(Long.parseLong(rs.getString("id")), rs.getString("name"), rs.getString("notes")) );
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static DartAdvertiser getAdvertiserOnDart() throws Exception {
        DartAdvertiser advertiserDart = new DartAdvertiser();
        String query = "" +
                " SELECT id, name, notes          " +
                " FROM   ADMANAGER.NG_ADVERTISERS " +
                " WHERE  ROWNUM = 1               ";

        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getDartInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
            	advertiserDart =  new DartAdvertiser(Long.parseLong(rs.getString("id")), rs.getString("name"), rs.getString("notes"));
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return advertiserDart;
    }

    public static DartAdvertiser selectDartAdvertiserById(long advId) throws Exception {
        final String query = String.format("" +
        		" SELECT ID, FOREIGNIDENTIFIER, NAME, NOTES " +
        		" FROM   ADMANAGER.NG_ADVERTISERS           " +
        		" WHERE  ID = '%s'                          ",
        		advId);

        try(final Resource res = BaseDaoSingleton.getDartInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            if (rs != null && rs.next()) {
                final DartAdvertiser advertiser = new DartAdvertiser();
                advertiser.setId(rs.getLong("ID"));
                advertiser.setForeignId(rs.getString("FOREIGNIDENTIFIER"));
                advertiser.setName(rs.getString("NAME"));
                advertiser.setNotes("NOTES");

                return advertiser;
            }
        }

        return null;
    }

}

