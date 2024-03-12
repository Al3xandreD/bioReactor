package org.openjfx.bioreactor;

import java.util.Timer;
import java.util.TimerTask;

public class MainReactor {
    public static void main(String[] args) {
        int i;

        BioReactor myreac=new BioReactor();

        // serveurs
        myreac.openReactor();

        // saving data to TCP servers
        for(i=0; i<=5812;i++){
            myreac.readingFile();
            for(ServeurTCP s: myreac.getServeurTCPS()){
                s.saveData(myreac.toString());
            }
        }
        System.out.println("Data was sent from bioreactor to all tcps");
    }
}
