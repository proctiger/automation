package sandbox;
import uol.adserversap.ws.test.domain.OrderDomain;
import uol.adserversap.ws.test.http.DfpLineItemHttpRequest;
import uol.adserversap.ws.test.http.DfpOrderHttpRequest;


public class createReprogrammingOrderToApproveManually {

	/**
	 * @param args
	 * @throws Exception
	 * 
	 *  Cria ordem no DFP com status Pending for Approval: para testes manuais
	 */

	public static void main(String[] args) throws Exception {
		String enterprise = "BOLB";
		OrderDomain order = DfpOrderHttpRequest.createDefaultOrder(enterprise);
		DfpLineItemHttpRequest.createDefaultLineItem(order);
		DfpOrderHttpRequest.updateBillingTypeId(order, 15);
		DfpOrderHttpRequest.submitForApproval(order);
		
		System.out.println(order.getId()+"-----"+ order.getStatus()+"----"+order.getName()+"----"+ order.getBillingType());
		
	}

}
