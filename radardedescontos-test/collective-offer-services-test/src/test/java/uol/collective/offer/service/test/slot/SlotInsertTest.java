package uol.collective.offer.service.test.slot;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;




public class SlotInsertTest extends SlotBase
{

    

    
    @Test
    public void successfullyInsert()
    {
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, slot ) );
    }

    
    
    @Test
    public void insertWithMinimumSizeForDescription() {
        SlotTestResponse slot = new SlotTestResponse( null, "A", SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, slot ) );
    }
    
    @Test
    @Ignore
    public void insertWithMaximumSizeForDescription() {
        SlotTestResponse slot = new SlotTestResponse( null, "s sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLK", SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, slot ) );
    }
    
    @Test
    public void insertWithOverSizeForDescription() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 300", defineFields( "Slot.description" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, "as sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLK", SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    @Test
    public void insertWithExistentName() {
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "5", "A" );
        slotDAO.saveOrUpdate( slot );
        slot.setAlias( SLOT_ALIAS + SLOT_ALIAS );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12001", "Slots must have a unique description", defineFields( "Slot.description" ) ) );
        
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    @Test
    public void insertWithoutDescription() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Slot.description" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, null, SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    @Test
    public void insertWithBlankDescription() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Slot.description" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, "", SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    
    
    

    @Test
    public void insertWithMinimumSizeForAlias() {
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, "A", "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, slot ) );
    }
    
    @Test
    @Ignore
    public void insertWithMaximumSizeForAlias() {
        SlotTestResponse slot = new SlotTestResponse( null, "s sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLK", SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, slot ) );
    }
    
    @Test
    public void insertWithOverSizeForAlias() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 30", defineFields( "Slot.alias" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, "012345678901234567890123456789#", "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    @Test
    public void insertWithExistentAlias() {
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "5", "A" );
        slotDAO.saveOrUpdate( slot );
        slot.setDescription( SLOT_DESCRIPTION + SLOT_DESCRIPTION );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12002", "Slots must have a unique alias", defineFields( "Slot.alias" ) ) );
        
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    @Test
    public void insertWithoutAlias() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Slot.alias" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, null, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    @Test
    public void insertWithBlankAlias() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Slot.alias" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, "", "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    
    
    
    
    
    @Test
    public void insertWithoutQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, null, "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithBlankQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Slot.quantity" ) ) );
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,4}", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithInvalidQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,4}", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "aaaaa", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithZeroQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0008", "Value should be greather than 0", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "0", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    
    @Test
    public void insertWithNegativeQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,4}", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "-1", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    

    
    

    @Test
    public void insertWithInactiveStatus() {
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "5", "I" );
        Assert.assertTrue( executeInsertAndValidate( slot, slot ) );
    }
    
    @Test
    public void insertWithoutStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Slot.status" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "1", null );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }

    @Test
    public void insertWithBlankStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "11000", "Status should be 'A' or 'I'", defineFields( "Slot.status" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "1", "" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }

    @Test
    public void insertWithInvalidStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "11000", "Status should be 'A' or 'I'", defineFields( "Slot.status" ) ) );
        
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "1", "XX" );
        Assert.assertTrue( executeInsertAndValidate( slot, expectedErrors ) );
    }
    
    
    
    
    
    
    
    
}
