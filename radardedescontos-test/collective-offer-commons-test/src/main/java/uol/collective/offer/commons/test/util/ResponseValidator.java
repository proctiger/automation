package uol.collective.offer.commons.test.util;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import uol.collective.offer.commons.test.model.ListTestResponse;

public abstract class ResponseValidator
{

    static final Logger logger = Logger.getLogger( ResponseValidator.class );

    public static final int UPDATE_VALIDATOR = 1;

    public static final int DEFAULT_VALIDATOR = 0;

    public static boolean validateXmlResult(String expectedXml, String xmlReturned, Class<? extends Object> classToParse, String[] fieldsToAvoidValidation, final int VALIDATOR) throws JAXBException
    {
        HttpHelper.checkXml( expectedXml, classToParse );
        HttpHelper.checkXml( xmlReturned, classToParse );

        Object expectedObj = HttpHelper.parse( expectedXml, classToParse );
        Object objReturned = HttpHelper.parse( xmlReturned, classToParse );

        // TODO VERIFICAR NECESSIDADE DE IMPLEMENTAR EQUALS PARA COLLECTIONS
        // return expectedObj instanceof ListTestResponse ? equalsCollection( objReturned, expectedObj ) :
        // equalsByFields( objReturned, expectedObj, classToParse, fieldsToAvoidValidation ) ;

        switch ( VALIDATOR )
        {
            case UPDATE_VALIDATOR:
                return GenericValidator.checkIfAllFieldsAreDifferents( objReturned, expectedObj, classToParse, fieldsToAvoidValidation );
            default:
                return expectedObj instanceof ListTestResponse ? GenericValidator.equalsCollection( objReturned, expectedObj ) : GenericValidator.equalsByFields( objReturned, expectedObj, classToParse, fieldsToAvoidValidation );
        }
    }

}
