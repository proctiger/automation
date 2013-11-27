package uol.collective.offer.commons.test.util;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Transient;

import org.apache.commons.lang.time.DateUtils;
import org.apache.log4j.Logger;

import uol.collective.offer.commons.test.annotation.XmlTypeBigDecimal;
import uol.collective.offer.commons.test.annotation.XmlTypeDateTime;

public abstract class GenericValidator
{

    private static final Logger logger = Logger.getLogger( GenericValidator.class );

    private static final SimpleDateFormat[] XmlTypeDateTimeSDFS = new SimpleDateFormat[] {
            new SimpleDateFormat( "dd-MM-yyyy HH:mm:ss" ),
            new SimpleDateFormat( "dd-MMM-yy" ),
            new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" ),
            new SimpleDateFormat( "yyyy-MM-dd'T'HH:mm:ss" ) };

    // TODO REFATORAR: MELHORAR ESTE METODO, PARA ANALISAR TODOS OS CAMPOS ANTES DE RETORNAR O ERRO
    // RETORNAR UMA LISTA DE CAMPOS COM VALORES IGUAIS, E JUNTAR OS DOIS METODOS SE POSSIVEL
    public static boolean checkIfAllFieldsAreDifferents(Object objReturned, Object expectedObj, Class<? extends Object> classToParse, String[] fieldsToAvoidValidation)
    {
        List<String> fieldsToAvoidValidationList = Arrays.asList( ( fieldsToAvoidValidation == null ? new String[] { "" } : fieldsToAvoidValidation ) );
        Field[] fields = classToParse.getDeclaredFields();
        boolean result = true;

        if ( fields != null && fields.length > 0 )
        {
            for ( Field field : fields )
            {
                if ( !fieldsToAvoidValidationList.contains( field.getName() ) && field.getAnnotation( Transient.class ) == null )
                {
                    try
                    {
                        field.setAccessible( true );
                        Object valueExp = field.get( expectedObj );
                        Object valueRet = field.get( objReturned );

                        XmlTypeDateTime ann = field.getAnnotation( XmlTypeDateTime.class );
                        if ( ann != null && !equalsDateValue( valueExp, valueRet, ann, field.getName() ) )
                        {
                            result = false;
                            logger.error( String.format( "Os valores nao foram atualizados para o campo '%s'", field.getName() ) );
                        } else if ( ann == null && ( ( valueExp != null && valueExp.equals( valueRet ) ) || ( valueRet != null && valueRet.equals( valueExp ) ) ) )
                        {
                            result = false;
                            logger.error( String.format( "Os valores nao foram atualizados para o campo '%s'", field.getName() ) );
                        }
                    } catch ( Exception e )
                    {
                        logger.error( String.format( "Falha ao buscar valor do campo:%s, ERRO:%s", field.getName(), e.getMessage() ), e );
                    } finally
                    {
                        field.setAccessible( false );
                    }
                }
            }
        }

        return result;
    }

