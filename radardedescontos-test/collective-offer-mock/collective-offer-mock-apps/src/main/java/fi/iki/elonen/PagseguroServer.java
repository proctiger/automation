package fi.iki.elonen;

import java.util.Map;

import fi.iki.elonen.NanoHTTPD.Response.Status;

public class PagseguroServer extends NanoHTTPD {
    
    public static String codUniqueClick = "5e77dc2d-dc80-4ee5-8f91-3a3b27e0cce6";
    public static String date = "2011-02-10T16:13:41.000-03:00";
    public static String transactionId = "9E884542-81B3-4419-9A75-BCC6FB495EF1";
    public static String status = "3";
    public static String grossAmount = "49990.00";
    public static String commissionAmount = "10.0";
    public static String paymentType = "2";
    public static String paymentCode = "202";
    public static String numItems = "2";
    
    public PagseguroServer() {
        super("ws.pagseguro.uol.com.br", 8080);
    }

    public static void main(String[] args) {
        ServerRunner.run(PagseguroServer.class);
    }

    @Override
    public Response serve(String uri, Method method, Map<String, String> header, Map<String, String> parms, Map<String, String> files) {
        if ( parms != null ) {
            for ( String key : parms.keySet() ) {
                if ( key.equals("codUniqueClick") ) {
                    codUniqueClick = parms.get( key );
                }
                if ( key.equals("date") ) {
                    date = parms.get( key );
                }
                if ( key.equals("transactionId") ) {
                    transactionId = parms.get( key );
                }
                if ( key.equals("status") ) {
                    status = parms.get( key );
                }
                if ( key.equals("grossAmount") ) {
                    grossAmount = parms.get( key );
                }
                if ( key.equals("commissionAmount") ) {
                    commissionAmount = parms.get( key );
                }
                if ( key.equals("paymentType") ) {
                    paymentType = parms.get( key );
                }
                if ( key.equals("paymentCode") ) {
                    paymentCode = parms.get( key );
                } 
                if ( key.equals( "numItems" ) ) {
                    numItems = parms.get( key );
                }
            }
        }
        StringBuffer items = new StringBuffer("");
        for( int i=1; i<=Integer.valueOf( numItems ); i++ ) {
            items.append( String.format( "<item><id>%d</id><description>Teste %d</description><quantity>1</quantity><amount>42.00</amount></item>", i, i ) );
        }
        String transaction = "<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"yes\"?> " +
        		   "<transaction> " +
        		        "<referralCode>%s</referralCode> " +
        		        "<date>%s</date> " +
        		        "<code>%s</code> " +
        		        "<status>%s</status> " +
        		        "<grossAmount>%s</grossAmount> " +
        		        "<commissionAmount>%s</commissionAmount> " +
        		        "<itemCount>%s</itemCount> " +
        		        "<items>" +
        		            "%s" +
        		        "</items> " +
        		        "<paymentMethod> " +
                        "    <type>%s</type> " +
                        "    <code>%s</code> " +
                        "</paymentMethod> " +
    		        "</transaction> ";
        
        return new Response( Status.OK, "application/xml", String.format( transaction, codUniqueClick, date, transactionId, status, grossAmount, commissionAmount, numItems, items, paymentType, paymentCode ) );
    }
}
