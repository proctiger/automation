package uol.collective.offer.commons.test.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import uol.collective.offer.commons.test.model.ListTestResponse;
import uol.collective.offer.commons.test.model.TestResponse;
import uol.collective.offer.commons.test.model.impl.ListTestResponseImpl;

@SuppressWarnings("rawtypes")
public class HttpHelper
{

    public static final String LOGIN = "cad_exavier";

    public static final String IP = "0.0.0.0";

    private static final Logger logger = Logger.getLogger( HttpHelper.class );

    private static final String CONTENT_TYPE = "Content-type";

    private static final String ENCODE_UTF8 = "UTF-8";

    /**
     * Efetua uma requisicao HTTP com o metodo POST.
     * 
     * @param uri
     *            - url da requisicao
     * @param content
     *            - String de um arquivo XML a ser enviado como parametro
     * @throws IOException
     */
    public static Response doPost(String uri, String xmlContent) throws IOException
    {
        try
        {
            logger.info( String.format( "Executando a URL como POST : [%s] [body:%s]", uri, xmlContent ) );
            PostMethod post = new PostMethod( uri );
            StringRequestEntity entity = new StringRequestEntity( xmlContent, MediaType.TEXT_XML, ENCODE_UTF8 );
            post.setRequestEntity( entity );
            post.setRequestHeader( CONTENT_TYPE, MediaType.APPLICATION_XML );
            HttpClient httpClient = new HttpClient();
            Response response = new Response();
            response.code = httpClient.executeMethod( post );
            if ( post.getResponseBodyAsString() != null )
            {
                response.content = post.getResponseBodyAsString();
                logger.info( String.format( "Retorno do servico, [codigo:%s] [xml:%s]", response.code, response.getContent() ) );
            } else
            {
                logger.info( "Retorno do servico sem response body" );
            }
            return response;
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha na execucao do servico, ERRO:%s", ex.getMessage() ), ex );
            throw new IOException( ex );
        }
    }

    /**
     * Efetua uma requisicao HTTP com o metodo POST e Content-type form.
     * 
     * @param uri
     *            - url da requisicao
     * @param content
     *            - Parametros
     * @throws IOException
     */
    public static Response doPostForm(String uri, String[] args) throws IOException
    {
        try
        {
            logger.info( String.format( "Executando a URL como POST : [%s] [args:%s]", uri, args ) );
            PostMethod post = new PostMethod( uri );

            StringBuilder argBuilder = new StringBuilder();
            if ( args != null )
            {
                for ( String arg : args )
                {
                    argBuilder.append( arg );
                    argBuilder.append( "&" );
                }
            }

            StringRequestEntity entity = new StringRequestEntity( argBuilder.toString(), MediaType.APPLICATION_FORM_URLENCODED, ENCODE_UTF8 );

            post.setRequestEntity( entity );
            post.setRequestHeader( CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED );
            HttpClient httpClient = new HttpClient();
            Response response = new Response();
            response.code = httpClient.executeMethod( post );
            if ( post.getResponseBodyAsString() != null )
            {
                response.content = post.getResponseBodyAsString();
                logger.info( String.format( "Retorno do servico, [codigo:%s] [xml:%s]", response.code, response.getContent() ) );
            } else
            {
                logger.info( "Retorno do servico sem response body" );
            }
            return response;
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha na execucao do servico, ERRO:%s", ex.getMessage() ), ex );
            throw new IOException( ex );
        }
    }

    /**
     * Efetua uma requisicao HTTP com o metodo GET.
     * 
     * @param uri
     *            - url da requisicao
     * @throws IOException
     */
    public static Response doGet(String uri, Map<String, String> additionalParameters) throws IOException
    {
        try
        {
            if ( !uri.contains( "?" ) )
                uri = uri.concat( "?1=1" );
            uri = uri.concat( parseAdditionalParams( additionalParameters ) );
            logger.info( String.format( "Executando a URL como GET : [%s]", uri ) );
            GetMethod get = new GetMethod( uri );
            HttpClient httpClient = new HttpClient();
            Response response = new Response();
            response.code = httpClient.executeMethod( get );
            if ( get.getResponseBody() != null )
            {
                response.content = new String( get.getResponseBody(), ENCODE_UTF8 );
                // TODO descomentar e tornar genérico para quando getContent recebe uma String e não um XML
                // logger.info( String.format( "Retorno do servico, [codigo:%s] [xml:%s]", response.code, response.getContent()) );
            } else
            {
                logger.info( "Retorno do servico sem response body" );
            }
            return response;
        } catch ( Exception ex )
        {
            logger.error( String.format( "Falha na execucao do servico, ERRO:%s", ex.getMessage() ), ex );
            throw new IOException( ex );
        }
    }

