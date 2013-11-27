package uol.bt.commons.test.profile;

public class Trait {

    private final String btCode;
    private final String description;

    public Trait(String btCode, String description) {
        this.btCode = btCode;
        this.description = description;
    }

    public String getBtCode() {
        return btCode;
    }

    public String getDescription() {
        return description;
    }
}
