package uol.collective.offer.commons.test.model;

public interface TestResponse extends TestEntity
{

    public static String ENTITY_ROOT_RADAR = "http://ws.radardedescontos.intranet";
    public static String ENTITY_ROOT_PS = "http://ws.pagseguro.uol.com.br";

    public abstract String getEntityRootUrl();

    public abstract String getId();

    public abstract void setId(String id);

}
