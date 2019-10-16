package SysProg.Lab3;

import java.util.regex.Pattern;
import com.google.common.base.Joiner;

public class Lexeme {
    private final Type type;
    private final int start;
    private final int end;

    public Lexeme(Type type, int start, int end) {
        this.type = type;
        this.start = start;
        this.end = end;
    }

    public Type getType() {
        return type;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public enum Type {
        SINGLE_COMMENT("#(.|\\n)*?[^\\\\]\\n"),
        COMMENT(Colours.GREY, SINGLE_COMMENT.pattern),

        STRING("\".*\""),
        CHAR("\'.*\'"),
        LITERALS(Colours.MAGENTA, STRING.pattern, CHAR.pattern),

        VARIABLE_VALUES("(NULL|Inf|NaN|NA|" +
                "NA_integer_|NA_real_|NA_complex_|NA_character_)"),
        LOGIC_VALUES("(TRUE|FALSE)"),
        RESERVED_VALUES(Colours.MAGENTA, VARIABLE_VALUES.pattern, LOGIC_VALUES.pattern ),

        FLOAT("(\\d+\\.\\d*|\\.\\d+|\\d+)([eE][+-]?\\d+)?"),
        INTEGER("\\d+[LlUu]*"),
        NUMBER(Colours.YELLOW, FLOAT.pattern, INTEGER.pattern),

        KEYWORD(
                Colours.CYAN,
                "(if|else|repeat|while|function|" +
                        "for|in|next|break)"),
        RESERVED_FUNCTION(Colours.BLUE, "(dnorm|pnorm|qnorm|rnorm|dbinom|pbinom|qbinom" +
                "|rbinom|dpois|ppois|qpois|rpois|dunif|punif|qunif|runif|mean|" +
                "sd|quantile|range|sum|diff|min|max|scale|seq|rep|cut|plot|sink|source|" +
                "library|print|paste|readline)"),

        OPERATOR(Colours.RED, "[~!%^&*+=|?:<>{}\\/-]"),
        FILLER(
                Colours.GREEN,
                ""),
        PUNCTUATION(Colours.WHITE, "[()\\[\\],.;]");
        private final Colours color;
        private final String pattern;

        Type(Colours color, String... patterns) {
            this.color = color;
            this.pattern = "(" + Joiner.on('|').join(patterns) + ")";
        }

        Type(String... patterns) {
            this(Colours.GREY, patterns);
        }

        public Colours getColor() {
            return color;
        }

        public Pattern compilePattern() {
            return Pattern.compile(pattern);
        }
    }
}
