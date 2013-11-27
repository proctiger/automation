package uol.adserversap.dao.sap;

import java.sql.ResultSet;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;
import uol.adserversap.entity.SapOvk;

public class SapOvkDao {

    public static SapOvk selectSapOvkById(String ovkId) throws Exception {
        final String query = String.format("" +
                " SELECT MANDT, ID, TIPO_ORDEM, TIMESTAMP, EMPRESA, ANUNCIANTE, AGENCIA, PAGADOR,               " +
                "        COND_PGTO, MOEDA, VAL_BRUTO, VAL_LIQUIDO, VAL_DESC_GERAL, VAL_DESC_ANUN, VAL_DESC_AGEN " +
                " FROM   SAPR3.ZDSDDARTSAP_OVK                                                                 " +
                " WHERE  ID = '%s'                                                                              ",
                ovkId);

        try(final Resource res = BaseDaoSingleton.getSapInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            if (rs != null && rs.next()) {
                final SapOvk ovk = new SapOvk();
                ovk.setMandt(rs.getString("MANDT"));
                ovk.setId(rs.getString("ID"));
                ovk.setOrderType(rs.getString("TIPO_ORDEM"));
                ovk.setTimestamp(rs.getString("TIMESTAMP"));
                ovk.setEnterprise(rs.getString("EMPRESA"));
                ovk.setAdvertiser(rs.getString("ANUNCIANTE"));
                ovk.setAgency(rs.getString("AGENCIA"));
                ovk.setPayer(rs.getString("PAGADOR"));
                ovk.setPaymentCondition(rs.getString("COND_PGTO"));
                ovk.setCurrency(rs.getString("MOEDA"));
                ovk.setGrossValue(rs.getDouble("VAL_BRUTO"));
                ovk.setNetValue(rs.getDouble("VAL_LIQUIDO"));
                ovk.setGeneralDiscount(rs.getDouble("VAL_DESC_GERAL"));
                ovk.setAdvertiserDiscount(rs.getDouble("VAL_DESC_ANUN"));
                ovk.setAgencyDiscount(rs.getDouble("VAL_DESC_AGEN"));

                return ovk;
            }
        }

        return null;
    }
}
