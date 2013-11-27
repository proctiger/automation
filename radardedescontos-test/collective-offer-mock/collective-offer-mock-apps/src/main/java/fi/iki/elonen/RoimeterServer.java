package fi.iki.elonen;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD.Response.Status;

public class RoimeterServer extends NanoHTTPD {
    
    public static String appender = "appender";
    public static String datExpires = "2014-02-10T16:13:41.000-03:00";
    public static String datPersistence = "2014-02-10T16:13:41.000-03:00";
    public static String datMeasure = "2013-02-10T16:13:41.000-03:00";
    public static String transactionId = "9E884542-81B3-4419-9A75-BCC6FB495EF1";
    public static String groupingsMapKey = "chave-group";
    public static String groupingsMapValue = "valor-group";
    public static String idtProduct = "42";
    public static String measuresMapKey = "chave-measure";
    public static String measuresMapValue = "valor-measure";
    public static String UUID = "blah-blah-UUID";
    
    public RoimeterServer() {
        super("ws.pagseguro.uol.com.br", 8085);
    }

    public static void main(String[] args) {
        ServerRunner.run(RoimeterServer.class);
    }

    @Override
    public Response serve(String uri, Method method, Map<String, String> header, Map<String, String> parms, Map<String, String> files) {
        if ( parms != null ) {
            for ( String key : parms.keySet() ) {
                if ( key.equals("appender") ) {
                    appender = parms.get( key );
                }
                if ( key.equals("datExpires") ) {
                    datExpires = parms.get( key );
                }
                if ( key.equals("datMeasure") ) {
                    datMeasure = parms.get( key );
                }
                if ( key.equals("datPersistence") ) {
                    datPersistence = parms.get( key );
                }
                if ( key.equals("groupingsMapKey") ) {
                    groupingsMapKey = parms.get( key );
                }  
                if ( key.equals( "groupingsMapValue" ) ) {
                    groupingsMapValue = parms.get( key );
                }
                if ( key.equals( "idtProduct" ) ) {
                    idtProduct = parms.get( key );
                }
                if ( key.equals( "measuresMapKey" ) ) {
                    measuresMapKey = parms.get( key );
                }
                if ( key.equals( "measuresMapValue" ) ) {
                    measuresMapValue = parms.get( key );
                }
                if ( key.equals( "UUID" ) ) {
                    UUID = parms.get( key );
                }
            }
        }        
        
        String measurement = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?>" +
        "<measurement>"+
          "<appender>%s</appender>" +
          "<datExpires>%s</datExpires>" +
          "<datMeasure>%s</datMeasure>" +
          "<datPersistence>%s</datPersistence>" +
          "<groupingsMap>" +
            "<entry>" +
              "<key>%s</key>" +
              "<value>%s</value>" +
            "</entry>" +
          "</groupingsMap>" +
          "<idtProduct>%s</idtProduct>" +
          "<measuresMap>" +
            "<entry>" +
              "<key>%s</key>" +
              "<value>%s</value>" +
            "</entry>" +
          "</measuresMap>" +
          "<UUID>%s</UUID>" +
        "</measurement>";

        return new Response( Status.OK, "application/xml", String.format( measurement, appender, datExpires, datMeasure, datPersistence, groupingsMapKey, groupingsMapValue, idtProduct, measuresMapKey, measuresMapValue, UUID ) );
    }
}
