
import org.testng.annotations.*;
import org.testng.Assert;

import java.io.*;
import java.util.Arrays;

public class TextSplitterTesting_testng {
    TextSplitter ts = new TextSplitter();
    @BeforeClass
    public static void setup() throws IOException {
        try( BufferedWriter writer = new BufferedWriter(new FileWriter("test.txt")))
        {
            writer.write("One/ Two two threE");
        }
    }
    @AfterClass
    public static void clear() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("textfile.txt"));
        writer.write("");

    }
    @DataProvider(name = "dp1")
    public static Object[][] dataForTest(){
        return new Object[][]{
                {"welldone btw ", "welldone", "btw",5},
                {"not so looooong", "not", "so",2},
                {"nice nice nice awful awful", "awful", "nice",4}
        };
    }
    @Test(dataProvider = "dp1",groups = {"operTests"})
    public void lengthLimit( String testText, String longWord, String shortWord ,Integer limit){
        ts.setWordLengthLimit(limit);
        ts.setText(testText);
        assert(!ts.splitText().contains(longWord)&&ts.splitText().contains(shortWord));
    }
    @Test(groups = {"operTests"})
    public void splitCorrect(){
        TextSplitter ts = new TextSplitter();
        ts.setSplitIdentifiers("]|\\)");
        ts.setText("f](lame)sat");
        assert(ts.splitText().contains("f")&&ts.splitText().contains("(lame")&&ts.splitText().contains("sat"));
    }
    @Test(groups = {"ioTests"})
    public void correctFileReading(){
        TextSplitter ts = new TextSplitter();
        File file = new File("test.txt");
        try {
            InputStream instr = new FileInputStream(file);
            Assert.assertEquals(ts.getText(instr),"One/ Two two threE");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

}
