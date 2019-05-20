class HashFunction {

    public static final int hash_size = 11;

    public static int hash_mod(int k){
        return k % hash_size;
    }

    public static int hash_div_mod(int k){
        return (k/hash_size) % hash_size;
    }
}