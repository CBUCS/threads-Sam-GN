package Classes;

public class Country {
    private int USA;
    private int Canada;
    private int UK;
    private int Unknown;

    public Country(){}

    public int getCanada() {
        return Canada;
    }

    public int getUK() {
        return UK;
    }

    public int getUnknown() {
        return Unknown;
    }

    public int getUSA() {
        return USA;
    }

    public void setCanada(int canada) {
        Canada = canada;
    }

    public void setUK(int UK) {
        this.UK = UK;
    }

    public void setUnknown(int unknown) {
        Unknown = unknown;
    }

    public void setUSA(int USA) {
        this.USA = USA;
    }
}
