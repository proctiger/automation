package uol.admanager.dao;

import java.util.HashSet;
import java.util.Set;

import uol.admanager.entity.Slot;

public class SlotDao {

    public static Set<Slot> selectSlots(Long typeAdvertisementId) throws Exception {
        final String query =
                " SELECT idt_slot, idt_channel, idt_type_advertisement, des_slot " +
                " FROM   slot                                                    " +
                " WHERE  idt_type_advertisement = ?                              ";

        final Set<Slot> slots = new HashSet<>();

        try(final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().selectPreparedStatement(query, typeAdvertisementId)) {
            while (res.getResultSet().next()) {
                final Slot slot = new Slot();
                slot.setId(res.getResultSet().getLong("idt_slot"));
                slot.setChannelId(res.getResultSet().getLong("idt_channel"));
                slot.setTypeAdvertisementId(res.getResultSet().getLong("idt_type_advertisement"));
                slot.setDescription(res.getResultSet().getString("des_slot"));

                slots.add(slot);
            }
        }

        return slots;
    }

    public static Slot selectSlot(Long id) throws Exception {
        final String query =
                " SELECT idt_slot, idt_channel, idt_type_advertisement, des_slot " +
                " FROM   slot                                                    " +
                " WHERE  idt_slot = ?                                            ";

        try(final Resource res = BaseDaoSingleton.getAdmanagerAdmInstance().selectPreparedStatement(query, id)) {
            if (res.getResultSet().next()) {
                final Slot slot = new Slot();
                slot.setId(res.getResultSet().getLong("idt_slot"));
                slot.setChannelId(res.getResultSet().getLong("idt_channel"));
                slot.setTypeAdvertisementId(res.getResultSet().getLong("idt_type_advertisement"));
                slot.setDescription(res.getResultSet().getString("des_slot"));

                return slot;
            }
        }

        return null;
    }
}
