package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;

import cucumber.api.java.pt.Entao;

public class LocalServiceThenSteps extends AbstractLocalServiceSteps {

	@Entao("^validar que os dados <(.+)> estejam persistidos na tabela de eventos$")
	public void checkAeEventLogForPayment(String dadosDePagamento)	throws JSONException {
		System.out.println("\n -> Entao validar que os dados "+ dadosDePagamento + " estejam persistidos na tabela de eventos \n");	
		if(getAeEventLogForPayment(dadosDePagamento)){
			System.out.println("OK os dados estão persistidos na tabela de eventos");
		}else{
			Assert.fail("NOK os dados não estão persistidos na tabela de eventos");
		}
		System.out.println("\n <- Entao validar que os dados "+ dadosDePagamento + " estejam persistidos na tabela de eventos \n");
		realRC();
	}
	
	@Entao("^validar que os dados <(.+)> não estejam persistidos na tabela de eventos$")
	public void checkAeEventLogNotExist(String dadosDePagamento)  throws JSONException {
		System.out.println("\n -> Entao validar que os dados "+ dadosDePagamento + " não estejam persistidos na tabela de eventos \n");	
		if(!getAeEventLogForPayment(dadosDePagamento)){
			System.out.println("OK os dados não estão persistidos na tabela de eventos");
		}else{
			Assert.fail("NOK os dados estão persistidos na tabela de eventos");
		}
		System.out.println("\n <- Entao validar que os dados "+ dadosDePagamento + " não estejam persistidos na tabela de eventos \n");
		realRC();

	}
	
	private boolean getAeEventLogForPayment(String dadosDePagamento) throws JSONException{
		Connection conn;
		PreparedStatement statement;
		ResultSet rs;
		JSONObject json = new JSONObject(dadosDePagamento);
		try {
			conn = getConnectionUol3();
			statement = conn.prepareStatement("SELECT * FROM AE_EVENT_LOG WHERE DES_NAME = 'Compra' AND IDT_PRODUCT_SOURCE = 13 AND DES_GROUPING like ?");
			statement.setString(1,"%" + json.getString("idtInscriptionAccount") + "%");
			rs = statement.executeQuery();
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",	e.getLocalizedMessage()));
		}
		return false;
		}
	
}