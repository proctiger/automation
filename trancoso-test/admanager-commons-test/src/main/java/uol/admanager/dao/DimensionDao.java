package uol.admanager.dao;

import uol.admanager.entity.Dimension;


/**
 *
 * @author cin_wrodrigues
 */
public class DimensionDao {

    public static Dimension selectDimension(Long adId, Long channelId, Long customerId, Long packSaleId, Long slotId, Long typeAdId, Number hour) throws Exception {
        final String query =
                " SELECT idt_ad_dimension, idt_ad, idt_channel, idt_customer, idt_pack_sale, idt_slot, idt_type_advertisement, num_hour " +
                " FROM   ad_dimension                                                                                                   " +
                " WHERE  idt_ad = ?                                                                                                     " +
                " AND    idt_channel = ?                                                                                                " +
                " AND    idt_customer = ?                                                                                               " +
                " AND    idt_pack_sale = ?                                                                                              " +
                " AND    idt_slot = ?                                                                                                   " +
                " AND    idt_type_advertisement = ?                                                                                     " +
                " AND    num_hour = ?                                                                                                   ";

        try(final Resource res = BaseDaoSingleton
                .getConsolidatorInstance()
                .selectPreparedStatement(query, adId, channelId, customerId, packSaleId, slotId, typeAdId, hour.longValue())) {

            if (res.getResultSet().next()) {
                final Dimension dim = new Dimension();
                dim.setId(res.getResultSet().getLong("idt_ad_dimension"));
                dim.setAdvertisementId(res.getResultSet().getLong("idt_ad"));
                dim.setChannelId(res.getResultSet().getLong("idt_channel"));
                dim.setCustomerId(res.getResultSet().getLong("idt_customer"));
                dim.setPackSaleId(res.getResultSet().getLong("idt_pack_sale"));
                dim.setSlotId(res.getResultSet().getLong("idt_slot"));
                dim.setTypeAdvertisementId(res.getResultSet().getLong("idt_type_advertisement"));
                dim.setHour(res.getResultSet().getInt("num_hour"));

                return dim;
            }
        }

        return null;
    }
    
    public static boolean deleteDimension(Long id) throws Exception {
        final String query =
                " DELETE FROM ad_dimension         " +
                " WHERE       idt_ad_dimension = ? ";

        try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().updatePreparedStatement(query, id)) {
            return res.getResult() > 0;
        }
    }

    public static boolean deleteAllDimension() throws Exception {
        final String query = " DELETE FROM ADMGR_CONSOLIDATOR_ADM.AD_DIMENSION" ;

        try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().executeStatement(query)) {
            return res.getResult() > 0;
        }
    }

    public static boolean insertDimension(Long adId, Long channelId, Long customerId, Long packSaleId, Long slotId, Long typeAdId, Number hour) throws Exception {
        final String query =
                " INSERT INTO ad_dimension                                                                                                     " +
                "             (idt_ad_dimension, idt_ad, idt_channel, idt_customer, idt_pack_sale, idt_slot, idt_type_advertisement, num_hour) " +
                " VALUES      (admgr_consolidator_adm.sq_addime_idt.nextval, ?, ?, ?, ?, ?, ?, ?)                                              ";

        try(final Resource res = BaseDaoSingleton
                .getConsolidatorInstance()
                .updatePreparedStatement(query, adId, channelId, customerId, packSaleId, slotId, typeAdId, hour.longValue())) {

            return res.getResult() > 0;
        }
    }
}
