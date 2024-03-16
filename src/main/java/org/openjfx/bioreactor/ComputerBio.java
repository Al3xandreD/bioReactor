package org.openjfx.bioreactor;

import java.util.ArrayList;

public class ComputerBio extends Computer{

    /** index for request from TCPs serveurs */
    private int indexRequest=0;
    private ArrayList<ServeurTCP> serveurTCPS=new ArrayList<>();

    public ComputerBio() {
        super();
        this.serveurTCPS.add(new ServeurTCP(new ProtocoleSendState(), this, port, 10));
        for (ServeurTCP s : serveurTCPS) {
            s.start();
        }
    }


    // getters and setters
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
