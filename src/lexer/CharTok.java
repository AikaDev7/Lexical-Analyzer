package lexer;

public class CharTok extends Token {

    public final char value;

    public CharTok(char value) {
        super(Tag.CHAR);
        this.value = value;
    }

    public String toString() {
        return Character.toString(value);
    }
}