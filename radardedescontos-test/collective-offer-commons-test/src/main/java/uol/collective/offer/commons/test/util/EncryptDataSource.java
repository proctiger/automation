package uol.collective.offer.commons.test.util;

import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;

import uol.db.crypt.Encrypt;

/**
 * Classe criada para atender ao requisito de criptografia de senha do banco de dados
 */
public class EncryptDataSource extends BasicDataSource
{

    private boolean encrypt;

    @Override
    public synchronized void setPassword(String passwd)
    {
        if ( encrypt )
        {
            passwd = Encrypt.encryptPassword( passwd );
        }
        super.setPassword( passwd );
    }

    public void setEncrypt(boolean encrypt)
    {
        this.encrypt = encrypt;
    }

    public boolean getEncrypt()
    {
        return encrypt;
    }
    
    public void setJdbcProperties( Map<String, String> jdbcProperties ){
        connectionProperties.putAll( jdbcProperties );
    }
    
}

