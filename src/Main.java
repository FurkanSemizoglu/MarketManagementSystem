import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
       File file = new File( "./supermarketData.txt");

      //  Scanner sc = new Scanner(file);
      //  System.out.println("sda");

       

        HashTable linearHash = new HashTable();

        
        HashTable2 Hash = new HashTable2();
   
        HashTableDH2 HashDH = new HashTableDH2();
        
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("./supermarketData.txt"));
        String line = reader.readLine();
    
        String[] arr;
      
       // System.out.println(hashCode2("5003c4af-98a1-4d90-af0e-10d54ddd2a10"));
        ProductList product = null ;
        while(line != null) {
        	
        	  String[] data = line.split(",");
        	  if (data.length < 4 ) break;
        	 
        	  product = new ProductList(data[1],data[2], data[3]);
        
        	  HashDH.put(data[0], product);
        	  
        	  HashDH.getValue(data[0]);
        	  
        	  
        	 
        //	  Hash.put(data[0], product);
        	  
       // 	  Hash.getValue(data[0]);
        	 
       // 	  linearHash.put(data[0], value);

        	  line = reader.readLine();
		}
        
        
        HashDH.getValue("11c34489-f95a-45ec-a833-8a329e4d1710");
        HashDH.printHashTable();

        System.out.println(" ");
      
       
        Scanner scanner = new Scanner(System.in);

      
       

        // Read the user's input
       
        
        while(true) {
        	 int userInput = 0;
        	 do{
        		 System.out.print("Which HashTable do you want to use Linear or Double (1/2): ");
        		 userInput = scanner.nextInt();
        		 if(userInput != 1 && userInput != 2 ) {
        			 System.out.println("Please give an exist value ! ");
        		 }
        		 
        	 }while(userInput != 1 && userInput != 2);
        	 
        	 
        	 switch (userInput) {
				case 1: {
					linearHash();
					
					return;
				}
				case 2 : {
					doubleHash();
					return;
				}
        	 }
        	 
        	
        }

    }
    
    public static void linearHash() throws IOException {
    	HashTable2 Hash = new HashTable2();
        
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("./supermarketData.txt"));
        String line = reader.readLine();
    
        String[] arr;
      
        
        ProductList product = null ;
        while(line != null) {
        	
        	  String[] data = line.split(",");
        	  if (data.length < 4 ) break;
        	 
        	  product = new ProductList(data[1],data[2], data[3]);
        	  
        	  Hash.put(data[0], product);;
     

        	  line = reader.readLine();
		}
        Hash.printHashTable();
        
        Hash.getValue("11c34489-f95a-45ec-a833-8a329e4d1710");

  }
  
    
    
    public static void doubleHash() throws IOException {
    	  HashTableDH2 HashDH = new HashTableDH2();
          
          BufferedReader reader;
          reader = new BufferedReader(new FileReader("./supermarketData.txt"));
          String line = reader.readLine();
      
          String[] arr;
        
          
          ProductList product = null ;
          while(line != null) {
          	
          	  String[] data = line.split(",");
          	  if (data.length < 4 ) break;
          	 
          	  product = new ProductList(data[1],data[2], data[3]);
          	  
          	  HashDH.put(data[0], product);;
       

          	  line = reader.readLine();
  		}
          HashDH.printHashTable();

    }
    
    public static long hashCode2(String code) {
        String tmpCode = code;
        char[] chars = new char[tmpCode.length()];

        int lenOfKey = tmpCode.length();

        long resultCode = 0;
        for (int i = 0; i < tmpCode.length(); i++) {
            chars[i] = tmpCode.charAt(i);

            int letter = 0;
            if (tmpCode.charAt(i) != '-' && tmpCode.charAt(i) != '0') {

                // sayı olma durumu
                if ((int) tmpCode.charAt(i) >= 49 && (int) tmpCode.charAt(i) < 58)
                    letter = ((int) tmpCode.charAt(i) - 48);
                else
                    letter =  ((int) tmpCode.charAt(i) - 96); // situation of being letter
            }
           
            System.out.println(letter);
          
            lenOfKey -= 1;
            resultCode += (long) (letter * Math.pow(7, lenOfKey));          
            System.out.println(resultCode);
        }

        return resultCode;
    }

    public static byte hashCode3(String code) {
        String tmpCode = (String) code;
        char[] chars = new char[tmpCode.length()];

        int lenOfKey = tmpCode.length();
        byte resultCode = 0;

        for (int i = 0; i < tmpCode.length(); i++) {
            chars[i] = tmpCode.charAt(i);

            byte letter = 0;
            if (tmpCode.charAt(i) != '-' && tmpCode.charAt(i) != '0') {

                // sayı olma durumu
                if ((int) tmpCode.charAt(i) >= 49 && (int) tmpCode.charAt(i) < 58)
                    letter = (byte) ((int) tmpCode.charAt(i) - 48);
                else
                    letter = (byte) ((int) tmpCode.charAt(i) - 96); // situation of being letter
            }
            System.out.println(letter);
            lenOfKey -= 1;

            float powResult = (float) Math.pow(33, lenOfKey);
            resultCode += (byte) (letter * powResult);
        }

        return resultCode;
    }


    public static int hashCode (String code) {
  	  String tmpCode = (String)code;
    	  char[] chars = new char[tmpCode.length()];

    	  
    	  int lenOfKey = tmpCode.length();    	  
          int resultCode = 0 ;
          
          for (int i = 0; i <  tmpCode.length(); i++) {
                 chars[i] =  tmpCode.charAt(i);
                 
                 int letter = 0; 
                 if( tmpCode.charAt(i) != '-'  && tmpCode.charAt(i) != '0' ) {
                	 
                	 // sayı olma durumu
              	   if((int)tmpCode.charAt(i) >= 49 && (int) tmpCode.charAt(i) < 58)
              		   letter = (int)tmpCode.charAt(i) - 48;
              	   else letter = (int) tmpCode.charAt(i)- 96; // situation of being letter
                 }
                 System.out.println(letter);
                 lenOfKey -= 1;
                 
                 float powResult=(float) Math.pow(33, lenOfKey) ;
                 resultCode += (letter * powResult );
                
          }
          
          return resultCode;
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