import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

public class TextSplitter {
    private String text;
    private String splitIdentifiers = " |[\\\r\\\n]|\\/|\\\\";
    private int wordLengthLimit = 30;


    public String getText(InputStream is) {
        StringBuilder sb = new StringBuilder(512);
        try {
            Reader r = new InputStreamReader(is, "UTF-8");
            int c = 0;
            while ((c = r.read()) != -1) {
                sb.append((char) c);
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        text = sb.toString();
        return sb.toString();
    }
    public void flagAdd(){

    }
    public ArrayList<String> splitText(){
        ArrayList<String> words = new ArrayList<>();
        String[] splited = text.split(splitIdentifiers);
        for(String s : splited){
            if(s.length()>0&&s.length()<=wordLengthLimit){
                words.add(s);
                flagAdd();
            }
        }
        return words;
    }



    public void setWordLengthLimit(int wordLengthLimit) {
        this.wordLengthLimit = wordLengthLimit;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setSplitIdentifiers(String splitIdentifiers){
        this.splitIdentifiers = splitIdentifiers;
    }
}
