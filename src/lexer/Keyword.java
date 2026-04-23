package lexer;

public class Keyword extends Token{

    public final String lexeme;

    public Keyword(String lexeme, int tag) {
        super(tag);
        this.lexeme = lexeme;
    }

    public static final Keyword AND = new Keyword("&&", Tag.AND),
            OR = new Keyword("||", Tag.OR),
            EQUAL = new Keyword("==", Tag.EQ),
            N_EQUAL = new Keyword("!=", Tag.NE),
            L_EQUAL = new Keyword("<=", Tag.LE),
            G_EQUAL = new Keyword(">=", Tag.GE),
            TRUE = new Keyword("true", Tag.TRUE),
            FALSE = new Keyword("false", Tag.FALSE),
            IF = new Keyword("if", Tag.IF),
            ELSE = new Keyword("else", Tag.ELSE),
            BREAK = new Keyword("break", Tag.BREAK),
            DO = new Keyword("do", Tag.DO),
            WHILE = new Keyword("while", Tag.WHILE),
            PUBLIC = new Keyword("public", Tag.BASIC_TYPE),
            CLASS = new Keyword("class", Tag.BASIC_TYPE),
            STATIC = new Keyword("static", Tag.BASIC_TYPE),
            VOID = new Keyword("void", Tag.BASIC_TYPE),
            STRING_TYPE = new Keyword("String", Tag.BASIC_TYPE),
            INT = new Keyword("int", Tag.BASIC_TYPE),
            FLOAT = new Keyword("float", Tag.BASIC_TYPE),
            CHAR = new Keyword("char", Tag.BASIC_TYPE),
            BOOLEAN = new Keyword("boolean", Tag.BASIC_TYPE);
}