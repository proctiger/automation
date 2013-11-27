package uol.collective.offer.commons.test.model.impl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import uol.collective.offer.commons.test.annotation.SequenceName;
import uol.collective.offer.commons.test.model.TestResponse;


@Entity
@Table(name = "site")
@XmlRootElement(name = "site")
@XmlAccessorType(XmlAccessType.FIELD)
@SequenceName(name = "sq_site_idt")
public class SiteTestResponse implements TestResponse
{

    @Id
    @Column(name = "idt_site")
    private String id;

    @Column(name = "nam_site")
    private String name;

    @Column(name = "ind_status")
    private String status;

    @Column(name = "des_logo_url")
    private String urlLogo;

    @Column(name = "des_xml_path")
    private String desXmlPath;

    @Column(name = "des_pagseguro_email")
    private String pagseguroEmail;

    @Column(name = "cod_private_safepay")
    private String pagseguroPrivateKey;
    
    @Column(name = "cod_public_safepay")
    private String pagseguroPublicKey;

    @Column(name = "idt_person")
    private String idtPerson;

    @Column(name = "nam_contact")
    private String contactName;

    @Column(name = "des_site_url")
    private String siteUrl;

    @Column(name = "cod_authorization_status")
    private String authorizationStatus;    

    public SiteTestResponse()
    {
        super();
    }

    public SiteTestResponse(String id)
    {
        super();
        this.id = id;
    }

    public SiteTestResponse(String name,
            String status)
    {
        super();
        this.name = name;
        this.status = status;
        this.authorizationStatus = "A";
    }

    public SiteTestResponse(String id,
            String name,
            String urlLogo,
            String desXmlPath,
            String pagseguroEmail,
            String idtPerson,
            String contactName,
            String siteUrl,
            String status,
            String pagseguroPrivateKey,
            String pagseguroPublicKey,
            String authorizationStatus)
    {
        super();
        this.id = id;
        this.name = name;
        this.urlLogo = urlLogo;
        this.desXmlPath = desXmlPath;
        this.pagseguroEmail = pagseguroEmail;
        this.idtPerson = idtPerson;
        this.contactName = contactName;
        this.siteUrl = siteUrl;
        this.status = status;
        this.pagseguroPrivateKey = pagseguroPrivateKey;
        this.pagseguroPublicKey = pagseguroPublicKey;
        this.authorizationStatus = authorizationStatus;
    }

    @Override
    public String getEntityRootUrl()
    {
        return ENTITY_ROOT_RADAR + "/site";
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getUrlLogo()
    {
        return urlLogo;
    }
    
    public void setUrlLogo(String urlLogo)
    {
        this.urlLogo = urlLogo;
    }

    public String getDesXmlPath()
    {
        return desXmlPath;
    }
    
    public void setDesXmlPath(String desXmlPath)
    {
        this.desXmlPath = desXmlPath;
    }

    public String getPagseguroEmail()
    {
        return pagseguroEmail;
    }
    
    public void setPagseguroEmail(String pagseguroEmail)
    {
        this.pagseguroEmail = pagseguroEmail;
    }
    
    public String getIdtPerson()
    {
        return idtPerson;
    }
    
    public void setIdtPerson(String idtPerson)
    {
        this.idtPerson = idtPerson;
    }
    
    public String getContactName()
    {
        return contactName;
    }
    
    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }
    
    public String getSiteUrl()
    {
        return siteUrl;
    }
    
    public void setSiteUrl(String siteUrl)
    {
        this.siteUrl = siteUrl;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getPagseguroPrivateKey()
    {
        return pagseguroPrivateKey;
    }

    public void setPagseguroPrivateKey(String pagseguroPrivateKey)
    {
        this.pagseguroPrivateKey = pagseguroPrivateKey;
    }

    public String getPagseguroPublicKey()
    {
        return pagseguroPublicKey;
    }

    public void setPagseguroPublicKey(String pagseguroPublicKey)
    {
        this.pagseguroPublicKey = pagseguroPublicKey;
    }
    
    public String getAuthorizationStatus()
    {
        return authorizationStatus;
    }
    
    public void setAuthorizationStatus(String authorizationStatus)
    {
        this.authorizationStatus = authorizationStatus;
    }

    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
        return result;
    }

    @Override
    public boolean equals(Object obj)
    {
        if ( this == obj )
            return true;
        if ( obj == null )
            return false;
        if ( getClass() != obj.getClass() )
            return false;
        SiteTestResponse other = (SiteTestResponse) obj;
        if ( id == null )
        {
            if ( other.id != null )
                return false;
        } else if ( !id.equals( other.id ) )
            return false;
        return true;
    }

    @Override
    public String toString()
    {
        return "SiteTestResponse [id=" + id + ", name=" + name + ", status=" + status + ", urlLogo=" + urlLogo + ", desXmlPath=" + desXmlPath + ", pagseguroEmail=" + pagseguroEmail + ", pagseguroCode=" + pagseguroPrivateKey + ", idtPerson=" + idtPerson + ", contactName=" + contactName + ", siteUrl=" + siteUrl + ", authorizationStatus=" + authorizationStatus + "]";
    }

}