    // TODO REFATORAR: MELHORAR ESTE METODO, PARA ANALISAR TODOS OS CAMPOS ANTES DE RETORNAR O ERRO
    // RETORNAR UMA LISTA DE CAMPOS COM VALORES DIFERENTES, E JUNTAR OS DOIS METODOS SE POSSIVEL
    public static boolean equalsByFields(Object objReturned, Object expectedObj, Class<? extends Object> classToParse, String[] fieldsToAvoidValidation)
    {
        if ( objReturned == null || expectedObj ==  null ) return false;
        
        logger.info( String.format( "Retornado: %s", objReturned ) );
        
        logger.info( String.format( "Experado: %s", expectedObj ) );
        
        List<String> fieldsToAvoidValidationList = Arrays.asList( ( fieldsToAvoidValidation == null ? new String[] { "" } : fieldsToAvoidValidation ) );
        Field[] fields = classToParse.getDeclaredFields();
        boolean generalResult = true;

        if ( fields != null && fields.length > 0 )
        {
            for ( Field field : fields )
            {
                boolean attributeResult = true;
                if ( !fieldsToAvoidValidationList.contains( field.getName() ) && field.getAnnotation( Transient.class ) == null )
                {
                    try
                    {
                        field.setAccessible( true );
                        Object valueExp = field.get( expectedObj );
                        Object valueRet = field.get( objReturned );

                        XmlTypeDateTime annTDT = field.getAnnotation( XmlTypeDateTime.class );
                        XmlTypeBigDecimal annTBD = field.getAnnotation( XmlTypeBigDecimal.class );
                        if ( ( annTDT != null && annTBD == null ) && !equalsDateValue( valueExp, valueRet, annTDT, field.getName() ) )
                        {
                            attributeResult = false;
                        } else if ( ( annTDT == null && annTBD != null ) && !equalsBigDecimalValue( valueExp, valueRet, annTBD, field.getName() ) )
                        {
                            attributeResult = false;
                        } else if ( ( annTDT == null && annTBD == null ) && ( ( valueExp != null && !valueExp.equals( valueRet ) ) || ( valueRet != null && !valueRet.equals( valueExp ) ) ) )
                        {
                            attributeResult = false;
                        }

                        if ( !attributeResult )
                        {
                            logger.error( String.format( "Os valores estao diferentes para o campo '%s', valor esperado [%s] e retornado [%s]", field.getName(), valueExp, valueRet ) );
                        }
                        generalResult &= attributeResult;
                    } catch ( Exception e )
                    {
                        logger.error( String.format( "Falha ao buscar valor do campo:%s, ERRO:%s", field.getName(), e.getMessage() ), e );
                    } finally
                    {
                        field.setAccessible( false );
                    }
                }
            }
        }

        return generalResult;
    }

    public static boolean equalsCollection(Object listReturned, Object expectedList)
    {
        if ( listReturned != null )
        {
            return listReturned.equals( expectedList );
        } else if ( listReturned == null && expectedList == null )
        {
            return true;
        }
        return false;
    }

    static boolean equalsDateValue(Object valueExp, Object valueRet, XmlTypeDateTime ann, String fieldName)
    {
        if ( valueExp == null && valueRet == null )
        {
            return true;
        } else if ( valueExp != null && valueRet != null )
        {
            Date dateExp = null;
            Date dateRet = null;
            for ( SimpleDateFormat sdf : XmlTypeDateTimeSDFS )
            {
                try
                {
                    dateExp = sdf.parse( (String) valueExp );
                    for ( SimpleDateFormat sdfAux : XmlTypeDateTimeSDFS )
                    {
                        try
                        {
                            dateRet = sdfAux.parse( (String) valueRet );

                            dateExp = DateUtils.truncate( dateExp, Calendar.DATE );
                            dateRet = DateUtils.truncate( dateExp, Calendar.DATE );

                            return dateExp.equals( dateRet );
                        } catch ( Exception ex )
                        { // Caso nao consiga realizar a conversao ira tentar outro formato!
                        }
                    }
                } catch ( Exception ex )
                { // Caso nao consiga realizar a conversao ira tentar outro formato!
                }
            }
        }
        return false;
    }

    private static boolean equalsBigDecimalValue(Object valueExp, Object valueRet, XmlTypeBigDecimal ann, String fieldName)
    {
        if ( valueExp == null && valueRet == null )
        {
            return true;
        } else if ( valueExp != null && valueRet != null )
        {
            if ( valueExp instanceof String && valueRet instanceof String )
            {
                return ( new BigDecimal( (String) valueExp ).equals( new BigDecimal( (String) valueRet ) ) );
            } else
            {
                return ( ( (BigDecimal) valueExp ).equals( (BigDecimal) valueRet ) );
            }
        }

        return false;
    }

    public static void main(String[] args)
    {
        System.out.println( new BigDecimal( "0" ).equals( new BigDecimal( 0 ) ) );
    }

}
