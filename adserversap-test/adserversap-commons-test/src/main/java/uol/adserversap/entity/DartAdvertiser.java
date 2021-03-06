package uol.adserversap.entity;

/**
 * @author wrodrigues@uolinc.com
 */

public class DartAdvertiser {

    private Long id;
    private String foreignId;
    private String name;
    private String notes;

    public DartAdvertiser() {
    }

    public DartAdvertiser(Long id, String name, String notes) {
        super();
        this.id = id;
        this.name = name;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getNotes() {
        return notes;
    }
    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getForeignId() {
        return foreignId;
    }
    public void setForeignId(String foreignId) {
        this.foreignId = foreignId;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DartAdvertiser other = (DartAdvertiser) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
