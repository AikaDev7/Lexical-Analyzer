package lexer;

public class Str extends Token {

    public final String value;

    public Str(String value) {
        super(Tag.STRING);
        this.value = value;
    }

    public String toString() {
        return value;
    }
}