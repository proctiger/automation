package uol.adserversap.dao.ploc;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import uol.adserversap.dao.BaseDaoSingleton;
import uol.adserversap.dao.Resource;
import uol.adserversap.entity.Association;
import uol.adserversap.entity.OrderAssociation;

/**
 *
 * @author cin_wrodrigues
 */
public class AssociationDao {


    public static ArrayList<Association> selectAllAdvertisersAssociations() throws Exception {
        ArrayList<Association> result = new ArrayList<Association>();
        String query = "" +
                " SELECT  idt_advertiser_source sourceID, " +
                "         idt_advertiser_sap sapID        " +
                " FROM    ADVERTISER_ASSOCIATION          ";

        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getPlocInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result.add( new Association(Long.parseLong(rs.getString("sourceID")), Long.parseLong(rs.getString("sapID"))));
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static ArrayList<Association> selectAdvertisersAssociations(Association association) throws Exception {
        ArrayList<Association> result = new ArrayList<Association>();
        String query = String.format("" +
                " SELECT idt_advertiser_source sourceID,         " +
                "        idt_advertiser_sap sapID                " +
                " FROM   ADSERVER_SAP_ADM.ADVERTISER_ASSOCIATION " +
                " WHERE  idt_advertiser_source = '%s'            " +
                " AND	   id_advertiser_sap = '%s'              ",
                association.getSourceID(),
                association.getSapID());
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getPlocInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result.add( new Association(Long.parseLong(rs.getString("sourceID")), Long.parseLong(rs.getString("sapID"))));
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static ArrayList<Association> selectAgencyAssociations(Association association) throws Exception {
        ArrayList<Association> result = new ArrayList<Association>();
        String query = String.format("" +
                " SELECT idt_agency_source sourceID,         " +
                "        idt_agency_sap sapID                " +
                " FROM   ADSERVER_SAP_ADM.AGENCY_ASSOCIATION " +
                " WHERE  idt_agency_source = '%s'            " +
                " AND    idt_agency_sap = '%s'               ",
                association.getSourceID(),
                association.getSapID());
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getPlocInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result.add( new Association(Long.parseLong(rs.getString("sourceID")), Long.parseLong(rs.getString("sapID"))));
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }


    public static ArrayList<Association> selectAllAgenciesAssociations() throws Exception {
        ArrayList<Association> result = new ArrayList<Association>();
        String query = "" +
                " SELECT idt_agency_source sourceID,         " +
                "        idt_agency_sap sapID                " +
                " FROM   ADSERVER_SAP_ADM.AGENCY_ASSOCIATION ";

        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getPlocInstance().
                    selectStatement(query);
            ResultSet rs = resource.getResultSet();
            if (rs == null) {
                throw new Exception(String.format("Query não retornou nenhum registro [%s]", query));
            }
            while (rs.next()) {
                result.add( new Association(Long.parseLong(rs.getString("sourceID")), Long.parseLong(rs.getString("sapID"))));
            }
        } finally {
            BaseDaoSingleton.getEtlInstance().closeResources(resource);
        }
        return result;
    }

    public static void deleteAdvertiserAssociation(Association association) throws Exception {
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getPlocInstance().
                    updateStatement(String.format("" +
                            " DELETE FROM ADSERVER_SAP_ADM.ADVERTISER_ASSOCIATION " +
                            " WHERE       idt_advertiser_source = '%s'            ",
                            association.getSourceID()));
        } finally {
            BaseDaoSingleton.getPlocInstance().closeResources(resource);
        }
    }

    public static void insertAdvertiserAssociation(Association association) throws Exception {
        Resource resource = null;
        try {
            Date date = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yy");

            resource = BaseDaoSingleton.getPlocInstance().
                    updateStatement(String.format("" +
                            "	Insert into adserver_sap_adm.advertiser_association              " +
                            "	(IDT_ADVERTISER_SAP,IDT_ADVERTISER_SOURCE,NAM_SOURCE,DAT_UPDATE) " +
                            " values ('%s','%s','%s', to_date('%s',\'dd/mm/yyyy\'))            ",
                            association.getSapID(),   association.getSourceID(), "DART", fm.format(date) ));

        } finally {
            BaseDaoSingleton.getPlocInstance().closeResources(resource);
        }
    }


    public static void deleteAgencyAssociation(Association association) throws Exception {
        Resource resource = null;
        try {
            resource = BaseDaoSingleton.getPlocInstance().
                    updateStatement(String.format("" +
                            " DELETE FROM ADSERVER_SAP_ADM.AGENCY_ASSOCIATION " +
                            " WHERE       idt_agency_source = '%s'            ",
                            association.getSourceID()));
        } finally {
            BaseDaoSingleton.getPlocInstance().closeResources(resource);
        }
    }

