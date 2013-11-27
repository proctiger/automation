package uol.collective.offer.click.test;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uol.collective.offer.commons.test.model.impl.ClickRequest;
import uol.collective.offer.commons.test.util.HttpHelper;


@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public abstract class AbstractBase
{

    private static final Logger logger = Logger.getLogger( AbstractBase.class );
    
    private static final String CLICK_BASE_URL = "http://click.radardedescontos.uol.com.br";
    
    @Autowired
    private CryptoUtils cryptoUtils;

    @Before
    protected void initialize()
    {
        logger.info( String.format( "Iniciando os Testes para a Entidade '%s'", getClass().getSimpleName() ) );
    }
    
    protected void executeClick(ClickRequest click)
    {
        try
        {
            HttpHelper.doGet( CLICK_BASE_URL, getClickUrlParameters( click ) );

        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
    }
    
    private Map<String,String> getClickUrlParameters(ClickRequest click) {
        Map<String, String> parameters = new HashMap<String, String>( );
        
        String encryptedClick = cryptoUtils.encrypt(click.toClickString());
        String hash = getHash(encryptedClick);
        
        parameters.put( "d", encryptedClick );
        parameters.put( "h", hash );
        
        return parameters;
    }
    
    private String getHash(String data) {
        String reversedHash = StringUtils.reverse(data);
        String reversedHashMd5 = cryptoUtils.getDigest(reversedHash);
        return reversedHashMd5;
    }
}
    
