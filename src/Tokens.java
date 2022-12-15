import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Tokens {
    ArrayList<String> Leks;
    Tokens(String input)
    {
        String delimiters2 = ";_-";
        String[] inputSplitted = input.split(delimiters2);
        Leks=new ArrayList<>();
        Leks.addAll(Arrays.asList(inputSplitted));
    }

    @Override
    public String toString() {
        String result="";
        int count=1;
        for(String a : Leks)
        {
            result=result+" leks number:" + count + " " +a;
        }
        return result;
    }
}

