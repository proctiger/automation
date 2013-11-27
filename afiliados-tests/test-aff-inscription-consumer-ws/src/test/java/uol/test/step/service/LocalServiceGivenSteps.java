package uol.test.step.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import uol.crypt.CryptographerException;
import uol.crypt.base64.Base64;
import br.com.uol.cms.redis.driver.RedisService;
import cucumber.api.java.Before;
import cucumber.api.java.pt.Dado;

public class LocalServiceGivenSteps extends AbstractLocalServiceSteps {
    private @Autowired RedisService redisService;
    
    @Before
	public void inicioTeste() throws Exception {
    	startAndWaitApplication("aff-inscription-consumer", "jboss","a1-jesse-s-prt1");
    	startAndWaitApplication("affiliated-event-persistence", "jetty","a1-iziafl-s-prt1");
		System.out.println("\n\n --------------------------------- Inicio - Cenario --------------------------------- \n\n");
    }
    
    @Dado("^que nao exista o idtInscription <(.+)> e codigo de produto <(.+)> no registro correspondente na tabela de evento$")
    public void removeIdtInscriptionEventLog(Long idtInscription, int prd_source) {
    	System.out.println("\n -> Dado que nao exista o idtInscription <"+idtInscription+"> e codigo de produto <"+prd_source+"> no registro correspondente na tabela de evento\n");
    	PreparedStatement statement = null;
        ResultSet rs = null;
    	Connection conn = null;
        try {
        	conn = getConnectionUol3();
        	statement = conn.prepareStatement("DELETE FROM AE_EVENT_LOG WHERE DES_NAME = 'Cadastro' AND IDT_PRODUCT_SOURCE = ? AND DES_GROUPING like ?");
			statement.setInt(1,prd_source);
			statement.setString(2,"%"+idtInscription+"%");
        	rs = statement.executeQuery();
        	
        	System.out.println("Registros apagados .");
		} catch (SQLException e) {
            Assert.fail(String.format("Erro ao tentar executar a query na base de dados, devido a: %s", e.getLocalizedMessage()));
		}finally{
			try {
				conn.commit();
			} catch (SQLException e) {
				Assert.fail(String.format("Nao foi possivel realizar comitar no banco "));
			}
    		closeResources(statement,rs);
    	}		
        System.out.println("\n <- Dado que nao exista o idtInscription <"+idtInscription+"> e codigo de produto <"+prd_source+"> no registro correspondente na tabela de evento\n");
    }
           
