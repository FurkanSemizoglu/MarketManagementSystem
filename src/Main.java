import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
    	
    	/*
        File file = new File( "./supermarketData.txt");
       
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("./supermarketData.txt"));
        String line = reader.readLine();
    
        String[] arr;
      
        LocalTime startTime = LocalTime.now();
        HashTableDH2 HashDH = new HashTableDH2(1 ,1 );
        System.out.println(startTime);
       // System.out.println(hashCode2("5003c4af-98a1-4d90-af0e-10d54ddd2a10"));
        ProductList product = null ;
        
        /*
        while(line != null) {
        	
        	  String[] data = line.split(",");
        	  if (data.length < 4 ) break;
        	 
        	  product = new ProductList(data[1],data[2], data[3]);
        
        	  HashDH.put(data[0], product);
        	  
   
        	  line = reader.readLine();
		}
		*/
        /*
        
        LocalTime finalTime = LocalTime.now();
     //   HashDH.printHashTable();
       
        System.out.println(" ");
     // long finalTime = System.currentTimeMillis();
        System.out.println("final"  + finalTime);
        Duration fark = Duration.between(startTime, finalTime);
        System.out.println("fark  "  + fark);
       // System.out.println("toplam süre" + (finalTime - startTime));
        
        HashDH.printHashTable();
        
        HashDH.getValue("020e5e7e-5702-4cf6-9623-ff0e1331891d");
        Scanner scanner = new Scanner(System.in);
       
      
       

        // Read the user's input
       
        */
    	Scanner scanner = new Scanner(System.in);
        while(true) {
        	
        	 int hashFunctionInput = 0;
        	 
        	 do{
        		 System.out.print("Which Hash Function do you want to use SSF or PAF (1/2): ");
        		 hashFunctionInput = scanner.nextInt();
        		 if(hashFunctionInput != 1 && hashFunctionInput != 2 ) {
        			 System.out.println("Please give an exist value ! ");
        		 }
        		 
        	 }while(hashFunctionInput != 1 && hashFunctionInput != 2);
        	 
        	 int userInput = 0;
        	 
        	 do{
        		 System.out.print("Which HashTable do you want to use Linear or Double (1/2): ");
        		 userInput = scanner.nextInt();
        		 if(userInput != 1 && userInput != 2 ) {
        			 System.out.println("Please give an exist value ! ");
        		 }
        		 
        	 }while(userInput != 1 && userInput != 2);
        	 
        	 int loadFactor = 0;
        	 do{
        		 System.out.print("Which LoadFactor do you want to use 0.5 or 0.8 (1/2): ");
        		 loadFactor = scanner.nextInt();
        		 if(loadFactor != 1 && loadFactor != 2 ) {
        			 System.out.println("Please give an exist value ! ");
        		 }
        		 
        	 }while(loadFactor != 1 && loadFactor != 2);
        	 HashTable2 linearHash = null ;
        	 HashTableDH2 doubleHash = null ;
        	 int hashSituation = 0; // checking for linear or double 
        	 
        	 switch (userInput) {
				case 1: {
					linearHash = linearHash(hashFunctionInput , loadFactor);
					hashSituation = 1;
					break;
				}
				case 2 : {
					doubleHash = doubleHash(hashFunctionInput , loadFactor);
					hashSituation = 2;
					break;
				}
        	 }
        	 
        	 int valueSearch = 0;
        	 
        	 String value = "";
        	 while(true) {
            	 do{
            		 System.out.println("Do you want to make a search , remove or restart again ? (search/remove/restart - 1/2/3): ");
            		 valueSearch = scanner.nextInt();
            		 if(valueSearch != 1 && valueSearch != 2 && valueSearch != 3) {
            			 System.out.println("Please give an exist value ! ");
            		 }
            		 
            	 }while(valueSearch != 1 && valueSearch != 2 && valueSearch != 3);

            	 while(true) {           		 
            	 
	            	 if(valueSearch == 1) {
	            		 if(hashSituation ==1) {
	            			 // linear
	            			 System.out.print("Give a value to check customer transactions (uuid): ");
	            			 value = scanner.next();
	            			 linearHash.getValue(value);
	            		//	 linearHash.getValue("5142adf6-d46d-4559-9b5c-acd43f60cdca");
	            			 break;
	            		 }
	            		 else if (hashSituation == 2) {
	            			 System.out.print("Give a value to check customer transactions (uuid): ");
	            			 value = scanner.next();
	            			
	            			 doubleHash.getValue(value);
	            			 break;
	            		 }
	            	 }
	            	 else if(valueSearch == 2) {
	            		 
	            		 if(hashSituation ==1) {
	            			 // linear
	            			 System.out.print("Give a value to remove customer transactions (uuid): ");
	            			 value = scanner.next();
	            			 linearHash.remove(value);
	            		
	            			 break;
	            		 }
	            		 else if (hashSituation == 2) {
	            			 System.out.print("Give a value to remove customer transactions (uuid): ");
	            			 value = scanner.next();
	            			 doubleHash.remove(value);	
	            			 doubleHash.getValue(value);
	            			 break;
	            		 }
	            		 
	            	 }
	            	 else break;
	            	 
	             }
            	 
            	 if(valueSearch == 3) break;
        	 }
        	 
        	
    
        	 clearScreen(); 
        }

    }
   
    public final static void clearScreen()
    {
    	for (int i = 0; i < 50; ++i) System.out.println();
    }
    
    public static HashTable2 linearHash(int hashFunctionInput , int loadFactor) throws IOException {
    	HashTable2 Hash = new HashTable2(hashFunctionInput ,loadFactor);
    	LocalTime startTime = LocalTime.now();
        
        BufferedReader reader;
        reader = new BufferedReader(new FileReader("./supermarketData.txt"));
        String line = reader.readLine();
    

      
        
        ProductList product = null ;
        while(line != null) {
        	
        	  String[] data = line.split(",");
        	  if (data.length < 4 ) break;
        	 
        	  
        	  // to escape from customerID first line
        	  if(data[0].length() > 33 ) {
        		  product = new ProductList(data[1],data[2], data[3]);
            	  
            	  Hash.put(data[0], product);
        	  }
        	
     

        	  line = reader.readLine();
		}
        Hash.printHashTable();
        LocalTime finalTime = LocalTime.now();
        
        Duration fark = Duration.between(startTime, finalTime);
        System.out.println("fark  "  + fark);
        
        
        return Hash;

  }
  
    
    
    public static HashTableDH2 doubleHash(int hashFunctionInput ,int loadFactor) throws IOException {
    	  HashTableDH2 HashDH = new HashTableDH2(hashFunctionInput ,loadFactor );
    	  LocalTime startTime = LocalTime.now();
          BufferedReader reader;
          reader = new BufferedReader(new FileReader("./supermarketData.txt"));
          String line = reader.readLine();
          
          ProductList product = null ;
          while(line != null) {
          	
          	  String[] data = line.split(",");
          	  if (data.length < 4 ) break;
          	 
          	  product = new ProductList(data[1],data[2], data[3]);
          	  
          	  HashDH.put(data[0], product);;
       

          	  line = reader.readLine();
  		}
          HashDH.printHashTable();
          LocalTime finalTime = LocalTime.now();
          
          Duration fark = Duration.between(startTime, finalTime);
          System.out.println("fark  "  + fark);
          return HashDH;
    }
    
  
}