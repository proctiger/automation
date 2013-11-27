package uol.test.step.persistence;

import java.io.PrintWriter;
import java.util.List;

import org.junit.Assert;

import uol.affiliated.commons.test.CucumberTestUtil;
import uol.affiliated.commons.test.TestStep;
import uol.affiliated.commons.test.DAO.AeEventLogDAOImpl;
import uol.affiliated.commons.test.bean.AeEventLog;
import uol.affiliated.commons.test.exception.StepException;
import cucumber.api.java.pt.Entao;


public class PersistenceThenSteps  extends AbstractPersistenceSteps {	
    
    @Entao("^havera registro de <(.*)> na tabela de eventos de <(.*)> e afiliado <(.*)> com agrupadores <(.*)>$")
    public void verifyIfWasEventLog( final String eventType ,  final Integer productSource ,   final String namLogin ,  final String groupings) throws StepException {
        CucumberTestUtil.then(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                Assert.assertTrue("O Evento nao esta na tabela de eventos ",checkAeEventLog(eventType, productSource, namLogin , groupings));
            }
    });
    }
    
    @Entao("^nao havera registro de <(.*)> na tabela de eventos de <(.*)> e afiliado <(.*)> com agrupadores <(.*)>$")
    public void verifyIfWasNotEventLog(final String eventType , final Integer productSource ,  final String namLogin , final String groupings) throws StepException {
        CucumberTestUtil.then(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                Assert.assertFalse("O Evento esta na tabela de eventos ",checkAeEventLog(eventType, productSource, namLogin , groupings));
            }
    });
    }
    
    @Entao("^a resposta tera o codigo <(.*)>$")
    public void verifyHttpStatus(final Integer httpStatus) throws StepException {
        CucumberTestUtil.then(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                verifyHttpCode(httpStatus);
            }
    });
    }
    
    private void verifyHttpCode(final Integer httpStatus) {
        Assert.assertEquals("O Status http é diferente"  , PersistenceWhenSteps.httpStatus, httpStatus);
    }
    
    @Entao("^a resposta sera <(.*)> com codigo <(.*)>$")
    public void verifyResponse(final String status , final Integer httpStatus) throws StepException {
        CucumberTestUtil.then(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                verifyHttpCode(httpStatus);
                verifyStatusResponse(status);
            }
            
    });
    }
    
    private void verifyStatusResponse(String status) {
        Assert.assertEquals("O Status da resposta é diferente "  ,PersistenceWhenSteps.response.getStatus() , status);
    }
    
    private boolean checkAeEventLog( String eventType,Integer productSource, String namLogin , String groupings) throws StepException {
        AeEventLogDAOImpl dao = new AeEventLogDAOImpl(dbUtil);
        boolean response = false;
        try {
            List<AeEventLog> events = dao.selectEvent(namLogin, productSource, eventType , groupings);
            if(!events.isEmpty() && events != null){
                response = true; 
            }
        } catch(Exception e) {
                throw new StepException(e);
        }
      return response;
    }
}