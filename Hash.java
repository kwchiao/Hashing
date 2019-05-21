

class Hash {


    public static void hashchain_test(){

    }

    public static void cuckoo_test(){
        // cuckoo hashing test
        int[] input = {12,26,92,23,28,94};
        // int[] input = {12,26, 92};
        CuckooHashing ch = new CuckooHashing();

        for (int i = 0; i < input.length; i++){
            ch.add(input[i], input[i]);
            // ch.print();
        }
        // ch.delete(26);
        ch.print();
    }
    public static void main(String[] args){

        // int[] input = {18, 41, 22, 44, 59, 32, 31, 73};
        
        // //HashChaining hc = new HashChaining();
        // LinearProbing hc = new LinearProbing();

        // for (int i = 0; i < input.length; i++)
        //     hc.add(input[i], Helper.hash_mod(input[i]));
        
        // hc.delete(18);
        // hc.print();

        cuckoo_test();




    }


}