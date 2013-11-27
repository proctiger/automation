package uol.adserversap.dao.sap;

import java.sql.ResultSet;
import java.util.ArrayList;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;
import uol.adserversap.entity.SapAdvertiser;

/**
 *
 * @author cin_wrodrigues
 */
public class SapAdvertiserDao {

    public static String countAdvertisersOnSap() throws Exception {

        String result = "";
        String query = ""
                + " SELECT     count(*) as count	"
                + " FROM        SAPR3.kna1			";

        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getDartInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result = rs.getString("count");
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static ArrayList<SapAdvertiser> selectAllAdvertisersSapOrderByName() throws Exception {
        ArrayList<SapAdvertiser> result = new ArrayList<>();
        String query = ""
                + " SELECT      kunnr id,							"
                + " 			name1 || name2 name,				"
                + " 			stcd1 cnpj							"
                + " FROM        SAPR3.kna1							"
                + " WHERE MANDT IN ( '400', '220')					"
                + " AND KTOKD IN ('Z040', 'Z050', 'Z060', 'Z070')	"
                + " ORDER BY    name								";

        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getSapInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result.add( new SapAdvertiser(rs.getString("id"), rs.getString("name"), rs.getString("cnpj")) );
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static SapAdvertiser selectOneAdvertiser() throws Exception {
        String query = "" +
                " SELECT kunnr id, name1 || name2 name, stcd1 cnpj " +
                " FROM   SAPR3.KNA1                                " +
                " WHERE  MANDT IN ('400', '220')                   " +
                " AND    KTOKD IN ('Z040', 'Z050', 'Z060', 'Z070') " +
                " AND    ROWNUM = 1                                ";

        try(final Resource res = BaseDaoSingleton.getSapInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            if (rs != null && rs.next()) {
                return new SapAdvertiser(rs.getString("id"), rs.getString("name"), rs.getString("cnpj"));
            }
        }

        return null;
    }

    public static SapAdvertiser selectAdvertiserById(String advertiserId) throws Exception {
        String query = String.format("" +
                " SELECT kunnr id, name1 || name2 name, stcd1 cnpj " +
                " FROM   SAPR3.KNA1                                " +
                " WHERE  KUNNR = '%s'                              " +
                " AND    MANDT IN ('400', '220')                   " +
                " AND    KTOKD IN ('Z040', 'Z050', 'Z060', 'Z070') " +
                " AND    ROWNUM = 1                                ",
                advertiserId);

        try(final Resource res = BaseDaoSingleton.getSapInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            if (rs != null && rs.next()) {
                return new SapAdvertiser(rs.getString("id"), rs.getString("name"), rs.getString("cnpj"));
            }
        }

        return null;
    }
}


