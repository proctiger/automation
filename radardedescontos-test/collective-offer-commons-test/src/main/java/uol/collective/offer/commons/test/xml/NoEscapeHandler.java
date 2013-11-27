package uol.collective.offer.commons.test.xml;

import java.io.IOException;
import java.io.Writer;

import com.sun.xml.bind.marshaller.CharacterEscapeHandler;

public class NoEscapeHandler implements CharacterEscapeHandler
{

    @Override
    public void escape(char[] ac, int i, int j, boolean flag, Writer writer) throws IOException
    {
        // do not escape
        writer.write( ac, i, j );
    }

}
