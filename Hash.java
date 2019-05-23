

class Hash {

    public static void quad_test(){
        // int[] input = {18, 41, 22, 44, 59, 32, 31, 73, 0, 11};
        int[] input = {18, 41, 22, 44, 59, 32, 31, 73, 5, 57, 70};
        QuadraticProbing qp = new QuadraticProbing(13);

        for (int i = 0; i < input.length; i++)
            qp.add(input[i], Helper.hash_mod(input[i], 13));
        
        qp.print();

    }

    public static void cuckoo_test(){
        // cuckoo hashing test
        int[] input = {12,26,92,23,28,94};
        // int[] input = {12,26, 92};
        CuckooHashing ch = new CuckooHashing(11);

        for (int i = 0; i < input.length; i++){
            ch.add(input[i], input[i]);
            // ch.print();
        }
        // ch.delete(26);
        ch.print();
    }

    public static void linear_test(){
        int[] input = {18, 41, 22, 44, 59, 32, 31, 73, 5, 57, 70};
        LinearProbing lp = new LinearProbing(13);

        for (int i = 0; i < input.length; i++)
            lp.add(input[i], Helper.hash_mod(input[i], 13));
        
        lp.print();
    }

    public static void hashchain_test(){
        int[] input = {18, 41, 22, 44, 59, 32, 31, 73};
        
        HashChaining hc = new HashChaining(13);

        for (int i = 0; i < input.length; i++)
            hc.add(input[i], Helper.hash_mod(input[i],13) );
        
        hc.delete(18);
        hc.print();
    }

    public static void main(String[] args){


        hashchain_test();
        // linear_test();
        // cuckoo_test();
        //quad_test();




    }


}