    public static void insertAgencyAssociation(Association association) throws Exception {
        Resource resource = null;
        try {
            Date date = new Date();
            SimpleDateFormat fm = new SimpleDateFormat("dd/MM/yy");

            resource = BaseDaoSingleton.getPlocInstance().
                    updateStatement(String.format("" +
                            " Insert into ADSERVER_SAP_ADM.AGENCY_ASSOCIATION          " +
                            " (IDT_AGENCY_SAP,IDT_AGENCY_SOURCE,NAM_SOURCE,DAT_UPDATE) " +
                            " values ('%s','%s','%s', to_date('%s',\'dd/mm/yyyy\'))    ",
                            association.getSapID(),   association.getSourceID(), "DART", fm.format(date) ));

        } finally {
            BaseDaoSingleton.getPlocInstance().closeResources(resource);
        }
    }

    public static List<OrderAssociation> selectOrderAssociationsById(long orderId) throws Exception {
        final List<OrderAssociation> associations = new ArrayList<>();
        final String query = String.format("" +
                " SELECT   IDT_ORDER, DES_PO_NUMBER, NAM_SOURCE, DAT_UPDATE, NAM_ENTERPRISE " +
                " FROM     ADSERVER_SAP_ADM.ORDER_ASSOCIATION                               " +
                " WHERE    IDT_ORDER = '%s'                                                 " +
                " ORDER BY DES_PO_NUMBER, NAM_SOURCE, NAM_ENTERPRISE, DAT_UPDATE            ",
                orderId);

        try(final Resource res = BaseDaoSingleton.getPlocInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            while (rs != null && rs.next()) {
                final OrderAssociation association = new OrderAssociation();
                association.setId(rs.getLong("IDT_ORDER"));
                association.setPoNumber(rs.getString("DES_PO_NUMBER"));
                association.setSource(rs.getString("NAM_SOURCE"));
                association.setLastUpdate(rs.getDate("DAT_UPDATE"));
                association.setEnterprise(rs.getString("NAM_ENTERPRISE"));

                associations.add(association);
            }
        }

        return associations;
    }

    public static List<OrderAssociation> selectOrderAssociationsByPoNumber(String orderPoNumber) throws Exception {
        final List<OrderAssociation> associations = new ArrayList<>();
        final String query = String.format("" +
                " SELECT   IDT_ORDER, DES_PO_NUMBER, NAM_SOURCE, DAT_UPDATE, NAM_ENTERPRISE " +
                " FROM     ADSERVER_SAP_ADM.ORDER_ASSOCIATION                               " +
                " WHERE    DES_PO_NUMBER = '%s'                                             " +
                " ORDER BY IDT_ORDER, NAM_SOURCE, NAM_ENTERPRISE, DAT_UPDATE                ",
                orderPoNumber);

        try(final Resource res = BaseDaoSingleton.getPlocInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            while (rs != null && rs.next()) {
                final OrderAssociation association = new OrderAssociation();
                association.setId(rs.getLong("IDT_ORDER"));
                association.setPoNumber(rs.getString("DES_PO_NUMBER"));
                association.setSource(rs.getString("NAM_SOURCE"));
                association.setLastUpdate(rs.getDate("DAT_UPDATE"));
                association.setEnterprise(rs.getString("NAM_ENTERPRISE"));

                associations.add(association);
            }
        }

        return associations;
    }

    public static List<OrderAssociation> selectOrderAssociationsByEnterprise(String enterprise) throws Exception {
        final List<OrderAssociation> associations = new ArrayList<>();
        final String query = String.format("" +
                " SELECT   IDT_ORDER, DES_PO_NUMBER, NAM_SOURCE, DAT_UPDATE, NAM_ENTERPRISE " +
                " FROM     ADSERVER_SAP_ADM.ORDER_ASSOCIATION                               " +
                " WHERE    NAM_ENTERPRISE = '%s'                                            " +
                " ORDER BY IDT_ORDER, DES_PO_NUMBER, NAM_SOURCE, NAM_ENTERPRISE, DAT_UPDATE ",
                enterprise);

        try(final Resource res = BaseDaoSingleton.getPlocInstance().selectStatement(query)) {
            final ResultSet rs = res.getResultSet();
            while (rs != null && rs.next()) {
                final OrderAssociation association = new OrderAssociation();
                association.setId(rs.getLong("IDT_ORDER"));
                association.setPoNumber(rs.getString("DES_PO_NUMBER"));
                association.setSource(rs.getString("NAM_SOURCE"));
                association.setLastUpdate(rs.getDate("DAT_UPDATE"));
                association.setEnterprise(rs.getString("NAM_ENTERPRISE"));

                associations.add(association);
            }
        }

        return associations;
    }
}
