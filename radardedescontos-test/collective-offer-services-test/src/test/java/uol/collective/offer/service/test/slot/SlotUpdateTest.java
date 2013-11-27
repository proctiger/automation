package uol.collective.offer.service.test.slot;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import uol.collective.offer.commons.test.model.impl.ErrorTestResponse;
import uol.collective.offer.commons.test.model.impl.ErrorsTestResponse;
import uol.collective.offer.commons.test.model.impl.SlotTestResponse;
import uol.collective.offer.commons.test.util.ResponseValidator;




public class SlotUpdateTest extends SlotBase
{

    

    
    @Test
    public void successfullyUpdate()
    {
        SlotTestResponse oldSlot = insertNextSlot( "5" );
        
        SlotTestResponse newSlot = getUpdated( oldSlot ); 
        
        Assert.assertTrue( executeUpdateAndValidate( newSlot, oldSlot, ResponseValidator.UPDATE_VALIDATOR ) );
    }

    @Test
    public void updateUnexisting()
    {
        SlotTestResponse nonExistingSlot = new SlotTestResponse( "99999", SLOT_DESCRIPTION, SLOT_ALIAS, "5", "A" );

        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12000", "Resource slot not found with id 99999", defineFields( "slot" ) ) );
        
        boolean result = executeUpdateAndValidate( nonExistingSlot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }

    @Test
    public void updateWithInvalidId()
    {
        SlotTestResponse nonExistingSlot = new SlotTestResponse( "aaaaaa", SLOT_DESCRIPTION, SLOT_ALIAS, "5", "A" );

        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "1100", "An invalid client request was made", defineFields( "/slot/aaaaaa" ) ) );
        
        boolean result = executeUpdateAndValidate( nonExistingSlot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR );
        Assert.assertTrue( result );
    }



    @Test
    public void updateWithMinimumSizeForDescription() {
        SlotTestResponse oldSlot = insertNextSlot( "5" );
        
        SlotTestResponse newSlot = getUpdated( oldSlot ); 
        newSlot.setDescription( "A" );
        
        Assert.assertTrue( executeUpdateAndValidate( newSlot, oldSlot, ResponseValidator.UPDATE_VALIDATOR ) );
    }
    
    @Test
    @Ignore
    public void updateWithMaximumSizeForDescription() {
        SlotTestResponse slot = new SlotTestResponse( null, "s sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLK", SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, slot ) );
    }
    
    @Test
    public void updateWithOverSizeForDescription() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 300", defineFields( "Slot.description" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setDescription( "as sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLK" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    @Test
    public void updateWithExistentName() {
        SlotTestResponse slot = new SlotTestResponse( null, SLOT_DESCRIPTION, SLOT_ALIAS, "5", "A" );
        slotDAO.saveOrUpdate( slot );
        slot.setAlias( SLOT_ALIAS + SLOT_ALIAS );
        
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12001", "Slots must have a unique description", defineFields( "Slot.description" ) ) );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    @Test
    public void updateWithoutDescription() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Slot.description" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5");
        slot = getUpdated( slot );
        slot.setDescription( null );
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    @Test
    public void updateWithBlankDescription() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Slot.description" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5");
        slot = getUpdated( slot );
        slot.setDescription( "" );
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    
    
    

    @Test
    public void updateWithMinimumSizeForAlias() {
        SlotTestResponse oldSlot = insertNextSlot( "5" );
        
        SlotTestResponse newSlot = getUpdated( oldSlot ); 
        newSlot.setAlias( "A" );
        
        Assert.assertTrue( executeUpdateAndValidate( newSlot, oldSlot, ResponseValidator.UPDATE_VALIDATOR ) );
    }
    
    @Test
    @Ignore
    public void updateWithMaximumSizeForAlias() {
        SlotTestResponse slot = new SlotTestResponse( null, "s sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLK", SLOT_ALIAS, "5", "A" );
        Assert.assertTrue( executeInsertAndValidate( slot, slot ) );
    }
    
    @Test
    public void updateWithOverSizeForAlias() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2001", "size must be between 0 and 30", defineFields( "Slot.alias" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setAlias( "as sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLKs sdn s sjfsakf akjs jk sa·df·sf‚sfs„fsdaf·sdfÙsdfsad’fsdaf…sfs¿!@#$%®&*()_- dsfg dsf gdsf gdfg fdLK" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    @Test
    public void updateWithExistentAlias() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "12002", "Slots must have a unique alias", defineFields( "Slot.alias" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        String oldAlias = slot.getAlias();
        slot = getUpdated( slot );
        slot.setAlias( oldAlias );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    @Test
    public void updateWithoutAlias() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Slot.alias" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setAlias( null );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    @Test
    public void updateWithBlankAlias() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Slot.alias" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setAlias( "" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    
    
    
    
    
    @Test
    public void updateWithoutQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setQuantity( null );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    
    @Test
    public void updateWithBlankQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2002", "may not be blank", defineFields( "Slot.quantity" ) ) );
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,4}", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setQuantity( "" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    
    @Test
    public void updateWithInvalidQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,4}", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setQuantity( "aaa" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    
    @Test
    public void updateWithZeroQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0008", "Value should be greather than 0", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setQuantity( "0" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    
    @Test
    public void updateWithNegativeQuantity()
    {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "0007", "must match the following regular expression: [0-9]{1,4}", defineFields( "Slot.quantity" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setQuantity( "-1" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    

    
    

    @Test
    public void inserirWithoutStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "2000", "may not be null", defineFields( "Slot.status" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setStatus( null );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }

    @Test
    public void inserirWithBlankStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "11000", "Status should be 'A' or 'I'", defineFields( "Slot.status" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setStatus( "" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }

    @Test
    public void updateWithInvalidStatus() {
        ErrorsTestResponse expectedErrors = new ErrorsTestResponse();
        expectedErrors.addError( new ErrorTestResponse( "11000", "Status should be 'A' or 'I'", defineFields( "Slot.status" ) ) );
        
        SlotTestResponse slot = insertNextSlot( "5" );
        slot = getUpdated( slot );
        slot.setStatus( "XX" );
        
        Assert.assertTrue( executeUpdateAndValidate( slot, expectedErrors, ResponseValidator.DEFAULT_VALIDATOR ) );
    }
    
    
    
    
    

    
    
    private SlotTestResponse getUpdated( SlotTestResponse slot )
    {
        SlotTestResponse newSlot = slotDAO.load( slot );
        newSlot.setDescription( SLOT_DESCRIPTION + "-UPDATED" );
        newSlot.setAlias( SLOT_ALIAS + "-UPDATED" );
        int qtd = Integer.valueOf( slot.getQuantity() );
        newSlot.setQuantity( String.valueOf( qtd + 1 ) );
        newSlot.setStatus( "A".equals( slot.getStatus() ) ? "I" : "A" );
        return newSlot;
    }    
    
    
}
