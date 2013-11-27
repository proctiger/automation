package uol.admanager.dao;

import java.util.HashSet;
import java.util.Set;

import uol.admanager.entity.PackSale;


public class PackSaleDao {

    public static PackSale selectPackSale(Long id) throws Exception {
        final String query =
                " SELECT idt_pack_sale, idt_customer, idt_channel " +
                " FROM   pack_sale                                " +
                " WHERE  idt_pack_sale = ?                        ";

        try(final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().selectPreparedStatement(query, id)) {
            if (res.getResultSet().next()) {
                final PackSale packSale = new PackSale();
                packSale.setId(res.getResultSet().getLong("idt_pack_sale"));
                packSale.setCustomerId(res.getResultSet().getLong("idt_customer"));
                packSale.setChannelId(res.getResultSet().getLong("idt_channel"));

                return packSale;
            }
        }

        return null;
    }

    public static Set<PackSale> selectPackSale(Long customerId, Long channelId) throws Exception {
        final String query =
                " SELECT idt_pack_sale, idt_customer, idt_channel " +
                " FROM   pack_sale                                " +
                " WHERE  idt_customer = ?                         " +
                " AND    idt_channel = ?                          ";

        final Set<PackSale> sales = new HashSet<>();

        try(final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().selectPreparedStatement(query, customerId, channelId)) {
            while (res.getResultSet().next()) {
                final PackSale packSale = new PackSale();
                packSale.setId(res.getResultSet().getLong("idt_pack_sale"));
                packSale.setCustomerId(res.getResultSet().getLong("idt_customer"));
                packSale.setChannelId(res.getResultSet().getLong("idt_channel"));

                sales.add(packSale);
            }
        }

        return sales;
    }

    public static boolean insertPackSale(Long customerId, Long channelId) throws Exception {
        final String query =
                " INSERT INTO pack_sale (dat_creation, idt_customer, flg_purge, idt_channel) " +
                " VALUES (SYSDATE(), ?, 0, ?) ";

        try(final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().updatePreparedStatement(query, customerId, channelId)) {
            return res.getResult() > 0;
        }
    }
}
