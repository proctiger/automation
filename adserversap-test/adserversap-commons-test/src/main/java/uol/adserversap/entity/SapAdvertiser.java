package uol.adserversap.entity;

/**
 * @author wrodrigues@uolinc.com
 */

public class SapAdvertiser {

    private String id;
    private String name;
    private String cnpj;


    public SapAdvertiser() {
    }


    public SapAdvertiser(String id, String name, String cnpj) {
        super();
        this.id = id;
        this.name = name;
        this.cnpj = cnpj;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getCnpj() {
        return cnpj;
    }
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
