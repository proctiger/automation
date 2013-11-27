package uol.test.step.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;

import cucumber.api.java.pt.Entao;

public class LocalServiceThenSteps extends AbstractLocalServiceSteps {
	int SECONDS = 15;
	
	@Entao("^validar que a inscricao com as informacoes <(.+)> e dados complementares <(.+)> para a inscricao <(.+)> estejam persistidas na base de dados$")
	public void checkSubscription(String info, String dadosComplementares,	String idtInscription) {		
		System.out.println("\n -> Entao validar que a inscricao com as informacoes <"+ info	+ "> e dados complementares <"+ dadosComplementares+ "> para a inscricao <"+ idtInscription + "> estejam persistidas na base de dados\n");
		System.out.println("Buscando inscrição na base de dados");
		for (int x = 0; x < 4; x++) {
			if (checkIdtInscription(idtInscription)) {
				System.out.println("Teste OK, a inscription " + idtInscription + " está persistida na base de dados de afiliados");
				Assert.assertTrue(true);
				System.out.println("\n <- Entao validar que a inscricao com as informacoes <"+ info + "> e dados complementares <"+ dadosComplementares + "> para a inscricao <" + idtInscription + "> estejam persistidas na base de dados\n");
				return;
			} else {
				System.out.println("Tentando novamente . ");
				wait(SECONDS);
			}
		}
		System.out.println("Teste Falhou, a inscription " + idtInscription + " não está persistida na base de dados de afiliados");
		Assert.assertTrue(false);
	}
	
	@Entao("^validar que o campo des_grouping para a inscricao <(.+)> termina com <(.+)>$")
	public void checkSubscriptionIndication(String idtInscription, String agrupador) {		
		System.out.println("\n -> Entao validar que a inscricao <"+ idtInscription + "> esteja persistida na base de dados com indicacao\n");
		System.out.println("Buscando inscrição na base de dados");
		for (int x = 0; x < 4; x++) {
			if (checkIdtInscription(idtInscription)) {
				System.out.println("Teste OK, a inscription " + idtInscription + " com indicacao está persistida na base de dados de afiliados");
				String desGrouping = findDesGroupingByIdtInscription(idtInscription);
				
				System.out.println("Desgrouping retornado pelo registro: " +desGrouping);
				
				Assert.assertTrue(desGrouping.endsWith(agrupador));
				return;
			} else {
				System.out.println("Tentando novamente . ");
				wait(SECONDS);
			}
		}
		System.out.println("Teste Falhou, a inscription " + idtInscription + " não está persistida na base de dados de afiliados");
		Assert.assertTrue(false);
	}

	@Entao("^validar que a inscricao com as informacoes <(.+)> e dados complementares <(.+)> para a inscricao <(.+)> nao estejam persistidas na base de dados$")
	public void checkNotSubscription(String info, String dadosComplementares, String idtInscription) {
		System.out.println("\n -> Entao validar que a inscricao com as informacoes <"+ info	+ "> e dados complementares <"+ dadosComplementares + "> para a inscricao <"+ idtInscription + "> nao estejam persistidas na base de dados\n");
			if (checkIdtInscription(idtInscription)) {
				System.out.println("Teste Falhou, Esta persistido na base.");
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
				System.out.println("Teste OK, não está persistido na base.");
			}
		System.out.println("\n <- Entao validar que a inscricao com as informacoes <"+ info + "> e dados complementares <"	+ dadosComplementares + "> para a inscricao <"+ idtInscription + "> nao estejam persistidas na base de dados\n");
	}

	@Entao("^validar que a inscricao <(.+)> nao esteja persistida na base de afiliados$")
	public void checkNoSubscription(String idtInscription) {
		System.out.println(" -> Então validar que a inscricao " + idtInscription + " nao esteja persistida na base de afiliados");
		if (checkIdtInscription(idtInscription)) {
			System.out.println("Teste Falhou, Esta persistido na base.");
			Assert.assertTrue(false);
		} else {
			Assert.assertTrue(true);
			System.out.println("Teste OK, não está persistido na base.");
		}
		System.out.println(" <- Então validar que a inscricao "	+ idtInscription + " nao esteja persistida na base de afiliados");
	}

	@Entao("^validar que a resposta do servico e o codigo HTTP <(.+)>$")
	public void checkHTTPResponseCode(int code) {
		System.out.println("\n -> Então validar que a resposta do servico e o codigo HTTP \n");
		System.out.println("Esperado : " + code + "   Retornado : "	+ LocalServiceThenSteps.code);
		Assert.assertEquals("O codigo HTTP retornado nao corresponde ao esperado", code,LocalServiceThenSteps.code);
		System.out.println("\n <- Então validar que a resposta do servico e o codigo HTTP \n ");
	}

