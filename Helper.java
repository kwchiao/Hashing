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

    Pair(){
    }

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

    public static final int hash_size = 11;

    public static int hash_mod(int k){
        return k % hash_size;
    }

    public static int hash_div_mod(int k){
        return (k/hash_size) % hash_size;
    }

    public static int next_index(int i){
        return (i + 1) % hash_size;
    }


    public static int hash(int flag, int k){
        if (flag == 0){
            return hash_mod(k);
        }
        else {
            return hash_div_mod(k);
        }
    }
}

