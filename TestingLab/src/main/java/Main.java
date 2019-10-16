

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.*;

public class Main{
//         C:\Users\Toxa\Desktop\nicedoc.txt

    public static void main(String[] args) throws IOException {
        System.out.print("Type a path to a text file");
        Scanner inp = new Scanner(System.in);
        String filePath =inp.next();
        File file = new File(filePath);
        InputStream instr = new FileInputStream(file);
        TextSplitter ts = new TextSplitter();
        ts.getText(instr);
        ts.setSplitIdentifiers(" |[\\\r\\\n]|\\/|\\\\");
        ArrayList<String> arrayList = ts.splitText();
        Counter c = new Counter(arrayList);
        c.printEntries();
    }
}
