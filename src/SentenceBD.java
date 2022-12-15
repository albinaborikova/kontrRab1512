import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.io.*;

public class SentenceBD {
    List<Tokens> SentensList;
    
    String readFile(){
        File file = new File("input.txt");
        String input="";
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            String line;
            int i=0;
            while ((line = br.readLine()) != null) {
               input+=line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input ;
    }

    SentenceBD(String input)
    {
        String delimiters1 = "!.";
        String[] inputSplitted = input.split(delimiters1);
        ArrayList<String> tmp = new ArrayList<>();
        tmp.addAll(Arrays.asList(inputSplitted));
        for( String a : tmp) {
            Tokens tmp1= new Tokens(a);
            SentensList.add(tmp1);
        }
    }

    public void writeFile(String data, String name){
        OutputStream os = null;
        try {
            os = new FileOutputStream(new File(name));
            os.write(data.getBytes(), 0, data.length());
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static int[] findBigDigits(ArrayList <Tokens> list) {
        int[] result = new int[];
        for (Tokens a : list) {
            for(String b: a.Leks) {
                int length = b.length(), count = 0;
                char[] data = b.toCharArray();
                for (int i = 0; i < data.length; ++i) {
                    //За большие числа возьмем числа больше или равные 100
                    if (Character.isDigit(data[i]) && Character.getNumericValue(data[i]) >= 100) {
                        result[count++] = Integer.parseInt(Character.toString(data[i]));
                    }
                }
            }
        }
        return result;
    }

    ArrayList deleteSubstring(ArrayList <Tokens> list) {
        ArrayList<String> newList=new ArrayList<>();
        for (Tokens a : list) {
            int length = a.toString().length(), count = 0;
            char[] data = a.toString().toCharArray();
            int[] result = new int[length];
            for (int i = 0; i < data.length; ++i) {
                if (Character.isDigit(data[i])) {
                    result[count++] = Integer.parseInt(Character.toString(data[i]));
                }
            }

            for (int i = 0; i <= count; i++) {
                int n1 = a.toString().indexOf((char) result[i]);
                int n2 = a.toString().lastIndexOf((char) result[i]);
                if (n1 != n2 && n1 != -1) {
                    String delete = a.toString().substring(n1, n2);
                    String tmp;
                    tmp= a.toString().replace(delete, "---");
                    newList.add(tmp);
                }
            }
        }
        return newList;
    }

    public int compareTo(SentenceBD cont){
        return SentensList.get(0).Leks.get(0).compareTo(cont.SentensList.get(0).Leks.get(0));
    }
    static class SentenceComparator implements Comparator<SentenceBD> {

        public int compare(SentenceBD sentence1, SentenceBD sentence2)
        {
            return sentence1.SentensList.get(0).Leks.get(0).compareTo(sentence2.SentensList.get(0).Leks.get(0));
        }
    }



    
}
