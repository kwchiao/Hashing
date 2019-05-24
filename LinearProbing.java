

class LinearProbing extends HashAlgo{

    LinearProbing(int cap){
        super(cap);
        hash_pair = new Pair[capacity];
    }

    int search(int key){
        int index = Helper.hash_mod(key, capacity);
        index = linear_probing(OP.SEARCH, index, key);
        if (index == -1 || hash_pair[index] == null){
           return -1;
        }
        return hash_pair[index].val;
    }

    void add(int key, int value){
        int index = Helper.hash_mod(key, capacity);

        if (index == -1){
            hash_pair[index] = new Pair(key, value);
        }
        else {
            index = linear_probing(OP.INSERT, index, key);
            if (index != -1){
                if (hash_pair[index] == null){
                    hash_pair[index] = new Pair(key, value);
                    size++;
                }
                else{
                    hash_pair[index].set(key, value);
                }
            }
            else {
                System.out.println("IllegalStateException: Hash full");
            }
        }
    }

    void delete(int key){
        int index = Helper.hash_mod(key, capacity);
        index = linear_probing(OP.DELETE, index, key);
        if (index == -1 || hash_pair[index] == null){
            System.out.println("exception");
        }
        
        int j = Helper.next_index(index, capacity);
        int count = 1;
        while (count < capacity && hash_pair[j] != null){
            int hash_key = Helper.hash_mod(hash_pair[j].key, capacity);
            int iplusone = Helper.next_index(index, capacity);
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
                hash_pair[index] = hash_pair[j];
                index = j;
            }

            j = Helper.next_index(j, capacity);
            count++;
        }
        hash_pair[index] = null;
        size--;
    }

    int linear_probing(OP option, int index, int key){
        int last_collision = 0;
        while (last_collision <= capacity && hash_pair[index] != null && hash_pair[index].key != key){
            index = Helper.next_index(index, capacity);
            last_collision++;
        }

        
        if (option == OP.INSERT)
            collision += last_collision;

        return (last_collision <= capacity) ? index : -1;
    }
    
    void print(){
        for (int i = 0; i < capacity; i++){
            if (hash_pair[i] != null)
                System.out.println(i + ": " + hash_pair[i].key + ", " + hash_pair[i].val);
            else
                System.out.println(i + ": ");
        }
    }

}