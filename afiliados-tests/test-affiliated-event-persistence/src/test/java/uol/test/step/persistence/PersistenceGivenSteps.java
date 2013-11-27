package uol.test.step.persistence;

import java.io.PrintWriter;

import uol.affiliated.commons.test.CucumberTestUtil;
import uol.affiliated.commons.test.TestStep;
import uol.affiliated.commons.test.DAO.AeEventLogDAOImpl;
import uol.affiliated.commons.test.exception.StepException;
import cucumber.api.java.pt.Dado;



public class PersistenceGivenSteps  extends AbstractPersistenceSteps {	
    
    @Dado("^que nao exista um evento de <(.*)> para o afiliado <(.+)> na tabela de eventos$")
    public void cleanEventLog(final String eventType , final String namLogin) throws StepException {
        CucumberTestUtil.given(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                    AeEventLogDAOImpl aeeventlogdaoimpl = new AeEventLogDAOImpl(dbUtil);
                    try {
                            aeeventlogdaoimpl.deleteEventByNamLoginAndDesName(namLogin, eventType);
                    } catch(Exception e) {
                            throw new StepException(e);
                    }
                    printer.println(String.format("Eventos de %s do afiliado %s deletados.", eventType ,namLogin));
            }
    });
    }
}
