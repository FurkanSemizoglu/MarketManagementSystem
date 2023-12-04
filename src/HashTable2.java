import java.util.ArrayList;

// this class is for linear probing
public class HashTable2<K,V> {

		private ArrayList<Product> products;
	
        private static int TABLE_SIZE = 11;
        private static final int DEFAULT_CAPACITY = 16;
        
        private int currentSize;
        HashEntry<K,V>[] table;
        public HashTable2() {
        	products = null;
            currentSize = 0;            
            table = new HashEntry[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++)
                table[i] = null;
        }

        public int hashFunction(K key) {

        	
            return hashCode(key) % TABLE_SIZE;

        }
        
        public void getValue(K key) {

            int i = hashFunction(key);// Calculate hash value

            while (table[i] != null) {
            	HashEntry<K, V> current = table[i];
            	if(table[i].getKey().equals((String)key)) {
            		System.out.println(current.getKey() + ", " + ((ProductList)current.getValue()).getName());
            		System.out.println("burdan kontrol et \n \n");
            		((ProductList)current.getValue()).displayProducts();
            		 //return table[i].getValue();
            		return;
            	}
            	
            	
            	/*
                if (table[i].equals(key))
                    return table[i].getValue();
                    */
                i = (i + 1) % TABLE_SIZE;
            }

            return ;
            /*
            if (table[i] == null)
                return -1;
            else
                return table[i].getValue();

             */
        }
        public void put(K key, V value) {

        //	System.out.println(((Transaction)value).getName() );
        	checkCapacity();
        	
        	String name = ((ProductList)value).getName();
        	String date = ((ProductList)value).getDate();
        	String productName = ((ProductList)value).getProductName();
        	
        	
        	//System.out.println(((ProductList)value).getProducts());
        	
        	
        //	int intKey = hashCode((String)key );
          	
            int tmpHash = hashFunction(key);// Calculate hash value

            int i = tmpHash;
            // Print "There is a collision !" message to indicate collision and do not insert item
            do {
                if(table[i] == null) {
                	 table[i] = new HashEntry<>(key,value);
                	 
                	 ((ProductList)value).addProduct(date,productName );
                	 
                //	 System.out.println("heyoo " + ((ProductList)value).getName());
                //	 HashEntry<K, V> current = table[i];
                	// current = current.getNext();
                //	 current.setNext( new HashEntry<>((K)date,(V)product));
                	// current.setValue((V)transaction);
                	 System.out.println(i);
                    currentSize++;
                    return;
                }
                else if (table[i].getKey().equals((String)key) ){
                
                	ProductList tmpList = (ProductList)table[i].getValue(); 
           //     	System.out.println("buraya geliyo mu ya");
                	
                	tmpList.addProduct(date, productName);
               // 	HashEntry<K, V> current = table[i];
		            
		            // if there is a new entry
		            
               //	    table[i] = new HashEntry<>(key, value);

               	    // think about setNext
               	    
               	    
               	   // current.setValue((V) transaction);
                    return;
                }
                else {
                	System.out.println(table[i].getKey()) ;
                	System.out.println(key) ;
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
                if (table[i] != null) {
                    System.out.println(table[i].getKey() + " " + ((ProductList)table[i].getValue()).getName());
                }
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

}
