package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import uol.test.step.AbstractTestStep;

/* 
 * @author tsantos
 */
public abstract class AbstractLocalServiceSteps extends AbstractTestStep {

    protected static float actualNumBalance;

    protected static String desNameEvent;

    protected static int idtProductSourceEvent;
    
    protected static String eventTypeForTestCase;
    
    protected static EventProcessorRunnable eventProcessorRunnable;
    


    public void setActualNumBalance(float actualNumBalance) {
        this.actualNumBalance = actualNumBalance;
    }

    protected long sqNextVal(String sqName) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn;
        try {
            conn = getConnectionUol3();
            statement = conn.prepareStatement("SELECT " + sqName + ".nextval FROM DUAL");
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getLong("NEXTVAL");
            }
        } catch (SQLException e) {
            Assert.fail(String.format("\n Erro ao encontrar a sequence da tabela %s a: %s", sqName, e.getLocalizedMessage()));
        }
        return 0;
    }

    protected String selectCafByIdtPerson(String idtPerson) {
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn;
        try {
            conn = getConnectionUol3();
            statement = conn.prepareStatement("SELECT COD_DISPLAY_SUPPLIER FROM AFFILIATED_ADM.USER_SUPPLIER WHERE IDT_PERSON = ?");
            statement.setString(1, idtPerson);
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("COD_DISPLAY_SUPPLIER");
            } else {
                Assert.fail(String.format("\n Não foi possível encontrar o caf do afiliado %s na tabela supplier", idtPerson));
            }
        } catch (SQLException e) {
            Assert.fail(String.format("\n Erro ao tentar apagar o evento de indicacao da tabela de extrato devido a: %s", e.getLocalizedMessage()));
        }
        return null;
    }

    protected String selectidtPersonSonByidtPersonFather(String idtPersonParent) {
        PreparedStatement statement = null;
        ResultSet rs;
        Connection conn;
        try {
            conn = getConnectionUol3();
            statement = conn.prepareStatement("SELECT IDT_PERSON FROM AFFILIATED_ADM.USER_SUPPLIER_PARENT WHERE IDT_PERSON_PARENT = ?");
            statement.setString(1, idtPersonParent);
            rs = statement.executeQuery();
            if (rs.next()) {
                return rs.getString("IDT_PERSON");
            } else {
                Assert.fail(String.format("\n Não foi possível encontrar o afiliado filho do afiliado pai %s na tabela supplier", idtPersonParent));
            }
        } catch (SQLException e) {
            Assert.fail(String.format("\n Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
        }
        return null;
    }

    public void startJobNow() {
        startJob("affiliated-event-processor.sh", "startwait", "affiliated-event-processor", "a1-zumbi-afil-s-prt1");
    }

    public void stopComponentNow(String applicationName,
                                 String container,
                                 String host,
                                 String pwd) {
        stopApplication(applicationName, container, host, pwd);
    }

    public void startComponentNow(String applicationName,
                                  String container,
                                  String host,
                                  String pwd) {
        startAndWaitApplication(applicationName, container, host, pwd);
    }
    
    protected static class EventProcessorRunnable implements Runnable {
        private boolean finished = false;
        private boolean started = false;
        @Override
        public void run() {
            System.out.println("\n -> Quando o processador de eventos for executado de forma paralela\n");
            System.out.println("Executando o Processor");
            LocalServiceStepsImpl localServiceStepsImpl = new LocalServiceStepsImpl();
            setStarted(true);
           /* try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.yield();*/
            localServiceStepsImpl.startJobNow();
            System.out.println("\n <- Quando o processador de eventos for executado de forma paralela\n");
            finished = true;
        }
        public boolean isFinished() {
            return finished;
        }
        public boolean isStarted() {
            return started;
        }
        public void setStarted(boolean started) {
            this.started = started;
        }
    }
}