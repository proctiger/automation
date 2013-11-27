package uol.collective.offer.commons.test.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;




public class CDATARadar extends XmlAdapter<String, String>
{
  //TODO REVER CDATA
    private static final String CDATA_STRING = "<![CDATA[%s]]>";
    
    
    @Override
    public String marshal(String value) throws Exception
    {
        if( value != null ){
            return String.format( CDATA_STRING, value );
        }
        return null;
    }

    @Override
    public String unmarshal(String value) throws Exception
    {
        return value;
    }

}
