package uol.collective.offer.service.test.pagseguro;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uol.collective.offer.commons.test.dao.CityDAO;
import uol.collective.offer.commons.test.dao.ClickConsolidationDAO;
import uol.collective.offer.commons.test.dao.ClickEventDAO;
import uol.collective.offer.commons.test.dao.ClickEventRoimeterDAO;
import uol.collective.offer.commons.test.dao.OfferDAO;
import uol.collective.offer.commons.test.dao.SiteDAO;
import uol.collective.offer.commons.test.dao.TransactionEventDAO;
import uol.collective.offer.commons.test.util.DateTimeUtil;
import uol.collective.offer.commons.test.util.HttpHelper;
import uol.collective.offer.service.test.AbstractBase;

@Component
public class PagseguroBase extends AbstractBase
{

    private static final Logger logger = Logger.getLogger( PagseguroBase.class );

    private static final String RADAR_NOTIFICATION_BASE_URL = "http://ws.radardedescontos.intranet/pagseguro/notification";

    private static final String PAGSEGURO_MOCK_BASE_URL = "http://ws.pagseguro.uol.com.br";

    protected static final String DEFAULT_COD_UNIQUE_CLICK = "5e77dc2d-dc80-4ee5-8f91-3a3b27e0cce6";

    protected static final String DEFAULT_DATE = DateTimeUtil.getCurrentDateInXmlFormat();

    protected static final String DEFAULT_CODE = "9E884542-81B3-4419-9A75-BCC6FB495EF1";

    protected static final String DEFAULT_STATUS = "3";

    protected static final String DEFAULT_GROSS_AMOUNT = "49900.00";

    @Autowired
    protected ClickEventDAO clickEventDAO;

    @Autowired
    protected ClickConsolidationDAO clickConsolidationDAO;
    
    @Autowired
    protected ClickEventRoimeterDAO clickEventRoimeterDAO;

    @Autowired
    protected OfferDAO offerDAO;

    @Autowired
    protected CityDAO cityDAO;

    @Autowired
    protected SiteDAO siteDAO;

    @Autowired
    protected TransactionEventDAO transactionEventDAO;

    @Before
    public void initialize()
    {
        super.initialize();
        clickEventDAO.deleteAllCascade();
        logger.info( "Preparando a base de dados..." );
        logger.info( "Preparacao finalizada!" );
    }

    protected void executeNotification()
    {
        try
        {
            HttpHelper.doPostForm( RADAR_NOTIFICATION_BASE_URL, new String[] {
                    "notificationCode=filho-do-cao",
                    "notificationType=transaction" } );

        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
    }

    protected void changeMockAttributes(String clickId, String commissionAmount, String paymentMethod, String status, String transactionId, String paymentValue)
    {
        try
        {
            Map<String, String> attributes = new HashMap<String, String>();
            attributes.put( "status", status );
            attributes.put( "codUniqueClick", clickId );
            attributes.put( "transactionId", transactionId );
            attributes.put( "grossAmount", paymentValue );
            attributes.put( "commissionAmount", commissionAmount );
            attributes.put( "paymentType", paymentMethod );
            

            HttpHelper.Response response = HttpHelper.doGet( PAGSEGURO_MOCK_BASE_URL, attributes );
            logger.info( response );

        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
    }
}
