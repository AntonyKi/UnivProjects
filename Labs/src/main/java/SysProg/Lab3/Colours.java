package SysProg.Lab3;

public enum Colours {
    WHITE("\u001B[30m"),
    RED("\u001b[31m"),
    GREEN("\u001B[32m"),
    BLUE("\u001B[34m"),
    MAGENTA("\u001B[35m"),
    CYAN("\u001B[36m"),
    GREY("\u001B[37m"),
    YELLOW("\u001B[33m");

    private String asciiCode;

    Colours(String asciiCode) {
        this.asciiCode = asciiCode;
    }

    public String getAsciiCode() {
        return asciiCode;
    }
}
