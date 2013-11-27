package sandbox;


import java.rmi.RemoteException;
import java.util.Random;

import uol.adserversap.ws.test.http.DfpHttpRequest;

import com.google.api.ads.dfp.axis.utils.v201302.StatementBuilder;
import com.google.api.ads.dfp.axis.v201302.ApiException;
import com.google.api.ads.dfp.axis.v201302.OrderPage;
import com.google.api.ads.dfp.axis.v201302.OrderServiceInterface;
import com.google.api.ads.dfp.axis.v201302.Statement;

public class teste2 extends DfpHttpRequest{

		public static void main (String args[]) throws ApiException, RemoteException{
			
			 Random rand = new Random(); 
		        System.out.println(rand.nextInt(20));
			
//			filterOrder();
//			long orderId = 112995421; 
//			
//			final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);
//	        final Statement stmtFilterById = new StatementBuilder().where("id = :id")
//	                .withBindVariableValue("id", orderId)
//	                .toStatement();
//	        
//	        OrderAction action = new SubmitOrdersForApprovalAndOverbook();
//			orderService.performOrderAction(action, stmtFilterById);
			

			
		}
	
	 public static void filterOrder() throws ApiException, RemoteException{
			final OrderServiceInterface orderService = dfpServiceFactory.newDfpService(OrderServiceInterface.class);
	        
//			"WHERE status = 'ACTIVE' ORDER BY id LIMIT 30"
			
			
			final Statement filterStatement = new StatementBuilder().where("status = 'PENDING APPROVAL'").toStatement();
	        
			OrderPage ordersByStatement = orderService.getOrdersByStatement(filterStatement);
			
			System.out.println(ordersByStatement.toString());
		
	 }
	
}
