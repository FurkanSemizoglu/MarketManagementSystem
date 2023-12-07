import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
    	
 
    	int consoleRun = 0;
    	boolean first = true;
    	Scanner scanner = new Scanner(System.in);
        while(true) {
        	
        	
        	if(first) {
        		start();
        		first = false;
        	}
        	
        	 int hashFunctionInput = 0;
        	
        		 
        	 
        	 do {
        		    try {
        		        System.out.print("Which Hash Function do you want to use SSF or PAF (1/2): ");
        		        hashFunctionInput = scanner.nextInt();
        		        if (hashFunctionInput != 1 && hashFunctionInput != 2) {
        		            System.out.println("Please give an exist value!");
        		        }
        		    } catch (InputMismatchException e) {
        		        System.out.println("Please enter a valid integer!");
        		        scanner.next(); // consume the invalid input
        		        hashFunctionInput = -1; // or some other invalid value
        		    }
        		} while (hashFunctionInput != 1 && hashFunctionInput != 2);
        	 
        	 int userInput = 0;

        	 do {
        	     try {
        	         System.out.print("Which HashTable do you want to use Linear or Double (1/2): ");
        	         userInput = scanner.nextInt();
        	         if (userInput != 1 && userInput != 2) {
        	             System.out.println("Please give an exist value!");
        	         }
        	     } catch (InputMismatchException e) {
        	         System.out.println("Please enter a valid integer!");
        	         scanner.next(); 
        	         userInput = -1; 
        	     }
        	 } while (userInput != 1 && userInput != 2);

        	 int loadFactor = 0;

        	 do {
        	     try {
        	         System.out.print("Which LoadFactor do you want to use 0.5 or 0.8 (1/2): ");
        	         loadFactor = scanner.nextInt();
        	         if (loadFactor != 1 && loadFactor != 2) {
        	             System.out.println("Please give an exist value!");
        	         }
        	     } catch (InputMismatchException e) {
        	         System.out.println("Please enter a valid integer!");
        	         scanner.next(); // consume the invalid input
        	         loadFactor = -1; // or some other invalid value
        	     }
        	 } while (loadFactor != 1 && loadFactor != 2);

        	 
        	 
        	 HashTable2 linearHash = new HashTable2(hashFunctionInput , loadFactor) ;
        	 HashTableDH2 doubleHash = new HashTableDH2(hashFunctionInput , loadFactor) ;

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

        		 do {
        		     try {
        		         System.out.println("Do you want to make a search, remove, customer check, or restart again? (search/remove/customer/restart - 1/2/3/4): ");
        		         valueSearch = scanner.nextInt();
        		         if (valueSearch != 1 && valueSearch != 2 && valueSearch != 3 && valueSearch != 4) {
        		             System.out.println("Please give an exist value!");
        		         }
        		     } catch (InputMismatchException e) {
        		         System.out.println("Please enter a valid integer!");
        		         scanner.next(); 
        		         valueSearch = -1;
        		     }
        		 } while (valueSearch != 1 && valueSearch != 2 && valueSearch != 3 && valueSearch != 4);


            	 while(true) {           		 
            	 
	            	 if(valueSearch == 1) {
	            		 if(hashSituation ==1) {
	            			 // linear
	            			 System.out.print("Give a value to check customer transactions (uuid): ");
	            			 value = scanner.next();
	            			 linearHash.getValue(value);
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
	            			 break;
	            		 }
	            		 
	            	 }
	            	// else break;
	            	 else if(valueSearch == 3){
	            		 
	            		 if(hashSituation == 1) {
	            			 // linear
	            			 linearHashFind(linearHash);	            		
	            			 break;
	            		 }
	            		 else if (hashSituation == 2) {
	            			 doubleHashFind(doubleHash);         			
	            			 break;
	            		 }
	            		
	            		 
	            	 }
	            	 else break;
	            	 
	             }
            	 
            	 if(valueSearch == 4) break;
        	 }       	 
        	
    
        	 clearScreen(); 
        }

    }
    
    
 
    public final static void clearScreen()
    {
    	for (int i = 0; i < 50; ++i) System.out.println();
    }
    
    public static void doubleHashFind(HashTableDH2 doubleHash) {
        LocalTime startTime = LocalTime.now();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./customer_1K.txt"));
            String line = reader.readLine();

            while (line != null) {
                // to escape from customerID first line
                if (line.length() > 33) {
                    doubleHash.find(line);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error during file I/O: " + e.getMessage());
        }

        LocalTime finalTime = LocalTime.now();
        Duration fark = Duration.between(startTime, finalTime);
        System.out.println("fark  " + fark);
    }

    public static void linearHashFind(HashTable2 linearHash) {
        LocalTime startTime = LocalTime.now();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("./customer_1K.txt"));
            String line = reader.readLine();

            while (line != null) {
                // to escape from customerID first line
                if (line.length() > 33) {
                    linearHash.find(line);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error during file I/O: " + e.getMessage());          
        }

        LocalTime finalTime = LocalTime.now();
        Duration fark = Duration.between(startTime, finalTime);
        System.out.println("fark  " + fark);
    }

    
    public static HashTable2 linearHash(int hashFunctionInput, int loadFactor) {
        HashTable2 Hash = new HashTable2(hashFunctionInput, loadFactor);
        LocalTime startTime = LocalTime.now();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("./supermarketData.txt"));
            String line = reader.readLine();

            ProductList product = null;
            while (line != null) {
                String[] data = line.split(",");
                if (data.length < 4) break;

                product = new ProductList(data[1], data[2], data[3]);

                // to escape from customerID first line
                if (data[0].length() > 33) {
                    Hash.put(data[0], product);
                }

                line = reader.readLine();
            }

            Hash.printHashTable();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing file: " + e.getMessage());
            }
        }

        LocalTime finalTime = LocalTime.now();
        Duration fark = Duration.between(startTime, finalTime);
        System.out.println("Geçen süre :   " + fark);

        return Hash;
    }

    
    
    public static HashTableDH2 doubleHash(int hashFunctionInput, int loadFactor) {
        HashTableDH2 HashDH = new HashTableDH2(hashFunctionInput, loadFactor);
        LocalTime startTime = LocalTime.now();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("./supermarketData.txt"));
            String line = reader.readLine();

            ProductList product = null;
            while (line != null) {
                String[] data = line.split(",");
                if (data.length < 4) break;

                product = new ProductList(data[1], data[2], data[3]);

                // to escape from customerID first line
                if (data[0].length() > 33) {
                    HashDH.put(data[0], product);
                }

                line = reader.readLine();
            }

            HashDH.printHashTable();
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing file: " + e.getMessage());
            }
        }

        LocalTime finalTime = LocalTime.now();
        Duration fark = Duration.between(startTime, finalTime);
        System.out.println("Geçen süre :   " + fark);

        return HashDH;
    }

    
    public static void start() throws IOException {
        HashTable2 linearHash = null;
        HashTableDH2 doubleHash = null;

        linearHash = linearHash(1, 1);
		doubleHash = doubleHash(1, 1);
    }

    

    
  
    
  
}