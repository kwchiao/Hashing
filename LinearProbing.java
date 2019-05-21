

class LinearProbing {

    Pair[] hash;
    int capacity;
    int size;
    int collision;

    LinearProbing(){
        capacity = Helper.hash_size;
        hash = new Pair[capacity];
        size = 0;
        collision = 0;        
    }

    int search(int key){
        int index = Helper.hash_mod(key);
        index = linear_probing(index, key);
        if (index == -1 || hash[index] == null){
           return -1;
        }
        return hash[index].val;
    }

    void add(int key, int value){
        int index = Helper.hash_mod(key);

        
        if (index == -1){
            hash[index] = new Pair(key, value);
        }
        else {
            index = linear_probing(index, key);
            if (index != -1){
                if (hash[index] == null){
                    hash[index] = new Pair(key, value);
                    size++;
                }
                else{
                    hash[index].set(key, value);
                }
            }
            else {
                System.out.println("IllegalStateException: Hash full");
            }
        }
    }

    void delete(int key){
        int index = Helper.hash_mod(key);
        index = linear_probing(index, key);
        if (index == -1 || hash[index] == null){
            System.out.println("exception");
        }
        
        int j = Helper.next_index(index);
        int count = 1;
        while (count < capacity && hash[j] != null){
            int hash_key = Helper.hash_mod(hash[j].key);
            int iplusone = Helper.next_index(index);
            boolean inrange = false;

            // check range
            if (iplusone <= j){
                inrange = (hash_key >= iplusone && hash_key <= j) ? false : true;
            }
            else {
                inrange = (hash_key >= iplusone && hash_key < capacity) || 
                            (hash_key >= 0 && hash_key <= j) ? false : true;
            }
            
            if (inrange){
                System.out.println("index: " + index + " j: " + j);
                hash[index] = hash[j];
                index = j;
            }

            j = Helper.next_index(j);
            count++;
        }
        hash[index] = null;
        size--;
    }

    int linear_probing(int index, int key){
        int count = 1;
        while (count < capacity && hash[index] != null && hash[index].key != key){
            index = Helper.next_index(index);
            count++;
        }

        return (count < capacity) ? index : -1;
    }
    
    void print(){
        for (int i = 0; i < capacity; i++){
            if (hash[i] != null)
                System.out.println(i + ": " + hash[i].key + ", " + hash[i].val);
            else
                System.out.println(i + ": ");
        }
    }

}