    // Classe que monta o Response
    public static class Response
    {

        int code;

        String content;

        public int getCode()
        {
            return this.code;
        }

        public String getContent()
        {
            return this.content == null ? null : XmlFormatter.format( this.content );
        }

        @Override
        public String toString()
        {
            return "Response [code=" + code + ", content=" + content + "]";
        }

    }

    // Gera um xml baseado no Objeto
    public static String parse(Object objToParse)
    {
        try
        {

            // Criando o contexto JAXB para o parse utilizando a classe do objeto informado
            final JAXBContext context = JAXBContext.newInstance( objToParse.getClass() );
            // Criando o Parseador e o objeto a ser escrito o XML
            final Marshaller marshaller = context.createMarshaller();
            // TODO REVER CDATA
            // CharacterEscapeHandler escapeHandler = new NoEscapeHandler();
            // marshaller.setProperty("com.sun.xml.bind.characterEscapeHandler", escapeHandler);

            final StringWriter stringWriter = new StringWriter();

            marshaller.marshal( objToParse, stringWriter );
            String xmlResult = XmlFormatter.format( stringWriter.toString() );

            // Tratando as colecoes
            if ( objToParse instanceof ListTestResponse && !StringUtils.isEmpty( xmlResult ) )
            {
                Class<?> clazz = ( (ListTestResponse) objToParse ).getPersistentClass();
                xmlResult = ( xmlResult.contains( "</entities>" ) ? preParseObj( xmlResult, clazz ) : xmlResult );
            }

            return xmlResult;
        } catch ( Exception e )
        {
            logger.error( String.format( "Falha ao realizar parse do Objeto [%s], ERRO:%s.", objToParse, e.getMessage() ) );
            String msgError = "nor any of its super class is known to this context";
            if ( e != null && e.toString()
                .contains( msgError ) )
            {
                logger.error( "Verifique se a CLASSE ITEM da colecao esta incluida na lista de classes da anotacao '@XmlSeeAlso' na classe 'uol.collective.offer.service.tester.model.impl.ListTestResponseImpl'." );
            }
            logger.error( e );
        }
        return null;
    }

