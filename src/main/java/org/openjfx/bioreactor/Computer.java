package org.openjfx.bioreactor;

import java.util.ArrayList;

/** Représente un ordinateur avec un stockage, UI, réseau comm,
 * associé à un bioréacteur */

public class Computer implements IComputer {
    private int port = 6666;

    /** hard drive for storing data from bioreactor */
    private ArrayList<String> hardDrive=new ArrayList<>();

    /** index for request from TCPs serveurs */
    private int indexRequest=0;
    private ArrayList<ServeurTCP> serveurTCPS=new ArrayList<>();

    public Computer(){
        this.serveurTCPS.add(new ServeurTCP(new ProtocoleSendState(), this, port,10 ));
        for (ServeurTCP s: serveurTCPS){
            s.start();
        }

    }
    public void saveData(String incoming){
        /**
         * saves the data in the hard drive
         */
        this.hardDrive.add(incoming);
    }


    //getters and setters
    public ArrayList<String> getHardDrive() {
        return hardDrive;
    }

    public void setHardDrive(ArrayList<String> hardDrive) {
        this.hardDrive = hardDrive;
    }

    public int getIndexRequest() {
        return indexRequest;
    }

    public void setIndexRequest(int indexRequest) {
        this.indexRequest = indexRequest;
    }

    public ArrayList<ServeurTCP> getServeurTCPS() {
        return serveurTCPS;
    }
    public void setServeurTCPS(ArrayList<ServeurTCP> serveurTCPS) {
        this.serveurTCPS = serveurTCPS;
    }
}
