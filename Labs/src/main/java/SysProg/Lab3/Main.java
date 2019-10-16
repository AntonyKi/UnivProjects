package SysProg.Lab3;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        String inpPath = "C:\\Users\\Toxa\\Desktop\\Labs\\src\\main\\java\\SysProg\\Lab3\\input.txt";
        String inp = new String(Files.readAllBytes(Paths.get(inpPath)), StandardCharsets.UTF_8);
        Highlighter h = new Highlighter();

        String hltedText = h.highlight(inp);
        System.out.println(hltedText);
    }
}
