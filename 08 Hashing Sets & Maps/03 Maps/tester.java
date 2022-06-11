import java.util.*;
import java.io.*;

public class tester
{
   public static void main(String[] args)
   {
      Map<String, String> h = new HashMap<String, String>();
      h.put("sad", "Blue");
      h.put("ocean", "Blue");
      h.put("earth", "Brown");
      h.put("barnie", "Purple");
      h.put("leaf", "Green");
      h.put("frog", "Green");
      h.put("jeans", "Blue");
      
      Map<String, Integer> returned = valFreqs(h);
   }
   
   public static Map<String, Integer> valFreqs(Map<String, String> h)

{

    Map<String, Integer> toRet = new HashMap<String, Integer>();

    Iterator<String> it = h.keySet().iterator();



    while(it.hasNext())

    {

        String key = it.next();

        String value = h.get(key);



        if(!toRet.containsKey(value))

            toRet.put(value, 1);



        else

        {

            int val = toRet.get(value);

            toRet.put(value, val + 1);

        }

    }



    return toRet;

}
}