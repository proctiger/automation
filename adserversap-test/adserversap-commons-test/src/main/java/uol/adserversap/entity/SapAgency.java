package uol.adserversap.entity;

/**
 * @author wrodrigues@uolinc.com
 */
public class SapAgency {

    private String id;
    private String name;
    private String cnpj;

    public SapAgency() {
    }

    public SapAgency(String id, String name, String cnpj) {
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



