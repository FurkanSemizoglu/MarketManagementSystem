import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class Main {
    public static void main(String[] args) throws IOException {
       File file = new File( "./supermarketData.txt");

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

        
        HashTable2 Hash = new HashTable2();
     //   linearHash.put(marketCode("11c34489-f95a-45ec-a833-8a329e4d1710"), "adam");
        
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("./supermarketData.txt"));
        String line = reader.readLine();
        //linearHash.put(5, "mal");
        String[] arr;
       // line = reader.readLine();
        
        ProductList product = null ;
        while(line != null) {
        	// check line nullability
        	  String[] data = line.split(",");
        	  if (data.length < 4 ) break;
        	  Transaction value = new Transaction(data[1],data[2],data[3]);
        	  
        	  /*
        	  if(product.getName() != data[1])
        	      product = new ProductList(data[1]);
        	  else {
        		  
        	  }
        	  */
        	  
        	  product = new ProductList(data[1],data[2], data[3]);
        	//  product.addProduct(data[2], data[3]);
        	  
        	  Hash.put(data[0], product);
        	  
        	  Hash.getValue(data[0]);
        	 
        	  linearHash.put(data[0], value);
        	 // System.out.println(linearHash.getValue(data[0])); 
        //	  linearHash.getValue(data[0]);
        	  line = reader.readLine();
		}
        
      
 
        
        /*
        for (int i = 0; i < 1000; i++) {
        	System.out.println(marketCode(line));
        	linearHash.put(line, "adam");
        	line = reader.readLine();
		}
		*/
        
        /*
        System.out.println(line);
        System.out.println("Hello world!");
        System.out.println((int) '0');
        System.out.println(" ");
        System.out.println(marketCode("11c34489-f95a-45ec-a833-8a329e4d1710"));
        */
        
        
        Hash.printHashTable();
  //      linearHash.printHashTable();
        System.out.println(" ");
        
        Hash.getValue("11c34489-f95a-45ec-a833-8a329e4d1710");
       // linearHash.getValue("11c34489-f95a-45ec-a833-8a329e4d1710");
 //       linearHash.getValue("f13989a8-36bd-48a8-8ef7-2ff0108a1e71");
 //       linearHash.getValue("9b9bcbf5-cf08-49de-b9dc-de142a4d8bb8");
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