package uol.collective.offer.service.test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.TestResponse;
import uol.collective.offer.commons.test.util.HttpHelper;
import uol.collective.offer.commons.test.util.HttpHelper.Response;
import uol.collective.offer.commons.test.util.ResponseValidator;

@SuppressWarnings("rawtypes")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/applicationContext.xml" })
public abstract class AbstractBase
{

    private static final Logger logger = Logger.getLogger( AbstractBase.class );

    private static final String DEFAULT_STR_ERROR = "Falha na execucao do teste!";

    private static final String POST_SERVICE = "post";

    private static final String POST_UPDATE_SERVICE = "post-update";

    public static final String GET_SERVICE = "get";

    @Before
    protected void initialize()
    {
        logger.info( String.format( "Iniciando os Testes para a Entidade '%s'", getClass().getSimpleName() ) );
    }

    protected HashSet<String> defineFields(String... fields)
    {
        HashSet<String> list = new HashSet<String>();
        if ( fields != null )
        {
            list.addAll( Arrays.asList( fields ) );
        }
        return list;
    }

    private boolean executeServiceAndValidate(Object entity, Map<String, String> additionalParameters, Object expectedObject, String[] fieldsToAvoidValidation, final String SERVICE_TYPE, final int VALIDATOR)
    {
        try
        {
            Response resp = null;

            if ( POST_SERVICE.equals( SERVICE_TYPE ) )
            {
                resp = HttpHelper.doPost( entity, additionalParameters );
            } else if ( POST_UPDATE_SERVICE.equals( SERVICE_TYPE ) )
            {
                resp = HttpHelper.doPostUpdate( entity, additionalParameters );
            } else if ( GET_SERVICE.equals( SERVICE_TYPE ) )
            {
                resp = HttpHelper.doGet( entity );
            }

            if ( resp != null )
            {
                String expectedXml = HttpHelper.parse( expectedObject );
                boolean result = ResponseValidator.validateXmlResult( expectedXml, resp.getContent(), expectedObject.getClass(), fieldsToAvoidValidation, VALIDATOR );

                return ( result ? true : printLogError( expectedXml, resp.getContent() ) );
            }
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
        return false;
    }

    private boolean executeListAllAndValidate(ListTestResponse<? extends TestResponse> listFromDataBase, String[] fieldsToAvoidValidation, Map<String, String> additionalParameters)
    {
        try
        {
            if ( listFromDataBase != null )
            {
                TestResponse instance = listFromDataBase.getPersistentClass()
                    .newInstance();
                Response resp = HttpHelper.doGet( instance.getEntityRootUrl() + "/list", additionalParameters );
                if ( resp != null )
                {
                    String expectedXml = HttpHelper.parse( listFromDataBase );
                    boolean result = ResponseValidator.validateXmlResult( expectedXml, resp.getContent(), listFromDataBase.getPersistentClass(), fieldsToAvoidValidation, ResponseValidator.DEFAULT_VALIDATOR );
                    if ( result )
                    {
                        return true;
                    } else
                    {
                        return printLogError( expectedXml, resp.getContent() );
                    }
                }
            }
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
        return false;
    }

    protected boolean executeListAllAndValidate(Object expectedObject, Object entity, String[] fieldsToAvoidValidation, Map<String, String> additionalParameters)
    {
        try
        {
            Response resp = HttpHelper.doGet( ( (TestResponse) entity ).getEntityRootUrl() + "/list", additionalParameters );

            if ( resp != null )
            {
                String expectedXml = HttpHelper.parse( expectedObject );
                boolean result = ResponseValidator.validateXmlResult( expectedXml, resp.getContent(), expectedObject.getClass(), fieldsToAvoidValidation, ResponseValidator.DEFAULT_VALIDATOR );
                if ( result )
                {
                    return true;
                } else
                {
                    return printLogError( expectedXml, resp.getContent() );
                }
            }

        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
        return false;
    }

    protected boolean executePostServiceAndValidateResult(Object entity, Map<String, String> additionalParameters, Object expectedObject, String[] fieldsToAvoidValidation, final int VALIDATOR)
    {
        return executeServiceAndValidate( entity, additionalParameters, expectedObject, fieldsToAvoidValidation, POST_SERVICE, VALIDATOR );
    }

    protected boolean executeInsertAndValidate(Object entity, Object expectedObject)
    {
        return executePostServiceAndValidateResult( entity, null, expectedObject, new String[] {
                "id",
                "creationDate" }, ResponseValidator.DEFAULT_VALIDATOR );
    }

    protected boolean executeInsertAndValidate(Object entity, Map<String, String> additionalParameters, Object expectedObject)
    {
        return executePostServiceAndValidateResult( entity, additionalParameters, expectedObject, new String[] {
                "id",
                "creationDate" }, ResponseValidator.DEFAULT_VALIDATOR );
    }

    private boolean executeGetServiceAndValidateResult(Object entity, Map<String, String> additionalParameters, Object expectedObject, String[] fieldsToAvoidValidation)
    {
        return executeServiceAndValidate( entity, additionalParameters, expectedObject, fieldsToAvoidValidation, GET_SERVICE, ResponseValidator.DEFAULT_VALIDATOR );
    }

    protected boolean executeSelectAndValidate(Object entity, Object expectedObject)
    {
        return executeGetServiceAndValidateResult( entity, null, expectedObject, new String[] { "creationDate" } );
    }

    protected boolean executeSelectAndValidate(Object entity, Map<String, String> additionalParameters, Object expectedObject)
    {
        return executeGetServiceAndValidateResult( entity, additionalParameters, expectedObject, new String[] { "creationDate" } );
    }

    protected boolean executeListAllAndValidate(ListTestResponse<? extends TestResponse> listFromDataBase)
    {
        return executeListAllAndValidate( listFromDataBase, new String[] { "creationDate" }, null );
    }

    protected boolean executeListAllAndValidate(ListTestResponse<? extends TestResponse> listFromDataBase, Map<String, String> additionalParameters)
    {
        return executeListAllAndValidate( listFromDataBase, new String[] { "creationDate" }, additionalParameters );
    }

    protected boolean executeUpdateAndValidate(Object newEntity, Object expectedObject, final int VALIDATOR)
    {
        return executeServiceAndValidate( newEntity, null, expectedObject, new String[] {
                "id",
                "creationDate" }, POST_UPDATE_SERVICE, VALIDATOR );
    }

    protected boolean executeUpdateAndValidate(Object newEntity, Map<String, String> additionalParameters, Object expectedObject, final int VALIDATOR)
    {
        return executeServiceAndValidate( newEntity, additionalParameters, expectedObject, new String[] {
                "id",
                "creationDate" }, POST_UPDATE_SERVICE, VALIDATOR );
    }

    protected boolean printLogError(String expectedXml, String xmlReturned)
    {
        logger.error( DEFAULT_STR_ERROR );
        logger.error( String.format( "Xml esperado..: [%s]", expectedXml ) );
        logger.error( String.format( "Xml retornado.: [%s]", xmlReturned ) );
        return false;
    }

    protected boolean executeListAndValidate(String servicePath, ListTestResponse<? extends TestResponse> listFromDataBase, Map<String, String> additionalParameters)
    {
        try
        {
            if ( listFromDataBase != null )
            {
                TestResponse instance = listFromDataBase.getPersistentClass()
                    .newInstance();
                Response resp = HttpHelper.doGet( instance.getEntityRootUrl()
                    .concat( servicePath ), additionalParameters );
                if ( resp != null )
                {
                    String expectedXml = HttpHelper.parse( listFromDataBase );
                    boolean result = ResponseValidator.validateXmlResult( expectedXml, resp.getContent(), listFromDataBase.getPersistentClass(), new String[] { "creationDate" }, ResponseValidator.DEFAULT_VALIDATOR );
                    if ( result )
                    {
                        return true;
                    } else
                    {
                        return printLogError( expectedXml, resp.getContent() );
                    }
                }
            }
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
        return false;
    }

    protected boolean executeListAndValidate(String servicePath, Object expectedObject, Object entity, Map<String, String> additionalParameters)
    {
        try
        {
            Response resp = HttpHelper.doGet( ( (TestResponse) entity ).getEntityRootUrl()
                .concat( servicePath ), additionalParameters );

            if ( resp != null )
            {
                String expectedXml = HttpHelper.parse( expectedObject );
                Class<?> classToParse = ( expectedObject instanceof ListTestResponse ? ( (ListTestResponse) expectedObject ).getPersistentClass() : expectedObject.getClass() );
                boolean result = ResponseValidator.validateXmlResult( expectedXml, resp.getContent(), classToParse, new String[] { "creationDate" }, ResponseValidator.DEFAULT_VALIDATOR );
                if ( result )
                {
                    return true;
                } else
                {
                    return printLogError( expectedXml, resp.getContent() );
                }
            }

        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha ao executar e validar o servico, ERRO: %s", ex.getMessage() ), ex );
        }
        return false;
    }

    protected String getEntityRootUrl(ListTestResponse<? extends TestResponse> listFromDataBase) throws InstantiationException, IllegalAccessException
    {
        if ( listFromDataBase != null && !listFromDataBase.isEmpty() )
        {
            TestResponse instance = listFromDataBase.getPersistentClass()
                .newInstance();
            return instance.getEntityRootUrl();
        }
        return null;
    }

}
