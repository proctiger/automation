package uol.admanager.dao;

import java.util.HashSet;
import java.util.Set;

public class CustomerDao {

    public static Set<Long> selectCustomerIdsLike(String name) throws Exception {
        final String query =
                " SELECT idt_customer " +
                " FROM   customer     " +
                " WHERE  nam_customer LIKE '%" + name + "%'";

        final Set<Long> ids = new HashSet<>();

        try (final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().executeStatement(query)) {
            while (res.getResultSet().next()) {
                ids.add(res.getResultSet().getLong("idt_customer"));
            }
        }

        return ids;
    }

    public static boolean insertCustomer(String customerName) throws Exception {
        final String query =
                " INSERT INTO customer (nam_customer, dat_creation, flg_purge, des_color, flg_replaceable) " +
                " VALUES (?, SYSDATE(), 0, '#000000', 0) ";

        try (final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().updatePreparedStatement(query, customerName)) {
            return res.getResult() > 0;
        }
    }
}
