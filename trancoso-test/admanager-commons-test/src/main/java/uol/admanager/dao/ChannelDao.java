package uol.admanager.dao;

import java.util.HashSet;
import java.util.Set;

public class ChannelDao {

    public static Set<Long> selectChannelIds() throws Exception {
        final String query =
                " SELECT idt_channel " +
                " FROM   channel     ";

        final Set<Long> channels = new HashSet<>();

        try(final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().executeStatement(query)) {
            while (res.getResultSet().next()) {
                channels.add(res.getResultSet().getLong("idt_channel"));
            }
        }

        return channels;
    }
}
