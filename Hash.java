

class Hash {

    public static void main(String[] args){

        // int a = HashFunction.hash_mod(26);
        // int b = HashFunction.hash_div_mod(26);

        // System.out.println(a + " " + b);

        int[] input = {18, 41, 22, 44, 59, 32, 31, 73};
        HashChaining hc = new HashChaining();
        for (int i = 0; i < input.length; i++)
            hc.add(input[i], HashFunction.hash_mod(input[i]));

        
        hc.print();
    }


}