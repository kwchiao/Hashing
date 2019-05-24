class QuadraticProbing extends HashAlgo{

    QuadraticProbing(int cap){
        super(cap);
        hash_pair = new Pair[capacity];    
    }

    int search(int key){
        int index = Helper.hash_mod(key, capacity);
        index = quad_probing(OP.SEARCH,index, key);
        if (index == -1 || hash_pair[index] == null){
           return -1;
        }
        return hash_pair[index].val;
    }

    int add(int key, int value){
        if (capacity == size) return -1;
        int index = Helper.hash_mod(key, capacity);

        index = quad_probing(OP.INSERT, index, key);
        if (index != -1){
            if (hash_pair[index] == null){
                hash_pair[index] = new Pair(key, value);
                size++;
            }
            else{
                hash_pair[index].set(key, value);
            }
            return 1;
        }
        else {
            System.out.println("IllegalStateException: Resize needed");
            return -1;
        }
    }

    int quad_probing(OP option, int index, int key){
        int last_collision = 1;
        int result = index;
        //System.out.println("heraselfkj");
        while (last_collision < capacity*capacity && hash_pair[result] != null && hash_pair[result].key != key){
        //while (last_collision < capacity && hash_pair[index] != null && hash_pair[index].key != key){
            result = Helper.quad_next_index(index, last_collision, capacity);
            last_collision++;
            //System.out.println(result + " " + index + " " + last_collision + " " + capacity);
        }

        if (last_collision >= capacity * capacity) return -1;

        last_collision--;
        if (option == OP.INSERT)
            collision += last_collision;
        else if (option == OP.SEARCH)
            search_collision += last_collision;

        return result;
        //return (last_collision+1 < capacity) ? result : -1;
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