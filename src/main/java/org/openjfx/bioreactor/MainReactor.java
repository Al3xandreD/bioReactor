package org.openjfx.bioreactor;

public class MainReactor {
    public static void main(String[] args) {
        BioReactor myreac=new BioReactor();
        String my_string=myreac.toString();
        System.out.println(my_string);
    }
}
