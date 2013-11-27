package test.cucumber;

import org.apache.commons.lang.StringUtils;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.domain.Event;
import test.services.EventPersistenceService;
import test.services.RedisHelperService;
import test.services.RequestSimulator;
import test.utils.ClickUriUtil;

@Service
public class LocalServiceStepsImpl {

    private @Autowired RedisHelperService redisHelperService;

    private @Autowired RequestSimulator requestSimulator;

    private @Autowired EventPersistenceService eventPersistenceService;

    private String cookieValue;

    public void checkRecordsQuantityBeforeRequest() {
        try {
            eventPersistenceService.recordsQuantityBeforeRequest();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    public void verifyCookieExistence() {
        if (StringUtils.isEmpty(cookieValue)) {
            Assert.fail("Cookie n�o foi plantado.");
        }
        System.out.println(String.format("\n --> Valor da chave do Redis (cookie + source): [%s]", cookieValue));
    }

    private void makeRequest(ClickUriUtil clickUriUtil) {
        try {
            cookieValue = requestSimulator.makeRequestAndGetCookieUOLAF(clickUriUtil);
        } catch (Exception e) {
            Assert.fail(String.format("Falha ao efetuar requisicao para event-click. Exception [%s]", e.getMessage()));
        }
    }

    public void verifyRedisValue(String expectedValue) {
        checkRedisValueWithExpected(redisHelperService.getValueByKey(cookieValue), expectedValue);
    }

    private void checkRedisValueWithExpected(String redisValue,
                                             String expectedValue) {
        if (StringUtils.isEmpty(redisValue)) {
            Assert.fail(String.format("Conteudo vazio para a chave [%s]", cookieValue));
        } else {
            Assert.assertEquals(expectedValue, redisValue);
            System.out.println(String.format("\n --> Valor esperado [%s] == Valor gerado no Redis [%s].", expectedValue, redisValue));
        }
    }

    public void checkDifferenceOfRecordsAfterRequest() {
        try {
            eventPersistenceService.checkDifferenceOfRecordsAfterRequest();
        } catch (Exception ex) {
            Assert.fail(ex.getMessage());
        }
    }

    public void makeRequestToClickEvent(String idtUrl,
                                        String idtLabel,
                                        String source) {
        ClickUriUtil clickUriUtil = new ClickUriUtil();
        clickUriUtil.setIdtUrl(idtUrl);
        clickUriUtil.setEvent(Event.CLIQUE);
        clickUriUtil.setIdtLabel(idtLabel);
        clickUriUtil.setSource(source);
        makeRequest(clickUriUtil);
    }

    public void makeRequestToIndicationEvent(String caf,
                                             String idtLabel,
                                             String source) {
        ClickUriUtil indicationUriUtil = new ClickUriUtil();
        indicationUriUtil.setCaf(caf);
        indicationUriUtil.setEvent(Event.INDICACAO);
        indicationUriUtil.setIdtLabel(idtLabel);
        indicationUriUtil.setSource(source);
        makeRequest(indicationUriUtil);
    }
}
