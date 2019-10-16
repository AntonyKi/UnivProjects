import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Counter {
    public HashMap<String, Integer> wordhm = new HashMap<>();
    public Counter(ArrayList<String> a){
        for(String s: a){
            if(wordhm.containsKey(s)){
                wordhm.replace(s , wordhm.get(s)+1);
            }else wordhm.put(s , 1);
        }
    }

    public String printEntries() {
       StringBuilder builder = new StringBuilder("");
       for(Map.Entry<String, Integer> a : wordhm.entrySet()){
            builder.append(a.getKey() + " " + (a.getValue()).toString()+'\n');
       }
       return builder.toString();
    }

}
