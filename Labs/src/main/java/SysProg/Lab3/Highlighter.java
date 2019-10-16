package SysProg.Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Highlighter {
    public static String highlight(String text) {
        List<Lexeme> lexemes = new ArrayList<>();

        lexemes.addAll(findLexemes(text, Lexeme.Type.COMMENT));
        lexemes.addAll(findLexemes(text, Lexeme.Type.RESERVED_VALUES));
        lexemes.addAll(findLexemes(text, Lexeme.Type.KEYWORD));
        lexemes.addAll(findLexemes(text, Lexeme.Type.RESERVED_FUNCTION));
        lexemes.addAll(findLexemes(text, Lexeme.Type.LITERALS));
        lexemes.addAll(findLexemes(text, Lexeme.Type.OPERATOR));
        lexemes.addAll(findLexemes(text, Lexeme.Type.NUMBER));
        lexemes.addAll(findLexemes(text, Lexeme.Type.PUNCTUATION));

        return highlightLexemes(text, lexemes);
    }

    private static List<Lexeme> findLexemes(String text, Lexeme.Type type) {
        Matcher m = type.compilePattern().matcher(text);
        List<Lexeme> lexemes = new ArrayList<>();
        String regex = "[A-Za-z]";
        while (m.find()) {
            if(type == Lexeme.Type.RESERVED_VALUES||type == Lexeme.Type.NUMBER
                    ||type == Lexeme.Type.KEYWORD||type == Lexeme.Type.RESERVED_FUNCTION){
                boolean req1, req2;
                if(m.start() !=0){
                    Matcher start = Pattern.compile(regex).matcher(Character.toString(text.charAt(m.start()-1)));
                    req1 = !start.matches();
                } else req1 = true;
                if(text.length()>m.end()){
                    Matcher end = Pattern.compile(regex).matcher(Character.toString(text.charAt(m.end())));
                    req2 = !end.matches();
                }else req2 = true;
                if(req1&&req2){
                    lexemes.add(new Lexeme(type, m.start(), m.end()));
                }

            }else lexemes.add(new Lexeme(type, m.start(), m.end()));
        }
        return lexemes;
    }

    private static List<Lexeme> findLexemesModified(String text, Lexeme.Type type) {
        Matcher m = type.compilePattern().matcher(text);
        List<Lexeme> lexemes = new ArrayList<>();
        while (m.find()) {
            if((text.charAt(m.start()-1)==' '||
                    text.charAt(m.start()-1)==')')&&
                    (text.charAt(m.end()+1)==' '||
                    text.charAt(m.end()+1)==')'))
            lexemes.add(new Lexeme(type, m.start(), m.end()));
        }
        return lexemes;
    }

    private static String highlightLexemes(String text, List<Lexeme> lexemes) {
        final String ANSI_RESET = Colours.GREEN.getAsciiCode();
        StringBuilder builder = new StringBuilder(text);
        lexemes.sort(
                (x, y) ->
                        x.getEnd() > y.getEnd() || x.getEnd() == y.getEnd() && x.getStart() < y.getStart()
                                ? -1
                                : 1);
        int l = text.length();
        for (Lexeme lexeme : lexemes) {
            if (lexeme.getEnd() <=l) {
                builder.insert(lexeme.getEnd(),ANSI_RESET);
                builder.insert(lexeme.getStart(), lexeme.getType().getColor().getAsciiCode());
                l = lexeme.getStart();
            }
        }
        builder.insert(0, ANSI_RESET);
        return builder.toString();
    }
}
/*if(text.charAt(m.start()-1)<65||
                        (text.charAt(m.start()-1)>90&&text.charAt(m.start()-1)<97)
                        ||text.charAt(m.start()-1)>122)
                    {
                        if(text.charAt(m.end()+1)<65||
                                (text.charAt(m.end()+1)>90&&text.charAt(m.end()+1)<97)
                                ||text.charAt(m.end()+1)>122)lexemes.add(new Lexeme(type, m.start(), m.end()));
                    }*/