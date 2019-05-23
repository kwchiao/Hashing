class QuadraticProbing {

    Pair[] hash;
    int capacity;
    int size;
    int collision;

    QuadraticProbing(int cap){
        capacity = cap;
        hash = new Pair[capacity];
        size = 0;
        collision = 0;        
    }

    int search(int key){
        int index = Helper.hash_mod(key, capacity);
        index = quad_probing(index, key);
        if (index == -1 || hash[index] == null){
           return -1;
        }
        return hash[index].val;
    }

    void add(int key, int value){
        int index = Helper.hash_mod(key, capacity);

        if (index == -1){
            hash[index] = new Pair(key, value);
        }
        else {
            index = quad_probing(index, key);
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

    int quad_probing(int index, int key){
        int count = 1;
        int result = index;
        while (count < capacity && hash[index] != null && hash[index].key != key){
            result = Helper.quad_next_index(index, count, capacity);
            count++;
        }

        return (count < capacity) ? result : -1;
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