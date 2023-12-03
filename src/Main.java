import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
       File file = new File( "./TR_SuperLeague_19_20.txt");

      //  Scanner sc = new Scanner(file);
      //  System.out.println("sda");

        String[] examples =  {
                "11c34489-f95a-45ec-a833-8a329e4d1710",
                "23e948c9-0305-4934-9bf1-d88a37e712e8",
                "11c34489-f95a-45ec-a833-8a329e4d1710",
                "a4cd48db-2983-4ddb-b81d-13577f39218c",
                "11c34489-f95a-45ec-a833-8a329e4d1710"
        };

        HashTable linearHash = new HashTable();

        BufferedReader reader;
        reader = new BufferedReader(new FileReader("./10_instance.txt.txt"));
        String line = reader.readLine();
        System.out.println("Hello world!");
        System.out.println((int) '0');
        System.out.println(" ");
        System.out.println(marketCode("11c34489-f95a-45ec-a833-8a329e4d1710"));
    }



    private static int marketCode(String code){

        char[] chars = new char[code.length()];

        int resultCode = 0 ;
        for (int i = 0; i < code.length(); i++) {
               chars[i] =  code.charAt(i);
               
               int letter = 0; 
               if( code.charAt(i) != '-'  && code.charAt(i) != '0' ) {
            	   if((int) code.charAt(i) >= 49 && (int) code.charAt(i) < 58)
            		   letter = (int)code.charAt(i) - 48;
            	   else letter = (int) code.charAt(i)- 96;
               }
             
               resultCode += letter;
             
        }
        
        return resultCode;

    }
}