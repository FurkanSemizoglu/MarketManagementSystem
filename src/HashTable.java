// this class is for linear probing
public class HashTable {

        private final static int TABLE_SIZE = 128;

        private int currentSize;
        HashEntry[] table;
        public HashTable() {
            currentSize = 0;
            table = new HashEntry[TABLE_SIZE];
            for (int i = 0; i < TABLE_SIZE; i++)
                table[i] = null;
        }

        public int hashFunction(int key) {

            return key % TABLE_SIZE;

        }
        public int getValue(int key) {

            int i = hashFunction(key);// Calculate hash value

            while (table[i] != null) {
                if (table[i].equals(key))
                    return table[i].getValue();
                i = (i + 1) % TABLE_SIZE;
            }

            return -1;
            /*
            if (table[i] == null)
                return -1;
            else
                return table[i].getValue();

             */
        }
        public void put(int key, int value) {

            int tmpHash = hashFunction(key);// Calculate hash value

            int i = tmpHash;
            // Print "There is a collision !" message to indicate collision and do not insert item
            do {
                if(table[i] == null) {
                    table[i] = new HashEntry(key, value);
                    currentSize++;
                    return;
                }
                else if (table[i].getKey() == key ){
                    table[i] = new HashEntry(key, value);
                    return;
                }
                else {
                    System.out.println("there is a collision : " + key + " we will make a linear probing");
                }

                i = hashFunction(i + 1);

            }while (i != tmpHash);
        }


        public void remove (int key ){

            int i = hashFunction(key);

            while(table[i] != null){
                if (table[i].getKey() == i){
                    // delete operation
                    table[i] = null;
                    return;
                }
                i = (i + 1) % TABLE_SIZE;

            }

        }


        public void printHashTable()
        {
            System.out.println("\nHash Table: ");
            for (int i = 0; i < TABLE_SIZE; i++)
                if (table[i] != null)
                    System.out.println(table[i].getKey() + " " + table[i].getValue());
            System.out.println();
        }

}
