import com.sun.org.apache.xpath.internal.operations.Equals;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.internal.verification.AtLeast;
import org.testng.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.zip.InflaterInputStream;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class CounterTesting {
    private ArrayList<String> a;
    private String[] arr = new String[]{"nice", "nice", "nice", "wow", "lul"};
    public CounterTesting(){
        a = new ArrayList<>();
        for(String s:arr){
            a.add(s);
        }
    }
    @Test
    public void correctEntriesAmount(){
        Counter c = new Counter(a);
        MatcherAssert.assertThat(c.wordhm.get("nice"),is(equalTo(3)));
    }
    @Spy
    private TextSplitter tsSpy = Mockito.spy(new TextSplitter());
    @Test
    public void counterTestWithSpy() throws FileNotFoundException {
        tsSpy.setText("nice nice lul wow");
        ArrayList<String> list = tsSpy.splitText();
        Mockito.verify(tsSpy,Mockito.atMost(4)).flagAdd();
        Counter c = new  Counter(list);
        Assert.assertEquals(c.wordhm.get("nice"), new Integer(2));
        Mockito.verify(tsSpy , Mockito.atMost(1)).setText(Mockito.anyString());

    }
    @Mock
    private TextSplitter tsMock = Mockito.mock(TextSplitter.class);
    @Test
    public void counterTestWithMock() throws FileNotFoundException {
        Mockito.when(tsMock.getText(Mockito.anyObject())).thenReturn("nice nice nice lul wow");
        Mockito.when(tsMock.getText(null)).thenThrow(new NullPointerException());

        tsMock.getText(new FileInputStream(new File("test.txt")));

        Mockito.when(tsMock.splitText()).thenReturn(a);

        Assert.assertEquals((new Counter(tsMock.splitText())).wordhm.get("nice") ,new Integer(3) );


        Mockito.verify(tsMock, Mockito.atLeast(1)).splitText();


    }



}
