package uol.admanager.dao;

import java.util.HashSet;
import java.util.Set;

public class TypeAdvertisementDao {

    public static Set<Long> selectTypeAdvertisementIds() throws Exception {
        final String query =
                " SELECT idt_type_advertisement " +
                " FROM   type_advertisement     ";

        final Set<Long> types = new HashSet<>();

        try(final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().executeStatement(query)) {
            while (res.getResultSet().next()) {
                types.add(res.getResultSet().getLong("idt_type_advertisement"));
            }
        }

        return types;
    }
}
