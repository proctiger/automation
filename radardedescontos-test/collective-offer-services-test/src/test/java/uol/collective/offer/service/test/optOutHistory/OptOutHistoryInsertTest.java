package uol.collective.offer.service.test.optOutHistory;

import junit.framework.Assert;

import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.OptOutHistoryTestResponse;
import uol.collective.offer.commons.test.util.DateTimeUtil;




public class OptOutHistoryInsertTest extends OptOutHistoryBase
{
	
    @Test  
    public void successfullyInsertReasonOne()
    {
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "teste@email.com", "1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, optOut ) );
    }
    
    @Test  
    public void successfullyInsertOtherReason()
    {
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "teste@email.com", "6", "Outra Razao" );
        Assert.assertTrue( executeInsertAndValidate( optOut, optOut ) );
    }
    
    @Test   
    public void insertWithMaximumSizeForEmailDescription() {
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "dddmkvfgsdgfsddfgsdfgfjvfisfmopfsiopfnijwkikwriruwrpegfngfismkvfgsdgfsddfgsdfgfdgsdfgfsdgsdfgdfsgsdfgsdfsdfgsdfgsdfgdfgfsdgdfsgsdfgdsfgdfgdfghnjfjuykjhnmgjyukyipoilhigkhgnjsgweqrwkscvjsvopdfjuasdsdnsisdfjsdksdsdipcdbvjopisfopasfmasfpekekekekekeekekek@la.de", "1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, optOut ) );
    }
    
    @Test  
    public void insertWithOverSizeForEmailDescription() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 1 and 256", defineFields( "OptOutHistory.email" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "dddmkvfgsdgfsddfgsdfgfjvfisfmopfsiopfnijwkikwriruwrpegfngfismkvfgsdgfsddfgsdfgfdgsdfgfsdgsdfgdfsgsdfgsdfsdfgsdfgsdfgdfgfsdgdfsgsdfgdsfgdfgdfghnjfjuykjhnmgjyukyipoilhigkhgnjsgweqrwkscvjsvopdfjuasdsdnsisdfjsdksdsdipcdbvjopisfopasfmasfpekekekekekeekekek0@l.com", "1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }    
    
    @Test  
    public void insertWithInvalidEmail() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2006", "must match the following regular expression: ^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$", defineFields( "OptOutHistory.email" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "yessssssssss.com", "1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }
    
    @Test 
    public void insertWithBlankEmail() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2006", "must match the following regular expression: ^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[a-zA-Z]{2,4}$", defineFields( "OptOutHistory.email" ) ) );
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 1 and 256", defineFields( "OptOutHistory.email" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "", "1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }
    
    @Test  
    public void insertWithoutEmail() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "OptOutHistory.email" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), null, "1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }
    
    @Test    
    public void insertWithNegativeReason() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0008", "must be greater than or equal to 0", defineFields( "OptOutHistory.optOutReason" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "ohh@yes.de", "-1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }
    
    @Test  
    public void insertWithoutReason() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "OptOutHistory.optOutReason" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "ohh@yes.de", null, null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }    
    
    @Test  
    public void insertWithInvalidReason() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "OptOutHistory.optOutReason" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "ohh@yes.de", "invalidReason", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }        
    
    @Test  
    public void insertWithoutOptOutDate() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "OptOutHistory.optOutDate" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( null, "ohh@yes.de", "1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }      
    
    @Test  
    public void insertWithInvalidOptOutDate() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "OptOutHistory.optOutDate" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( "DataInvalida", "ohh@yes.de", "1", null );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }      
    
    @Test   
    public void insertWithMaximumSizeForOtherReason() {
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "yes@no.com", "6", "dddmkvfgsdgfsddfgsdfgfjvfisfmopfsiopfnijwkikwriruwrpegfngfismkvfgsdgfsddfgsdfgfdgsdfgfsdgsdfgdfsgsdfgsdfsdfgsdfgsdfgdfgfsdgdfsgsdfgdsfgdfgdfghnjfjuykjhnmgjyukyipoilhigkhgnjsgweqrwkscvjsvopdfjuasdsdnsisdfjsdksdsdipcdbvjopisfopasfmasfpekekekekekeekekekdddmkvfgsdgfsddfgsdfgfjvfisfmopfsiopfnijwkikwriruwrpegfngfismkvfgsdgfsddfgsdfgfdgsdfgfsdgsdfgdfsgsdfgsdfsdfgsdfgsdfgdfgfsdgdfsgsdfgdsfgdfgdfghnjfjuykjhnmgjyukyipoilhigkhgnjsgweqrwkscvjsvopdfjuasdsdnsisdfjsdksdsdipcdbvjopisfopasfmasfpekekekekekeekekek" );
        Assert.assertTrue( executeInsertAndValidate( optOut, optOut ) );
    }
    
    @Test  
    public void insertWithOverSizeForOtherReason() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 1 and 500", defineFields( "OptOutHistory.otherReason" ) ) );
        
        OptOutHistoryTestResponse optOut = new OptOutHistoryTestResponse( DateTimeUtil.getCurrentDateInXmlFormat(), "yes@no.com", "6", "dddmkvfgsdgfsddfgsdfgfjvfisfmopfsiopfnijwkikwriruwrpegfngfismkvfgsdgfsddfgsdfgfdgsdfgfsdgsdfgdfsgsdfgsdfsdfgsdfgsdfgdfgfsdgdfsgsdfgdsfgdfgdfghnjfjuykjhnmgjyukyipoilhigkhgnjsgweqrwkscvjsvopdfjuasdsdnsisdfjsdksdsdipcdbvjopisfopasfmasfpekekekekekeekekekdddmkvfgsdgfsddfgsdfgfjvfisfmopfsiopfnijwkikwriruwrpegfngfismkvfgsdgfsddfgsdfgfdgsdfgfsdgsdfgdfsgsdfgsdfsdfgsdfgsdfgdfgfsdgdfsgsdfgdsfgdfgdfghnjfjuykjhnmgjyukyipoilhigkhgnjsgweqrwkscvjsvopdfjuasdsdnsisdfjsdksdsdipcdbvjopisfopasfmasfpekekekekekeekekek0" );
        Assert.assertTrue( executeInsertAndValidate( optOut, expectedErrors ) );
    }      
}
