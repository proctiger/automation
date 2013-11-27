package uol.collective.offer.service.test.site;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.SiteTestResponse;




public class SiteInsertTest extends SiteBase
{
    
    
    


    

    @Test   //@Ignore
    public void successfullyInsert() {
        SiteTestResponse site = getSiteInstance();
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }



    @Test   //@Ignore
    public void insertWithMinimumSizeForName() {
        SiteTestResponse site = getSiteInstance();
        site.setName( "A" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }
    
    @Test   //@Ignore
    public void insertWithOverSizeForName() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 100", defineFields( "Site.name" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setName( "as sdn s sjfsakf akjs jk saádfásfâsfsãfsdafásdfôsdfsadÕfsdafÉsfsÀ!@#$%¨&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk saádfásfâsfsãfsdafásdfôsdfsadÕfsdafÉsfsÀ!@#$%¨&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk saádfásfâsfsãfsdafásdfôsdfsadÕfsdafÉsfsÀ!@#$%¨&*()_- dsfg dsf gdsf gdfg fdLK" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    @Test   //@Ignore
    public void insertWithExistentName() {
        SiteTestResponse site = getSiteInstance();
        siteDAO.saveOrUpdate( site );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "3001", "Sites must have a unique name", defineFields( "Site.name" ) ) );
        
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    @Test   //@Ignore
    public void insertWithoutName() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Site.name" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setName( null );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    @Test   //@Ignore
    public void insertWithBlankName() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Site.name" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setName( "" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    

    
    

