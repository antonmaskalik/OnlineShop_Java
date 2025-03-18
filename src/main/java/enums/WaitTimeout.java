package enums;

public enum WaitTimeout {
    ELEMENT(5),
    PAGE(10),
    LONG_PAGE(20);

    private final int seconds;

    WaitTimeout(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
