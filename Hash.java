

class Hash {

    public static void main(String[] args){

        int[] input = {18, 41, 22, 44, 59, 32, 31, 73};
        
        //HashChaining hc = new HashChaining();
        LinearProbing hc = new LinearProbing();

        for (int i = 0; i < input.length; i++)
            hc.add(input[i], HashFunction.hash_mod(input[i]));
        
        hc.delete(18);
        hc.print();
    }


}