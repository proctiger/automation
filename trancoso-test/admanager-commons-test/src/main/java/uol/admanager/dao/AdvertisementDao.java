package uol.admanager.dao;

import java.util.HashSet;
import java.util.Set;

import uol.admanager.entity.Advertisement;

public class AdvertisementDao {

    public static Set<Advertisement> selectAdvertisementsLike(String name) throws Exception {
        final String query =
                " SELECT idt_advertisement, idt_customer, idt_type_advertisement " +
                " FROM   advertisement                                           " +
                " WHERE  nam_advertisement LIKE '%" + name + "%'                  ";

        final Set<Advertisement> advertisements = new HashSet<>();

        try(final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().executeStatement(query)) {
            while (res.getResultSet().next()) {
                final Advertisement ad = new Advertisement();
                ad.setId(res.getResultSet().getLong("idt_advertisement"));
                ad.setCustomerId(res.getResultSet().getLong("idt_customer"));
                ad.setTypeAdvertisementId(res.getResultSet().getLong("idt_type_advertisement"));

                advertisements.add(ad);
            }
        }

        return advertisements;
    }

    public static boolean insertAdvertisement(String name, String url, Long customerId, Long typeAdvertisementId) throws Exception {
        final String query =
                " INSERT INTO advertisement (idt_customer, flg_purge, des_url, nam_advertisement, dat_creation, dat_last_update, cod_status, flg_default, flg_archive, idt_type_advertisement) " +
                " VALUES (" + customerId + ", 0, '" + url + "', " + name + ", SYSDATE(), SYSDATE(), 2, 0, 0, " + typeAdvertisementId + ") ";

        try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().updateStatement(query)) {
            return res.getResult() > 0;
        }
    }
}
