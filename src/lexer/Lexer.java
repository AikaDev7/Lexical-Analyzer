package lexer;

import java.io.IOException;
import java.util.HashMap;

public class Lexer {

    public Lexer() {
        reserveKeywords();
    }

    private HashMap<String, Keyword> words = new HashMap<>();
    private char peek = ' ';
    private static int line = 1;

    private void reserve(Keyword kword) {
        words.put(kword.lexeme, kword);
    }

    private void reserveKeywords() {
        reserve(Keyword.FALSE);
        reserve(Keyword.TRUE);

        reserve(Keyword.DO);
        reserve(Keyword.WHILE);
        reserve(Keyword.IF);
        reserve(Keyword.ELSE);
        reserve(Keyword.BREAK);

        reserve(Keyword.PUBLIC);
        reserve(Keyword.CLASS);
        reserve(Keyword.STATIC);
        reserve(Keyword.VOID);
        reserve(Keyword.STRING_TYPE);

        reserve(Keyword.INT);
        reserve(Keyword.FLOAT);
        reserve(Keyword.CHAR);
        reserve(Keyword.BOOLEAN);
    }

    private String input = """
    public class JavaExample {
      public static void main(String[] args) {
        int num1 = 5, num2 = 15, sum;
        sum = num1 + num2;
        System.out.println("Sum of " + num1 + " and " + num2 + " is: " + sum);
      }
    }
    """;

    private int index = 0;

    private void read() {
        if (index < input.length()) {
            peek = input.charAt(index++);
        } else {
            peek = (char) -1;
        }
    }

    private boolean read(char ch) throws IOException{
        read();
        if (peek != ch) {
            return false;
        }
        peek = ' ';
        return true;
    }

    public Token scan() throws IOException {
        for (;; read()) {
            if (peek == ' ' || peek == '\t') {
            } else if (peek == '\n') {
                line = line + 1;
            } else {
                break;
            }
        }

        switch (peek) {
            case '&':
                if (read('&')) {
                    return Keyword.AND;
                } else {
                    return new Token('&');
                }
            case '|':
                if (read('|')) {
                    return Keyword.OR;
                } else {
                    return new Token('|');
                }
            case '>':
                if (read('=')) {
                    return Keyword.G_EQUAL;
                } else {
                    return new Token('>');
                }
            case '<':
                if (read('=')) {
                    return Keyword.L_EQUAL;
                } else {
                    return new Token('<');
                }
            case '=':
                if (read('=')) {
                    return Keyword.EQUAL;
                } else {
                    return new Token('=');
                }
            case '!':
                if (read('=')) {
                    return Keyword.N_EQUAL;
                } else {
                    return new Token('!');
                }
        }

        if (Character.isDigit(peek)) {
            int v = 0;
            do {
                v = 10 * v + Character.digit(peek, 10);
                read();
            } while (Character.isDigit(peek));

            if (peek != '.') {
                return new Num(v);
            }
            float x = v, d = 10;
            for (;;) {
                read();
                if (!Character.isDigit(peek)) {
                    break;
                }
                x = x + Character.digit(peek, 10) / d;
                d = d * 10;
            }
            return new Real(x);
        }

        if (Character.isLetter((int) peek)) {
            StringBuilder letter = new StringBuilder();
            do {
                letter.append(peek);
                read();
            } while (Character.isLetterOrDigit((int) peek));
            String s = letter.toString();
            Keyword w = words.get(s);
            if (w != null) {
                return w;
            }
            w = new Keyword(s, Tag.ID);
            words.put(s, w);
            return  w;
        }

        if (peek == '"') {
            StringBuilder str = new StringBuilder();
            read();

            while (peek != '"' && peek != (char)-1) {
                str.append(peek);
                read();
            }

            read();
            return new Str(str.toString());
        }

        if (peek == '\'') {
            read();
            char c = peek;
            read(); // move past char
            read(); // skip closing

            return new CharTok(c);
        }

        if (peek == (char) -1) return null;

        Token t = new Token(peek);
        peek = ' ';
        return t;
    }
}