    @Test   //@Ignore
    public void insertWithInactiveStatus() {
        SiteTestResponse site = getSiteInstance();
        site.setStatus( "I" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }
    
    @Test   //@Ignore
    public void insertWithoutStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Site.status" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setStatus( null );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }

    @Test   //@Ignore
    public void insertWithBlankStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "3000", "Status should be 'A' or 'I'", defineFields( "Site.status" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setStatus( "" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }

    @Test   //@Ignore
    public void insertWithInvalidStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "3000", "Status should be 'A' or 'I'", defineFields( "Site.status" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setStatus( "XX" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
   
    @Test   //@Ignore
    public void insertWithOverSizeForUrlLogo() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 512", defineFields( "Site.siteUrl" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setSiteUrl( "http://uol.com/012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    @Test   //@Ignore
    public void insertWithoutUrlLogo() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Site.siteUrl" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setSiteUrl( null );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    @Test   //@Ignore
    public void insertWithBlankUrlLogo() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Site.siteUrl" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setSiteUrl( "" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    

    
    
    
    @Test   //@Ignore
    public void insertWithMaximumSizeForXml() {
        SiteTestResponse site = getSiteInstance();
        site.setDesXmlPath( "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }
    
    @Test   //@Ignore
    public void insertWithOverSizeForXml() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 255", defineFields( "Site.desXmlPath" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setDesXmlPath( "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    @Test   //@Ignore
    public void insertWithoutXml() {
        SiteTestResponse site = getSiteInstance();
        site.setDesXmlPath( null );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }
    
    @Test  // @Ignore
    public void insertWithBlankXml() {
        SiteTestResponse site = getSiteInstance();
        site.setDesXmlPath( "" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }

    
    
    
    @Test  // @Ignore
    public void insertWithMaximumSizeForPagseguroEmail() {
        SiteTestResponse site = getSiteInstance();
        site.setPagseguroEmail( "012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789@uol.com" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }
    
    @Test  // @Ignore
    public void insertWithOverSizeForPagseguroEmail() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 128", defineFields( "Site.pagseguroEmail" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setPagseguroEmail( "0123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234567890@uol.com" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, expectedErrors ) );
    }
    
    @Test  // @Ignore
    public void insertWithoutPagseguroEmail() {
        SiteTestResponse site = getSiteInstance();
        site.setPagseguroEmail( null );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }
    
    @Test  // @Ignore
    public void insertWithBlankPagseguroEmail() {
        SiteTestResponse site = getSiteInstance();
        site.setPagseguroEmail( "" );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }

    
    
    
    @Test  // @Ignore
    public void insertWithOverSizeForPagseguroCode() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 50", defineFields( "Site.pagseguroCode" ) ) );
        
        SiteTestResponse site = getSiteInstance();
        site.setPagseguroPublicKey( "012345678901234567890123456789012345678901234567890" );
        site.setPagseguroPrivateKey( "012345678901234567890123456789012345678901234567890" );
        Assert.assertTrue( executeInsertAndValidate( site, expectedErrors ) );
    }
    
    @Test  // @Ignore
    public void insertWithoutPagseguroCode() {
        SiteTestResponse site = getSiteInstance();
        site.setPagseguroEmail( null );
        site.setPagseguroPublicKey( null );
        site.setPagseguroPrivateKey( null );
        Assert.assertTrue( executeInsertAndValidate( site, site ) );
    }
    
    
    
    
    
    @Test  // @Ignore
    public void insertWithoutIdtPerson()
    {
        SiteTestResponse site = getSiteInstance();
        site.setIdtPerson( null );
        Assert.assertTrue( executeInsertAndValidateForSite( site, site ) );
    }
    
    

    
    
    
    
    
    
    
    
    
    
    

//    @Test   @Ignore
//    @Ignore //TODO REVER TESTE, TAMANHO DA BASE 64 PROBLEMATICO SE ANALISADO COM A COLUNA NO BANCO
//    public void inserirSiteInformandoImagemDeLogo() {
//        Site expectedResult = new Site(null, "Novo site com logo", "A", BASE64_GIF, null, "pagseguro@email.com", "contact name", "http://site.url.com.br");
//        removeSiteByName(expectedResult.getName());
//        Assert.assertTrue(executeTestSuccess(expectedResult));
//        expectedResult = new Site(null, "Novo site com logo", "A", BASE64_PNG, null, "pagseguro@email.com", "contact name", "http://site.url.com.br");
//        removeSiteByName(expectedResult.getName());
//        Assert.assertTrue(executeTestSuccess(expectedResult));
//        expectedResult = new Site(null, "Novo site com logo", "A", BASE64_JPG, null, "pagseguro@email.com", "contact name", "http://site.url.com.br");
//        removeSiteByName(expectedResult.getName());
//        Assert.assertTrue(executeTestSuccess(expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteComAQuantidadeMinimaDeCaracteresParaONome() {
//        Site expectedResult = new Site(null, "X", "A", "http://url.log.image.png", null, "pagseguro@email.com", "contact name", "http://site.url.com.br");
//        removeSiteByName(expectedResult.getName());
//        Assert.assertTrue(executeTestSuccess(expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteComAQuantidadeMaximaDeCaracteresParaONome() {
//        Site expectedResult = new Site(null, "Site com 100 caracteres ã â à á ä ê è é ë î ì í ï õ ô ò ó ö û ú ù ü Ã Â À Á Ä Ê ÈÉËÎÌÍÏÕÔÒÓÖÛÙÚÜçÇñÑ", "A", "http://url.log.image.png", null, "pagseguro@email.com", "contact name", "http://site.url.com.br");
//        removeSiteByName(expectedResult.getName());
//        Assert.assertTrue(executeTestSuccess(expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteComStatusAtivo() {
//        Site expectedResult = new Site(null, "Site com status ativo", "A", "http://url.log.image.png", null, "pagseguro@email.com", "contact name", "http://site.url.com.br");
//        removeSiteByName(expectedResult.getName());
//        Assert.assertTrue(executeTestSuccess(expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteComStatusInativo() {
//        Site expectedResult = new Site(null, "Site com status inativo", "I", "http://url.log.image.png", null, "pagseguro@email.com", "contact name", "http://site.url.com.br");
//        removeSiteByName(expectedResult.getName());
//        Assert.assertTrue(executeTestSuccess(expectedResult));
//    }
//
//    private boolean executeTestSuccess(Site expectedResult) {
//        audit.persistLogin(LOGIN);
//        audit.removeRecentEvents(LOGIN);
//        try {
//            ServiceRequestSite.postSite(expectedResult, LOGIN, IP);
//        } catch (Exception e) {
//            TestLogger.error("Falha ao executar serviço: " + e);
//        }
//        if (!validateSiteFromDAO(expectedResult)) {
//            return false;
//        }
//        return audit.validateAuditEvent(LOGIN, IP, "uol.collective.offer.business.impl.SiteServiceImpl.insertSite", getEventDescription(
//                null, expectedResult.getName(), expectedResult.getImageUrl(), expectedResult.getStatus(), expectedResult.getXmlPath(), expectedResult.getPagSeguroEmail(), 
//                expectedResult.getImageSrc(), null, expectedResult.getContactName(), expectedResult.getSiteUrl()));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteSemInformarNome() {
//        audit.persistLogin(LOGIN);
//        HashSet<Error> expectedResult = new HashSet<Error>();
//        expectedResult.add(new Error("2000", "may not be null", defineFields("Site.name")));
//        Assert.assertTrue(executeTestFail(new Site(null, null, "A", null, null, null, "contactName", "siteUrl"), LOGIN, IP, expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteSemInformarStatus() {
//        audit.persistLogin(LOGIN);
//        HashSet<Error> expectedResult = new HashSet<Error>();
//        expectedResult.add(new Error("2000", "may not be null", defineFields("Site.status")));
//        Assert.assertTrue(executeTestFail(new Site(null, "Site sem Status", null, null, null, null, "contactName", "siteUrl"), LOGIN, IP, expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteInformandoNomeVazio() {
//        audit.persistLogin(LOGIN);
//        HashSet<Error> expectedResult = new HashSet<Error>();
//        expectedResult.add(new Error("2002", "may not be blank", defineFields("Site.name")));
//        Assert.assertTrue(executeTestFail(new Site(null, " ", "A", null, null, null, "contactName", "siteUrl"), LOGIN, IP, expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteInformandoNomeInvalido() {
//        audit.persistLogin(LOGIN);
//        HashSet<Error> expectedResult = new HashSet<Error>();
//        expectedResult.add(new Error("2001", "size must be between 0 and 100", defineFields("Site.name")));
//        Assert.assertTrue(executeTestFail(new Site(null, "Site com 101 caracteres ã â à á ä ê è é ë î ì í ï õ ô ò ó ö û ú ù ü Ã Â À Á Ä Ê È ÉËÎÌÍÏÕÔÒÓÖÛÙÚÜçÇñÑ", "A", null, null, null, "contactName", "siteUrl"), LOGIN, IP, expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteInformandoStatusVazio() {
//        audit.persistLogin(LOGIN);
//        HashSet<Error> expectedResult = new HashSet<Error>();
//        expectedResult.add(new Error("3000", "Status should be 'A' or 'I'", defineFields("Site.status")));
//        Assert.assertTrue(executeTestFail(new Site(null, "Site sem Status", "", null, null, null, "contactName", "siteUrl"), LOGIN, IP, expectedResult));
//    }
//
//    @Test   @Ignore
//    public void inserirSiteInformandoStatusInvalido() {
//        audit.persistLogin(LOGIN);
//        HashSet<Error> expectedResult = new HashSet<Error>();
//        expectedResult.add(new Error("3000", "Status should be 'A' or 'I'", defineFields("Site.status")));
//        Assert.assertTrue(executeTestFail(new Site(null, "Site sem Status", "O", null, null, null, "contactName", "siteUrl"), LOGIN, IP, expectedResult));
//    }
//
//    private boolean executeTestFail(Site site, String login, String ip, HashSet<Error> expectedResult) {
//        Response clientResponse = new Response();
//        try {
//            clientResponse = ServiceRequestSite.postSite(site, login, ip);
//        } catch (Exception e) {
//            TestLogger.error("Falha ao executar servico: " + e);
//            return false;
//        }
//        return validateServiceErrors(expectedResult, clientResponse);
//    }
    
    
}