    private static String preParseObj(String xml, Class<?> clazz)
    {
        xml = xml.replace( " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "" );
        xml = xml.replace( getNewClassTagName( clazz ), String.format( "<%s>", getClassTagName( clazz ) ) );
        xml = xml.replace( "</entities>", String.format( "</%s>", getClassTagName( clazz ) ) );
        return XmlFormatter.format( xml );
    }

    // Gera um Objeto baseado no xml
    public static Object parse(String xml, Class<?> clazz)
    {
        try
        {
            // Tratando as colecoes
            if ( xml.contains( "<collection>" ) || xml.contains( "<collection/>" ) )
            {
                xml = ( !xml.contains( "</entities>" ) ? preParseXml( xml, clazz ) : xml );
                clazz = ListTestResponseImpl.class;
            }

            // Criando o contexto JAXB para o parse utilizando a classe do objeto informado
            final JAXBContext context = JAXBContext.newInstance( clazz );
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            // Convertendo o xml para o Objeto java
            Object parsedObj = unmarshaller.unmarshal( new StringReader( xml ) );

            return parsedObj;
        } catch ( Exception e )
        {
            logger.error( String.format( "Falha ao realizar parse do Xml [%s], ERRO:%s.", xml, e.getMessage() ), e );
        }
        return null;
    }

    private static String preParseXml(String xml, Class<?> clazz)
    {
        xml = xml.replace( " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"", "" );
        xml = xml.replace( String.format( "<%s>", getClassTagName( clazz ) ), getNewClassTagName( clazz ) );
        xml = xml.replace( String.format( "</%s>", getClassTagName( clazz ) ), "</entities>" );
        xml = xml.replace( "xsi:type", " xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type" );
        return XmlFormatter.format( xml );
    }

    private static String getNewClassTagName(Class<?> clazz)
    {
        // return String.format(
        // "<entities xsi:type=\"%sTestResponse\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\">", getClassTagName( clazz ) );
        return String.format( "<entities xsi:type=\"%sTestResponse\">", getClassTagName( clazz ) );
    }

    private static CharSequence getClassTagName(Class<?> clazz)
    {
        String clazzName = clazz.getSimpleName()
            .replace( "TestResponse", "" );
        String firstLetter = clazzName.substring( 0, 1 )
            .toLowerCase();
        return String.format( "%s%s", firstLetter, clazzName.substring( 1 ) );
    }

    public static Response doPost(Object entity, Map<String, String> additionalParameters) throws Exception
    {
        if ( entity instanceof TestResponse )
        {
            String urlPost = String.format( "%s?login=%s&ip=%s%s", ( (TestResponse) entity ).getEntityRootUrl(), LOGIN, IP, parseAdditionalParams( additionalParameters ) );
            return doPost( urlPost, HttpHelper.parse( entity ) );
        } else if ( entity instanceof ListTestResponse )
        {
            String urlPost = String.format( "%s?login=%s&ip=%s", ( (TestResponse) ( (ListTestResponse) entity ).getPersistentClass()
                .newInstance() ).getEntityRootUrl(), LOGIN, IP );
            return doPost( urlPost, HttpHelper.parse( entity ) );
        } else
        {
            throw new IllegalArgumentException( "O objeto a ser enviado nao implementa a interface TestResponse ou ListTestResponse" );
        }
    }

    public static Response doPostUpdate(Object entity, Map<String, String> additionalParameters) throws Exception
    {
        if ( entity instanceof TestResponse )
        {
            String urlPost = String.format( "%s/%s?login=%s&ip=%s%s", ( (TestResponse) entity ).getEntityRootUrl(), ( (TestResponse) entity ).getId(), LOGIN, IP, parseAdditionalParams( additionalParameters ) );
            return doPost( urlPost, HttpHelper.parse( entity ) );
        } else
        {
            throw new IllegalArgumentException( "O objeto a ser enviado nao implementa a interface TestResponse" );
        }
    }

    public static String parseAdditionalParams(Map<String, String> additionalParameters)
    {
        if ( additionalParameters != null )
        {
            StringBuilder sb = new StringBuilder();
            for ( String key : additionalParameters.keySet() )
            {
                sb.append( String.format( "&%s=%s", key, additionalParameters.get( key ) ) );
            }
            return sb.toString();
        }
        return "";
    }

    public static Response doGet(Object entity) throws Exception
    {
        if ( entity instanceof TestResponse )
        {
            String urlGet = String.format( "%s/%s", ( (TestResponse) entity ).getEntityRootUrl(), ( (TestResponse) entity ).getId() );
            return doGet( urlGet, null );
        } else
        {
            throw new IllegalArgumentException( "O objeto a ser enviado nao implementa a interface TestResponse" );
        }
    }

    public static void checkXml(String xml, Class<? extends Object> classToParse)
    {
        XmlRootElement ann = classToParse.getAnnotation( XmlRootElement.class );
        if ( StringUtils.isEmpty( xml ) )
        {
            throw new IllegalArgumentException( "O xml informado esta vazio" );
        } else if ( !xml.contains( "<collection/>" ) && ann != null && !"collection".equals( ann.name() ) && ( !xml.contains( ann.name() ) || !xml.toLowerCase()
            .contains( classToParse.getSimpleName()
                .replace( "TestResponse", "" )
                .toLowerCase() ) ) )
        {
            String strError = String.format( "O XML nao bate com a CLASSE [%s] esperada para ser parseada.", classToParse.getSimpleName() );
            logger.error( strError );
            throw new IllegalArgumentException( strError );
        }
    }

    public static void main(String[] args)
    {
        System.out.println( new StringBuilder().toString() );
    }

}
