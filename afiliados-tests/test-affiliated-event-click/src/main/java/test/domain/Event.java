package test.domain;

public enum Event {
    INDICACAO("Indicacao"),
    CLIQUE("Clique");

    private String name;

    private Event(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