	@Entao("^verificar se nao ha o idtInscription <(.+)> e codigo de produto <(.+)> no registro correspondente na tabela de evento$")
	public void checkAeEventLogForInscription(String  idtInscription, int prd_source) {
		System.out.println("\n -> Entao verificar se nao ha o idtInscription <"+ idtInscription + "> e codigo de produto <" + prd_source+ "> no registro correspondente na tabela de evento\n");
		if(checkEvent(idtInscription, prd_source)){
			System.out.println("Teste Falhou");
			Assert.fail(String.format("Há o idtInscription %d e codigo de produto %d na tabela de eventos",	idtInscription, prd_source));
		}else{
			System.out.println("Teste OK");
		}
		System.out.println("\n <- Entao verificar se nao ha o idtInscription <"+ idtInscription + "> e codigo de produto <" + prd_source + "> no registro correspondente na tabela de evento\n");
	}

	@Entao("^verificar se ha o idtInscription <(.+)> e codigo de produto <(.+)> no registro correspondente na tabela de evento$")
	public void checkAeEventLogForNoInscription(String idtInscription,	int prd_source) {
		System.out.println(" -> Entao verificar se ha o idtInscription "+ idtInscription + " e codigo de produto " + prd_source	+ " no registro correspondente na tabela de evento");
		System.out.println("Buscando Evento na base de dados.");
		for (int x = 0; x < 4; x++) {
			if (checkEvent(idtInscription, prd_source)) {
				System.out.println("Teste OK");
				Assert.assertTrue(true);
				System.out.println(" <- Entao verificar se ha o idtInscription "+ idtInscription + " e codigo de produto " + prd_source	+ " no registro correspondente na tabela de evento");
				return;
			} else {
				System.out.println("Tentando novamente . ");
				wait(SECONDS);
			}
		}
			System.out.println("Teste Falhou");
			Assert.fail(String.format("Não há o idtInscription %s e codigo de produto %s na tabela de eventos",	idtInscription, prd_source));
		System.out.println(" <- Entao verificar se ha o idtInscription "+ idtInscription + " e codigo de produto " + prd_source	+ " no registro correspondente na tabela de evento");
	}
	
	public boolean checkEvent(String idtInscription,int prd_source){
		Connection conn = null;
		PreparedStatement statement = null;
		ResultSet rs = null;
		try {
			conn = getConnectionUol3();
			statement = conn.prepareStatement("SELECT * FROM AE_EVENT_LOG WHERE DES_NAME = 'Cadastro' AND IDT_PRODUCT_SOURCE = ? AND DES_GROUPING like ?");
			statement.setInt(1, prd_source);
			statement.setString(2, "%" + idtInscription + "%");
			rs = statement.executeQuery();
			if (!rs.next()) {
				System.out.println("Não encontrei o Evento");
				return false;
			} else {
				System.out.println("Encontrei o Evento");
				return true;
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s",e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return false;
	}
	
	public boolean checkIdtInscription(String idtInscription) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement statement	= null;
		conn = getConnectionUol3();
		try {
			String select = "SELECT * FROM AFF_SUBS_IMPORTER_EVENT WHERE IDT_INSCRIPTION = ? ";
			statement = conn.prepareStatement(select);
			statement.setString(1, idtInscription);
			rs = statement.executeQuery();
			if (!rs.next()) {
				System.out.println("Não achei a IDT_INSCRIPTION : "	+ idtInscription);
				return false;
			} else {
				System.out.println("Achei a IDT_INSCRIPTION : "	+ idtInscription);
				return true;
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return false;
	}

	public String findDesGroupingByIdtInscription(String idtInscription) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement statement	= null;
		conn = getConnectionUol3();
		try {
			String select = "SELECT DES_GROUPING FROM AFF_SUBS_IMPORTER_EVENT WHERE IDT_INSCRIPTION = ? ";
			statement = conn.prepareStatement(select);
			statement.setString(1, idtInscription);
			rs = statement.executeQuery();
			if (!rs.next()) {
				System.out.println("Não achei a IDT_INSCRIPTION : "	+ idtInscription);
				return "";
			} else {
				System.out.println("Achei a IDT_INSCRIPTION : "	+ idtInscription);
				return rs.getString(1);
			}
		} catch (SQLException e) {
			Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
		} finally {
			closeResources(statement, rs);
		}
		return "";
	}
	
	
	private void wait(int seg) {
		System.out.println("Esperando " + seg + " segundo(s) ... ");
		try {
			Thread.sleep(1000 * seg);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}