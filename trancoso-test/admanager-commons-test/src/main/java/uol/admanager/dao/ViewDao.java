package uol.admanager.dao;

import java.util.Date;

import uol.admanager.entity.View;


/**
 *
 * @author cin_wrodrigues
 */
public class ViewDao {

    public static View selectViewByDimensionAndDate(Long dimensionId, Date date) throws Exception {
        final String query =
                " SELECT idt_ad_view, idt_ad_dimension, dat_ad_view, num_quantity " +
                " FROM   ad_view                                                  " +
                " WHERE  idt_ad_dimension = ?                                     " +
                " AND    dat_ad_view = ?                                          ";

        try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().selectPreparedStatement(query, dimensionId, date)) {
            if (res.getResultSet().next()) {
                final View view = new View();
                view.setId(res.getResultSet().getLong("idt_ad_view"));
                view.setDimensionId(res.getResultSet().getLong("idt_ad_dimension"));
                view.setDate(res.getResultSet().getDate("dat_ad_view"));
                view.setQuantity(res.getResultSet().getLong("num_quantity"));

                return view;
            }
        }

        return null;
    }

    public static boolean deleteView(Long id) throws Exception {
        final String query =
                " DELETE FROM ad_view         " +
                " WHERE       idt_ad_view = ? ";

        try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().updatePreparedStatement(query, id)) {
            return res.getResult() > 0;
        }
    }

    public static boolean deleteAllView() throws Exception {
        final String query = " DELETE FROM ad_view ";

        try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().executeStatement(query)) {
            return res.getResult() > 0;
        }
    }

    public static boolean insertView(Long dimensionId, Date date, Number quantity) throws Exception {
        final String query =
                " INSERT INTO ad_view (idt_ad_view, idt_ad_dimension, dat_ad_view, num_quantity) " +
                " VALUES              (admgr_consolidator_adm.sq_adview_idt.nextval, ?, ?, ?)    ";

        try(final Resource res = BaseDaoSingleton
                .getConsolidatorInstance()
                .updatePreparedStatement(query, dimensionId, date, quantity.longValue())) {

            return res.getResult() > 0;
        }
    }
}
