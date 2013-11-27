package uol.collective.offer.commons.test.xml.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class BooleanAdapter extends XmlAdapter<String, String>
{

    @Override
    public String marshal(String value) throws Exception
    {
        return parse( value );
    }

    @Override
    public String unmarshal(String value) throws Exception
    {
        return parse( value );
    }

    private String parse(String value)
    {
        if ( value != null )
        {
            value = "0".equals( value ) ? "false" : "true";
        }
        return value;
    }

}
