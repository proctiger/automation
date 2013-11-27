package uol.collective.offer.commons.test.model.impl;

public class ClickRequest
{

    private String offerId;

    private String offerPosition;

    private String redirUrl;

    private String siteName;

    private String cityName;

    private String categoryName;

    private String pagseguro;

    private String cityId;

    private String siteId;

    private String cpc;

    private String platform;
    
    private String impressionId;
    
    private String uniqueClick;
    
    private String pagseguroPrivateKey;
    
    private String pagseguroPublicKey;
    
    private String timestamp;

    public ClickRequest(String offerId,
            String offerPosition,
            String redirUrl,
            SiteTestResponse site,
            CityTestResponse city,
            String categoryName,
            String pagseguro,
            String cpc,
            String platform,
            String impressionId,
            String pagseguroPrivateKey,
            String pagseguroPublicKey,
            String timestamp)
    {
        super();
        this.offerId = offerId;
        this.offerPosition = offerPosition;
        this.redirUrl = redirUrl;
        this.siteName = site.getName();
        this.cityName = city.getName();
        this.cityId = city.getId();
        this.siteId = site.getId();
        this.categoryName = categoryName;
        this.pagseguro = pagseguro;
        this.cpc = cpc;
        this.platform = platform;
        this.setImpressionId( impressionId );
        this.setPagseguroPrivateKey( pagseguroPrivateKey );
        this.setPagseguroPublicKey( pagseguroPublicKey );
        this.setTimestamp( timestamp );
        
    }

    public String getOfferId()
    {
        return offerId;
    }

    public void setOfferId(String offerId)
    {
        this.offerId = offerId;
    }

    public String getOfferPosition()
    {
        return offerPosition;
    }

    public void setOfferPosition(String offerPosition)
    {
        this.offerPosition = offerPosition;
    }

    public String getRedirUrl()
    {
        return redirUrl;
    }

    public void setRedirUrl(String redirUrl)
    {
        this.redirUrl = redirUrl;
    }

    public String getSiteName()
    {
        return siteName;
    }

    public void setSiteName(String siteName)
    {
        this.siteName = siteName;
    }

    public String getCityName()
    {
        return cityName;
    }

    public void setCityName(String cityName)
    {
        this.cityName = cityName;
    }

    public String getCategoryName()
    {
        return categoryName;
    }

    public void setCategoryName(String categoryName)
    {
        this.categoryName = categoryName;
    }

    public String getPagseguro()
    {
        return pagseguro;
    }

    public void setPagseguro(String pagseguro)
    {
        this.pagseguro = pagseguro;
    }

    public String getCityId()
    {
        return cityId;
    }

    public void setCityId(String cityId)
    {
        this.cityId = cityId;
    }

    public String getSiteId()
    {
        return siteId;
    }

    public void setSiteId(String siteId)
    {
        this.siteId = siteId;
    }

    public String getCpc()
    {
        return cpc;
    }

    public void setCpc(String cpc)
    {
        this.cpc = cpc;
    }

    public String getPlatform()
    {
        return platform;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }
    
    public String getImpressionId()
    {
        return impressionId;
    }
    
    public void setImpressionId(String impressionId)
    {
        this.impressionId = impressionId;
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

    public String getUniqueClick()
    {
        return uniqueClick;
    }

    public void setUniqueClick(String uniqueClick)
    {
        this.uniqueClick = uniqueClick;
    }

    public String getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(String timestamp)
    {
        this.timestamp = timestamp;
    }

    public String toClickString()
    {
        return String.format( "offerId=%s,offerPosition=%s,redirUrl=%s,siteName=%s,cityName=%s,categoryName=%s,pagseguro=%s,cityId=%s,siteId=%s,cpc=%s,platform=%s,impressionId=%s,pagseguroPrivateKey=%s,pagseguroPublicKey=%s,timestamp=%s", 
                this.offerId, 
                this.offerPosition, 
                this.redirUrl, 
                this.siteName, 
                this.cityName, 
                this.categoryName, 
                this.pagseguro, 
                this.cityId, 
                this.siteId, 
                this.cpc, 
                this.platform,
                this.impressionId,
                this.pagseguroPrivateKey,
                this.pagseguroPublicKey,
                this.timestamp);
    }

}