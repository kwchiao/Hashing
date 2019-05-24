class QuadraticProbing extends HashAlgo{

    // Pair[] hash_pair;
    // int capacity;
    // int size;
    // int collision;

    QuadraticProbing(int cap){
        // capacity = cap;
        super(cap);
        hash_pair = new Pair[capacity];
        // size = 0;
        // collision = 0;        
    }

    int search(int key){
        int index = Helper.hash_mod(key, capacity);
        index = quad_probing(OP.SEARCH,index, key);
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
            index = quad_probing(OP.INSERT, index, key);
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

    int quad_probing(OP option, int index, int key){
        int last_collision = 1;
        int result = index;
        while (last_collision < capacity && hash_pair[index] != null && hash_pair[index].key != key){
            result = Helper.quad_next_index(index, last_collision, capacity);
            last_collision++;
        }

        last_collision--;
        if (option == OP.INSERT)
            collision += last_collision;

        return (last_collision+1 < capacity) ? result : -1;
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