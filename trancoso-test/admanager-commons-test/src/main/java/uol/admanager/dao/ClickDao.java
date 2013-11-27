package uol.admanager.dao;

import java.util.Date;

import uol.admanager.entity.Click;


/**
 *
 * @author cin_wrodrigues
 */
public class ClickDao {

    public static Click selectClickByDimensionAndDate(Long dimensionId, Date date) throws Exception {
        final String query =
                " SELECT idt_ad_click, idt_ad_dimension, dat_ad_click, num_quantity " +
                " FROM   ad_click                                                   " +
                " WHERE  idt_ad_dimension = ?                                       " +
                " AND    dat_ad_click = ?                                           ";

        try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().selectPreparedStatement(query, dimensionId, date)) {
            if (res.getResultSet().next()) {
                final Click click = new Click();
                click.setId(res.getResultSet().getLong("idt_ad_click"));
                click.setDimensionId(res.getResultSet().getLong("idt_ad_dimension"));
                click.setDate(res.getResultSet().getDate("dat_ad_click"));
                click.setQuantity(res.getResultSet().getLong("num_quantity"));

                return click;
            }
        }

        return null;
    }

    public static boolean deleteClick(Long id) throws Exception {
        final String query =
                " DELETE FROM ad_click         " +
                " WHERE       idt_ad_click = ? ";

        try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().updatePreparedStatement(query, id)) {
            return res.getResult() > 0;
        }
    }
    
    public static boolean deleteAllClick() throws Exception{
    	 final String query =
    			" DELETE FROM ad_click         ";

    	 try(final Resource res = BaseDaoSingleton.getConsolidatorInstance().executeStatement(query)) {
            return res.getResult() > 0;
        }
    }

    public static boolean insertClick(Long dimensionId, Date date, Number quantity) throws Exception {
        final String query =
                " INSERT INTO ad_click (idt_ad_click, idt_ad_dimension, dat_ad_click, num_quantity) " +
                " VALUES               (admgr_consolidator_adm.sq_adclic_idt.nextval, ?, ?, ?)      ";

        try(final Resource res = BaseDaoSingleton
                .getConsolidatorInstance()
                .updatePreparedStatement(query, dimensionId, date, quantity.longValue())) {

            return res.getResult() > 0;
        }
    }
}
