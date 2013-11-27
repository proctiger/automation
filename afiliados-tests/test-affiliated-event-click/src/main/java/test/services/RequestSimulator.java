package test.services;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.utils.ClickUriUtil;
import test.utils.CookieUtil;
import test.utils.PersistenceDelayUtil;

@Service
public class RequestSimulator {

    private @Autowired RestGetCookieService restGetCookieService;

    private @Autowired CookieUtil cookieUtil;

    public String makeRequestAndGetCookieUOLAF(ClickUriUtil clickUriUtil) throws Exception {
        String cookieValue = restGetCookieService.getCookieUOLAFByRequest(clickUriUtil);
        if (StringUtils.isEmpty(cookieValue)) {
            return StringUtils.EMPTY;
        }
        
        PersistenceDelayUtil.waitForPersistence(); //Aguardando processamento assync do event-click.
        
        return cookieUtil.getCookieUOLAFWithSource(clickUriUtil.getSource(), cookieValue);
    }
}