    @Dado("^que exista no DAS3 a hash <(.+)> de inscricao <(.+)>$")
    public void setDASInfoFor(String hash, Long idtInscription) {
       System.out.println("\n -> Dado que exista no DAS3 a hash <"+ hash +"> de inscricao <"+ idtInscription +">\n");
    	try {
            List<Map<String, Object>> data;
            try {
                System.out.print("subscriptionService.getExtraInscriptionData: ");
                data = SUBSCRIPTION_SERVICE_IMPL.getExtraInscriptionData(idtInscription);
                System.out.println("Obtendo dados da inscription: "+idtInscription);
                
            } catch (Exception e) {
                data = null;
                System.out.println("NOK");
            }
            if (data != null && data.size() > 0) {
                for (Map<String, Object> map : data) {
                    if (AFFILIATED_INFO.equals(map.get(INFO_TYPE))) {
                    	System.out.println("data delete: " + data);
                        System.out.print("subscriptionService.deleteExtraInscriptionData: ");
                        SUBSCRIPTION_SERVICE_IMPL.deleteExtraInscriptionData(idtInscription, AFFILIATED_INFO_CODE);
                        System.out.println("OK");
                        System.out.println("Deletada a inscription: "+idtInscription);
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail(String.format("Falha na verificacao/exclusao da variavel %s, devido a exception: %s",
                                      AFFILIATED_INFO,
                                      e.getLocalizedMessage()));
            System.out.println("NOK");
        }
        Map<String, String> map = new HashMap<String, String>(1);
        map.put(AFFILIATED_INFO, hash);
        try {
            System.out.print("subscriptionService.persistExtraInscriptionData: ");
            System.out.println("data persist: "+map);
            SUBSCRIPTION_SERVICE_IMPL.persistExtraInscriptionData(idtInscription, map);
            System.out.println("Setando dados da inscription: "+idtInscription);
            
        } catch (Exception e) {
            Assert.fail(String.format("Nao foi possivel persistir as informacoes adicionais no DAS, devido a: %s", e.getLocalizedMessage()));
            
            System.out.println("NOK");
        }
        System.out.println("\n <- Dado que exista no DAS3 a hash <"+ hash +"> de inscricao <"+ idtInscription +">\n");
    }
    
    @Dado("^que nao exista a idtInscription <(.+)> cadastrado em afiliados$")
    public void deleteInscriptionEventFor(Long idtInscription) {
        System.out.println("\n -> Dado que nao exista a idtInscription <"+ idtInscription +"> cadastrado em afiliados \n");
    	Connection conn = null;
    	PreparedStatement statement = null;
    	ResultSet rs = null;
    	conn = getConnectionUol3();
        try {
            statement = conn.prepareStatement("select 1 from aff_subs_importer_event e where e.idt_inscription = ?");
            statement.setLong(1, idtInscription);
            System.out.println(String.format("Realizando pesquisa de evento existente para a inscription %d", idtInscription));
             rs = statement.executeQuery();
            if(rs.next()){
                System.out.println(String.format("Registro encontrado para a inscription %d", idtInscription));
                System.out.println(String.format("Removendo o registro existente para a inscription %d", idtInscription));
                statement = conn.prepareStatement("delete from aff_subs_importer_event e where e.idt_inscription = ?");
                statement.setLong(1, idtInscription);
                statement.executeUpdate();
                System.out.println(String.format("Registro removido com suceso para a inscription %d", idtInscription));
            } else {
                System.out.println(String.format("Registro nao encontrado para a inscription %d", idtInscription));
            }
        } catch (SQLException e) {
            Assert.fail(String.format("Nao foi possivel realizar a exclusao do registro devido a '%s'", e.getLocalizedMessage()));
        } finally{
        	try {
				conn.commit();
			} catch (SQLException e) {
				Assert.fail(String.format("Nao foi possivel realizar comitar no banco "));
			}
    		closeResources(statement,rs);
        }
        System.out.println("\n <- Dado que nao exista a idtInscription <"+ idtInscription +"> cadastrado em afiliados \n");
    }

    @Dado("^que nao exista no DAS3 a hash <(.+)> de inscricao <(.+)>$")
    public void removeDASInfoFor(String hash, Long idtInscription) {
        System.out.println("\n -> Dado que nao exista no DAS3 a hash <"+hash+"> de inscricao <"+idtInscription+">\n");
    	try { 
            List<Map<String, Object>> data;
            try {
                System.out.print("subscriptionService.getExtraInscriptionData: ");
                data = SUBSCRIPTION_SERVICE_IMPL.getExtraInscriptionData(idtInscription);
                System.out.println("Obtendo dados da inscription: "+idtInscription);
            } catch (Exception e) {
                data = null;
                System.out.println("NOK");
            }
            if (data != null && data.size() > 0) {
                for (Map<String, Object> map : data) {
                    if (AFFILIATED_INFO.equals(map.get(INFO_TYPE))) {
                        System.out.print("subscriptionService.deleteExtraInscriptionData: ");
                        SUBSCRIPTION_SERVICE_IMPL.deleteExtraInscriptionData(idtInscription, AFFILIATED_INFO_CODE);
                        System.out.println("Deletada a inscription: "+idtInscription);
                    }
                }
            }
        } catch (Exception e) {
            Assert.fail(String.format("Falha na verificacao/exclusao da variavel %s, devido a exception: %s", AFFILIATED_INFO, e.getLocalizedMessage()));
            System.out.println("NOK");
        }
    	System.out.println("\n <- Dado que nao exista no DAS3 a hash <"+hash+"> de inscricao <"+idtInscription+">\n");
    }

    
    @Dado("^que no servidor de sessao exista as informacoes de afiliados <(.+)> de hash <(.+)> e codigo de produto <(.+)>$")
    public void insertRedisKey(String info, String hash, int prd_source) throws UnsupportedEncodingException, CryptographerException{
    	System.out.println("\n -> Dado que no servidor de sessao exista as informacoes de afiliados <"+ info +"> de hash <"+ hash +"> e codigo de produto <"+ prd_source +"> \n");
    	String key = hash + ":" + prd_source;
    	System.out.println("Inserindo no Redis > KEY:" +key  +" VALUE: " + info);
    	byte[] bytes = info.getBytes("UTF-8");
    	byte[] encoded = Base64.encode(bytes);
    	String infoBase64 = new String(encoded);
    	System.out.println("Passando value para base 64 > "+  infoBase64);
    	redisService.put(key,infoBase64.toString() ,2592000);
    	System.out.println("Inserido no Redis > KEY:" +key  +" VALUE: " + infoBase64);
    	System.out.println("\n <- Dado que no servidor de sessao exista as informacoes de afiliados <"+ info +"> de hash <"+ hash +"> e codigo de produto <"+ prd_source +">\n");
    }
    	
    @Dado("^que no servidor de sessao nao exista as informacoes de afiliados <(.+)> de hash <(.+)> e codigo de produto <(.+)>$")
    public void notInsertRedisKey(String info, String hash, int prd_source) throws UnsupportedEncodingException, CryptographerException {
    	System.out.println("\n -> Dado que no servidor de sessao nao exista as informacoes de afiliados <"+ info +"> de hash <"+ hash +"> e codigo de produto <"+ prd_source +"> \n");
    	String key = hash + ":" + prd_source;
    	System.out.println("Buscando no redis :  " +  key);
    	String value = redisService.get(key, String.class);
    	if(value == null){
    		System.out.println("Ok , não achei no Redis...");
    	}else{
    		System.out.println("Removendo do redis : " + key);
        	redisService.remove(key);
    	}
        System.out.println("\n <- Dado que no servidor de sessao nao exista as informacoes de afiliados <"+ info +"> de hash <"+ hash +"> e codigo de produto <"+ prd_source +">\n");

    }
    
    @Dado("^que o registrador de evento esteja inoperante$")
    public void stopRegistrator() throws Exception{
    	System.out.println(" -> Dado que o registrador de evento esteja inoperante");
    	stopApplication("affiliated-event-persistence", "jetty","a1-iziafl-s-prt1");
    	System.out.println(" <- Dado que o registrador de evento esteja inoperante");
    }


}
