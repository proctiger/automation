package uol.test.step.persistence.util;

import java.text.SimpleDateFormat;

import uol.affiliated.commons.test.bean.AeEventLog;


public class URLBuilder {
    
    private static final String  GROUPINGS = "g";
    private static final String EQUAL = "=";
    private static final String QUERYPARAM = "?";
    private static final String PATH = "/";
    private static final String EVENT = "event";
    private static final String persistenceURI = "http://event-persistence.ws.afiliados.intranet";

    public String getPersistenceUrlForEvent(AeEventLog event) {
        StringBuilder builder =  new StringBuilder();
        builder.append(persistenceURI);
        builderPathParam(builder , EVENT);
        builderPathParam(builder , event.getIdtProductSource());
        builderPathParam(builder , event.getCodTransaction());
        builderPathParam(builder , event.getCodAffiliated());
        builderPathParam(builder , event.getDesName());
        builderPathParam(builder , new SimpleDateFormat("dd-MM-yyyy").format(event.getDatEvent())) ;
        builderPathParam(builder , event.getNumEventAmount()) ;
        builderPathParam(builder ,event.getNumValue()) ;
        builderQueryParam(builder , GROUPINGS , event.getGroupings());
        return builder.toString();
    }
 
    public void builderPathParam(StringBuilder builder , Object param) {
        if(param != null){
            builder.append(PATH + param) ;
        }
    }
    
    public void builderQueryParam(StringBuilder builder , String name , Object param) {
        if(param != null){
            builder.append( QUERYPARAM + name + EQUAL + param) ;
        }
    }
}
