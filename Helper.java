class Node {
    int val;
    int key;
    Node next;

    Node(int k, int v, Node n){ 
        key = k;
        val = v;
        next = n;
    }
}

class Pair {
    int key;
    int val;

    Pair(int k, int v){
        key = k;
        val = v;
    }

    Pair(){}

    void set(int k, int v){
        key = k;
        val = v;
    }

    void copy(Pair p){
        this.key = p.key;
        this.val = p.val;
    }
}

class Helper {

    public static int hash_mod(int k, int hash_size){
        return k % hash_size;
    }

    public static int hash_div_mod(int k, int hash_size){
        return (k/hash_size) % hash_size;
    }

    public static int next_index(int i, int hash_size){
        return (i + 1) % hash_size;
    }

    public static int quad_next_index(int i, int count, int hash_size){
        return (i + count * count) % hash_size;
    }

    public static int hash(int flag, int k, int hash_size){
        if (flag == 0){
            return hash_mod(k, hash_size);
        }
        else {
            return hash_div_mod(k, hash_size);
        }
    }
}

