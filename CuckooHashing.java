class CuckooHashing extends HashAlgo{

    CuckooHashing(int cap){
        super(cap);
        hash_cuckoo = new Pair[2][capacity];
        hash_cuckoo[0] = new Pair[capacity];
        hash_cuckoo[1] = new Pair[capacity];
    }

    int search(int key){
        if (size == 0) return -1;
        int index1 = Helper.hash_mod(key, capacity);
        int index2 = Helper.hash_div_mod(key, capacity);
        if (hash_cuckoo[0][index1] != null){
            last_collision = 0;
            search_collision += last_collision;
            return hash_cuckoo[0][index1].val;
        }
        else if (hash_cuckoo[1][index2] != null){
            last_collision = 1;
            search_collision += last_collision;
            return hash_cuckoo[1][index2].val;
        }
        else {
            last_collision = 1;
            search_collision += last_collision;
            return -1;
        }
        
    }

    int add(int key, int value){
        if (capacity == size) return -1;
        Pair pair = new Pair(key, value);
        int flag = 0;
        int count = 1;

        while(pair != null){       
            Pair temp = new Pair(pair.key, pair.val);

            if (addHelper(flag, pair.key, temp, pair) == 0)
                pair = null;

            flag = 1 - flag;
            count++;

            // if (pair != null)
            //     System.out.println(pair.key);
            // System.out.println(count);
            if (count > capacity){
                System.out.println("Resize needed.");
                return -1;
            }
        }

        last_collision = count - 1;
        collision += last_collision;
        size++;
        return 1;
    }

    //swap pair with hash_f[mod(pair.key)]
    int addHelper(int flag, int key, Pair temp, Pair pair){
        int index = Helper.hash(flag, key, capacity);
        if (hash_cuckoo[flag][index] == null){
            pair = null;
        }
        else {
            pair.copy(hash_cuckoo[flag][index]);
        }

        if (hash_cuckoo[flag][index] == null){
            hash_cuckoo[flag][index] = new Pair();
        }
        hash_cuckoo[flag][index].copy(temp);

        return pair == null ? 0 : 1;
    }

    void delete(int key){
        if (size == 0) return;
        int index1 = Helper.hash_mod(key, capacity);
        int index2 = Helper.hash_div_mod(key, capacity);

        if (hash_cuckoo[0][index1] != null && hash_cuckoo[0][index1].key == key ){
            hash_cuckoo[0][index1] = null;
            size--;
            last_collision = 0;
        }
        else if (hash_cuckoo[1][index2] != null && hash_cuckoo[1][index2].key == key ){
            hash_cuckoo[1][index2] = null;
            size--;
            last_collision = 1;
        }
    }

    void print(){
        for (int z = 0; z < 2; z++){
            for (int i = 0; i < capacity; i++){
                if (hash_cuckoo[z][i] != null)
                    System.out.println(i + ": " + hash_cuckoo[z][i].key + ", " + hash_cuckoo[z][i].val);
                else
                    System.out.println(i + ": ");
            }
            System.out.println();
        }
    }

}