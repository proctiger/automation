package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.pt.Entao;

public class LocalServiceThenSteps extends AbstractLocalServiceSteps {

    @Entao("^validar que os dados <(.+)> estejam persistidos na tabela de eventos$")
    public void checkAeEventLogForPayment(String dadosDePagamento) throws Exception {
        System.out.println("\n -> Entao validar que os dados " + dadosDePagamento + " estejam persistidos na tabela de eventos \n");
        
        if (commonsCheckAeEventLogForPayment(dadosDePagamento)) {
            System.out.println("OK os dados estao persistidos na tabela de eventos");
        } else {
            Assert.fail("NOK os dados nao estao persistidos na tabela de eventos");
        }
        realRC();
    }

    @Entao("^validar que os dados <(.+)> nao estejam persistidos na tabela de eventos$")
    public void checkAeEventLogNotExist(String dadosDePagamento) throws Exception {
        System.out.println("\n -> Entao validar que os dados " + dadosDePagamento + " nao estejam persistidos na tabela de eventos \n");
        
        if (!commonsCheckAeEventLogForPayment(dadosDePagamento)) {
            System.out.println("OK os dados nao estao persistidos na tabela de eventos");
        } else {
            Assert.fail("NOK os dados estao persistidos na tabela de eventos");
        }
        realRC();
    }

    @Entao("^validar que os dados persistidos na tabela de eventos possuam o agrupador de indicacao$")
    public void checkAeEventLogWithIndication() throws Exception {
        System.out.println("\n --> Entao validar que os dados persistidos na tabela de eventos possuam o agrupador de indicacao (I:1) <-- \n");
        
        boolean rs = executeQueryAndGetResult("select * from AE_EVENT_LOG where DES_NAME = 'Compra' and IDT_PRODUCT_SOURCE = 13 and DES_GROUPING like '%I:1%'");
        if (rs) {
            System.out.println("\n --> OK os dados estao persistidos na tabela de eventos com agrupador de indicacao (I:1) <-- \n");
        } else {
            Assert.fail("\n --> NOK os dados nao estao persistidos na tabela de eventos com agrupador de indicacao (I:1)");
        }
        realRC();
    }

    private boolean commonsCheckAeEventLogForPayment(String dadosDePagamento) throws Exception {
        String sql = "SELECT * FROM AE_EVENT_LOG WHERE DES_NAME = 'Compra' AND IDT_PRODUCT_SOURCE = 13 AND DES_GROUPING like (select '%' || ua.NAM_LOGIN || '%' from USER_ALL ua , INSCRIPTION i where ua.IDT_PERSON = i.IDT_USER_ALL and i.IDT_INSCRIPTION_ACCOUNT = ?)";
        JSONObject json = new JSONObject(dadosDePagamento);
        return executeQueryAndGetResult(sql, json.getString("idtInscriptionAccount"));
    }

//    private void deleteIndicationRecordsAfterTest() {
//        System.out.println("\n ------> Deletando dados persistidos na AE_EVENT_LOG de indicacao para proxima execucao....");
//        
//        Connection conn = null;
//        PreparedStatement statement;
//        try {
//            conn = getConnectionUol3();
//            conn.setAutoCommit(false);
//            statement = conn.prepareStatement("delete from AE_EVENT_LOG where DES_NAME = 'Compra' and DES_GROUPING like '%I:1%' and IDT_PRODUCT_SOURCE = 13");
//            int rs = statement.executeUpdate();
//            if (rs <= 0) {
//                Assert.fail("\n --> Dados de indicacao na AE_EVENT_LOG nao foram apagados apos a execucao");
//            } else if (rs == 1) {
//                conn.commit();
//                System.out.println("\n --> Dado inserido apagado com sucesso!");
//            } else {
//                conn.rollback();
//            }
//        } catch (SQLException e) {
//            Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
//        } 
//    }
    
    private boolean executeQueryAndGetResult(String sql,
                                             String... params) {
        Connection conn;
        PreparedStatement statement;
        ResultSet rs;
        try {
            conn = getConnectionUol3();
            statement = conn.prepareStatement(sql);
            if (params != null && params.length > 0) {
                for (int pos = 0; pos < params.length; pos++) {
                    int paramIdx = pos + 1;
                    statement.setString(paramIdx, params[pos]);
                }
            }
            rs = statement.executeQuery();
            while (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
        }
        return false;
    }
}