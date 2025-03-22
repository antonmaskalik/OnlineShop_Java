package enums;

public enum WaitTimeout {
    ELEMENT(5),
    API_REQUEST(15);

    private final int seconds;

    WaitTimeout(int seconds) {
        this.seconds = seconds;
    }

    public int getSeconds() {
        return seconds;
    }
}
