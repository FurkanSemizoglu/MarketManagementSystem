import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class HashTableDH2<K,V> {

	private ArrayList<Product> products;

    private static int TABLE_SIZE = 11;
    private static final int DEFAULT_CAPACITY = 16;
    private static final double LOAD_FACTOR =  0.5;
    
    private int currentSize;
    HashEntry<K,V>[] table;
    private int primeSize;
    
    public HashTableDH2() {
    	products = null;
        currentSize = 0;            
        table = new HashEntry[TABLE_SIZE];
        for (int i = 0; i < TABLE_SIZE; i++)
            table[i] = null;
        
        primeSize = getPrime();  
    }

    public int hashFunction(K key) {

    	
        return (int) (hashCode(key) % TABLE_SIZE);

    }
    
    private long myhash1(K key )
    {
       // System.out.println(hashCode(key));  
        if (hashCode(key) % TABLE_SIZE < 0)
        	return (hashCode(key) % TABLE_SIZE) + TABLE_SIZE;
        else
        	return  hashCode(key) % TABLE_SIZE;
    }
    /* Function myhash function for double hashing */
    private long hash2(K key)
    { 	
    	
    	
    	long hashVal = 7 - ( hashCode(key) % 7);
    	if (hashVal < 0)
    		hashVal += TABLE_SIZE;
    	return hashVal; 
    }
  
    
    public void getValue(K key) {

        
        int i = (int)myhash1( key );
	    int hash2 =(int) hash2( key );        
	     
        while (table[i] != null) {
        	HashEntry<K, V> current = table[i];
        	if(table[i].getKey().equals((String)key)) {
        		System.out.println(current.getKey() + ", " + ((ProductList)current.getValue()).getName());
        		System.out.println("burdan kontrol et \n \n");
        		((ProductList)current.getValue()).displayProducts();
        		System.out.println(" araa \n");
        		return;
        	}
        	i = (i + hash2) % TABLE_SIZE;
        }

        return ;
   
    }
    public void put(K key, V value) {

   
    	checkCapacity();
    	
    	String name = ((ProductList)value).getName();
    	String date = ((ProductList)value).getDate();
    	String productName = ((ProductList)value).getProductName();
    	
    	int hash1 = (int)myhash1( key );
	    int hash2 =(int) hash2( key );        
	     
	    System.out.println(hash1);
	    System.out.println(hash2);
	       
        int tmpHash = hashFunction(key);// Calculate hash value

        int i = hash1;
     
        do {
            if(table[i] == null) {
            	table[i] = new HashEntry<>(key,value);        	 
            	System.out.println(name + " added  " + i + " bu tabıla ");
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
            	System.out.println(table[i].getKey()) ;
            	System.out.println(key) ;
                System.out.println(i + " there is a collision : " + key + " we will make a double hashing");
                System.out.println(hash2) ;
                System.out.println("prime size " + primeSize );
            }
            
            i = (i + hash2) % TABLE_SIZE;

        }while (i != tmpHash);
    }


    
    public int getPrime()
    {
        for (int i = TABLE_SIZE - 1; i >= 1; i--)
        {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        /* Return a prime number */
        return 3;
    }
    
    private int getNextPrime(int integer)
	{
		// if even, add 1 to make odd
		if (integer % 2 == 0)
		{
			integer++;
		} // end if

		// test odd integers
		while (!isPrime(integer))
		{
			integer = integer + 2;
		} // end while

		return integer;
	} // end getNextPrime

	// Returns true if the given intege is prime.
	private boolean isPrime(int integer)
	{
		boolean result;
		boolean done = false;

		// 1 and even numbers are not prime
		if ( (integer == 1) || (integer % 2 == 0) )
		{
			result = false; 
		}

		// 2 and 3 are prime
		else if ( (integer == 2) || (integer == 3) )
		{
			result = true;
		}

		else // integer is odd and >= 5
		{
			assert (integer % 2 != 0) && (integer >= 5);

			// a prime is odd and not divisible by every odd integer up to its square root
			result = true; // assume prime
			for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2)
			{
				if (integer % divisor == 0)
				{
					result = false; // divisible; not prime
					done = true;
				} // end if
			} // end for
		} // end if

		return result;
	} // end isPrime

    
    public void remove (K key ){

        int hash1 =(int) myhash1( key );
        int hash2 = (int)hash2( key );        
        while (table[hash1] != null && !table[hash1].key.equals(key))
        {
            hash1 += hash2;
            hash1 %= TABLE_SIZE;
        }
        table[hash1] = null;
        currentSize--;
    }
    
    // mine code
    public long hashCode (K code) {
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
              }
             
           
            
              lenOfKey -= 1;
              resultCode += (long) (letter * Math.pow(7, lenOfKey));          

          }

          return resultCode;
    }
    

    


    

    public void printHashTable()
    {
        System.out.println("\nHash Table: ");
        for (int i = 0; i < TABLE_SIZE; i++)
            if (table[i] != null) {
                System.out.println(table[i].getKey() + " " + ((ProductList)table[i].getValue()).getName());
            }
        System.out.println();
    }
    
    public void checkCapacity() {
    	
    	double proportion = currentSize / TABLE_SIZE ;
    	if (proportion >= 0.5) {
    		System.out.println("load factor is full !!!");
    		resize();
    	}
    }
    
    public void resize2 () {
    		int newCapacity = TABLE_SIZE * 2;
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
    
    
    private void resize() {
        int oldSize = (TABLE_SIZE *2) -1;
        TABLE_SIZE = getNextPrime(oldSize); // Update TABLE_SIZE to the new prime number

        HashEntry[] oldTable = Arrays.copyOf(table, oldSize);
        table = new HashEntry[TABLE_SIZE];

        currentSize = 0; // Reset size as we will re-insert all key-value pairs

        // Rehash and insert all key-value pairs from the old table to the new table
        for (int i = 0; i < oldSize; i++) {
            if (oldTable[i] != null) {
                put((K)oldTable[i].key, (V)oldTable[i].value);
            }
        }
    }

}
