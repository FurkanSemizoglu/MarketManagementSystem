// this class is for linear probing
public class HashTable<K,V> {

        private static int TABLE_SIZE = 11;
        private static final int DEFAULT_CAPACITY = 16;
        
        private int currentSize;
        HashEntry<K,V>[] table;
        public HashTable() {
            currentSize = 0;            
            table = new HashEntry[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++)
                table[i] = null;
        }

        public int hashFunction(K key) {

        	
            return hashCode(key) % TABLE_SIZE;

        }
        
        public V getValue(K key) {

            int i = hashFunction(key);// Calculate hash value

            while (table[i] != null) {
                if (table[i].equals(key))
                    return table[i].getValue();
                i = (i + 1) % TABLE_SIZE;
            }

            return null;
            /*
            if (table[i] == null)
                return -1;
            else
                return table[i].getValue();

             */
        }
        public void put(K key, V value) {

        	
        	checkCapacity();
        	
        //	int intKey = hashCode((String)key );
          	
            int tmpHash = hashFunction(key);// Calculate hash value

            int i = tmpHash;
            // Print "There is a collision !" message to indicate collision and do not insert item
            do {
                if(table[i] == null) {
                	 table[i] = new HashEntry<>(key, value);
                	 System.out.println(i);
                    currentSize++;
                    return;
                }
                else if (table[i].getKey() == key ){
               	 table[i] = new HashEntry<>(key, value);
                    return;
                }
                else {
                    System.out.println(i + "there is a collision : " + key + " we will make a linear probing");
                }

                i = (i + 1) % TABLE_SIZE;

            }while (i != tmpHash);
        }


        public void remove (K key ){
        //	int intKey = hashCode((String)key );
        	
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
        public int hashCode (K code) {
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


        public void printHashTable()
        {
            System.out.println("\nHash Table: ");
            for (int i = 0; i < TABLE_SIZE; i++)
                if (table[i] != null)
                    System.out.println(table[i].getKey() + " " + table[i].getValue());
            System.out.println();
        }
        
        public void checkCapacity() {
        	if (currentSize == TABLE_SIZE) {
        		System.out.println("capacity is full !!!");
        		resize();
        	}
        }
        
        public void resize () {
        		int newCapacity = TABLE_SIZE * 2;
        	    HashEntry<K, V>[] newTable = new HashEntry[newCapacity];

        	    // Rehash all existing key-value pairs
        	    for (int i = 0; i < TABLE_SIZE; i++) {
        	        if (table[i] != null) {
        	            K key = table[i].getKey();
        	            V value = table[i].getValue();
        	            int newIndex = Math.abs(key.hashCode()) % newCapacity;

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

}
