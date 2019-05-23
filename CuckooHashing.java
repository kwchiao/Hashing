class CuckooHashing {

    Pair[][] hash;
    int capacity;
    int size;
    int collision;

    CuckooHashing(int cap){
        capacity = cap;
        hash = new Pair[2][capacity];
        hash[0] = new Pair[capacity];
        hash[1] = new Pair[capacity];
        size = 0;
        collision = 0;    
    }

    int search(int key){
        if (size == 0) return -1;
        int index1 = Helper.hash_mod(key, capacity);
        int index2 = Helper.hash_div_mod(key, capacity);
        if (hash[0][index1] != null){
            return hash[0][index1].val;
        }
        else if (hash[1][index2] != null){
            return hash[1][index2].val;
        }
        else {
            return -1;
        }
    }

    void add(int key, int value){
        Pair pair = new Pair(key, value);
        int flag = 0;
        int count = 1;

        while(count < capacity && pair != null){       
            Pair temp = new Pair(pair.key, pair.val);

            if (addHelper(flag, pair.key, temp, pair) == 0)
                pair = null;

            flag = 1 - flag;
            count++;
        }

        size++;
    }

    //swap pair with hash_f[mod(pair.key)]
    int addHelper(int flag, int key, Pair temp, Pair pair){
        int index = Helper.hash(flag, key, capacity);
        if (hash[flag][index] == null){
            pair = null;
        }
        else {
            pair.copy(hash[flag][index]);
        }

        if (hash[flag][index] == null){
            hash[flag][index] = new Pair();
        }
        hash[flag][index].copy(temp);

        return pair == null ? 0 : 1;
    }

    void delete(int key){
        if (size == 0) return;
        int index1 = Helper.hash_mod(key, capacity);
        int index2 = Helper.hash_div_mod(key, capacity);

        if (hash[0][index1] != null && hash[0][index1].key == key ){
            hash[0][index1] = null;
            size--;
            System.out.println("h");
        }
        else if (hash[1][index2] != null && hash[1][index2].key == key ){
            hash[1][index2] = null;
            size--;
        }
    }

    void print(){
        for (int z = 0; z < 2; z++){
            for (int i = 0; i < capacity; i++){
                if (hash[z][i] != null)
                    System.out.println(i + ": " + hash[z][i].key + ", " + hash[z][i].val);
                else
                    System.out.println(i + ": ");
            }
            System.out.println();
        }
    }

}