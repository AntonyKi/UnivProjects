import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class CounterTesting_testng {
    private ArrayList<String> a;
    private String[] arr = new String[]{"nice", "nice", "nice", "wow", "lul"};
    private Counter c;
    public CounterTesting_testng(){
        a = new ArrayList<>();
        for(String s:arr){
            a.add(s);
        }
        c = new Counter(a);
    }
    @Test(groups = {"operTests"})
    public void testEntriesAmount(){
        Assert.assertEquals(c.wordhm.get("nice"), new Integer(3));
    }
    @Test(groups = {"ioTests"})
    public  void testPrinting(){
        String val = c.printEntries();
        String expected ="lul 1\nnice 3\nwow 1\n";
        Assert.assertEquals(val, expected);
    }

}
