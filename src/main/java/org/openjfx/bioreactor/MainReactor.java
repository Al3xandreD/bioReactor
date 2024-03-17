package org.openjfx.bioreactor;

import java.util.Timer;
import java.util.TimerTask;

public class MainReactor {
    public static void main(String[] args) {
        int i;
        Computer mycomp=new ComputerBio(); // opening a computer for bioreactor
        BioReactor myreac=new BioReactor();

        // saving data to TCP servers
        myreac.transmit(mycomp);


    }
}
