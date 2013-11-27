package uol.adserversap.ws.test.domain;

import java.util.ArrayList;
import java.util.List;

public class Targeting {

    private List<String> adUnitNames = new ArrayList<>();
    private List<String> adPlacementNames = new ArrayList<>();
    private List<String> cities = new ArrayList<>();
    private List<String> countries = new ArrayList<>();
    private List<String> metropolitans = new ArrayList<>();
    private List<String> postalCodes = new ArrayList<>();
    private List<String> regions = new ArrayList<>();
    private List<String> browsers = new ArrayList<>();
    private List<String> browserLanguages = new ArrayList<>();
    private List<String> bandwidths = new ArrayList<>();
    private List<String> operatingSystems = new ArrayList<>();
    private List<String> domains = new ArrayList<>();
    private List<String> customs = new ArrayList<>();
    
    public List<String> getAdUnitNames() {
        return adUnitNames;
    }
    
    public void setAdUnitNames(List<String> adUnitNames) {
        this.adUnitNames = adUnitNames;
    }

    public List<String> getAdPlacementNames() {
        return adPlacementNames;
    }
    
    public void setAdPlacementNames(List<String> adPlacementNames) {
        this.adPlacementNames = adPlacementNames;
    }

    public List<String> getCountries() {
        return countries;
    }
    
    public void addCountry(String location) {
        if (this.countries == null){
            this.countries = new ArrayList<>();
        }
        countries.add(location);
    }
    
    public List<String> getCities() {
        return cities;
    }
    
    public void addCities(String city) {
        if (this.cities == null){
            this.cities = new ArrayList<>();
        }
        cities.add(city);
    }
    
    public List<String> getMetropolitans() {
        return metropolitans;
    }
    
    public void addMetropolitan(String metropolitan) {
        if (this.metropolitans == null){
            this.metropolitans = new ArrayList<>();
        }
        metropolitans.add(metropolitan);
    }    
    
    public List<String> getPostalCodes() {
        return postalCodes;
    }
    
    public void addPostalCode(String postalCode) {
        if (this.postalCodes == null){
            this.postalCodes = new ArrayList<>();
        }
        postalCodes.add(postalCode);
    }   

    public List<String> getRegions() {
        return regions;
    }
    
    public void addRegion(String region) {
        if (this.regions == null){
            this.regions = new ArrayList<>();
        }
        regions.add(region);
    }
   
    public List<String> getBrowsers() {
        return browsers;
    }
    
    public void addBrowser(String browser) {
        if (this.browsers == null){
            this.browsers = new ArrayList<>();
        }
        browsers.add(browser);
    }
    
    public List<String> getBrowserLanguages() {
        return browserLanguages;
    }
    
    public void addBrowserLanguage(String browserLanguage) {
        if (this.browserLanguages == null){
            this.browserLanguages = new ArrayList<>();
        }
        browserLanguages.add(browserLanguage);
    }
    
    public List<String> getBandwidths() {
        return bandwidths;
    }

    public void addBandwidth(String bandwidth) {
        if (this.bandwidths == null){
            this.bandwidths = new ArrayList<>();
        }
        bandwidths.add(bandwidth);
    }
    
    public List<String> getOperatingSystems() {
        return operatingSystems;
    }   
    
    public void addOperatingSystem(String operatingSystem) {
        if (this.operatingSystems == null){
            this.operatingSystems = new ArrayList<>();
        }
        operatingSystems.add(operatingSystem);
    }
    
    public List<String> getDomains() {
        return domains;
    }
    
    public void addDomains(String... domains) {
        for (String domain : domains) {
            this.domains.add(domain);
        }
    }
    
    public List<String> getCustoms() {
        return customs;
    }

    public void addCustom(String custom) {
        if (this.customs == null){
            this.customs = new ArrayList<>();
        }
        customs.add(custom);
    }
}
