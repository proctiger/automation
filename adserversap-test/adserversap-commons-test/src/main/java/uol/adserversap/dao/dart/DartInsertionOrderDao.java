package uol.adserversap.dao.dart;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;
import uol.adserversap.entity.DartInsertionOrder;

public class DartInsertionOrderDao {

    public static DartInsertionOrder selectDartInsertionOrderById(long orderId) throws Exception {
        final String query = String.format("" +
                " SELECT ID, TSTAMP, PONUMBER, NOTES, NAME, ADVERTISERID, AGENCYID, BILLINGTYPEID, " +
                "        DISCOUNTAMOUNT, DISCOUNTPERCENT, ISRESERVATION                            " +
                " FROM   ADMANAGER.NG_INSERTIONORDERS                                              " +
                " WHERE  ID = '%s'                                                                 ",
                orderId);

        try(final Resource res = BaseDaoSingleton.getDartInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            if (rs != null && rs.next()) {
                final DartInsertionOrder order = new DartInsertionOrder();
                order.setId(rs.getLong("ID"));
                order.setTimestamp(rs.getDate("TSTAMP"));
                order.setPoNumber(rs.getString("PONUMBER"));
                order.setNotes(rs.getString("NOTES"));
                order.setName(rs.getString("NAME"));
                order.setAdvertiser(DartAdvertiserDao.selectDartAdvertiserById(rs.getLong("ADVERTISERID")));
                order.setAgency(DartAgencyDao.selectDartAgencyById(rs.getLong("AGENCYID")));
                order.setBillingTypeId(rs.getInt("BILLINGTYPEID"));
                order.setDiscountAmount(rs.getDouble("DISCOUNTAMOUNT"));
                order.setDiscountPercent(rs.getDouble("DISCOUNTPERCENT"));
                order.setReservation(rs.getInt("ISRESERVATION") == 1);

                return order;
            }
        }

        return null;
    }

    public static List<DartInsertionOrder> selectDartInsertionOrdersByPoNumber(String orderPoNumber) throws Exception {
        final List<DartInsertionOrder> insertionOrders = new ArrayList<>();
        final String query = String.format("" +
                " SELECT   ID, TSTAMP, PONUMBER, NOTES, NAME, ADVERTISERID, AGENCYID, BILLINGTYPEID, " +
                "          DISCOUNTAMOUNT, DISCOUNTPERCENT, ISRESERVATION                            " +
                " FROM     ADMANAGER.NG_INSERTIONORDERS                                              " +
                " WHERE    PONUMBER = '%s'                                                           " +
                " ORDER BY ID",
                orderPoNumber);

        try(final Resource res = BaseDaoSingleton.getDartInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            while (rs != null && rs.next()) {
                final DartInsertionOrder order = new DartInsertionOrder();
                order.setId(rs.getLong("ID"));
                order.setTimestamp(rs.getDate("TSTAMP"));
                order.setPoNumber(rs.getString("PONUMBER"));
                order.setNotes(rs.getString("NOTES"));
                order.setName(rs.getString("NAME"));
                order.setAdvertiser(DartAdvertiserDao.selectDartAdvertiserById(rs.getLong("ADVERTISERID")));
                order.setAgency(DartAgencyDao.selectDartAgencyById(rs.getLong("AGENCYID")));
                order.setBillingTypeId(rs.getInt("BILLINGTYPEID"));
                order.setDiscountAmount(rs.getDouble("DISCOUNTAMOUNT"));
                order.setDiscountPercent(rs.getDouble("DISCOUNTPERCENT"));
                order.setReservation(rs.getInt("ISRESERVATION") == 1);

                insertionOrders.add(order);
            }
        }

        return insertionOrders;
    }
}
