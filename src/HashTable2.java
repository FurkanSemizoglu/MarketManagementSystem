import java.util.ArrayList;

// this class is for linear probing
public class HashTable2<K,V> {

		private ArrayList<Product> products;
	
        private static int TABLE_SIZE = 1049;
        private static final int DEFAULT_CAPACITY = 16;
        private static double LOAD_FACTOR =  0.5;
        
        private int currentSize;
        private int customerFounded;
        private long collisionCount;
        private int hashFunctionInput;
        HashEntry<K,V>[] table;
        public HashTable2(int hashFunctionInput , int loadFactor) {
        	products = null;
            currentSize = 0;   
            collisionCount = 0;
            customerFounded =0;
            this.hashFunctionInput = hashFunctionInput;
            table = new HashEntry[TABLE_SIZE];
            
            if(loadFactor == 1) LOAD_FACTOR = 0.5;
            else LOAD_FACTOR = 0.8;
            
            for (int i = 0; i < TABLE_SIZE; i++)
                table[i] = null;
        }
  
        public int hashFunction(K key) {     	            
            if (hashCode(key) % TABLE_SIZE < 0)
            	return (int) ((hashCode(key) % TABLE_SIZE) + TABLE_SIZE);
            else
            	return  (int) (hashCode(key) % TABLE_SIZE);
        }
        
        public void getValue(K key) {

            int i = hashFunction(key);// Calculate hash value
            boolean isFound = false;
            while (table[i] != null) {
            	HashEntry<K, V> current = table[i];
            	if(table[i].getKey().equals((String)key)) {            	
            		System.out.println(((ProductList) current.getValue()).getProductsSize()+ " transactions found for  " + ((ProductList)current.getValue()).getName());       		        		
            		((ProductList)current.getValue()).displayProducts();
            		isFound = true;
            		return;
            	}           	           
                i = (i + 1) % TABLE_SIZE;
            }
            return ;      
        }
        
        public void put(K key, V value) {

        	checkCapacity();
        	
        	String name = ((ProductList)value).getName();
        	String date = ((ProductList)value).getDate();
        	String productName = ((ProductList)value).getProductName();
            int tmpHash = hashFunction(key);// Calculate hash value

            int i = tmpHash;

            do {
                if(table[i] == null) {
                	 table[i] = new HashEntry<>(key,value);                	 
                	 ((ProductList)value).addProduct(date,productName );
                    currentSize++;
                    return;
                }
                else if (table[i].getKey().equals((String)key) ){                
                	ProductList tmpList = (ProductList)table[i].getValue(); 
                	tmpList.addProduct(date, productName);
                    return;
                }
                else {
                	collisionCount++;
              //      System.out.println(i + "there is a collision : " + key + " we will make a linear probing");
                }
                i = (i + 1) % TABLE_SIZE;
            }while (i != tmpHash);
        }


        public void remove (K key ){     	
            int i = hashFunction(key);
            while(table[i] != null){            	
                if (table[i].getKey() == key){
                    // delete operation
                    table[i] = null;
                    return;
                }
                i = (i + 1) % TABLE_SIZE;
            }
        }
        
        // mine code
        public long hashCode (K code) {        	
        	if(hashFunctionInput == 1) {
        		String tmpCode = (String)code;
          	  char[] chars = new char[tmpCode.length()];

                int resultCode = 0 ;
                for (int i = 0; i <  tmpCode.length(); i++) {
                       chars[i] =  tmpCode.charAt(i);                       
                       int letter = 0; 
                       if( tmpCode.charAt(i) != '-'  && tmpCode.charAt(i) != '0' ) {
                    	   if((int)tmpCode.charAt(i) >= 49 && (int) tmpCode.charAt(i) < 58)
                    		   letter = (int)tmpCode.charAt(i) - 48;
                    	   else letter = (int) tmpCode.charAt(i)- 96;
                    	   }                     
                       resultCode += letter;                      
                }
                
                return resultCode;
        	}
        	else {
        		  String tmpCode = (String)code;
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

                          lenOfKey -= 1;
                          resultCode += (long) (letter * (Math.pow(7, lenOfKey))); 
                      }
                      else if( tmpCode.charAt(i) == '0') {
                          lenOfKey -= 1;
                          resultCode += (long) (letter * (Math.pow(7, lenOfKey))); 
                      }                    
                  }

                  return resultCode;
        	}
        	
        }


        public void printHashTable()
        {
            System.out.println("\nHash Table: ");
            for (int i = 0; i < TABLE_SIZE; i++)
                if (table[i] != null) {
                    System.out.println(table[i].getKey() + " " + ((ProductList)table[i].getValue()).getName());
                }
            System.out.println();
            System.out.println(collisionCount + " collision founded");
        }
        
        public void checkCapacity() {
        	
        	double proportion = currentSize / TABLE_SIZE ;
        	if (proportion >= LOAD_FACTOR) {
        		System.out.println("load factor is full !!!");
        		resize();
        	}
        	
        }
        
        
        public void resize () {
    		int newCapacity = (TABLE_SIZE *2) -1;
    		newCapacity = getNextPrime(newCapacity);
    		HashEntry<K, V>[] newTable = new HashEntry[newCapacity];
    	
    	    // Rehash all existing key-value pairs
    	    for (int i = 0; i < TABLE_SIZE; i++) {
    	        if (table[i] != null) {
    	            K key = table[i].getKey();
    	            V value = table[i].getValue();
    	            int newIndex = hashFunction(key) % newCapacity;

    	            // Linear probing for collision resolution in the new array
    	            while (newTable[newIndex] != null) {
    	                newIndex = (newIndex + 1) % newCapacity;
    	            }

    	            newTable[newIndex] = new HashEntry<>(key, value);
    	        }
    	    }

    	    // Update the hash table to use the new array
    	    TABLE_SIZE = newCapacity;
    	    table = newTable;
    	
     }
        

        
        public void find(K key) {
               
     	   	int i = hashFunction(key);
     	   
     	   	boolean isFound = false;
            while (table[i] != null) {             
             	if(table[i].getKey().equals((String)key)) {         		
             		isFound = true;
             		customerFounded ++;
             	//	System.out.println(customerFounded + " customer founded");
             		return;
             	}
             	i = (i + 1) % TABLE_SIZE;
             }
             
          //   if(isFound == false) System.out.println("customer not found");

             return ;
        }
        
        private int getNextPrime(int integer)
    	{
    	
    		if (integer % 2 == 0)
    		{
    			integer++;
    		} 
    	
    		while (!isPrime(integer))
    		{
    			integer = integer + 2;
    		} 

    		return integer;
    	} 

    	private boolean isPrime(int integer)
    	{
    		boolean result;
    		boolean done = false;
    		if ( (integer == 1) || (integer % 2 == 0) )
    		{
    			result = false; 
    		}

    		else if ( (integer == 2) || (integer == 3) )
    		{
    			result = true;
    		}

    		else // integer is odd and >= 5
    		{
    			assert (integer % 2 != 0) && (integer >= 5);
    			result = true; // assume prime
    			for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2)
    			{
    				if (integer % divisor == 0)
    				{
    					result = false; 
    					done = true;
    				} 
    			} 
    		} 

    		return result;
    	} 


}
