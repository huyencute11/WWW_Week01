package iuh.www.week1.week01_lab_tranthiminhhuyen_20105231.models;

public enum Grant {
    DISABLE("1"),
    ENABLE("0");

    private final String value;

    Grant(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
