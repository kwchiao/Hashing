
class HashChaining {

    //array of nodes
    Node[] hash;
    int capacity;
    int size;
    int collisions;

    HashChaining(){
        hash = new Node[HashFunction.hash_size];
        
    }

    int search(int key){
        int index = HashFunction.hash_mod(key);

        return 0;
    }

    void put(int key, int value){

    }

    void delete(int key){


    }

}