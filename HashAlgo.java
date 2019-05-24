enum OP { 
    INSERT, SEARCH, DELETE; 
} 

class HashAlgo{
    Node[] hash_node;
    Pair[] hash_pair;
    Pair[][] hash_cuckoo;
    int capacity;
    int size;
    int collision;
    int last_collision;

    HashAlgo(int cap){
        capacity = cap;
        size = 0;
        collision = 0; 
        last_collision = 0;  
    }
    
    int search(int key){ return 0;}
    void add(int key, int val){}
}