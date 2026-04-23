import lexer.*;
import java.io.*;

public class LexerTest {

    public static void main(String[] args) throws IOException {

        Lexer lexer = new Lexer();
        Token token;

        while ((token = lexer.scan()) != null) {

            String type = "";
            String value = "";

            if (token instanceof Keyword) {
                Keyword k = (Keyword) token;

                if (token.tag == Tag.ID) {
                    type = "IDENTIFIER";
                    value = k.lexeme;
                } else {
                    type = "KEYWORD";
                    value = k.lexeme;
                }
            }
            else if (token instanceof Num) {
                type = "INTEGER";
                value = String.valueOf(((Num) token).value);
            }
            else if (token instanceof Real) {
                type = "FLOAT";
                value = String.valueOf(((Real) token).value);
            }
            else if (token instanceof Str) {
                type = "STRING";
                value = "\"" + ((Str) token).value + "\"";
            }
            else if (token instanceof CharTok) {
                type = "CHAR";
                value = "'" + ((CharTok) token).value + "'";
            }
            else {
                // operators and separators
                type = "SYMBOL";
                value = Character.toString((char) token.tag);
            }

            System.out.printf("%-12s: %s%n", type, value);
        }
    }
}