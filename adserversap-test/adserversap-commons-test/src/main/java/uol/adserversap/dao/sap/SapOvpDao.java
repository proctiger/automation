package uol.adserversap.dao.sap;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;
import uol.adserversap.entity.SapOvp;

public class SapOvpDao {

    public static List<SapOvp> selectSapOvpById(String ovpId) throws Exception {
        final String query = String.format("" +
                " SELECT MANDT, ID, TIPO_ORDEM, TIMESTAMP, PRODUTO_SAP, VAL_BRUTO, VAL_LIQUIDO, " +
                "        VAL_DESC_GERAL, VAL_DESC_ANUN, VAL_DESC_AGEN                           " +
                " FROM   SAPR3.ZDSDDARTSAP_OVP                                                 " +
                " WHERE  ID = '%s'                                                              " +
                " ORDER  BY PRODUTO_SAP ASC                                                     ",
                ovpId);

        try(final Resource res = BaseDaoSingleton.getSapInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            final List<SapOvp> ovps = new ArrayList<>();
            while (rs != null && rs.next()) {
                final SapOvp ovp = new SapOvp();
                ovp.setMandt(rs.getString("MANDT"));
                ovp.setId(rs.getString("ID"));
                ovp.setOrderType(rs.getString("TIPO_ORDEM"));
                ovp.setTimestamp(rs.getString("TIMESTAMP"));
                ovp.setSapProduct(rs.getString("PRODUTO_SAP"));
                ovp.setGrossValue(rs.getDouble("VAL_BRUTO"));
                ovp.setNetValue(rs.getDouble("VAL_LIQUIDO"));
                ovp.setGeneralDiscount(rs.getDouble("VAL_DESC_GERAL"));
                ovp.setAdvertiserDiscount(rs.getDouble("VAL_DESC_ANUN"));
                ovp.setAgencyDiscount(rs.getDouble("VAL_DESC_AGEN"));

                ovps.add(ovp);
            }

            return ovps;
        }
    }
}
