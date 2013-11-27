package uol.test.step.persistence;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;

import javax.ws.rs.core.MediaType;

import org.junit.Assert;

import uol.affiliated.commons.rest.client.RestServiceCaller;
import uol.affiliated.commons.rest.client.factory.RestServiceCallerFactory;
import uol.affiliated.commons.test.CucumberTestUtil;
import uol.affiliated.commons.test.TestStep;
import uol.affiliated.commons.test.DAO.UserSupplierImpl;
import uol.affiliated.commons.test.bean.AeEventLog;
import uol.affiliated.commons.test.exception.StepException;
import uol.test.step.persistence.model.PersistenceResponse;
import cucumber.api.java.pt.Quando;

public class PersistenceWhenSteps  extends AbstractPersistenceSteps {	

    private static final String persistenceURI = "http://event-persistence.ws.afiliados.intranet";

    @Quando("^for feita um chamada do evento de <(.*)> para o produto <(.*)> e afiliado <(.*)> com valores <(.*)> para o servico de persistencia$")
    public void persistEventClick(final String eventType, final Long productSource , final String namLogin ,final String groupings) throws StepException {
        CucumberTestUtil.when(new TestStep() {
            public void execute(PrintWriter printer) throws StepException {
                String caf = null;
                UserSupplierImpl dao = new UserSupplierImpl(dbUtil);
                try {
                    caf = dao.selectCafByNamLogin(namLogin);
                    printer.println(String.format("Caf - %s",caf));
                } catch(Exception e) {
                        throw new StepException(e);
                }
                AeEventLog event = new AeEventLog(productSource,caf,eventType,Long.valueOf(1),BigDecimal.valueOf(0),groupings);
                PersistenceWhenSteps.httpStatus = callPersistenceForPersistEvent(event);
                printer.println(String.format("Persistindo %s",event.toString()));
                printer.println(String.format("O servico retornou %s",PersistenceWhenSteps.httpStatus.toString()));
            }
    });
    }
    

    private Integer callPersistenceForPersistEvent(AeEventLog event) throws StepException {
        Integer httpStatus = 0;
        RestServiceCaller caller = RestServiceCallerFactory.createRestServiceCaller(persistenceURI, 1000);
        try {
            String url =  builder.getPersistenceUrlForEvent(event);
            response = caller.doGet(url, PersistenceResponse.class, MediaType.APPLICATION_XML_TYPE , false);
            httpStatus = caller.getResponseStatus().getStatusCode();
        } catch (Exception e) {
            Assert.fail("Erro ao chamar o servico " + e.getLocalizedMessage());
        }
        try {
            caller.close();
        } catch (IOException e) {
            Assert.fail("Erro ao fechar conexão " + e.getLocalizedMessage());
        }
        return httpStatus ;
    }
}
